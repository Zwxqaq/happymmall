package com.mmall.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.mmall.common.ServerResponse;
import com.mmall.dao.ProductMapper;
import com.mmall.pojo.Product;
import com.mmall.service.IProductService;
import com.mmall.util.FTPUtil;
import com.mmall.util.PropertyUtil;
import com.mmall.vo.ProductListVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service("iProductService")
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ServerResponse list(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> productList = productMapper.selectAll();
        PageInfo pageInfo = new PageInfo(productList);
        List<ProductListVo> productListVos = Lists.newArrayList();
        for (Product productItem : productList) {
            ProductListVo productListVo = assembleProductVoList(productItem);
            productListVos.add(productListVo);
        }
        pageInfo.setList(productListVos);
        return ServerResponse.createBySuccess(pageInfo);
    }

    private ProductListVo assembleProductVoList(Product productItem) {
        ProductListVo productListVo = new ProductListVo();
        productListVo.setId(productItem.getId());
        productListVo.setCategoryId(productItem.getCategoryId());
        productListVo.setName(productItem.getName());
        productListVo.setSubtitle(productItem.getSubtitle());
        productListVo.setMainImage(productItem.getMainImage());
        productListVo.setStatus(productItem.getStatus());
        productListVo.setPrice(productItem.getPrice());
        productListVo.setImageHost(PropertyUtil.getProperty("ftp.server.http.prefix", "http://image.imooc.com/"));
        return productListVo;
    }

    @Override
    public ServerResponse search(Integer pageNum, Integer pageSize, String productName, Integer productId) {
        PageHelper.startPage(pageNum, pageSize);
        if (productId == null && StringUtils.isBlank(productName)) {
            return ServerResponse.createByErrorMsg("请输入搜索的参数");
        }
        if (StringUtils.isNotBlank(productName)) {
            StringBuilder buffer = new StringBuilder();
            productName = buffer.append("%").append(productName).append("%").toString();
        }
        List<Product> products = productMapper.selectByProductNameAndId(productName, productId);
        PageInfo pageInfo = new PageInfo(products);
        if (CollectionUtils.isEmpty(products)) {
            return ServerResponse.createBySuccess(pageInfo);
        }

        List<ProductListVo> productListVos = Lists.newArrayList();
        for (Product productItem : products) {
            ProductListVo productListVo = assembleProductVoList(productItem);
            productListVos.add(productListVo);
        }
        pageInfo.setList(productListVos);
        return ServerResponse.createBySuccess(pageInfo);
    }




}
