package com.multi.travelapp.common;

import com.multi.travelapp.model.dto.MemberDto;
import java.lang.reflect.Member;

public class Session {
  private static ThreadLocal<Long> currentMemberId = new ThreadLocal<>();
  private static ThreadLocal<Boolean> isAdmin = ThreadLocal.withInitial(() -> false);

  public static void login(MemberDto member) {
    currentMemberId.set(member.getMemberId());
  }

  public static Long getCurrentMemberId() {
    return currentMemberId.get();
  }

  public static void logout() {
    currentMemberId.remove();
    isAdmin.remove();
  }

  public static void setIsAdminTrue(){
    isAdmin.set(true);
  }

  public static boolean getIsAdmin(){
    return isAdmin.get();
  }
}