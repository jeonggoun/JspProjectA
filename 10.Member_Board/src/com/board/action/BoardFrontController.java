package com.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.commons.action.Action;
import com.commons.action.ActionForward;

@WebServlet("/BoardFrontController.bo")
public class BoardFrontController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 클라이언트가 어떤 요청을 했는가를 파악
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
		String command = uri.substring(ctx.length());
		//System.out.println("uri : " + uri);
		//System.out.println("ctx : " + ctx);
		//System.out.println("command : " + command);
		
		//2. 클라이언트의 요청과 실제 처리할 비지니스 로직 연결
		//*.bo → command ▶ BoardXXXAction.java
		Action action = null;
		ActionForward forward = null;
		
		if (command.equals("/boardList.bo")) {
			action = new BoardListAction();
			forward = action.execute(request, response);
		}else if(command.equals("/boardWrite.bo")) {
			//글쓰기 화면으로 이동(DB연동 X)
			forward = new ActionForward();
			forward.setPath("board/board_write.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/boardAddAction.bo")) {
			action = new boardAddAction();
			forward = action.execute(request, response);
		}
		
		//3. 페이지 전환
		if (forward != null) {
			if (forward.isRedirect()) {	//true : sendRedirect()
				response.sendRedirect(forward.getPath());
			} else {					//false : forward()
				RequestDispatcher rd = request.getRequestDispatcher(forward.getPath());
				rd.forward(request, response);
			}
		}

	}

}
