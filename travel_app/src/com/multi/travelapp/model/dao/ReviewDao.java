package com.multi.travelapp.model.dao;

import com.multi.travelapp.common.DBConnectionMgr;
import com.multi.travelapp.model.dto.ReviewDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.multi.travelapp.common.DBConnectionMgr.getInstance;

public class ReviewDao {

    public ReviewDao() {
    }

    // ------------------------------- 리뷰 서비스 ------------------------------
    // memberId로 리뷰 조회
    public ArrayList<ReviewDto> selectAllReviewByMemberId(Connection conn, Long memberId) {

        ArrayList<ReviewDto> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from review where member_id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, memberId);
            rs = pstmt.executeQuery();

            while(rs.next()){
                ReviewDto reviewDto = new ReviewDto();
                reviewDto.setReviewId(rs.getLong("review_id"));
                reviewDto.setTitle(rs.getString("title"));
                reviewDto.setDescription(rs.getString("description"));
                reviewDto.setScore(rs.getInt("score"));
                reviewDto.setMemberId(rs.getLong("member_id"));
                reviewDto.setTouristSpotId(rs.getLong("tourist_spot_id"));

                list.add(reviewDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnectionMgr dbcp = getInstance();
            dbcp.freeConnection(pstmt, rs);
        }

        return list;

    }

    // touristSpotId로 리뷰 조회
    public ArrayList<ReviewDto> selectAllReviewByTouristSpotId(Connection conn, Long touristSpotId) {
        ArrayList<ReviewDto> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from review where tourist_spot_id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, touristSpotId);
            rs = pstmt.executeQuery();

            while(rs.next()){
                ReviewDto reviewDto = new ReviewDto();
                reviewDto.setReviewId(rs.getLong("review_id"));
                reviewDto.setTitle(rs.getString("title"));
                reviewDto.setDescription(rs.getString("description"));
                reviewDto.setScore(rs.getInt("score"));
                reviewDto.setMemberId(rs.getLong("member_id"));
                reviewDto.setTouristSpotId(rs.getLong("tourist_spot_id"));

                list.add(reviewDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnectionMgr dbcp = getInstance();
            dbcp.freeConnection(pstmt, rs);
        }

        return list;
    }

    // memberId, reviewId로 리뷰 등록
    public int insertReview(Connection conn, Long memberId, ReviewDto reviewDto) {
        PreparedStatement pstmt = null;
        int result = 0;

        String sql = "insert into review values (null, ?, ?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, reviewDto.getTitle());
            pstmt.setString(2, reviewDto.getDescription());
            pstmt.setInt(3, reviewDto.getScore());
            pstmt.setLong(4, reviewDto.getMemberId());
            pstmt.setLong(5, reviewDto.getTouristSpotId());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnectionMgr dbcp = getInstance();
            dbcp.freeConnection(pstmt);
        }

        return result;
    }

    // memberId, reviewId로 리뷰 삭제
    public int deleteReview(Connection conn, Long memberId, Long reviewId) {
        PreparedStatement pstmt = null;
        int result = 0;

        String sql = "delete from review where member_id = ? and review_id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, memberId);
            pstmt.setLong(2, reviewId);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnectionMgr dbcp = getInstance();
            dbcp.freeConnection(pstmt);
        }

        return result;
    }

    // reviewId로 리뷰 수정
    public int updateReview(Connection conn, Long reviewId, String title, String description, int score) {
        PreparedStatement pstmt = null;
        int result = 0;

        String sql = "update review set title = ?, description = ?, score = ? where review_id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setInt(3, score);
            pstmt.setLong(4, reviewId);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnectionMgr dbcp = getInstance();
            dbcp.freeConnection(pstmt);
        }

        return result;
    }

}
