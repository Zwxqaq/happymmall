package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Shipping;

public interface IShippingService {
    ServerResponse add(int userId, Shipping shipping);

    ServerResponse del(Integer id, int shippingId);

    ServerResponse update(Integer id, Shipping shipping);

    ServerResponse<Shipping> select(int userId, int shippingId);

    ServerResponse<PageInfo> list(Integer id, int pageSize, int pageNum);
}
