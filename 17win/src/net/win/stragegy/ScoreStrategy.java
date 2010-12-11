package net.win.stragegy;

import java.util.Date;

import net.win.BaseDAO;
import net.win.entity.CapitalLogEntity;
import net.win.entity.UserEntity;
import net.win.utils.Constant;

/**
 * 积分策略
 * 
 * @author Administrator
 * 
 */
public final class ScoreStrategy {
	private ScoreStrategy() {

	}

	/**
	 * 
	 * 通过你的宣传链接注册的会员积分每上升1000 你的收益=N个积分
	 * 
	 * @param buyerEntity
	 * @param itemURL
	 * @param baseDAO
	 * @return
	 * @throws Exception
	 */
	public static void updateRefreeScoreByScore(BaseDAO baseDAO,
			UserEntity userEntity) throws Exception {
		final int REFREE_SCORE = Constant.getScore1000Refree().intValue();
		if (userEntity.getUpgradeScore() > 1000
				&& userEntity.getUpgradeScore() / 1000 > userEntity
						.getRecord1000ScoreCount()) {
			if (userEntity.getReferee() != null) {
				userEntity.setRecord1000ScoreCount(userEntity
						.getRecord1000ScoreCount() + 1);
				userEntity.getReferee().setUpgradeScore(
						userEntity.getReferee().getUpgradeScore()
								+ REFREE_SCORE);
				userEntity.getReferee().setConvertScore(
						userEntity.getReferee().getConvertScore()
								+ REFREE_SCORE);
				updatScore(baseDAO, REFREE_SCORE + 0.0, "您推广的"
						+ userEntity.getUsername() + "积分达到1000，您获得"
						+ REFREE_SCORE + "点积分!", userEntity);
				updateRefreeScoreByScore(baseDAO, userEntity.getReferee());
			}
		}
	}

	private static void updatScore(BaseDAO baseDAO, Double value, String desc,
			UserEntity userEntity) throws Exception {
		CapitalLogEntity capitalLogEntity = new CapitalLogEntity();
		capitalLogEntity.setType("3");
		capitalLogEntity.setValue(value);
		capitalLogEntity.setRemainValue(userEntity.getConvertScore().doubleValue());
		capitalLogEntity.setDesc(desc);
		capitalLogEntity.setUser(userEntity);
		capitalLogEntity.setLogTime(new Date());
		baseDAO.save(capitalLogEntity);
	}
}
