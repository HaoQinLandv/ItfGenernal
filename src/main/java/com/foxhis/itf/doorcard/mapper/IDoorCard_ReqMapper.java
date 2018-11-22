package com.foxhis.itf.doorcard.mapper;

import com.foxhis.itf.doorcard.model.DoorCard_Req;

public interface IDoorCard_ReqMapper {
	
	public DoorCard_Req getDoorCard_ReqBySta(String sta,String pc_id);
	
	public int updateWriteData(DoorCard_Req dReq);
	
	public int updateReadData(DoorCard_Req dReq);
	
	public int updateEraseData(DoorCard_Req dReq);
}
