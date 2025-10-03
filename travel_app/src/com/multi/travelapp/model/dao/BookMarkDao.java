package com.multi.travelapp.model.dao;

import com.multi.travelapp.common.DBConnectionMgr;
import com.multi.travelapp.model.dto.TouristSpotDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

import java.util.List;

public class BookMarkDao {

    // 즐겨찾기 존재 여부
    public boolean existsBookMark(Connection conn, Long memberId, Long touristSpotId) throws SQLException {
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
    public int insertBookMark(Connection conn, Long memberId, Long touristSpotId) {
        int result;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO bookmark (member_id, tourist_spot_id) VALUES (?, ?)";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, memberId);
            pstmt.setLong(2, touristSpotId);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnectionMgr dbcp = DBConnectionMgr.getInstance();
            dbcp.freeConnection(pstmt);
        }

        return result;
    }


    // 즐겨찾기 삭제
    public int deleteBookMark(Connection conn, Long memberId, Long touristSpotId) throws SQLException {
        int result;

        String sql = "DELETE FROM bookmark WHERE member_id = ? AND tourist_spot_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, memberId);
            pstmt.setLong(2, touristSpotId);
            result = pstmt.executeUpdate();
        }

        return result;
    }

    // 특정 관광지의 즐겨찾기 수
    public int countBookMarkByTouristSpotId(Connection conn, Long touristSpotId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM bookmark WHERE tourist_spot_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, touristSpotId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }


    // 내가 즐겨찾기한 관광지 개수
    public int countBookmarksByMemberId(Connection conn, Long memberId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM bookmark WHERE member_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, memberId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }

    //memeberId별로 즐겨찾기한 목록 조회
    public ArrayList<TouristSpotDto> selectAllBookMarkByMemberId(Connection conn, Long memberId) {
        ArrayList<TouristSpotDto> list = new ArrayList<>();

        String sql = "SELECT a.*\n" +
                "FROM tourist_spot a\n" +
                "INNER JOIN bookmark b \n" +
                "    ON a.tourist_spot_id = b.tourist_spot_id\n" +
                "WHERE b.member_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, memberId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    TouristSpotDto dto = new TouristSpotDto();
                    dto.setTourist_spot_id(rs.getLong("tourist_spot_id"));
                    dto.setTitle(rs.getString("title"));
                    dto.setDistrict(rs.getString("district"));
                    dto.setAddress(rs.getString("address"));
                    dto.setDescription(rs.getString("description"));
                    dto.setPhone(rs.getString("phone"));
                    list.add(dto);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;


    }


}




