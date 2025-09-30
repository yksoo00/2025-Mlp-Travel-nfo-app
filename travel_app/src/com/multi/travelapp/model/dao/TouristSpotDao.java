package com.multi.travelapp.model.dao;
import static com.multi.travelapp.common.DBConnectionMgr.getInstance;
import com.multi.travelapp.common.DBConnectionMgr;
import com.multi.travelapp.model.dto.TouristSpotDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.multi.travelapp.model.dto.TouristSpotDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TouristSpotDao {
    public int insertTourist(Connection conn, TouristSpotDto touristSpotDto) {

        PreparedStatement pstmt = null;
        int result = 0;

        String sql = "INSERT INTO TOURIST_SPOT VALUES(null,?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,touristSpotDto.getDistrict());
            pstmt.setString(2,touristSpotDto.getTitle());
            pstmt.setString(3,touristSpotDto.getDescription());
            pstmt.setString(4,touristSpotDto.getAddress());
            pstmt.setString(5,touristSpotDto.getPhone());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBConnectionMgr dbcp = getInstance();
            dbcp.freeConnection(pstmt);
        }
        return result;


    }

    public ArrayList<TouristSpotDto> selectTourist(Connection conn) {
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<TouristSpotDto> touristSpots = new ArrayList<>();
        String sql ="SELECT * FROM tourist_spot";

        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                TouristSpotDto t = new TouristSpotDto();
                t.setTourist_spot_id(rs.getLong("tourist_spot_id"));
                t.setDistrict(rs.getString("district"));
                t.setTitle(rs.getString("title"));
                t.setDescription(rs.getString("description"));
                t.setAddress(rs.getString("address"));
                t.setPhone(rs.getString("phone"));
                touristSpots.add(t);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBConnectionMgr dbcp = getInstance();
            dbcp.freeConnection(stmt,rs);

        }
        return touristSpots;

    }

    public int deleteTourist(Connection conn,Long tourist_spot_id) {
        PreparedStatement pstmt = null;
        int result = 0;

        String sql = "DELETE FROM TOURIST_SPOT WHERE TOURIST_SPOT_ID = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,tourist_spot_id);


            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBConnectionMgr dbcp = getInstance();
            dbcp.freeConnection(pstmt);
        }
        return result;


    }

    public int updateTourist(Connection conn, TouristSpotDto touristSpotDto, Long touristSpotId) {
        PreparedStatement pstmt = null;
        int result = 0;

        String sql = "UPDATE TOURIST_SPOT SET district = ?, title = ?, description = ?, address = ?, phone = ?WHERE TOURIST_SPOT_ID = ?";
        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,touristSpotDto.getDistrict());
            pstmt.setString(2,touristSpotDto.getTitle());
            pstmt.setString(3,touristSpotDto.getDescription());
            pstmt.setString(4,touristSpotDto.getAddress());
            pstmt.setString(5,touristSpotDto.getPhone());
            pstmt.setLong(6,touristSpotId);


            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBConnectionMgr dbcp = getInstance();
            dbcp.freeConnection(pstmt);
        }
        return result;

    }
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
}
