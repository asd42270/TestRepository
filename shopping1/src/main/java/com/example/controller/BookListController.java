package com.example.controller;

import com.example.Repository.BookMyBatisDAO;
import com.example.action.Controller;
import com.example.entity.BookDTO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
//@WebServlet("/list")
public class BookListController implements Controller {
    //POJO
    public String requestHandler(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        BookMyBatisDAO dao = new BookMyBatisDAO();
        List<BookDTO> list = dao.bookList();
        request.setAttribute("list", list) ; //리스트라는 이름에 실제 객체를 저장함.

        //List<BookDTO> -> Json(String)으로 바꿔서 데이터 그 자체를 전달하려면?
        //return "list" 이 부분을 바꾸어 주어야 한다.
        //컨트롤러가 직접 View에게 데이터를 전달하면 된다.
        Gson gson = new Gson();
        String json = gson.toJson(list);
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(json.toString());
        return "list";
    }
}