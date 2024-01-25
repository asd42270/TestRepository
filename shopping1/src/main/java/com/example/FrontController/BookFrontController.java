package com.example.FrontController;

import com.example.action.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//웹에서 실행가능한 클래스로 만들어야 한다.(WAS)->Servlet
//servlet의 시작 메소드는 doPost(), doGet(), service()
@WebServlet("*.do") //우선 순위가 가장 높다.
public class BookFrontController extends HttpServlet {
    protected  void service(HttpServletRequest request, HttpServletResponse response)
                            throws IOException, ServletException {
        System.out.println("모든 요청을 받아서 처리하는 프론트 컨트롤러입니다.");
        //1. 클라이언트의 요청을 분석하는 작업
        String requestPath = request.getRequestURI();// 내가 요청한 경로
        System.out.println("requsetPath: " + requestPath);
        String contextPath = request.getContextPath();
        System.out.println("contextPath: " + contextPath); //컨텍스트 패스
        String command = requestPath.substring(contextPath.length()); //사용자가 요청한 명령
        System.out.println(command);

        //2. 요청에 따른 분기 작업-> 핸들러 매핑
        //BookMyBatisDAO bookMyBatisDAO = new BookMyBatisDAO();
        String nextView = null;
        Controller controller = null;
        HandlerMapping handlerMapping = new HandlerMapping();
        controller = handlerMapping.getController(command);
        nextView = controller.requestHandler(request,response);
        if (controller!=null){
        //3. View의 경로를 forward 혹은 redirect로 결정
            if (nextView != null) {
                if (nextView.indexOf(":")==-1) {
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher(ViewResolver.makeView(nextView));
                    requestDispatcher.forward(request, response);
                } else {
                    response.sendRedirect(nextView.split(":")[1]);
                }
            }
        }
    }
}
