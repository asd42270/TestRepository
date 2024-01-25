package com.example.controller;

import com.example.action.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//Json을 응답하는 컨트롤러 (RestControllervs
public class BookLogoutController implements Controller {
    //POJO
    public String requestHandler(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //로그 아웃
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:list.do"; //인증 여부를 판단하는 로직을 jsp에서 만들어라
    }
}