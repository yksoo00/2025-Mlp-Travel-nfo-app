package com.multi.travelapp.controller;

import com.multi.travelapp.model.dto.MemberDto;
import com.multi.travelapp.model.dto.SignInDto;
import com.multi.travelapp.model.dto.SignInDto;
import com.multi.travelapp.service.MemberService;
import com.multi.travelapp.view.TravelView;

public class MemberController {
    private MemberService memberService = new MemberService();

    // 회원가입 처리
    public boolean signUp(MemberDto memberDto) {

        boolean result = memberService.signUp(memberDto);

        // 회원가입 여부 출력
        if (result) {
            System.out.println("회원가입 성공");
        }

        return result; // View로 반환
    }

    public MemberDto signIn(SignInDto signInDto) {
        MemberDto memberDto = memberService.signIn(signInDto);
        TravelView travelView = new TravelView();

        try {
        if (memberDto != null) {
            System.out.println("로그인 성공" + signInDto.getEmail());

        }else {
            System.out.println("로그인 실패");
                }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return memberDto;

    }
}