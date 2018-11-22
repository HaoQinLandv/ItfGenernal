package com.foxhis.itf.genernal;

import java.net.InetAddress;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.foxhis.itf.doorcard.mapper.IDoorCard_ReqMapper;
import com.foxhis.itf.doorcard.model.DoorCard_Req;
import com.foxhis.itf.genfactory.IGenCommon;
import com.foxhis.itf.handler.IDoorCardHandler;
import com.foxhis.itf.main.Utils;

public class DoorCardGenernal implements IGenCommon{

	private static final Logger LOGGER = Logger.getLogger(Utils.SERVER_LOGGER_NAME);
	private static SqlSessionFactory sqlSessionFactory;
	private IDoorCardHandler doorcardimpl;
	private Object obj = new Object();

	private static final String INITSTA="F";
	private static final String SUCCESS = "T";
	private static final String FAILS = "E";
	
	private static final String WRITE = "W";
	private static final String READ  = "R";
	private static final String ERASE = "C";

	public DoorCardGenernal(SqlSessionFactory SessionFactory) {
		// TODO Auto-generated constructor stub
		sqlSessionFactory = SessionFactory;
	}
	/**
	 * 初始化以获取handler
	 */
	@Override
	public void initialize() throws Exception {
		// TODO Auto-generated method stub
		doorcardimpl = Utils.getItfInstance(IDoorCardHandler.class);
	}


	/**
	 * 调用写卡具体方法
	 * @param input  传参给具体方
	 * @param doorcardreq 实例
	 * @param doorCard_ReqMapper mapper更新表
	 */
	public void writeCard(Map<String,Object> input,DoorCard_Req doorcardreq,IDoorCard_ReqMapper doorCard_ReqMapper) {
		Map<String,Object> reMap=doorcardimpl.writeCard(input);
		LOGGER.info(MessageFormat.format("写卡返回值{0}", reMap));
		if((Boolean)reMap.get("result"))
		{
			doorcardreq.setSta(SUCCESS);		
		}
		else {
			doorcardreq.setSta(FAILS);
		
		}
		doorcardreq.setRemark((String)reMap.get("msg"));
		int k = doorCard_ReqMapper.updateWriteData(doorcardreq);
		if(k>=1)
		{
			LOGGER.info(MessageFormat.format("{0}记录写卡更新成功", doorcardreq.getId()));
		}
	}
	/**
	 * 调用读卡具体方法
	 * @param input  传参给具体方
	 * @param doorcardreq 实例
	 * @param doorCard_ReqMapper mapper更新表
	 */
	public void readCard(Map<String,Object> input,DoorCard_Req doorcardreq,IDoorCard_ReqMapper doorCard_ReqMapper) {
		Map<String,Object> reMap=doorcardimpl.readCard(input);
		LOGGER.info(MessageFormat.format("读卡返回值{0}", reMap));
		int k=0;
		if((Boolean)reMap.get("result"))
		{
			doorcardreq.setSta(SUCCESS);
			doorcardreq.setRoomno((String)reMap.get("roomno"));
			doorcardreq.setArr((Date)reMap.get("arr"));
			doorcardreq.setDep((Date)reMap.get("dep"));
			doorcardreq.setFlag4((String)reMap.get("flag4"));
			doorcardreq.setRemark((String)reMap.get("msg"));
			k= doorCard_ReqMapper.updateReadData(doorcardreq);
			
		}
		else {
			doorcardreq.setSta(FAILS);
			doorcardreq.setRemark((String)reMap.get("msg"));
			k = doorCard_ReqMapper.updateReadData(doorcardreq);
		}
		if(k>=1)
		{
			LOGGER.info(MessageFormat.format("{0}记录读卡更新成功", doorcardreq.getId()));
		}
	}

	/**
	 * 调用销卡具体方法
	 * @param input  传参给具体方
	 * @param doorcardreq 实例
	 * @param doorCard_ReqMapper mapper更新表
	 */
	public void eraseCard(Map<String,Object> input,DoorCard_Req doorcardreq,IDoorCard_ReqMapper doorCard_ReqMapper) {
		Map<String,Object> reMap=doorcardimpl.eraseCard(input);
		LOGGER.info(MessageFormat.format("销卡返回值{0}", reMap));
		
		if((Boolean)reMap.get("result"))
		{
			doorcardreq.setSta(SUCCESS);
		
		}
		else {
			doorcardreq.setSta(FAILS);
		}
		doorcardreq.setRemark((String)reMap.get("msg"));
		int k =doorCard_ReqMapper.updateEraseData(doorcardreq);
		if(k>=1)
		{
			LOGGER.info(MessageFormat.format("{0}记录销卡更新成功", doorcardreq.getId()));
		}
	}

	
	/**
	 * 循环任务
	 * @param session
	 * @throws Exception
	 */
	public void doTask(SqlSession session) throws Exception
	{
		IDoorCard_ReqMapper doorCard_ReqMapper = session.getMapper(IDoorCard_ReqMapper.class);
		String pc_id = InetAddress.getLocalHost().getHostAddress();
		pc_id = pc_id.substring(pc_id.length() - 4, pc_id.length());
        LOGGER.info(MessageFormat.format("获取当前的站点{0}", pc_id));
		System.out.println(pc_id);
		//通过状态与站点获取当前站点没有处理的卡
		DoorCard_Req  doorcardreq = doorCard_ReqMapper.getDoorCard_ReqBySta(INITSTA,pc_id);
		if(doorcardreq!=null)
		{
			Map<String,Object> input = new HashMap<String,Object>();
			input.put("roomno", doorcardreq.getRoomno());
			input.put("name", doorcardreq.getName());
			input.put("arr", doorcardreq.getArr());
			input.put("dep", doorcardreq.getDep());
			input.put("card_type", doorcardreq.getCard_type());
			input.put("encoder", doorcardreq.getEncoder());
			input.put("pc_id", doorcardreq.getPc_id());
			input.put("cardno1", doorcardreq.getCardno1());
			input.put("cardno2", doorcardreq.getCardno2());
			input.put("flag1", doorcardreq.getFlag1());
			input.put("flag2", doorcardreq.getFlag2());
			input.put("flag3", doorcardreq.getFlag3());
			input.put("flag4", doorcardreq.getFlag4());
			input.put("remark", doorcardreq.getRemark());
			LOGGER.info(MessageFormat.format("获取站点{0}的{1}状态的数据:{2}",pc_id, INITSTA,input));
			String dotype = doorcardreq.getFlag3();
			if(WRITE.equals(dotype))
			{
				LOGGER.info("开始写卡...");
				writeCard(input, doorcardreq, doorCard_ReqMapper);
			}
			else if (READ.equals(dotype)) {
				LOGGER.info("开始读卡...");
				readCard(input, doorcardreq, doorCard_ReqMapper);
				
			}
			else if (ERASE.equals(dotype)) {
				LOGGER.info("开始销卡...");
				eraseCard(input, doorcardreq, doorCard_ReqMapper);
			}   
			else {
				LOGGER.error(MessageFormat.format("不存在的卡操作类型{0}", dotype));
			}
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (obj) {
			SqlSession session =null;
			try {
				session= sqlSessionFactory.openSession();	
				doTask(session);
			}catch (Throwable e) {
				// TODO: handle exception
				LOGGER.error("轮询异常："+e);
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
