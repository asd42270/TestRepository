package com.example.controller;

import com.example.Repository.BookMyBatisDAO;
import com.example.action.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Json을 응답하는 컨트롤러 (RestControllervs
public class BookDeleteController implements Controller {
    //POJO
    public String requestHandler(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int num =Integer.parseInt( request.getParameter("num"));
        BookMyBatisDAO bookMyBatisDAO = new BookMyBatisDAO();
        int cnt = bookMyBatisDAO.bookDelete(num);
        return "redirect:list.do";
    }
}