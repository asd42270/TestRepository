package com.example.controller;

import com.example.action.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//웹에서 실행가능한 클래스로 만들어야 한다.(WAS)->Servlet
//servlet의 시작 메소드는 doPost(), doGet(), service()
//@WebServlet("/register")
public class BookRegisterController implements Controller {
    public String requestHandler(HttpServletRequest request, HttpServletResponse response)
                            throws IOException, ServletException {
    //jsp 파일을 열기 위해 컨트롤러에서 뷰와 연동해야 함.
        //뷰와 연동하기-> 포워딩
        //객체 바인딩은 필요없다.
        //책 등록 jsp로 포워딩
        //RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/register.jsp");
        //컨트롤러가 view로 데이터를 보내는 방법 -> 객체 바인딩
        //rd.forward(request, response);
        return "register";

    }
}
