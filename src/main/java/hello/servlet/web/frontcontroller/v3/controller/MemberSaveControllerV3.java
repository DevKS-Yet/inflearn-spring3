package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {

        String username = paramMap.get("username");  // new-form.jsp에서 온 username input 값을 String형으로 저장
        int age = Integer.parseInt(paramMap.get("age"));  // 위와 동일하게 age input 값을 int형으로 저장

        Member member = new Member(username, age);  // 받아온 값으로 Member 객체 생성
        memberRepository.save(member);  // 생성된 Member 객체 저장

        ModelView mv = new ModelView("save-result");  // save-result 페이지로 이동하기 위한 ModelView 생성
        mv.getModel().put("member",member);  // ModelView에 생성한 Member 객체 put
        return mv;

    }
}
