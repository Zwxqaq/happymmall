package com.mmall.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.dao.ProductMapper;
import com.mmall.pojo.Category;
import com.mmall.pojo.Product;
import com.mmall.service.ICategoryService;
import com.mmall.service.IProductService;
import com.mmall.util.DateTimeUtil;
import com.mmall.util.PropertyUtil;
import com.mmall.vo.ProductDetailVo;
import com.mmall.vo.ProductListVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service("iProductService")
public class ProductServiceImpl implements IProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ICategoryService iCategoryService;

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

    private ProductDetailVo assembleProductDetail(Product product) {
        ProductDetailVo detailVo = new ProductDetailVo();
        detailVo.setId(product.getId());
        detailVo.setCategoryId(product.getCategoryId());
        Integer parentCategoryId = categoryMapper.selectParentCategoryId(product.getCategoryId());
        if (parentCategoryId == null) {
            detailVo.setParentCategoryId(0);
        } else {
            detailVo.setParentCategoryId(parentCategoryId);
        }
        detailVo.setName(product.getName());
        detailVo.setSubtitle(product.getSubtitle());
        detailVo.setImageHost(PropertyUtil.getProperty("ftp.server.http.prefix"));
        detailVo.setMainImage(product.getMainImage());
        detailVo.setSubImages(product.getSubImages());
        detailVo.setDetail(product.getDetail());
        detailVo.setPrice(product.getPrice());
        detailVo.setStock(product.getStock());
        detailVo.setStatus(product.getStatus());
        detailVo.setCreateTime(DateTimeUtil.dateToStr(product.getCreateTime()));
        detailVo.setUpdateTime(DateTimeUtil.dateToStr(product.getUpdateTime()));
        return detailVo;
    }

    @Override
    public ServerResponse<ProductDetailVo> listProductDetail(Integer productId) {
        if (productId == null) {
            return ServerResponse.createByErrorMsg(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product != null) {
            ProductDetailVo detailVo = assembleProductDetail(product);
            return ServerResponse.createBySuccess(detailVo);
        }
        return ServerResponse.createBySuccessMsg("查询不到商品详情信息");
    }

    @Override
    public ServerResponse setStatus(Integer productId, Integer status) {
        if (productId == null || status == null) {
            return ServerResponse.createByErrorMsg(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = new Product();
        product.setStatus(status);
        product.setId(productId);
        int res = productMapper.updateByPrimaryKeySelective(product);
        if (res > 0) {
            return ServerResponse.createBySuccess("修改产品状态成功");
        }
        return ServerResponse.createByErrorMsg("修改产品状态失败");
    }

    @Override
    public ServerResponse saveOrUpdate(Product product) {
        if (product == null) {
            return ServerResponse.createByErrorMsg(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        if (StringUtils.isNotBlank(product.getSubImages())) {
            String[] subImage = product.getSubImages().split(",");
            if (subImage.length > 0)
                product.setMainImage(subImage[0]);
        }
        if (product.getId() == null) {
            int res = productMapper.insert(product);
            if (res > 0) {
                return ServerResponse.createBySuccess("新增产品成功");
            }
            return ServerResponse.createByErrorMsg("新增产品失败");
        } else {
            int updateRes = productMapper.updateByPrimaryKeySelective(product);
            if (updateRes > 0) {
                return ServerResponse.createBySuccess("更新产品成功");
            }
            return ServerResponse.createByErrorMsg("更新产品失败");
        }
    }

    @Override
    public ServerResponse<PageInfo> selectByKeywordCategoryId(Integer categoryId, String keyword, Integer pageNum, Integer pageSize, String orderBy) {
        if (categoryId == null && StringUtils.isBlank(keyword)) {
            return ServerResponse.createByErrorMsg(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        List<Integer> categoryIdList = new ArrayList<Integer>();
        if (categoryId != null) {
            Category category = categoryMapper.selectByPrimaryKey(categoryId);
            if (category == null && StringUtils.isBlank(keyword)) {
                PageHelper.startPage(pageNum, pageSize);
                List<ProductListVo> productListVos = Lists.newArrayList();
                PageInfo pageInfo = new PageInfo(productListVos);
                return ServerResponse.createBySuccess(pageInfo);
            }
            categoryIdList = (List<Integer>) iCategoryService.getDeepCategory(category.getId()).getData();
            logger.info("通过分类获取到数据:{}", categoryIdList);
        }
        if (StringUtils.isNotBlank(keyword)) {
            StringBuilder keywordExtend = new StringBuilder();
            keyword = keywordExtend.append("%").append(keyword).append("%").toString();
            logger.info("keyword:{}", keyword);
        }
        PageHelper.startPage(pageNum, pageSize);
        Set<String> productOrderBySet = Const.ProductListOrderBy.PRICE_ASC_DESC;
        if (StringUtils.isNotBlank(orderBy)) {
            for (String orderItem: productOrderBySet) {
                if (StringUtils.equals(orderBy, orderItem)) {
                    String[] orderSplit = orderItem.split("_");
                    orderBy = orderSplit[0] + " " + orderSplit[1];
                    logger.info("orderBy:{}", orderBy);
                    PageHelper.orderBy(orderBy);
                }
            }
        }
        List<Product> products = productMapper.selectByKeywordAndIds(StringUtils.isBlank(keyword)?null:keyword, categoryIdList.size()>0?categoryIdList:null);
        List<ProductListVo> productListVos = Lists.newArrayList();
        for (Product proItem : products) {
            ProductListVo listVo = assembleProductVoList(proItem);
            productListVos.add(listVo);
        }
        PageInfo pageInfo = new PageInfo(products);
        pageInfo.setList(productListVos);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse portalDetail(Integer productId) {
        if (productId == null) {
            return ServerResponse.createByErrorMsg(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = productMapper.selectByPrimaryKeyAndStatus(productId);
        if (product != null) {
            ProductDetailVo productDetailVo = assembleProductDetail(product);
            return ServerResponse.createBySuccess(productDetailVo);
        }
        return ServerResponse.createByErrorMsg("该商品已下架或删除");
    }
//####2.产品detail
//
///product/detail.do
//
//    http://localhost:8080/product/detail.do?productId=2
//
//    request
//
//            productId
//
//    response
//
//            success
//
//    {
//        "status": 0,
//            "data": {
//        "id": 2,
//                "categoryId": 2,
//                "name": "oppo R8",
//                "subtitle": "oppo促销进行中",
//                "mainImage": "mainimage.jpg",
//                "subImages": "[\"mmall/aa.jpg\",\"mmall/bb.jpg\",\"mmall/cc.jpg\",\"mmall/dd.jpg\",\"mmall/ee.jpg\"]",
//                "detail": "richtext",
//                "price": 2999.11,
//                "stock": 71,
//                "status": 1,
//                "createTime": "2016-11-20 14:21:53",
//                "updateTime": "2016-11-20 14:21:53"
//    }
//    }
//
//    fail
//
//    {
//        "status": 1,
//            "msg": "该商品已下架或删除"
//    }


}
