package com.example.Repository;

import com.example.entity.BookDTO;
import com.example.entity.UserDTO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class BookMyBatisDAO {
    //DB 연걸 -> config.xml(환경설정파일)->API read 연결 작업을 대신 해주면 된다.
    private static SqlSessionFactory sqlSessionFactory; //connection pool

    static{ //초기화 블럭
        try {
            String resource = "mybatis-config/config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public List<BookDTO> bookList(){
        SqlSession session = sqlSessionFactory.openSession();
         List<BookDTO> list = session.selectList("bookList");
         session.close();
         return list;
    }
    public int bookinsert(BookDTO book){
        SqlSession session = sqlSessionFactory.openSession();
        int cnt = session.insert("bookinsert", book);
        session.commit();
        session.close();
        return cnt;
    }

    public UserDTO userLogin(UserDTO dto){
        SqlSession session = sqlSessionFactory.openSession();
        UserDTO user = session.selectOne("userLogin", dto);
        session.close();
        return user;
    }

    public int bookDelete(int num) {
        SqlSession session = sqlSessionFactory.openSession();
        int cnt = session.delete("bookDelete", num);
        session.commit();
        session.close();
        return cnt;
    }
}
