package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //어느 경로로 들어왔는지 값 저장
        String requestURI = request.getRequestURI();

        //들어온 경로가 맵에 있는지 확인하고 있다면 변수 controller에 대입
        ControllerV2 controller = controllerMap.get(requestURI);

        //만약 없는 URI를 입력했더라도 404가 아닌 500에러가 뜬다면 if문 안에 return을 적었는지 확인해보자
        if (controller ==  null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //변수 controller가 null이 아니라면 해당 컨트롤러의 프로세스 실행
        MyView view = controller.process(request, response); // 변수 컨트롤러에 들어가있는 Member~ControllerV2의 process 메서드가 이젠 MyView를 반환함
        view.render(request, response);

    }
}
