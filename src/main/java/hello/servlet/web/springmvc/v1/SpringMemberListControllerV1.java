package hello.servlet.web.springmvc.v1;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class SpringMemberListControllerV1 {

    // MemberRepository에서 findAll() 메서드를 사용하기 위함
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members")
    public ModelAndView process() {
        List<Member> members = memberRepository.findAll();  // 전체 인원을 찾은 후 List로 return
        ModelAndView mv = new ModelAndView("members");  // members 페이지로 return하기 위한 ModelView 생성
        mv.addObject("members", members);

        return mv;
    }
}
