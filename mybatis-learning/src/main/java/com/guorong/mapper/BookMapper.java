package com.guorong.mapper;

import com.guorong.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> selectList();
}
