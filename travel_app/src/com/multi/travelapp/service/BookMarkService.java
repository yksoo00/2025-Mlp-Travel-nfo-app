package com.multi.travelapp.service;

import com.multi.travelapp.common.DBConnectionMgr;
import com.multi.travelapp.model.dao.BookMarkDao;

import java.sql.Connection;
import java.util.List;

public class BookMarkService {
    private final BookMarkDao bookMarkDao = new BookMarkDao();
    private final DBConnectionMgr dbcp = DBConnectionMgr.getInstance();

    public boolean checkIfFavorited(Long memberId, Long touristSpotId) {
        Connection conn = null;
        try {
            conn = dbcp.getConnection();
            return bookMarkDao.existsFavorite(conn, memberId, touristSpotId);
        } catch (Exception e) {
            throw new RuntimeException("즐겨찾기 여부 확인 실패", e);
        } finally {
            if (conn != null) dbcp.freeConnection(conn);
        }
    }

    public void addFavorite(Long memberId, Long touristSpotId) {
        Connection conn = null;
        try {
            conn = dbcp.getConnection();
            if (!bookMarkDao.existsFavorite(conn, memberId, touristSpotId)) {
                bookMarkDao.insertFavorite(conn, memberId, touristSpotId);
            }
        } catch (Exception e) {
            throw new RuntimeException("즐겨찾기 등록 실패", e);
        } finally {
            if (conn != null) dbcp.freeConnection(conn);
        }
    }

    public void removeFavorite(Long memberId, Long touristSpotId) {
        Connection conn = null;
        try {
            conn = dbcp.getConnection();
            bookMarkDao.deleteFavorite(conn, memberId, touristSpotId);
        } catch (Exception e) {
            throw new RuntimeException("즐겨찾기 삭제 실패", e);
        } finally {
            if (conn != null) dbcp.freeConnection(conn);
        }
    }


    public int getFavoriteCount(Long touristSpotId) {
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

    public List<Long> getFavoritesByMember(Long memberId) {
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
