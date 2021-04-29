package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.commons.action.Action;
import com.commons.action.ActionForward;
import com.member.study.MemberDAO;
import com.member.study.MemberDTO;

public class MemberViewAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {	//로그인이 안 된 상태
			forward.setPath("memberLogin.me");
			forward.setRedirect(true);
			return forward;
		}else if (!id.equals("admin")) {	//관리자가 아닌 id로 로그인되어 있는 경우
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자가 아닙니다!');");
			out.println("location.href='boardList.bo';");
			out.println("</script>");
			return null;
		} else {	//관리자로 로그인한 상태
			String member_id = request.getParameter("member_id");
			
			MemberDAO dao = new MemberDAO();
			MemberDTO dto = dao.getDetailMember(member_id);
			request.setAttribute("dto", dto);
			
			forward.setPath("member/member_info.jsp");
			forward.setRedirect(false);
			
			return forward;
		}
	}
}//class
