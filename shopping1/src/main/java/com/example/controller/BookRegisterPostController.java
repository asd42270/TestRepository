package com.example.controller;

import com.example.Repository.BookMyBatisDAO;
import com.example.action.Controller;
import com.example.entity.BookDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//웹에서 실행가능한 클래스로 만들어야 한다.(WAS)->Servlet
//servlet의 시작 메소드는 doPost(), doGet(), service()
//@WebServlet("/registerPost") //클라이언트로 요청 정보(title, price, name, page)가 넘어온다.
public class BookRegisterPostController implements Controller {
    public String requestHandler (HttpServletRequest request, HttpServletResponse response)
                            throws IOException, ServletException {
    //폼에 있던 파라미터가 전달되어야 함, 패킷의 바디가 전달되어야 한다.
    //클라이언트로부터 넘어온 폼 파라미터를 얻어오는 작업(request로):파라미터 수집
        //기본이 문자열
        request.setCharacterEncoding("utf-8");
        String title = request.getParameter("title");
        int price = Integer.parseInt(request.getParameter("price"));
        String name = request.getParameter("name");
        int page = Integer.parseInt(request.getParameter("page"));
        //DTO묶어서->DAO
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(title);
        bookDTO.setPrice(price);
        bookDTO.setName(name);
        bookDTO.setPage(page);

        BookMyBatisDAO bookDAO = new BookMyBatisDAO();
        int cnt = bookDAO.bookinsert(bookDTO); //저장 후에 리스트 보기로 이동: redirect
        return "redirect:list.do";
    }
}
