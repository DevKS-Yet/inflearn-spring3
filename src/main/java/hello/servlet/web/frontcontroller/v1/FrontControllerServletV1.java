package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //어느 경로로 들어왔는지 값 저장
        String requestURI = request.getRequestURI();

        //들어온 경로가 맵에 있는지 확인하고 있다면 변수 controller에 대입
        ControllerV1 controller = controllerMap.get(requestURI);

        //만약 없는 URI를 입력했더라도 404가 아닌 500에러가 뜬다면 if문 안에 return을 적었는지 확인해보자
        if (controller ==  null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //변수 controller가 null이 아니라면 해당 컨트롤러의 프로세스 실행
        controller.process(request, response);

    }
}
