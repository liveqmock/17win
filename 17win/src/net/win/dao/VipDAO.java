package net.win.dao;

import net.win.BaseDAO;
import net.win.entity.VipEntity;

import org.springframework.stereotype.Repository;

@Repository("vipDAO")
public final class VipDAO extends BaseDAO<VipEntity> {

	public VipEntity getVIP1() {
		return uniqueResult("from VipEntity where type=1");
	}

	public VipEntity getVIP2() {
		return uniqueResult("from VipEntity where type=2");
	}

	public VipEntity getVIP3() {
		return uniqueResult("from VipEntity where type=3");
	}
}
