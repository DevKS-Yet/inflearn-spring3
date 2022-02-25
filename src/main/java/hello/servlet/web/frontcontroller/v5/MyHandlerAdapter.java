package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {

    // handler는 컨트롤러를 말하며 어댑터가 해당 컨트롤러를 처리할 수 있는지 판단
    boolean supports(Object handler);

    // handle은 핸들러를 호출하며 ModelView를 반환
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;

}
