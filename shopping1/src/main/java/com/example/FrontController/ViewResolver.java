package com.example.FrontController;

public class ViewResolver {
    public static String makeView(String nextView){
        return "/WEB-INF/views/" + nextView + ".jsp";
    }
}
