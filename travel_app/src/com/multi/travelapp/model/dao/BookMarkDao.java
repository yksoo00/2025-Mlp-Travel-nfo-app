package com.multi.travelapp.model.dao;

import com.multi.travelapp.common.DBConnectionMgr;
import com.multi.travelapp.model.dto.TouristSpotDto;

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
    public int insertFavorite(Connection conn, Long memberId, Long touristSpotId){
        int result;
        PreparedStatement pstmt=null;
        String sql = "INSERT INTO bookmark (member_id, tourist_spot_id) VALUES (?, ?)";

        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setLong(1, memberId);
            pstmt.setLong(2, touristSpotId);

            result=pstmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            DBConnectionMgr dbcp=DBConnectionMgr.getInstance();
            dbcp.freeConnection(pstmt);
        }

        return result;
    }


    // 즐겨찾기 삭제
    public int deleteFavorite(Connection conn, Long memberId, Long touristSpotId) throws SQLException {
        int result;

        String sql = "DELETE FROM bookmark WHERE member_id = ? AND tourist_spot_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, memberId);
            pstmt.setLong(2, touristSpotId);
            result=pstmt.executeUpdate();
        }

        return result;
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



    // 특정 사용자의 즐겨찾기 관광지 상세 목록 조회
    public List<TouristSpotDto> findFavoritesByMemberId(Connection conn, Long memberId) throws SQLException {
        List<TouristSpotDto> list = new ArrayList<>();

        String sql = "SELECT ts.tourist_spot_id, ts.title, ts.district, ts.description, ts.address, ts.phone " +
                "FROM bookmark b " +
                "JOIN tourist_spot ts ON b.tourist_spot_id = ts.tourist_spot_id " +
                "WHERE b.member_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, memberId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    TouristSpotDto dto = new TouristSpotDto();
                    dto.setTouristSpotId(rs.getLong("tourist_spot_id"));
                    dto.setTitle(rs.getString("title"));
                    dto.setDistrict(rs.getString("district"));
                    dto.setDescription(rs.getString("description"));
                    dto.setAddress(rs.getString("address"));
                    dto.setPhone(rs.getString("phone"));
                    list.add(dto);
                }
            }
        }
        return list;
    }










}
