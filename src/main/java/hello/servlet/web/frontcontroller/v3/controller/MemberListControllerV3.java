package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    // MemberRepository에서 findAll() 메서드를 사용하기 위함
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();  // 전체 인원을 찾은 후 List로 return
        ModelView mv = new ModelView("members");  // members 페이지로 return하기 위한 ModelView 생성
        mv.getModel().put("members", members);  // 위에서 생성한 ModelView에 전체 인원이 적혀 있는 List를 넣은 맵 추가

        return mv;
    }
}
