package com.multi.travelapp.model.dao;

import com.multi.travelapp.common.DBConnectionMgr;
import com.multi.travelapp.model.dto.TouristSpotDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.multi.travelapp.common.DBConnectionMgr.getInstance;

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

    // page로 관광지 조회
    public ArrayList<TouristSpotDto> selectAllTouristSpotByPage(Connection conn, int page) {

        ArrayList<TouristSpotDto> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from tourist_spot limit ?, 10";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, page);
            rs = pstmt.executeQuery();

            while(rs.next()){
                TouristSpotDto touristSpotDto = new TouristSpotDto();
                touristSpotDto.setId(rs.getInt("tourist_spot_id"));
                touristSpotDto.setTitle(rs.getString("title"));
                touristSpotDto.setDescription(rs.getString("description"));
                touristSpotDto.setAddress(rs.getString("address"));
                touristSpotDto.setPhone(rs.getString("phone"));
                touristSpotDto.setDistrict(rs.getString("district"));

                list.add(touristSpotDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnectionMgr dbcp = getInstance();
            dbcp.freeConnection(pstmt, rs);
        }

        return list;

    }

}
