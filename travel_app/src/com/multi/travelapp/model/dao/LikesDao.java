package com.multi.travelapp.model.dao;

import com.multi.travelapp.common.DBConnectionMgr;
import com.multi.travelapp.model.dto.TouristSpotDto;

import javax.print.attribute.standard.OrientationRequested;
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
    public int insertLike(Connection conn, Long memberId, Long touristSpotId)  {
        int result;
        PreparedStatement pstmt=null;
        String sql = "INSERT INTO likes (member_id, tourist_spot_id) VALUES (?, ?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, memberId);
            pstmt.setLong(2, touristSpotId);
            result=pstmt.executeUpdate();
            pstmt.close();

            // 좋아요 등록시 tourist_spot의 좋아요 수 컬럼 1 증가
            String updateSql="UPDATE tourist_spot SET like_count = like_count + 1 WHERE tourist_spot_id = ?";
            pstmt = conn.prepareStatement(updateSql);
            pstmt.setLong(1, touristSpotId);
            pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBConnectionMgr dbcp=DBConnectionMgr.getInstance();
            dbcp.freeConnection(pstmt);
        }


        return result;


    }

    // 3. 좋아요 삭제
    public int deleteLike(Connection conn, Long memberId, Long touristSpotId){
        int result=0;
        PreparedStatement pstmt=null;
        DBConnectionMgr dbcp=DBConnectionMgr.getInstance();

        try{
            //like테이블에서 삭제
            String sql = "DELETE FROM likes WHERE member_id = ? AND tourist_spot_id = ?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setLong(1, memberId);
            pstmt.setLong(2, touristSpotId);
            result=pstmt.executeUpdate();
            pstmt.close();

            if(result>0){ //삭제 성공했을때만 감소
                String updateSql="UPDATE tourist_spot SET like_count = CASE WHEN like_count > 0 THEN like_count - 1 ELSE 0 END WHERE tourist_spot_id = ?";
                pstmt=conn.prepareStatement(updateSql);
                pstmt.setLong(1, touristSpotId);
                pstmt.executeUpdate();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            dbcp.freeConnection(pstmt);
        }

        return result;


    }

    // 4. 특정 관광지의 좋아요 총 개수 조회
    // 조회할때마다 tourist_spot_id에 해당하는 컬럼을 모두 count(*)해오는 방식(부하 증가) => tourist_spot의 like_count컬럼만을 조회하는 방식으로 수정
    public int countLikesByTouristSpotId(Connection conn, Long touristSpotId) {
        String sql="SELECT like_count FROM tourist_spot WHERE tourist_spot_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, touristSpotId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("like_count");
                }
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 좋아요 순으로 목록 조회
    public List<TouristSpotDto> findAllTouristSpotsOrderByLikes(Connection conn){
        List<TouristSpotDto> list = new ArrayList<>();

        String sql = "SELECT tourist_spot_id, title, district, description, address, phone, like_count " +
                "FROM tourist_spot " +
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
                dto.setLikeCount(rs.getInt("like_count"));

                list.add(dto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

}
