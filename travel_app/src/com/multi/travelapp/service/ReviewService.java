package com.multi.travelapp.service;

import com.multi.travelapp.common.DBConnectionMgr;
import com.multi.travelapp.model.dao.ReviewDao;
import com.multi.travelapp.model.dto.ReviewDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReviewService {
    private final ReviewDao reviewDao;
    Connection conn = null;
    DBConnectionMgr dbcp = null;

    public ReviewService() {
        dbcp = DBConnectionMgr.getInstance();

        if (dbcp.getConnectionCount() == 0) {
            try {
                dbcp.setInitOpenConnections(10);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        reviewDao = new ReviewDao();
    }

    // ------------------------------- 리뷰 서비스 ------------------------------
    // memberId로 리뷰 조회
    public ArrayList<ReviewDto> selectAllReviewByMemberId(Long memberId) {
        ArrayList<ReviewDto> list;
        try {
            conn = dbcp.getConnection();
            list = reviewDao.selectAllReviewByMemberId(conn, memberId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }

        return list;
    }

    // touristSpotId로 리뷰 조회
    public ArrayList<ReviewDto> selectAllReviewByTouristSpotId(Long touristSpotId) {
        ArrayList<ReviewDto> list;
        try {
            conn = dbcp.getConnection();
            list = reviewDao.selectAllReviewByTouristSpotId(conn, touristSpotId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }

        return list;
    }

    // memberId, reviewDto로 리뷰 등록
    public int insertReview(Long memberId, ReviewDto reviewDto) {
        int result;
        try {
            conn = dbcp.getConnection();
            conn.setAutoCommit(false); // 오토커밋 false
            result = reviewDao.insertReview(conn, memberId, reviewDto);
            if (result > 0) {
                conn.commit();
            } else {
                conn.rollback();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }

        return result;

    }

    // memberId, reviewId로 리뷰 삭제
    public int deleteReview(Long memberId, Long reviewId) {
        int result;
        try {
            conn = dbcp.getConnection();
            conn.setAutoCommit(false); // 오토커밋 false
            result = reviewDao.deleteReview(conn, memberId, reviewId);
            if (result > 0) {
                conn.commit();
            } else {
                conn.rollback();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }

        return result;
    }

    // reviewId로 리뷰 수정
    public int updateReview(Long reviewId, String title, String description, int score) {
        int result;
        try {
            conn = dbcp.getConnection();
            conn.setAutoCommit(false); // 오토커밋 false
            result = reviewDao.updateReview(conn, reviewId, title, description, score);
            if (result > 0) {
                conn.commit();
            } else {
                conn.rollback();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }

        return result;
    }
}
