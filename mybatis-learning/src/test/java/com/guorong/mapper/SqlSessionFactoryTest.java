package com.guorong.mapper;

import com.guorong.entity.Book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

class SqlSessionFactoryTest {

    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    void test() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            List<Book> books = mapper.selectList();
            Assertions.assertNotNull(books);
        }
    }

}
