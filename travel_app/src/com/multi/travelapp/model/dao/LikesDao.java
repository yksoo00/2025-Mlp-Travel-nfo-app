package com.multi.travelapp.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikesDao {

    // 1. 좋아요 존재 여부 확인
    public boolean existsLike(Connection conn, Long memberId, Long touristSpotId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM likes WHERE member_id = ? AND tourist_spot_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, memberId);
            pstmt.setLong(2, touristSpotId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
                return false;
            }
        }
    }

    // 2. 좋아요 등록
    public void insertLike(Connection conn, Long memberId, Long touristSpotId) throws SQLException {
        String sql = "INSERT INTO likes (member_id, tourist_spot_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, memberId);
            pstmt.setLong(2, touristSpotId);
            pstmt.executeUpdate();
        }
    }

    // 3. 좋아요 삭제
    public void deleteLike(Connection conn, Long memberId, Long touristSpotId) throws SQLException {
        String sql = "DELETE FROM likes WHERE member_id = ? AND tourist_spot_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, memberId);
            pstmt.setLong(2, touristSpotId);
            pstmt.executeUpdate();
        }
    }

    // 4. 특정 관광지의 좋아요 총 개수 조회
    public int countLikesByTouristSpotId(Connection conn, Long touristSpotId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM likes WHERE tourist_spot_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, touristSpotId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return 0;
            }
        }
    }
}
