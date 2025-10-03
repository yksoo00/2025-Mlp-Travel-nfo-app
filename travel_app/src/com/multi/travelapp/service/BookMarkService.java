package com.multi.travelapp.service;

import com.multi.travelapp.common.DBConnectionMgr;
import com.multi.travelapp.model.dao.BookMarkDao;

import com.multi.travelapp.model.dao.ReviewDao;
import com.multi.travelapp.model.dto.TouristSpotDto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookMarkService {
    private BookMarkDao bookMarkDao = new BookMarkDao();
    DBConnectionMgr dbcp = null;
    Connection conn = null;

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

  
    // memberId와 touristSpotId로 즐겨찾기 등록 여부 확인
    public boolean checkIfBookMarked(Long memberId, Long touristSpotId) {
        Connection conn = null;

        try {
            conn = dbcp.getConnection();
            conn.setAutoCommit(false);

            return bookMarkDao.existsBookMark(conn, memberId, touristSpotId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) dbcp.freeConnection(conn);
        }
    }


    // 즐겨 찾기 등록
    public void addBookMark(Long memberId, Long touristSpotId) {

        Connection conn = null;
        int result;

        try {
            conn = dbcp.getConnection();
            conn.setAutoCommit(false);
            result=bookMarkDao.insertBookMark(conn, memberId, touristSpotId);

            if(result>0){
                conn.commit();
            }else{
                conn.rollback();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) dbcp.freeConnection(conn);
        }
    }


    // 즐겨찾기 삭제
    public int removeBookMark(Long memberId, Long touristSpotId) {
        Connection conn = null;
        int result = 0;
        try {
            conn = dbcp.getConnection();
            conn.setAutoCommit(false);
            result=bookMarkDao.deleteBookMark(conn, memberId, touristSpotId);

            if(result>0){
                conn.commit();
            }else{
                conn.rollback();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) dbcp.freeConnection(conn);
        }

        return result;

    }


    // member별 즐겨찾기 개수조회
    public int getBookmarkCountByMember(Long memberId) {
        Connection conn = null;
        try {
            conn = dbcp.getConnection();
            return bookMarkDao.countBookmarksByMemberId(conn, memberId);
        } catch (Exception e) {
            throw new RuntimeException("회원별 즐겨찾기 개수 조회 실패", e);
        } finally {
            if (conn != null) dbcp.freeConnection(conn);
        }
    }





    // MemberId로 즐겨찾기 조회
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
