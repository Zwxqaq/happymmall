package com.mmall.dao;

import com.mmall.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUsername(String username);

    User checkLoginInfo(@Param("username") String username,@Param("pass") String pass);

    int checkEmail(String checkData);

    String selectQuestion(String username);

    int checkAnswer(@Param("username") String username, @Param("question") String question,
                    @Param("answer") String answer);

    int updateByNameAndPass(@Param("username") String username,@Param("passwordNew") String passwordNew);

    int checkPass(@Param("username") String username,@Param("passwordOld") String passwordOld);

    List<User> selectAllUser();
}