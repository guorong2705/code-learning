package com.guorong.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.guorong.mp.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper extends BaseMapper<Student> {

    IPage<Student> findLikePageVo(@Param("page") IPage<Student> page, @Param("student") Student student);
}
