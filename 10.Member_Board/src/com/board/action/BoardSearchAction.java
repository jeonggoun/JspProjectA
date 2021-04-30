package com.board.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.study.BoardDAO;
import com.board.study.BoardDTO;
import com.board.study.SearchDTO;
import com.commons.action.Action;
import com.commons.action.ActionForward;

public class BoardSearchAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String part = request.getParameter("part");
		String searchData = request.getParameter("searchData");
		SearchDTO dto = new SearchDTO();
		dto.setPart(part);
		dto.setSearchData("%"+searchData+"%");
		
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardDTO> list = new ArrayList<>();
		list = dao.boardSearch(dto);
		request.setAttribute("list", list);
		
		ActionForward forward = new ActionForward();
		forward.setPath("board/board_searchList.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
