package com.multi.travelapp.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookMarkDao {

    // 즐겨찾기 존재 여부
    public boolean existsFavorite(Connection conn, Long memberId, Long touristSpotId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM bookmark WHERE member_id = ? AND tourist_spot_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, memberId);
            pstmt.setLong(2, touristSpotId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    // 즐겨찾기 등록
    public void insertFavorite(Connection conn, Long memberId, Long touristSpotId) throws SQLException {
        String sql = "INSERT INTO bookmark (member_id, tourist_spot_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, memberId);
            pstmt.setLong(2, touristSpotId);
            pstmt.executeUpdate();
        }
    }


    // 즐겨찾기 삭제
    public void deleteFavorite(Connection conn, Long memberId, Long touristSpotId) throws SQLException {
        String sql = "DELETE FROM bookmark WHERE member_id = ? AND tourist_spot_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, memberId);
            pstmt.setLong(2, touristSpotId);
            pstmt.executeUpdate();
        }
    }

    // 특정 관광지의 즐겨찾기 수
    public int countFavoritesByTouristSpotId(Connection conn, Long touristSpotId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM bookmark WHERE tourist_spot_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, touristSpotId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }


    // 특정 사용자의 즐겨찾기 목록
    public List<Long> findFavoritesByMemberId(Connection conn, Long memberId) throws SQLException {
        List<Long> list = new ArrayList<>();
        String sql = "SELECT tourist_spot_id FROM bookmark WHERE member_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, memberId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getLong("tourist_spot_id"));
                }
            }
        }
        return list;
    }






}
