package com.multi.travelapp.service;

import com.multi.travelapp.common.DBConnectionMgr;
import com.multi.travelapp.model.dao.BookMarkDao;

import com.multi.travelapp.model.dao.ReviewDao;
import com.multi.travelapp.model.dto.TouristSpotDto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookMarkService {
    private final BookMarkDao bookMarkDao = new BookMarkDao();
    private final DBConnectionMgr dbcp = DBConnectionMgr.getInstance();
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

  


    public boolean checkIfFavorited(Long memberId, Long touristSpotId) {
        Connection conn = null;
        int result;

        try {
            conn = dbcp.getConnection();
            conn.setAutoCommit(false);

            return bookMarkDao.existsFavorite(conn, memberId, touristSpotId);
        } catch (Exception e) {
            throw new RuntimeException("즐겨찾기 여부 확인 실패", e);
        } finally {
            if (conn != null) dbcp.freeConnection(conn);
        }
    }

    public void addFavorite(Long memberId, Long touristSpotId) {

        Connection conn = null;
        int result;

        try {
            conn = dbcp.getConnection();
            conn.setAutoCommit(false);
            result=bookMarkDao.insertFavorite(conn, memberId, touristSpotId);

            if(result>0){
                conn.commit();
            }else{
                conn.rollback();
            }

        } catch (Exception e) {
            throw new RuntimeException("즐겨찾기 등록 실패", e);
        } finally {
            if (conn != null) dbcp.freeConnection(conn);
        }
    }

    public int removeFavorite(Long memberId, Long touristSpotId) {
        Connection conn = null;
        int result = 0;
        try {
            conn = dbcp.getConnection();
            result=bookMarkDao.deleteFavorite(conn, memberId, touristSpotId);
            conn.setAutoCommit(false);
            if(result>0){
                conn.commit();
            }else{
                conn.rollback();
            }
        } catch (Exception e) {
            throw new RuntimeException("즐겨찾기 삭제 실패", e);
        } finally {
            if (conn != null) dbcp.freeConnection(conn);
        }

        return result;

    }


    public int getBookMarkedCount(Long touristSpotId) {
        Connection conn = null;
        try {
            conn = dbcp.getConnection();
            return bookMarkDao.countFavoritesByTouristSpotId(conn, touristSpotId);
        } catch (Exception e) {
            throw new RuntimeException("즐겨찾기 개수 조회 실패", e);
        } finally {
            if (conn != null) dbcp.freeConnection(conn);
        }
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
