package com.ict.myapp.controller;

import java.io.File;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.myapp.service.DataService;
import com.ict.myapp.vo.DataVO;


@Controller
@RequestMapping("/data")
public class DataController {
	@Inject
	DataService service;
	ModelAndView mav;
	
	@GetMapping("/list")
	public ModelAndView list() {
		mav = new ModelAndView();
		
		mav.addObject("list", service.dataAllSelect());
		
		mav.setViewName("data/list");
		return mav;
	}
	@GetMapping("/dataWrite")
	public String dataWrite() {
		return "data/dataWrite";
	}
	//파일업로드
	@PostMapping("/dataWriteOk") //제목 ,글내용,MultipartFile
	public ModelAndView dataWriteOk(DataVO vo,MultipartFile mf, HttpSession session){
		
		// session 있는 글쓴이 아이디
		vo.setUserid((String)session.getAttribute("logid"));
		// 파일업로드 (rename)
		String path = session.getServletContext().getRealPath("/uploadfile");
		//업로드 한 실제 파일명구하기
		String orgFilename = mf.getOriginalFilename();
		System.out.println("원래파일명: "+ orgFilename);
		
		//새로 업로드 할 파일이 서버에 있는지 확인후
		File file = new File(path,orgFilename);
	
		orgFilename = fileRename(file, path, orgFilename);
		
		//업로드시작
		try {
		mf.transferTo(file);
		}catch(Exception e) {}  //없으면 업로드 수행
		//orgFilename : 업로드한 실제 파일명
		vo.setFilename(orgFilename); // 제목,글내용,글쓴이,파일명
		
		//레코드 시작
		int result = 0;
		try {
			//레코드 시작
			result = service.dataInsert(vo);
		}catch(Exception e) {//insert하다가 실패하면 파일을 삭제해야함.
			e.printStackTrace();
			File f = new File(path, orgFilename);
			f.delete();
		}
		
		mav = new ModelAndView();
		if(result<=0) {//추가실패
			
		}else {
			mav.setViewName("redirect:list");
		}
		return mav;
	}
	
	@GetMapping("/dataView")
	public ModelAndView dataView(int no) {
		mav = new ModelAndView();
		
		mav.addObject("vo", service.dataSelect(no));
		mav.setViewName("data/dataView");
		return mav;
	}
	@GetMapping("/dataEdit")
	public ModelAndView dataEdit(int no){
		mav = new ModelAndView();
		mav.addObject("vo", service.dataSelect(no));
		mav.setViewName("data/dataEdit");
		return mav;
	}
	//수정(dB)
	@PostMapping("/dataEditOk")
	public ModelAndView dataEditOk(DataVO vo,MultipartFile mf, HttpSession session){
		//업로드 위치
		String path = session.getServletContext().getRealPath("/uploadfile");
		
		DataVO orgVO = service.dataSelect(vo.getNo());//업데이트 전 레코드 - 파일삭제시 db저장된 파일명이 필요하다.
		
		//첨부된 파일이 있을 때 - 제목, 글내용, 파일명 수정
		//파입업로드해야함.
		//기존 파일삭제
		if(mf!=null) {
			String orgFilename = mf.getOriginalFilename();
			File file = new File(path,orgFilename);
			orgFilename = fileRename(file, path, orgFilename);
			
			//업로드
			try {
				file = new File(path,orgFilename);
				mf.transferTo(file);
			}catch(Exception e) {
				
			}
			vo.setFilename(orgFilename);
			
			
		}
		int result = 0;
		//dbupdate(제목, 글내용, 파일명)
		try {
			result = service.dataUpdate(vo);
			//기존 업로드 파일 삭제 - update성공
			if(vo.getFilename()!=null) {
				File f =new File(path, orgVO.getFilename());
				f.delete();
			}
			mav.setViewName("redirect:dataView?no="+vo.getNo());
		}catch(Exception e) {
			e.printStackTrace();
			//새로 업로드 파일 삭제 - update실패
			if(vo.getFilename()!=null) {
				File f = new File(path,vo.getFilename());
				f.delete();
			}
			mav.setViewName("bbs/result"); //수정폼으로 이동:history
		}
		return mav;
	}
	@GetMapping("/dataDel")
	public ModelAndView dataDel(int no, HttpServletRequest request) {
		mav = new ModelAndView();
		// 0. 해당레코드 선택 - 레코드를 먼저 삭제시 첨부파일의 이름을 알수 없음.
		DataVO vo = service.dataSelect(no);
		// 1. 해당레코드 지우기
		try {
			service.dataDelete(no);
			
			//파일삭제
			String path = request.getSession().getServletContext().getRealPath("/uploadfile");
			File file = new File(path,vo.getFilename());
			file.delete();
			//글목록
			mav.setViewName("redirect:list");
		}catch(Exception e) {
			//글내용보기로 이동
			mav.setViewName("redirect:dataView?no="+no);
		}
		
		return mav;
	}
	public String fileRename(File file, String path,String orgFilename) {
		// file.exists():파일이 존재하면 true, 존재하지 않으면 false
				if(file.exists()) {
					for(int i=1; ;i++) {
					//파일명과 확장자 구분
					int point = orgFilename.lastIndexOf(".");
					String f = orgFilename.substring(0,point);
					String ext = orgFilename.substring(point+1);
					
					//새로운 파일명
					String newFilename = f + " ("+i+")."+ext;// 09(1).jpeg
					file = new File(path,newFilename);
					if(!file.exists()){//존재하지않는 파일이면
					    	orgFilename= newFilename;
					    	break;
						}
					}	
				}
				return orgFilename;
		}
 	}

