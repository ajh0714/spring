package com.ict.myapp.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ict.myapp.dao.DataDAO;
import com.ict.myapp.vo.DataVO;

@Service
public class DataServiceImpl implements DataService {
	@Inject
	DataDAO dao;

	@Override
	public int dataInsert(DataVO vo) {
		// TODO Auto-generated method stub
		return dao.dataInsert(vo);
	}

	@Override
	public List<DataVO> dataAllSelect() {
		// TODO Auto-generated method stub
		return dao.dataAllSelect();
	}

	@Override
	public DataVO dataSelect(int no) {
		// TODO Auto-generated method stub
		return dao.dataSelect(no);
	}

	@Override
	public int dataUpdate(DataVO vo) {
		// TODO Auto-generated method stub
		return dao.dataUpdate(vo);
	}

	@Override
	public void dataDelete(int no) {
		// TODO Auto-generated method stub
		dao.dataDelete(no);
	}
}
