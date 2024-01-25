package com.example.controller;

import com.example.Repository.BookMapper;
import com.example.entity.BookDTO;
import com.example.entity.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller //Spring에서 POJO(컨트롤러)를 나타내기 위해서 사용하는 어노테이션
public class BookController {

    @Autowired //DI 의존성 주입
    private BookMapper bookMapper;

    @RequestMapping("/spring")
    public String index(){
        return "template";
    }

    @RequestMapping("/rest")
    public @ResponseBody List<UserDTO> rest(){
        List<UserDTO> list=new ArrayList<>();
        list.add(new UserDTO(1, "CUST01", "CUST01", "관리자"));
        list.add(new UserDTO(2, "CUST02", "CUST02", "박매일"));
        return list;
    }

    @RequestMapping("/list")
    public String list(Model model){
        List<BookDTO> list = bookMapper.bookList();
        model.addAttribute("list", list);
        return "list";
    }
}
