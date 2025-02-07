package com.ict.myapp.vo;

import lombok.ToString;

@ToString
public class PagingVO {
	private int nowPage = 1;//����������
	private int onePageRecord = 5;//���������� ǥ���� ���ڵ� ��
	
	private int totalRecord; //�ѷ��ڵ� ��
	private int totalPage; // �� ������ ��
	
	private int offset;
	
	/*��������ȣ*/
	private int onePageCount=5; //�ѹ��� ǥ���� ������ ��
	private int startPageNum=1; // ��������ȣ�� ���۰�
	
	private String searchKey;
	private String searchWord;
	

	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
		
		//������������ ������ ���ڵ� ������ġ�� ������
		//offset�� 0���� �Ű���
		offset = (nowPage-1)*onePageRecord;
		
		//��������ȣ�� ���۰� ���
		// (����������-1)/�ѹ��� ǥ���� ������ �ѹ��� ǥ���� ������+1
		startPageNum = (nowPage-1)/onePageCount * onePageCount+1;
	}
	public int getOnePageRecord() {
		return onePageRecord;
	}
	public void setOnePageRecord(int onePageRecord) {
		this.onePageRecord = onePageRecord;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		
		//Ȩ�������� ���ϱ�
		totalPage = (totalRecord%onePageRecord) == 0? totalRecord/onePageRecord:totalRecord/onePageRecord+1;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getOnePageCount() {
		return onePageCount;
	}
	public void setOnePageCount(int onePageCount) {
		this.onePageCount = onePageCount;
	}
	public int getStartPageNum() {
		return startPageNum;
	}
	public void setStartPageNum(int startPageNum) {
		this.startPageNum = startPageNum;
	}
	@Override
	public String toString() {
		return "PagingVO [nowPage=" + nowPage + ", onePageRecord=" + onePageRecord + ", totalRecord=" + totalRecord
				+ ", totalPage=" + totalPage + ", offset=" + offset + ", onePageCount=" + onePageCount
				+ ", startPageNum=" + startPageNum + ", searchKey=" + searchKey + ", searchWord=" + searchWord + "]";
	}

	
}
