package com.ict.myapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ict.myapp.vo.DataVO;

@Mapper
@Repository
public interface DataDAO {
	public int dataInsert(DataVO vo); //등록
	public List<DataVO> dataAllSelect();//목록
	public DataVO dataSelect(int no); //글선택
	public int dataUpdate(DataVO vo);//글수정 
	public void dataDelete(int no);//자료실 글 삭제
}
