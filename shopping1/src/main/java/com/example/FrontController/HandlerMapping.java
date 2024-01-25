package com.example.FrontController;

import com.example.action.Controller;
import com.example.controller.*;

import java.util.HashMap;

public class HandlerMapping {
    private HashMap<String, Controller> mapping;

    public HandlerMapping(){
        mapping = new HashMap<String, Controller>();
        initMapping();
    }

    private void initMapping(){
        //요청 --> POJO
        //XML 파일, properties 파일로 넘긴다(아래 방법처럼 하지 말고)
        mapping.put("/list.do", new BookListController());
        mapping.put("/register.do", new BookRegisterController());
        mapping.put("/registerPost.do", new BookRegisterPostController());
        mapping.put("/ajaxList.do", new BookAjaxListController());
        mapping.put("/login.do", new BookLoginController());
        mapping.put("/logout.do", new BookLogoutController());
        mapping.put("/delete.do", new BookDeleteController());
    }
    public Controller getController(String key){ //key(경로)로 컨트롤러를 호출

        return mapping.get(key);
    }
}
