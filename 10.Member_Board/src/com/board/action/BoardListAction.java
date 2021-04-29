package com.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.commons.action.Action;
import com.commons.action.ActionForward;

public class BoardListAction implements Action{
@Override
public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//클라이언트의 요청 받는다 : 세션을 받는다.
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		//비지니스 로직 : DB 접속 후 전체 글 목록 검색, 페이징 처리
		
		//프리젠테이션 로직
		ActionForward forward = new ActionForward(); //화면 전환 역할 담당
		if(id == null) {	//로그인이 되어 있지 않은 상태
			forward.setPath("memberLogin.me");
			forward.setRedirect(true);
		}else {
			forward.setPath("board/board_list.jsp");
			forward.setRedirect(false);
		}
		return forward;
	}
}
