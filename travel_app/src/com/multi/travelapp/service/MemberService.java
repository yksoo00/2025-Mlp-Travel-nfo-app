package com.multi.travelapp.service;

import com.multi.travelapp.common.DBConnectionMgr;
import com.multi.travelapp.model.dao.MemberDao;
import com.multi.travelapp.model.dto.MemberDto;
import com.multi.travelapp.model.dto.SignInDto;

import java.sql.Connection;

import static java.sql.DriverManager.getConnection;

public class MemberService {

    private MemberDao memberDao = new MemberDao();
    private DBConnectionMgr dbcp = DBConnectionMgr.getInstance();


    public boolean signUp(MemberDto memberDto) {
        boolean result = false;
        Connection conn = null;

        try {
            conn = dbcp.getConnection();
            conn.setAutoCommit(false);
            int insertResult = memberDao.insertMember(conn, memberDto);

            if(insertResult>0) {
                conn.commit();
                result = true;
            }else {
                conn.rollback();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }

        return result;

    }

    public MemberDto signIn(SignInDto signInDto) {
        Connection conn = null;
        MemberDto memberDto = null;

        try {
            conn = dbcp.getConnection();

            memberDto = memberDao.selectMemberByEmail(conn, signInDto.getEmail(), signInDto.getPassword());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }
        return memberDto;

    }
}
