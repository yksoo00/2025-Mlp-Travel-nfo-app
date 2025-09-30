package com.multi.travelapp.service;

import com.multi.travelapp.common.DBConnectionMgr;
import com.multi.travelapp.model.dao.MemberDao;
import com.multi.travelapp.model.dto.MemberDto;
import com.multi.travelapp.model.dto.SignInDto;
import com.multi.travelapp.model.dao.TouristSpotDao;
import com.multi.travelapp.model.dto.TouristSpotDto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.sql.DriverManager.getConnection;

public class MemberService {

    private MemberDao memberDao = new MemberDao();
    private DBConnectionMgr dbcp;
      
    public MemberService() {
        this.dbcp = DBConnectionMgr.getInstance();

        if (dbcp.getConnectionCount() == 0) {
            try {
                dbcp.setInitOpenConnections(10);
            } catch (SQLException e) {
                throw new RuntimeException("DB 커넥션 풀 초기화 실패", e);
            }
        }


    }
  



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

    // page로 관광지 조회
    public ArrayList<TouristSpotDto> selectAllTouristSpotByPage(int page) {
        ArrayList<TouristSpotDto> list = new ArrayList<>();
        Connection conn = null;

        try {
            conn = dbcp.getConnection();
            list = touristSpotDao.selectAllTouristSpotByPage(conn, page);
        } catch (Exception e) {
            throw new RuntimeException("페이지별 관광지 조회 실패", e);
        } finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }

        return list;
    }
}
