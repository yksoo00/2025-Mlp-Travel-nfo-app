package com.multi.travelapp.service;

import com.multi.travelapp.common.DBConnectionMgr;
import com.multi.travelapp.model.dao.BookMarkDao;
import com.multi.travelapp.model.dao.ReviewDao;
import com.multi.travelapp.model.dto.BookMarkDto;
import com.multi.travelapp.model.dto.TouristSpotDto;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookMarkService {
    private BookMarkDao bookMarkDao;
    Connection conn = null;
    DBConnectionMgr dbcp = null;

    public BookMarkService() {
        dbcp = DBConnectionMgr.getInstance();

        if (dbcp.getConnectionCount() == 0) {
            try {
                dbcp.setInitOpenConnections(10);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        bookMarkDao = new BookMarkDao();
    }

    public  ArrayList<TouristSpotDto> selectAllBookMarkByMemberId(Long memberId) {
        ArrayList<TouristSpotDto> list;
        try {
            conn = dbcp.getConnection();
            list = bookMarkDao.selectAllBookMarkByMemberId(conn, memberId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }

        return list;
    }
}
