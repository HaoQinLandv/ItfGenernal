package com.foxhis.itf.genfactory;

import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;

import com.foxhis.itf.genernal.DoorCardGenernal;
import com.foxhis.itf.genernal.PoliceGenernal;
import com.foxhis.itf.genernal.SMS_Genernal;
import com.foxhis.itf.genernal.VODGenernal;


public class GenFactory {
	
    public final static String policehandler = "POLICE";
    public final static String vodhandler = "VOD";
    public final static String smshandler = "SMS";
    public final static String doorcardhandler = "DOORCARD";
   
    
	public static IGenCommon getItfGenInstance(Map<String, Object> input,SqlSessionFactory sqlSession)
	{
		String handler = (String)input.get("handler");
		int length = (Integer)input.get("length");
		if(handler ==null)
		{
			return null;
		}
		if(policehandler.equalsIgnoreCase(handler))
		{
			return new PoliceGenernal(sqlSession);
		}
		
		if(vodhandler.equalsIgnoreCase(handler))
		{
			return new VODGenernal(sqlSession);
		}
		if(smshandler.equalsIgnoreCase(handler))
		{
			return new SMS_Genernal(sqlSession,length);
		}
		if(doorcardhandler.equalsIgnoreCase(handler));
		{
			return new DoorCardGenernal(sqlSession);
		}
		
		
	}

}
