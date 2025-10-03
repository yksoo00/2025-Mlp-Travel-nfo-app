package org.example;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String jdbcBasic = "jdbc:mysql://localhost:3306?serverTimezone=UTC";
        String jdbc = "jdbc:mysql://localhost:3306/travel_db"
                + "?useSSL=false"
                + "&allowPublicKeyRetrieval=true"
                + "&serverTimezone=UTC";
        String user = "root";
        String password = "1234";
        String csvFilePath = "src/main/resources/travel.csv";

        try (Connection conn = DriverManager.getConnection(jdbcBasic, user, password)) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS travel_db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (Connection conn = DriverManager.getConnection(jdbc, user, password)) {
            Statement stmt = conn.createStatement();

            String createSql = """
                                create table tourist_spot (
                                tourist_spot_id bigint primary key auto_increment,
                                district varchar(50) not null,
                                title varchar(50) not null,
                                description varchar(10000),
                                address varchar(255),
                                phone varchar(100),
                                like_count int unsigned not null default 0
                            );
                    
                    """;
            String createMemberSql = """
create table member (
    member_id bigint primary key auto_increment,
    name varchar(50) not null,
    email varchar(50) not null unique,
    password varchar(255) not null,
    phone varchar(20) not null unique,
    address varchar(255) not null
);
                   
                    """;
            String createReviewSql = """
create table review (
    review_id bigint primary key auto_increment,
    title varchar(50) not null,
    description varchar(255),
    score int not null,
    member_id bigint not null,
    tourist_spot_id bigint not null,
    constraint fk_review_member foreign key (member_id) references member(member_id) on delete cascade,
    constraint fk_review_tourist_spot foreign key (tourist_spot_id) references tourist_spot(tourist_spot_id) on delete cascade
);
                    

                    """;
            String createBookSql = """
create table bookmark (
    bookmark_id bigint primary key auto_increment,
    member_id bigint not null,
    tourist_spot_id bigint not null,
    constraint fk_bookmark_member foreign key (member_id) references member(member_id) on delete cascade,
    constraint fk_bookmark_tourist_spot foreign key (tourist_spot_id) references tourist_spot(tourist_spot_id) on delete cascade,
    unique (member_id, tourist_spot_id) -- 중복 방지 제약 조건 추가
);
                    
                 
                    """;
            String createLikeSql = """
create table likes (
    likes_id bigint primary key auto_increment,
    member_id bigint not null,
    tourist_spot_id bigint not null,
    constraint fk_likes_member foreign key (member_id) references member(member_id) on delete cascade,
    constraint fk_likes_tourist_spot foreign key (tourist_spot_id) references tourist_spot(tourist_spot_id) on delete cascade,
    unique (member_id, tourist_spot_id) -- 중복 방지 제약 조건 추가
);
                    """;
            stmt = conn.createStatement();
            stmt.executeUpdate(createSql);
            stmt.executeUpdate(createMemberSql);
            stmt.executeUpdate(createReviewSql);
            stmt.executeUpdate(createBookSql);
            stmt.executeUpdate(createLikeSql);

            System.out.println("테이블 생성");

            try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
                String[] line;
                reader.readNext();

                String sql = "INSERT INTO tourist_spot (tourist_spot_id, district, title, description, address, phone) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql);

                while ((line = reader.readNext()) != null) {
                    statement.setInt(1, Integer.parseInt(line[0]));
                    statement.setString(2, line[1]);
                    statement.setString(3, line[2]);
                    statement.setString(4, line[3]);
                    statement.setString(5, line[4]);
                    String cleanedPhone = line[5].trim().replaceAll("[^\\p{L}\\p{N}- ]", "");  // 문자, 숫자, 하이픈, 공백만 남김
                    System.out.println("Cleaned phone: " + cleanedPhone + ", Length: " + cleanedPhone.length());
                    statement.setString(6, cleanedPhone);


                    statement.executeUpdate();
                }

                System.out.println("CSV 데이터가 DB에 삽입되었습니다!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}