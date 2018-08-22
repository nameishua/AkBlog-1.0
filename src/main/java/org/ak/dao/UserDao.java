package org.ak.dao;

import org.apache.ibatis.annotations.Param;
import org.ak.entity.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao {
    public User getUser(@Param("username") String username, @Param("password") String password);
}
