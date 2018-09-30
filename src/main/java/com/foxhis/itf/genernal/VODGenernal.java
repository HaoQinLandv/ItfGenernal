package com.foxhis.itf.genernal;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.foxhis.itf.genfactory.IGenCommon;
import com.foxhis.itf.handler.IVODHandler;
import com.foxhis.itf.main.Utils;
import com.foxhis.itf.vod.mapper.IVod_GrdMapper;
import com.foxhis.itf.vod.model.Vod_Grd;

/**
 * VOD服务的通用方法
 *
 */
public class VODGenernal  implements IGenCommon{


	private static final Logger LOGGER = Logger.getLogger(Utils.SERVER_LOGGER_NAME);
	private  SqlSessionFactory sqlSessionFactory;
	private IVODHandler vodimpl;
	//rivate final String changed = "T";
	private Object obj = new Object();


	@Override
	public void initialize() throws Exception {
		// TODO Auto-generated method stub
		this.vodimpl =Utils.getItfInstance(IVODHandler.class);
	}
	
	public VODGenernal(SqlSessionFactory sqlSession){
		// TODO Auto-generated constructor stub
		this.sqlSessionFactory = sqlSession;
		//sqlSessionFactory.getConfiguration().addMapper(IMessage_SendMapper.class);//已经在mybatis-config.xml映射
		
	
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (obj) {
			SqlSession session =null;
			try {
				session= sqlSessionFactory.openSession();
				doTask(session);
			}
			catch (Exception e) {
				// TODO: handle exception
				LOGGER.error("轮询异常："+e);
			}
			finally {
				if(session!=null)
				{
					session.close();
				}
			}
		}
	}

    public void doTask(SqlSession session)throws Exception
    {
    
    	IVod_GrdMapper vodmapper = session.getMapper(IVod_GrdMapper.class);
		
		List<Vod_Grd> vodlist = vodmapper.getVodByChanged();
		if(vodlist!=null && !vodlist.isEmpty())
		{
			for (Vod_Grd vodgrd : vodlist) {
                Map<String, Object> input = new HashMap<String, Object>();
                String grd = vodgrd.getGrade();
                String roomno  =vodgrd.getRoomno();
                input.put("roomno", roomno);
                input.put("changed", vodgrd.getChanged());
                input.put("ograde", vodgrd.getOgrade());
                input.put("grade", vodgrd.getGrade());
                input.put("obox_addr", vodgrd.getObox_addr());
                input.put("box_addr", vodgrd.getBox_addr());
                input.put("gst_grd", vodgrd.getGst_grd());
                input.put("gst_name", vodgrd.getGst_name());
                input.put("empno", vodgrd.getEmpno());
                input.put("shift", vodgrd.getShift());
                input.put("date", vodgrd.getDate());
                input.put("logmark", vodgrd.getLogmark());
                LOGGER.info(MessageFormat.format("获取的vodgrd记录：{0}", input));
				if(Utils.isNotBlank(grd))
				{
					 Map<String, Object> re = new HashMap<String, Object>();
                    if("1".equals(grd)) //入住
                    {
                    	re=vodimpl.vodCkin(input);
                    	
                    }
                    else {            //退房
                    	re =vodimpl.vodCkot(input);
                    	
					}
                    if((Boolean)re.get("result"))
                	{
                		vodgrd.setChanged("T");                    		
                	}
                	else {
                		vodgrd.setChanged("N");
                		LOGGER.info(MessageFormat.format("推送失败原因：{0}", re.get("msg")));
					}
                    int i=vodmapper.updateVodChanged(vodgrd);
                    LOGGER.info(MessageFormat.format("更新状态是否成功:{0}",i>=0?true:false));
            		session.commit();
				}
				
			}
		}
    }
	
}
