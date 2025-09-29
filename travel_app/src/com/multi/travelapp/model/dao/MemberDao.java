package com.multi.travelapp.model.dao;

import com.multi.travelapp.common.DBConnectionMgr;
import com.multi.travelapp.model.dto.MemberDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MemberDao {
    public int insertMember(Connection conn, MemberDto memberDto) {
        int result = 0;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO member (name, email, password, phone, address) VALUES (?, ?, ?, ?, ?)";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, memberDto.getName());
            pstmt.setString(2, memberDto.getEmail());
            pstmt.setString(3, memberDto.getPassword());
            pstmt.setString(4, memberDto.getPhone());
            pstmt.setString(5, memberDto.getAddress());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ignored) {
                }
            }
        }

        return result;
    }

    public MemberDto selectMemberByEmail(Connection conn, String email, String password) {
        MemberDto memberDto = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM member WHERE email = ? and password = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                memberDto = new MemberDto();

                memberDto.setMemberId(rs.getLong("member_id"));
                memberDto.setEmail(rs.getString("email"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            DBConnectionMgr dbcp = DBConnectionMgr.getInstance();
            dbcp.freeConnection(pstmt, rs);

        }
        return memberDto;

    }
}

