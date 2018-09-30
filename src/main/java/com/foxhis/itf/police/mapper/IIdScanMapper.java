package com.foxhis.itf.police.mapper;

import com.foxhis.itf.police.model.IdScan;
import com.foxhis.itf.police.model.IdScan_ga;

public interface IIdScanMapper {
	
	public int insertIdScan(IdScan idscan);

	public int getIdsBySysExtraid();
	
	public int updateIDS(int ids);
	
	/**
	 * 插入公安专用表
	 * @param idscan_ga
	 * @return
	 */
	public int insertIdScan_ga(IdScan_ga idscan_ga);
	
	public void afterUpdate(String updateIdScangaById);
	
	public void updateProMode();
}
