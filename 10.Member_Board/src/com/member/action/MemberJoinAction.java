package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.commons.action.Action;
import com.commons.action.ActionForward;
import com.member.study.MemberDAO;
import com.member.study.MemberDTO;

public class MemberJoinAction implements Action{	//서블렛처럼 사용하기 위해 Action을 상속받고 execute 오버라이드
@Override
public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	//클라이언트의 요청을 받는다 : 매개변수를 받는다
	request.setCharacterEncoding("utf-8");
//	String member_id = request.getParameter("member_id");
//	String member_pw = request.getParameter("member_pw");
//	String member_name = request.getParameter("member_name");
//	int member_age = Integer.parseInt(request.getParameter("member_age"));
//	String member_gender = request.getParameter("member_gender");
//	String member_email = request.getParameter("member_email");
//	MemberDTO dto = new MemberDTO(member_id, member_pw, member_name, member_age, member_gender, member_email);
	
	MemberDTO dto = new MemberDTO();
	dto.setMember_id(request.getParameter("member_id"));
	dto.setMember_pw(request.getParameter("member_pw"));
	dto.setMember_name(request.getParameter("member_name"));
	dto.setMember_age(Integer.parseInt(request.getParameter("member_age")));
	dto.setMember_gender(request.getParameter("member_gender"));
	dto.setMember_email(request.getParameter("member_email"));
	
	//비지니스 로직 : DB연동하여 회원가입 처리
	MemberDAO dao = new MemberDAO();
	int succ = dao.joinMember(dto);
	
	//프리젠테이션 로직 : 결과를 출력하고 화면 전환
	response.setContentType("text/html; charset=utf-8");
	PrintWriter out = response.getWriter();
	if (succ > 0) {
		out.println ("<script>alert('회원가입 성공!')");
		out.println ("location.href='memberLogin.me';</script>");
	} else {
		out.println ("<script>alert('회원가입 실패!')");
		out.println ("location.href='memberLogin.me';</script>");
	}
	
	return null;
}
}
