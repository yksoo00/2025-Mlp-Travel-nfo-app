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
        String jdbc = "jdbc:mysql://localhost:3306/travel_db?serverTimezone=UTC";
        String user = "root";
        String password = "rudtn315";
        String csvFilePath = "travel_import/src/main/resources/travel.csv";

        try (Connection conn = DriverManager.getConnection(jdbcBasic, user, password)) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS travel_db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (Connection conn = DriverManager.getConnection(jdbc, user, password)) {
            Statement stmt = conn.createStatement();

            String createSql = """
                    CREATE TABLE IF NOT EXISTS tourist_spot (
                        tourist_spot_id INT PRIMARY KEY,
                        district VARCHAR(50),
                        title VARCHAR(255),
                        description TEXT,
                        address VARCHAR(255),
                        phone VARCHAR(255)
                    )
                    """;
            stmt = conn.createStatement();
            stmt.executeUpdate(createSql);
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