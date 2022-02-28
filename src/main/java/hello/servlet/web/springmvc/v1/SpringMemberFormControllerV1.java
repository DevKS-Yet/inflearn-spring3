package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller  // 스프링 빈에 등록됨(@Component가 있기 때문) / RequestMappingHandlerMapping역할도 함
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")  // 요청 정보를 매핑한다.
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }

}
