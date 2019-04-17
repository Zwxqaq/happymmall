package com.mmall.service;

import com.mmall.common.ServerResponse;

public interface ICategoryService {
    ServerResponse selectByCategoryId(int categoryId);

    ServerResponse addCategory(Integer parentId, String categoryName);

    ServerResponse setCategoryName(Integer categoryId, String categoryName);

    ServerResponse getDeepCategory(Integer categoryId);
}
