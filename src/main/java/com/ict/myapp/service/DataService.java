package com.ict.myapp.service;

import java.util.List;

import com.ict.myapp.vo.DataVO;

public interface DataService {
	public int dataInsert(DataVO vo);
	public List<DataVO> dataAllSelect();
	public DataVO dataSelect(int no);
	public int dataUpdate(DataVO vo);
	public void dataDelete(int no);
}
