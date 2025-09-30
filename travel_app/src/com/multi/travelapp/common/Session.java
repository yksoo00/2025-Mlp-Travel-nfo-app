package com.multi.travelapp.common;

import com.multi.travelapp.model.dto.MemberDto;
import java.lang.reflect.Member;

public class Session {
    private static Long currentMemberId;

    public static void login(MemberDto member) {
        currentMemberId = member.getMemberId();
    }

    public static Long getCurrentMemberId() {
        return currentMemberId;
    }

    public static void logout() {
        currentMemberId = null;
    }
}
