package com.multi.travelapp.service;

import com.multi.travelapp.common.DBConnectionMgr;
import com.multi.travelapp.model.dao.TouristSpotDao;
import com.multi.travelapp.model.dto.TouristSpotDto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class TouristService {

    private final TouristSpotDao touristSpotDao;
    Connection conn=null;
    DBConnectionMgr dbcp=null;

    public TouristService() {
        dbcp = DBConnectionMgr.getInstance();

        if (dbcp.getConnectionCount() == 0) {
            try {
                dbcp.setInitOpenConnections(10);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        touristSpotDao = new TouristSpotDao();
    }

    //장소 아이디로 상세정보 조회
    public ArrayList<TouristSpotDto> selectTouristSpotById(Long memberId, Long touristSpotId) {
        ArrayList<TouristSpotDto> list;
        Connection conn = null;

        try {
            conn = dbcp.getConnection();
            list = touristSpotDao.selectTourSpotsById(conn, memberId, touristSpotId);
        } catch (Exception e) {
            throw new RuntimeException("상세정보 조회 실패", e);
        } finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }

        }
        return list;
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

        //제목별 장소조회
        public ArrayList<TouristSpotDto> selectTourSpotsByTitle(String title) {
            ArrayList<TouristSpotDto> list;
            Connection conn = null;

            try {
                conn = dbcp.getConnection();
                list = touristSpotDao.selectTourSpotsByTitle(conn, title);
            } catch (Exception e) {
                throw new RuntimeException("제목별 관광지 조회 실패", e);
            } finally {
                if (conn != null) {
                    dbcp.freeConnection(conn);
                }
            }
            return list;
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

                //권역별 관광지 조회
                public ArrayList<TouristSpotDto> selectTourSpotsByRegion(String district) {
                    ArrayList<TouristSpotDto> list;
                    Connection conn = null;

                    try {
                        conn = dbcp.getConnection();
                        list = touristSpotDao.selectTourSpotsByRegion(conn, district);
                    } catch (Exception e) {
                        throw new RuntimeException("권역별 관광지 조회 실패", e);
                    } finally {
                        if (conn != null) {
                            dbcp.freeConnection(conn);
                        }
                    }

                    return list;

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
    // page로 관광지 조회
    public ArrayList<TouristSpotDto> selectAllTouristSpotByPage(int page) {
        ArrayList<TouristSpotDto> list = new ArrayList<>();
        Connection conn = null;

        try {
            conn = dbcp.getConnection();
            list = touristSpotDao.selectAllTouristSpotByPage(conn, page);
        } catch (Exception e) {
            throw new RuntimeException("페이지별 관광지 조회 실패", e);
        } finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }

        return list;
    }
}
