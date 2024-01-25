package com.example.Repository;

import com.example.entity.BookDTO;
import com.example.entity.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {
    public List<BookDTO> bookList();
    public int bookinsert(BookDTO book);

    public UserDTO userLogin(UserDTO user);

    public int bookDelete(int num);

}

