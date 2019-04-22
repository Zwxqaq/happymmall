package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.vo.CartVo;

public interface ICartService {
    ServerResponse<CartVo> list(Integer userId);

    ServerResponse<CartVo> add(Integer id, Integer productId, Integer count);

    ServerResponse<CartVo> update(Integer id, Integer productId, Integer count);

    ServerResponse<CartVo> delete(Integer id, String productIds);

    ServerResponse<CartVo> selOrUnsel(Integer id, Integer productId, Integer checked);

    ServerResponse getProductCount(Integer id);

    ServerResponse<CartVo> selOrNotSelAll(Integer id, Integer checked);
}
