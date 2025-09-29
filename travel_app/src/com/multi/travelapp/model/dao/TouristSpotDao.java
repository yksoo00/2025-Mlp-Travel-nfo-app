package com.multi.travelapp.model.dao;

import com.multi.travelapp.model.dto.TouristSpotDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TouristSpotDao {

    public ArrayList<TouristSpotDto> selectTourSpotsByRegion(Connection conn, String region) throws Exception {
        ArrayList<TouristSpotDto> list = new ArrayList<>();
        String sql = "SELECT tourist_spot_id,title, district FROM tourist_spot WHERE district = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,region);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    TouristSpotDto dto = new TouristSpotDto();
                    dto.setId(rs.getInt("tourist_spot_id"));
                    dto.setTitle(rs.getString("title"));
                    dto.setDistrict(rs.getString("district"));
                    list.add(dto);
                }
            }
        }

        return list;
    }





    public ArrayList<TouristSpotDto> selectTourSpotsByTitle(Connection conn, String title) {
        ArrayList<TouristSpotDto> list = new ArrayList<>();
        String sql = "SELECT * FROM tourist_spot WHERE title = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,title);


            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    TouristSpotDto dto = new TouristSpotDto();
                    dto.setId(rs.getInt("tourist_spot_id"));
                    dto.setTitle(rs.getString("title"));
                    dto.setDistrict(rs.getString("district"));
                    dto.setDescription(rs.getString("description"));
                    dto.setAddress(rs.getString("address"));
                    dto.setPhone(rs.getString("phone"));
                    list.add(dto);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public ArrayList<TouristSpotDto> selectTourSpotsById(Connection conn, Long memberId, Long touristSpotId) {
        ArrayList<TouristSpotDto> list = new ArrayList<>();
        String sql = "SELECT * FROM tourist_spot WHERE tourist_spot_id = ?";


        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1,touristSpotId);


            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    TouristSpotDto dto = new TouristSpotDto();
                    dto.setId(rs.getInt("tourist_spot_id"));
                    dto.setTitle(rs.getString("title"));
                    dto.setDistrict(rs.getString("district"));
                    dto.setDescription(rs.getString("description"));
                    dto.setAddress(rs.getString("address"));
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
