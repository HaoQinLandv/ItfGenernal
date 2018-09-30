package com.foxhis.itf.genernal;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.foxhis.itf.genfactory.IGenCommon;
import com.foxhis.itf.handler.ISMSHandler;
import com.foxhis.itf.main.Utils;
import com.foxhis.itf.sms.mapper.IMessage_SendMapper;
import com.foxhis.itf.sms.model.Message_Send;
import com.foxhis.itf.sms.model.SM_Sendplus;


/**
 * 发送短信类
 *
 */
public class SMS_Genernal implements IGenCommon
{

	private static final Logger LOGGER = Logger.getLogger(Utils.SERVER_LOGGER_NAME);
	private static SqlSessionFactory sqlSessionFactory;
	private ISMSHandler smsimpl;
	private int length;
	private Object obj = new Object();

	
	@Override
	public void initialize() throws Exception {
		// TODO Auto-generated method stub
		this.smsimpl=Utils.getItfInstance(ISMSHandler.class);
		SqlSession session= sqlSessionFactory.openSession();
		IMessage_SendMapper ms = session.getMapper(IMessage_SendMapper.class);
		//先更新过期的数据
		ms.updateOldMsg();
		session.commit();
		session.close();
		LOGGER.info("更新过期数据...");
	}
	
	public SMS_Genernal(SqlSessionFactory sqlSession,int length){

		sqlSessionFactory = sqlSession;
		this.length = length;
		
		//sqlSessionFactory.getConfiguration().addMapper(IMessage_SendMapper.class);//已经在mybatis-config.xml映射
		

	}

	public boolean isRegularlySend(Message_Send message_Send)
	{
		Calendar calendar=Calendar.getInstance();
		//calendar.add(Calendar.SECOND, 5);//时间往后延续5秒
		Date now = calendar.getTime();
		Date sendtime = message_Send.getSendtime();
		if(sendtime==null)
			return false;
		long snr = sendtime.getTime()-now.getTime();
		if(message_Send.getSendstate()==2 && (snr<0))
		{
			return true;
		}
		else{
			return false;
		}
	}




	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (obj) {
			SqlSession session =null;
			try {
				session= sqlSessionFactory.openSession();
				IMessage_SendMapper ms = session.getMapper(IMessage_SendMapper.class);
				//先更新过期的数据
				//ms.updateOldMsg();
				List<Message_Send> msds = ms.getMs_SendBySta();
				session.commit();
				if(msds!=null && !msds.isEmpty() && smsimpl!=null)
				{
					for (Message_Send message_Send : msds) {
						//获取每条记录做推送,将手机号码内容封装成map类型
						if(isRegularlySend(message_Send)) 
							continue;
						Map<String,Object> reMap = new HashMap<String,Object>();
						reMap.put("mobile", message_Send.getShortmsgno());//号码
						
						StringBuffer context = new StringBuffer();
						//byte[] body = message_Send.getShortmsgbody();
						String body = message_Send.getShortmsgbody();
						//String bodys = new String(body,"GBK");
					    context.append(Utils.nullToStr(body));
						SM_Sendplus sendplus = message_Send.getSm_sendplus();
						if(sendplus!=null)
						{
							//String plus1=new String(sendplus.getShortmsgbody1(),"GBK");
							//String plus2=new String(sendplus.getShortmsgbody2(),"GBK");
							//String plus3=new String(sendplus.getShortmsgbody3(),"GBK");
							//String plus4=new String(sendplus.getShortmsgbody4(),"GBK");
							//String plus5=new String(sendplus.getShortmsgbody5(),"GBK");

							String plus1=sendplus.getShortmsgbody1();
							String plus2=sendplus.getShortmsgbody2();
							String plus3=sendplus.getShortmsgbody3();
							String plus4=sendplus.getShortmsgbody4();
							String plus5=sendplus.getShortmsgbody5();
							
							context.append(Utils.nullToStr(plus1));
							context.append(Utils.nullToStr(plus2));
							context.append(Utils.nullToStr(plus3));
							context.append(Utils.nullToStr(plus4));
							context.append(Utils.nullToStr(plus5));
						}
						String contexts = context.toString();
						//contexts = new String(contexts.getBytes("ISO-8859-1"),"GBK");
						int len = contexts.length();
						int tem =0;

						Map<String,Object> result =new HashMap<String, Object>();
						do
						{
							String str1="";
							if((tem+length)>len)
							{
								str1 = contexts.substring(tem);
							}
							else
							{
								str1 = contexts.substring(tem, tem+length);

							}
							LOGGER.info("发送内容:"+str1);
							tem += length;
							reMap.put("content", str1);//内容
							result = smsimpl.send(reMap);
							LOGGER.info("返回结果:"+result);
							if(!(Boolean)result.get("result"))
							{
								break;
							}
						}
						while(len-tem>0);
						//发送成功，修改状态
						LOGGER.info("最后返回结果:"+result);
						if((Boolean)result.get("result"))
						{
							message_Send.setSendstate(5);
							message_Send.setResultdescrib((String)result.get("msg"));
							ms.updateMs_Send(message_Send);
							session.commit();
						}
						//发送失败，修改日志
						else{
							message_Send.setSendstate(-1);
							message_Send.setResultdescrib((String)result.get("msg"));
							ms.updateMs_Send(message_Send);
							session.commit();
							//LOGGER.info("发送短信失败:"+result.get("msg"));
						}

					}
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				LOGGER.error("轮询异常：",e);
			}
			finally{
				if(session!=null)
				{
					session.close();
				}
			}
		}
	}

	
}
