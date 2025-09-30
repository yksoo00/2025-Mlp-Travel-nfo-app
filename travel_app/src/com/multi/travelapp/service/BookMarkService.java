package com.multi.travelapp.service;

import com.multi.travelapp.common.DBConnectionMgr;
import com.multi.travelapp.model.dao.BookMarkDao;
import com.multi.travelapp.model.dto.TouristSpotDto;

import java.sql.Connection;
import java.util.List;

public class BookMarkService {
    private final BookMarkDao bookMarkDao = new BookMarkDao();
    private final DBConnectionMgr dbcp = DBConnectionMgr.getInstance();

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


    public List<TouristSpotDto> getMyBookMarkById(Long memberId) {
        Connection conn = null;
        try {
            conn = dbcp.getConnection();
            return bookMarkDao.findFavoritesByMemberId(conn, memberId);
        } catch (Exception e) {
            throw new RuntimeException("즐겨찾기 목록 조회 실패", e);
        } finally {
            if (conn != null) dbcp.freeConnection(conn);
        }
    }
}
