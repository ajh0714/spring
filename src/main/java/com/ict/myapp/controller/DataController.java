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
	//���Ͼ��ε�
	@PostMapping("/dataWriteOk") //���� ,�۳���,MultipartFile
	public ModelAndView dataWriteOk(DataVO vo,MultipartFile mf, HttpSession session){
		
		// session �ִ� �۾��� ���̵�
		vo.setUserid((String)session.getAttribute("logid"));
		// ���Ͼ��ε� (rename)
		String path = session.getServletContext().getRealPath("/uploadfile");
		//���ε� �� ���� ���ϸ��ϱ�
		String orgFilename = mf.getOriginalFilename();
		System.out.println("�������ϸ�: "+ orgFilename);
		
		//���� ���ε� �� ������ ������ �ִ��� Ȯ����
		File file = new File(path,orgFilename);
	
		orgFilename = fileRename(file, path, orgFilename);
		
		//���ε����
		try {
		mf.transferTo(file);
		}catch(Exception e) {}  //������ ���ε� ����
		//orgFilename : ���ε��� ���� ���ϸ�
		vo.setFilename(orgFilename); // ����,�۳���,�۾���,���ϸ�
		
		//���ڵ� ����
		int result = 0;
		try {
			//���ڵ� ����
			result = service.dataInsert(vo);
		}catch(Exception e) {//insert�ϴٰ� �����ϸ� ������ �����ؾ���.
			e.printStackTrace();
			File f = new File(path, orgFilename);
			f.delete();
		}
		
		mav = new ModelAndView();
		if(result<=0) {//�߰�����
			
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
	//����(dB)
	@PostMapping("/dataEditOk")
	public ModelAndView dataEditOk(DataVO vo,MultipartFile mf, HttpSession session){
		//���ε� ��ġ
		String path = session.getServletContext().getRealPath("/uploadfile");
		
		DataVO orgVO = service.dataSelect(vo.getNo());//������Ʈ �� ���ڵ� - ���ϻ����� db����� ���ϸ��� �ʿ��ϴ�.
		
		//÷�ε� ������ ���� �� - ����, �۳���, ���ϸ� ����
		//���Ծ��ε��ؾ���.
		//���� ���ϻ���
		if(mf!=null) {
			String orgFilename = mf.getOriginalFilename();
			File file = new File(path,orgFilename);
			orgFilename = fileRename(file, path, orgFilename);
			
			//���ε�
			try {
				file = new File(path,orgFilename);
				mf.transferTo(file);
			}catch(Exception e) {
				
			}
			vo.setFilename(orgFilename);
			
			
		}
		int result = 0;
		//dbupdate(����, �۳���, ���ϸ�)
		try {
			result = service.dataUpdate(vo);
			//���� ���ε� ���� ���� - update����
			if(vo.getFilename()!=null) {
				File f =new File(path, orgVO.getFilename());
				f.delete();
			}
			mav.setViewName("redirect:dataView?no="+vo.getNo());
		}catch(Exception e) {
			e.printStackTrace();
			//���� ���ε� ���� ���� - update����
			if(vo.getFilename()!=null) {
				File f = new File(path,vo.getFilename());
				f.delete();
			}
			mav.setViewName("bbs/result"); //���������� �̵�:history
		}
		return mav;
	}
	@GetMapping("/dataDel")
	public ModelAndView dataDel(int no, HttpServletRequest request) {
		mav = new ModelAndView();
		// 0. �ش緹�ڵ� ���� - ���ڵ带 ���� ������ ÷�������� �̸��� �˼� ����.
		DataVO vo = service.dataSelect(no);
		// 1. �ش緹�ڵ� �����
		try {
			service.dataDelete(no);
			
			//���ϻ���
			String path = request.getSession().getServletContext().getRealPath("/uploadfile");
			File file = new File(path,vo.getFilename());
			file.delete();
			//�۸��
			mav.setViewName("redirect:list");
		}catch(Exception e) {
			//�۳��뺸��� �̵�
			mav.setViewName("redirect:dataView?no="+no);
		}
		
		return mav;
	}
	public String fileRename(File file, String path,String orgFilename) {
		// file.exists():������ �����ϸ� true, �������� ������ false
				if(file.exists()) {
					for(int i=1; ;i++) {
					//���ϸ�� Ȯ���� ����
					int point = orgFilename.lastIndexOf(".");
					String f = orgFilename.substring(0,point);
					String ext = orgFilename.substring(point+1);
					
					//���ο� ���ϸ�
					String newFilename = f + " ("+i+")."+ext;// 09(1).jpeg
					file = new File(path,newFilename);
					if(!file.exists()){//���������ʴ� �����̸�
					    	orgFilename= newFilename;
					    	break;
						}
					}	
				}
				return orgFilename;
		}
 	}

