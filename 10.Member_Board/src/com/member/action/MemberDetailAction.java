package com.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.commons.action.Action;
import com.commons.action.ActionForward;
import com.member.study.MemberDAO;
import com.member.study.MemberDTO;

public class MemberDetailAction implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      HttpSession session = request.getSession();
      String id = (String) session.getAttribute("id");
      
      ActionForward forward = new ActionForward();
      if (id == null) {   //로그인이 되지 않은 상태
         forward.setPath("memberLogin.me");
         forward.setRedirect(true);
         return forward;
      }else {
         MemberDAO dao = new MemberDAO();
         MemberDTO dto = dao.getDetailMember(id);
         request.setAttribute("dto", dto);
         
         forward.setPath("member/member_detailForm.jsp");
         forward.setRedirect(false);
         return forward;
      }
   }//ActionForward()
}//class