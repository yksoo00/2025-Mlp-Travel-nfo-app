package com.multi.travelapp.service;

import com.multi.travelapp.common.DBConnectionMgr;
import com.multi.travelapp.model.dao.LikesDao;
import com.multi.travelapp.model.dto.TouristSpotDto;

import java.sql.Connection;
import java.util.List;

public class LikesService {
    private final LikesDao likesDao = new LikesDao();
    private final DBConnectionMgr dbcp = DBConnectionMgr.getInstance();

    //memberId와 touristSpotId로 좋아요 등록 여부 확인
    public boolean checkIfLiked(Long memberId, Long touristSpotId) {
        Connection conn = null;
        try {
            conn = dbcp.getConnection();
            return likesDao.existsLike(conn, memberId, touristSpotId);
        } catch (Exception e) {
            throw new RuntimeException("좋아요 여부 확인 실패", e);
        } finally {
            if (conn != null) dbcp.freeConnection(conn);
        }

    }

    // 좋아요 등록
    public void addLike(Long memberId, Long touristSpotId) {
        Connection conn = null;
        try {
            conn = dbcp.getConnection();
            likesDao.insertLike(conn, memberId, touristSpotId);
        } catch (Exception e) {
            throw new RuntimeException("좋아요 등록 실패", e);
        } finally {
            if (conn != null) dbcp.freeConnection(conn);
        }
    }

    // 좋아요 삭제
    public void removeLike(Long memberId, Long touristSpotId) {
        Connection conn = null;
        try {
            conn = dbcp.getConnection();
            likesDao.deleteLike(conn, memberId, touristSpotId);
        } catch (Exception e) {
            throw new RuntimeException("좋아요 삭제 실패", e);
        } finally {
            if (conn != null) dbcp.freeConnection(conn);
        }
    }

    // 좋아요 개수 조회
    public int getLikeCount(Long touristSpotId) {
        Connection conn = null;
        try {
            conn = dbcp.getConnection();
            return likesDao.countLikesByTouristSpotId(conn, touristSpotId);
        } catch (Exception e) {
            throw new RuntimeException("좋아요 개수 조회 실패", e);
        } finally {
            if (conn != null) dbcp.freeConnection(conn);
        }
    }


    // 좋아요 순으로 관광지 조회
    public List<TouristSpotDto> getTouristSpotsOrderByLikes() {
        Connection conn = null;
        try {
            conn = dbcp.getConnection();
            return likesDao.findAllTouristSpotsOrderByLikes(conn);
        } catch (Exception e) {
            throw new RuntimeException("좋아요 순 관광지 조회 실패", e);
        } finally {
            if (conn != null) dbcp.freeConnection(conn);
        }
    }



}
