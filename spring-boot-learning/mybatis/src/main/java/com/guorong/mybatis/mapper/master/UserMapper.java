package com.guorong.mybatis.mapper.master;


import com.guorong.mybatis.entity.master.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    /**
     * 查询全部用户
     *
     * @return
     */
    List<User> selectAll();

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    User selectById(@Param("id") Long id);


    /**
     * 插入用户并设置对象到插入实例中
     *
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 批量插入数据，并返回主键
     *
     * @param list
     * @return
     */
    int insertBatch(@Param("list") List<User> list);

}
