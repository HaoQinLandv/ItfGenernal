package com.foxhis.itf.sms.mapper;

import java.util.List;

import com.foxhis.itf.sms.model.Message_Send;



public interface IMessage_SendMapper {
	
	/**
	 * 查询所有符合发送请求的数据
	 * @return
	 */
	public List<Message_Send> getMs_SendBySta();
	
	
	/**
	 * 更新发送后成功与否的状态
	 * @return
	 */
	public void updateMs_Send(Message_Send ms);
	
	
	/**
	 * 更新过时的短信数据
	 * @return
	 */
	public void updateOldMsg();

}
