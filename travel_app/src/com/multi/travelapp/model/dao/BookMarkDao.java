package com.multi.travelapp.model.dao;

import com.multi.travelapp.model.dto.TouristSpotDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookMarkDao {

    public ArrayList<TouristSpotDto> selectAllBookMarkByMemberId(Connection conn, Long memberId) throws Exception {
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
        }
        return list;
    }
}




