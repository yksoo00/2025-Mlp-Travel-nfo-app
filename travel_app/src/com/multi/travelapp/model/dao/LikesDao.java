package com.multi.travelapp.model.dao;

import com.multi.travelapp.model.dto.TouristSpotDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    // 좋아요 순으로 목록 조회
    public List<TouristSpotDto> findAllTouristSpotsOrderByLikes(Connection conn) throws SQLException {
        List<TouristSpotDto> list = new ArrayList<>();

        String sql = "SELECT ts.tourist_spot_id, ts.title, ts.district, ts.description, ts.address, ts.phone, " +
                "       COUNT(l.likes_id) AS like_count " +
                "FROM tourist_spot ts " +
                "LEFT JOIN likes l ON ts.tourist_spot_id = l.tourist_spot_id " +
                "GROUP BY ts.tourist_spot_id " +
                "ORDER BY like_count DESC";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                TouristSpotDto dto = new TouristSpotDto();
                dto.setTouristSpotId(rs.getLong("tourist_spot_id"));
                dto.setTitle(rs.getString("title"));
                dto.setDistrict(rs.getString("district"));
                dto.setDescription(rs.getString("description"));
                dto.setAddress(rs.getString("address"));
                dto.setPhone(rs.getString("phone"));
                dto.setLikeCount(rs.getInt("like_count")); // DTO에 likeCount 필드 추가 필요

                list.add(dto);
            }
        }

        return list;
    }

}
