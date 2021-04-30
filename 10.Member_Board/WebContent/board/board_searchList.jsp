<%@page import="com.board.study.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("utf-8");
	ArrayList<BoardDTO> list = new ArrayList<>();
	list = (ArrayList<BoardDTO>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search List</title>
</head>
<body>
	<div align="center">
		<h3>[검색 결과 화면]</h3>
		<%-- 검색결과 : <%=list.size()%>개 --%>
		<table border="1">
			<tr>
				<th>번호</th>
				<th width="200">제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:if test="${empty list}">
				<tr>
					<td colspan="5" align="center">검색된 글이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty list}">
				<c:forEach var="i" items="${list}">
					<tr align="center">
						<td>${i.board_num}</td>
						<td align="left"><a
							href="boardDetailAction.bo?board_num=${i.board_num}">
								${i.board_subject}</a></td>
						<td>${i.board_id}</td>
						<td>${i.board_date}</td>
						<td>${i.board_readcount}</td>
					</tr>
				</c:forEach>
			</c:if>
				<tr align="center">
					<td colspan="5">
					<input type="button" value="로그아웃" onclick="location.href='memberLogoutAction.me'" /> 
					<input type="button" value="글쓰기" onclick="locatioon.href='boardWrite.bo'" /> 
					<input type="button" value="목록보기" onclick="location.href='boardList.bo'" /></td>
				</tr>
		</table>
	</div>
</body>
</html>