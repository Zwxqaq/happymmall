package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.vo.ProductDetailVo;


public interface IProductService {
    ServerResponse list(Integer pageNum, Integer pageSize);

    ServerResponse search(Integer pageNum, Integer pageSize, String productName, Integer productId);

    ServerResponse<ProductDetailVo> listProductDetail(Integer productId);

    ServerResponse setStatus(Integer productId, Integer status);

    ServerResponse saveOrUpdate(Product product);

    ServerResponse<PageInfo> selectByKeywordCategoryId(Integer categoryId, String keyword, Integer pageNum, Integer pageSize, String orderBy);

    ServerResponse portalDetail(Integer productId);
}
