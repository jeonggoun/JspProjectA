package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.study.BoardDAO;
import com.board.study.BoardDTO;
import com.commons.action.Action;
import com.commons.action.ActionForward;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sun.media.sound.SoftSynthesizer;

public class BoardAddAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String saveFolder = "boardupload";
//		String realFolder = request.getRealPath(saveFolder);
//		System.out.println(realFolder);
		
		String realFolder = "";	//업로드한 파일이 저장되는 경로(공간, 주소, 위치)
		realFolder = "D:\\Study_Web\\workspace\\10.Member_Board\\WebContent\\boardupload";
		int fileSize = 5 * 1024 * 1024;	//최대용량 : 5MB
		
		MultipartRequest multi = null;	//파일 업로드 처리
		DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();	//파일명의 중복 방지
		multi = new MultipartRequest(request, realFolder, fileSize, "utf-8", policy);
	
		
		BoardDTO dto = new BoardDTO();	//디폴트 생성자 만들고
		dto.setBoard_id(multi.getParameter("board_id"));	//request 사용하면 안 됨 multi로 써야 한다
		dto.setBoard_subject(multi.getParameter("board_subject"));
		dto.setBoard_content(multi.getParameter("board_content"));

		//System.out.println(multi.getParameter("board_id"));
		//System.out.println(multi.getParameter("board_file"));	//null : 파일명 중복검사 수행 X
		//System.out.println(multi.getFilesystemName((String)multi.getFileNames().nextElement()));
		//System.out.println(dto.getBoard_id());
		//System.out.println(dto.getBoard_subject());
		//System.out.println(dto.getBoard_content());
		//System.out.println(dto.getBoard_file());
		
		dto.setBoard_file(multi.getFilesystemName((String)multi.getFileNames().nextElement()));
		
		BoardDAO dao = new BoardDAO();
		int succ = dao.boardInsert(dto);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if(succ > 0) {
			out.println("<script>alert('등록성공!');");
			out.println("location.href='boardList.bo';</script>");
		}else {
			out.println("<script>alert('등록실패!');");
			out.println("location.href='boardList.bo';</script>");
		}
		return null;
	}
}