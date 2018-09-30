package com.foxhis.itf.vod.mapper;

import java.util.List;

import com.foxhis.itf.vod.model.Vod_Grd;





public interface IVod_GrdMapper {
	
	public List<Vod_Grd> getVodByChanged(); 
	
	public int updateVodChanged(Vod_Grd vod);

}
