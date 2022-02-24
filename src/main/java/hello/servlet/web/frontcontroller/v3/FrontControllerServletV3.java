package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private final Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //어느 경로로 들어왔는지 값 저장
        String requestURI = request.getRequestURI();

        //들어온 경로가 맵에 있는지 확인하고 있다면 변수 controller에 대입
        ControllerV3 controller = controllerMap.get(requestURI);

        //만약 없는 URI를 입력했더라도 404가 아닌 500에러가 뜬다면 if문 안에 return을 적었는지 확인해보자
        if (controller ==  null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //paramMap
        Map<String, String> paramMap = createParamMap(request);

        //변수 controller가 null이 아니라면 해당 컨트롤러의 프로세스 실행
        ModelView mv = controller.process(paramMap);// 변수 컨트롤러에 들어가있는 Member~ControllerV2의 process 메서드가 이젠 MyView를 반환함

        String viewName = mv.getViewName(); // 논리이름 new-form

        MyView view = viewResolver(viewName);
        view.render(mv.getModel(), request, response);

    }

    private MyView viewResolver(String viewName) {
        return new MyView("WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName))); // 매개변수 이름만큼 읽고 paramName마다 paramMap에 paramName와 해당 매개변수 저장
        return paramMap;
    }
}
