package com.commons.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {	//인터페이스 : 다중상속을 지원, 추상 메소드로만 구성
	//public abstract void execute();	//추상 메소드
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException;
}