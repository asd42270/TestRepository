package com.example.Repository;

import com.example.entity.BookDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// WEB: MVC Framework->변형 -> Spring Web MVC -> Spring boot
public class BookDAO { //JDBC->Framework도입(MyBatis)->Hibernate->JPA
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public Connection getConncet(){
        String url = "jdbc:mysql://localhost:3306/hellodb";
        String username = "root";
        String password = "0000";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username,password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public List<BookDTO> bookList(){
        List<BookDTO> list = new ArrayList<>();
        String sql = "select * from book order by title desc";
        conn = getConncet();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                int num = rs.getInt("num");
                String title = rs.getString("title");
                int price = rs.getInt("price");
                String name = rs.getString("name");
                int page = rs.getInt("page");
                BookDTO dto = new BookDTO(num, title, price, name, page);
                list.add(dto);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public int bookInsert(BookDTO bookDTO){
        String SQL = "insert into books(title, price, name, page) values(?, ?, ?, ?)";
        conn = getConncet();
        int cnt = -1;
        try {
            ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, bookDTO.getTitle());
            ps.setInt(2, bookDTO.getPrice());
            ps.setString(3, bookDTO.getName());
            ps.setInt(4, bookDTO.getPage());
            cnt = ps.executeUpdate();
            // 생성된 키(증가된 'num' 값 포함) 검색
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                bookDTO.setNum(generatedKeys.getInt(1)); // BookDTO에 'num' 설정
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbClose();
        }
        return cnt; // 1  or -1
    }

    private void dbClose() {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}


