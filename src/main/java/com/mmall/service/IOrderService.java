package com.mmall.service;

import com.mmall.common.ServerResponse;

import java.util.Map;

public interface IOrderService {
    ServerResponse pay(Integer id, String orderNo, String path);

    ServerResponse aliCallback(Map<String, String> params);

    ServerResponse queryOrderStatus(Integer id, String orderNo);
}
