package com.mmall.service;

import com.mmall.common.ServerResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IProductService {
    ServerResponse list(Integer pageNum, Integer pageSize);

    ServerResponse search(Integer pageNum, Integer pageSize, String productName, Integer productId);

}
