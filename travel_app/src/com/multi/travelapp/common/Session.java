package com.multi.travelapp.common;

import java.lang.reflect.Member;

public class Session {
    private static Long currentMemberId;

    public static void login(Member member) {
//        currentMemberId = member.getMemberId();
    }

    public static Long getCurrentMemberId() {
        return currentMemberId;
    }

    public static void logout() {
        currentMemberId = null;
    }
}
