package com.mmall.dao;

import com.mmall.pojo.Shipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);

    int selectByShippingId(int shippingId);

    int updateByUserIdAndIdSelective(Shipping shipping);

    Shipping selectByUserIdAndId(@Param("userId") int userId,@Param("shippingId") int shippingId);

    List<Shipping> selectAll(Integer id);
}