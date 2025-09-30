package com.multi.travelapp.service;


import com.multi.travelapp.common.DBConnectionMgr;
import com.multi.travelapp.model.dao.TouristSpotDao;
import com.multi.travelapp.model.dto.TouristSpotDto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class TouristService {
    private final TouristSpotDao touristSpotDao;
    DBConnectionMgr dbcp = null;
    Connection conn = null;

    public TouristService(){
        dbcp = DBConnectionMgr.getInstance();
        if(dbcp.getConnectionCount() == 0){
            try{
                dbcp.setInitOpenConnections(10);
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        touristSpotDao = new TouristSpotDao();
    }

    public int insertTourist(TouristSpotDto touristSpotDto) {
        int result = 0;
        try {
            conn = dbcp.getConnection();

            result = touristSpotDao.insertTourist(conn,touristSpotDto);
            conn.setAutoCommit(false);
            if (result > 0) {
                conn.commit();
            } else {
                conn.rollback();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(conn!=null){
                dbcp.freeConnection(conn);
            }
        }

    return result;
    }

    public ArrayList<TouristSpotDto> selectTourist() {
        ArrayList<TouristSpotDto>  touristSpots;
        try {
            conn = dbcp.getConnection();

            touristSpots = touristSpotDao.selectTourist(conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(conn!=null){
                dbcp.freeConnection(conn);
            }
        }

        return touristSpots;
    }

    public int deleteTourist(Long tourist_spot_id) {
        int result = 0;
        try {
            conn = dbcp.getConnection();
            result = touristSpotDao.deleteTourist(conn,tourist_spot_id);
            conn.setAutoCommit(false);
            if (result > 0) {
                conn.commit();
            } else {
                conn.rollback();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(conn!=null){
                dbcp.freeConnection(conn);
            }
        }
        return result;

    }

    public int UpdateTourist(Long tourist_spot_id, TouristSpotDto touristSpotDto) {
        int result = 0;
        try {
            conn = dbcp.getConnection();
            result = touristSpotDao.updateTourist(conn,touristSpotDto,tourist_spot_id);
            conn.setAutoCommit(false);
            if (result > 0) {
                conn.commit();
            } else {
                conn.rollback();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(conn!=null){
                dbcp.freeConnection(conn);
            }
        }
        return result;
    }
}
