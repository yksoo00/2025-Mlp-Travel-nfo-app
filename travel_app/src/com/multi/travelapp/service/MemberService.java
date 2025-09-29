package com.multi.travelapp.service;

import com.multi.travelapp.common.DBConnectionMgr;
import com.multi.travelapp.model.dao.TouristSpotDao;
import com.multi.travelapp.model.dto.TouristSpotDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberService {

    private final TouristSpotDao touristSpotDao;
    private final DBConnectionMgr dbcp;

    public MemberService() {
        this.dbcp = DBConnectionMgr.getInstance();

        // 커넥션 풀 초기화 (처음 10개)
        if (dbcp.getConnectionCount() == 0) {
            try {
                dbcp.setInitOpenConnections(10);
            } catch (SQLException e) {
                throw new RuntimeException("DB 커넥션 풀 초기화 실패", e);
            }
        }

        this.touristSpotDao = new TouristSpotDao();
    }

    public ArrayList<TouristSpotDto> selectTourSpotsByRegion(String region) {
        ArrayList<TouristSpotDto> list = new ArrayList<>();
        Connection conn = null;

        try {
            conn = dbcp.getConnection();
            list = touristSpotDao.selectTourSpotsByRegion(conn, region);
        } catch (Exception e) {
            throw new RuntimeException("지역별 관광지 조회 실패", e);
        } finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }

        return list;
    }



    public ArrayList<TouristSpotDto> selectTourSpotsByTitle(String title) {
        ArrayList<TouristSpotDto> list = new ArrayList<>();
        Connection conn = null;

        try {
            conn = dbcp.getConnection();
            list = touristSpotDao.selectTourSpotsByTitle(conn, title);
        } catch (Exception e) {
            throw new RuntimeException("제목x별 관광지 조회 실패", e);
        } finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }

        return list;
    }
}
