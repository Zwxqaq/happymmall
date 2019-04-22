package com.mmall.dao;

import com.mmall.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    List<Cart> selectByUserId(Integer userId);

    int selectCheckedByUserId(Integer userId);

    int deleteByIdsAndUserId(@Param("productList") List<String> productList,@Param("id") Integer id);

    int updateByCheckedUserAndProductId(Cart selOrNotCart);

    int selectCountByUserId(Integer id);

    int updateAllByUserId(@Param("id") Integer id, @Param("checked") Integer checked);
}