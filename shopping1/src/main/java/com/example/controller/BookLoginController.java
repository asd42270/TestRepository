package com.example.controller;

import com.example.action.Controller;
import com.example.entity.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//Json을 응답하는 컨트롤러 (RestControllervs
public class BookLoginController implements Controller {
    //POJO
    public String requestHandler(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //회원 인증 처리
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        UserDTO dto = new UserDTO();
        dto.setName(username);
        dto.setName(password);
        dto.setName("관리자");

        //BookMyBatisDAO bookMyBatisDAO = new BookMyBatisDAO();
        //UserDTO user =bookMyBatisDAO.userLogin(dto);
        //if (user!=null){
          //  HttpSession session = request.getSession();
            //session.setAttribute("uservo", user);
       // }
        if (username.equals("admin") &&  password.equals("admin")){
            //회원 인증 성공 했다면
            //sessionId는 32자로 만들어진 고유한 값.
            HttpSession session = request.getSession();
            session.setAttribute("uservo", dto);
        }
        return "redirect:list.do"; //인증 여부를 판단하는 로직을 jsp에서 만들어라
    }
}