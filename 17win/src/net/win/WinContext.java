package net.win;

import java.util.HashMap;

public final class WinContext {
	private static WinContext winContext = new WinContext();
	// 在线用户
	private Integer onlineCount = 0;

	private WinContext() {

	}

	public static WinContext getInstance() {
		return winContext;
	}

	private HashMap<String, UserLoginInfo> sessionCache = new HashMap<String, UserLoginInfo>();

	public Integer getOnlineCount() {
		return onlineCount;
	}

	public void addOnlineCount() {
		this.onlineCount++;
	}

	/**
	 * 获取UserLoginInfo
	 * 
	 * @param sessionId
	 * @return
	 */
	public UserLoginInfo getUserLoginInfo(String username) {
		return sessionCache.get(username);
	}

	/**
	 * 存放UserLoginInfo
	 * 
	 * @param sessionId
	 * @return
	 */
	public void putUserLoginInfo(String username, UserLoginInfo UserLoginInfo) {
		sessionCache.put(username, UserLoginInfo);
	}

	/**
	 * 是否存在
	 * 
	 * @param sessionId
	 * @return
	 */
	public Boolean existsUserLongInfo(String username) {
		return sessionCache.containsKey(username);
	}

	/**
	 * 移出UserLoginInfo
	 * 
	 * @param sessionId
	 * @return
	 */
	public void removeUserLoginInfo(String username) {
		sessionCache.remove(username);
	}

	/**
	 * sessionCache大小
	 * 
	 * @param sessionId
	 * @return
	 */
	public Integer sessionCacheSize() {
		return sessionCache.size();
	}

}
