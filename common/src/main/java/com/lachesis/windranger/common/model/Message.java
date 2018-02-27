/**   
 * @Title: Message.java 
 * @Package com.lachesis.windranger.common.model 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author Xi   
 * @date 2017年4月12日 上午10:31:42 
 * @version V1.0   
 */
package com.lachesis.windranger.common.model;

import java.io.Serializable;

/**
 * @ClassName: Message
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Xi xi.yang@lachesis-mh.com
 * @date 2017年4月12日 上午10:31:42
 * 
 */
public class Message implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -2519287525239724195L;
	private String toUserName;
	private String fromUserName;
	private String msgType;
	private Object content;
	private String CreateTime;

	/**
	 * @return toUserName
	 */
	public String getToUserName() {
		return toUserName;
	}

	/**
	 * @param toUserName
	 *            要设置的 toUserName
	 */
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	/**
	 * @return fromUserName
	 */
	public String getFromUserName() {
		return fromUserName;
	}

	/**
	 * @param fromUserName
	 *            要设置的 fromUserName
	 */
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	/**
	 * @return msgType
	 */
	public String getMsgType() {
		return msgType;
	}

	/**
	 * @param msgType
	 *            要设置的 msgType
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	/**
	 * @return content
	 */
	public Object getContent() {
		return content;
	}

	/**
	 * @param content
	 *            要设置的 content
	 */
	public void setContent(Object content) {
		this.content = content;
	}

	/**
	 * @return createTime
	 */
	public String getCreateTime() {
		return CreateTime;
	}

	/**
	 * @param createTime
	 *            要设置的 createTime
	 */
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	/*
	 * (非 Javadoc) <p>Title: toString</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Message [toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", msgType=" + msgType
				+ ", content=" + content + ", CreateTime=" + CreateTime + "]";
	}

}
