package com.mmall.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CartMapper;
import com.mmall.dao.ProductMapper;
import com.mmall.pojo.Cart;
import com.mmall.pojo.Product;
import com.mmall.service.ICartService;
import com.mmall.util.BigDecimalUtil;
import com.mmall.util.PropertyUtil;
import com.mmall.vo.CartProductVo;
import com.mmall.vo.CartVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("iCartService")
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public ServerResponse<CartVo> list(Integer userId) {
        CartVo cartVo = this.getCartVoServerResponse(userId);
        if (cartVo == null)
            return ServerResponse.createByErrorMsg("找不到相关的购物车订单");
        return ServerResponse.createBySuccess(cartVo);
    }



    /**
     *
     * @param id  userId
     * @param productId
     * @param count  limit the stock sign
     * @return CartVo
     *
     * 1add to sql
     * product may exist , so add count to exist product
     * or new add count product
     * find the stock of product  if not enough , limit the stock
     * 2list the result
     */
    @Override
    public ServerResponse<CartVo> add(Integer id, Integer productId, Integer count) {
        if (productId == null && count == null) {
            return ServerResponse.createByErrorMsg(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        if (productId != null) {
            List<Cart> carts = cartMapper.selectByUserId(id);
            Integer stock = productMapper.selectByProductId(productId);
            for (Cart cartItem : carts) {
                if (cartItem.getProductId().equals(productId) ) {
                    // exist product in the cart
                    Cart addCart = new Cart();
                    addCart.setId(cartItem.getId());
                    parseCartCountLimit(count, stock, addCart , cartItem);
                    cartMapper.updateByPrimaryKeySelective(addCart);
                    CartVo cartVo = this.getCartVoServerResponse(id);
                    return ServerResponse.createBySuccess(cartVo);
                }
            }
            Cart addCart = new Cart();
            addCart.setChecked(Const.Cart.CHECKED);
            addCart.setProductId(productId);
            addCart.setUserId(id);
            parseEmptyCartCountLimit(count, stock, addCart);
            cartMapper.insert(addCart);
        }
        CartVo cartVo = this.getCartVoServerResponse(id);
        return ServerResponse.createBySuccess(cartVo);
    }

    @Override
    public ServerResponse<CartVo> update(Integer id, Integer productId, Integer count) {
        if (productId == null && count == null) {
            return ServerResponse.createByErrorMsg(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        if (productId != null) {
            List<Cart> carts = cartMapper.selectByUserId(id);
            Integer stock = productMapper.selectByProductId(productId);
            for (Cart cartItem : carts) {
                if (cartItem.getProductId().equals(productId)) {
                    // exist product in the cart
                    Cart addCart = new Cart();
                    addCart.setId(cartItem.getId());
                    parseCartCountLimit(count, stock, addCart, cartItem);
                    cartMapper.updateByPrimaryKeySelective(addCart);
                }
            }
        }
        CartVo cartVo = this.getCartVoServerResponse(id);
        return ServerResponse.createBySuccess(cartVo);
    }

    @Override
    public ServerResponse<CartVo> delete(Integer id, String productIds) {
        if (StringUtils.isBlank(productIds)) {
            return ServerResponse.createByErrorMsg(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        List<String> productList = Splitter.on(",").splitToList(productIds);
        cartMapper.deleteByIdsAndUserId(productList, id);
        CartVo cartVo = this.getCartVoServerResponse(id);
        return ServerResponse.createBySuccess(cartVo);
    }

    @Override
    public ServerResponse<CartVo> selOrUnsel(Integer id, Integer productId, Integer checked) {
        if (productId == null && checked == null) {
            return ServerResponse.createByErrorMsg(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Cart selOrNotCart = new Cart();
        if (productId != null) {
            selOrNotCart.setProductId(productId);
            selOrNotCart.setUserId(id);
            if (checked.equals(Const.Cart.CHECKED)) {
                selOrNotCart.setChecked(Const.Cart.CHECKED);
            } else if (checked.equals(Const.Cart.UN_CHECKED)) {
                selOrNotCart.setChecked(Const.Cart.UN_CHECKED);
            }
        }
        cartMapper.updateByCheckedUserAndProductId(selOrNotCart);
        CartVo cartVo = this.getCartVoServerResponse(id);
        return ServerResponse.createBySuccess(cartVo);
    }

    @Override
    public ServerResponse getProductCount(Integer id) {
        int res = cartMapper.selectCountByUserId(id);
        return ServerResponse.createBySuccess(res);
    }

    @Override
    public ServerResponse<CartVo> selOrNotSelAll(Integer id, Integer checked) {
        if (checked.equals(Const.Cart.CHECKED))
            checked = Const.Cart.CHECKED;
        else if (checked.equals(Const.Cart.UN_CHECKED))
            checked = Const.Cart.UN_CHECKED;
        cartMapper.updateAllByUserId(id, checked);
        CartVo cartVo = this.getCartVoServerResponse(id);
        return ServerResponse.createBySuccess(cartVo);
    }


    private void parseEmptyCartCountLimit(Integer count, Integer stock, Cart addCart) {
        if (count != null) {
            if (count <= stock) {
                addCart.setQuantity(count);
            } else {
                addCart.setQuantity(stock);
            }
        }
    }

    private void parseCartCountLimit(Integer count, Integer stock, Cart addCart, Cart cartItem) {
        if (count != null) {
            if (count <= stock) {
                addCart.setQuantity(cartItem.getQuantity() + count);
            } else {
                addCart.setQuantity(cartItem.getQuantity() + stock);
            }
        }
    }

    private CartVo getCartVoServerResponse(Integer userId) {
        CartVo cartVo = new CartVo();
        List<Cart> carts = cartMapper.selectByUserId(userId);
        List<CartProductVo> cartProductVos = Lists.newArrayList();
        BigDecimal batchTotalPrice = new BigDecimal("0");
        if (CollectionUtils.isNotEmpty(carts)) {
            for (Cart cartItem : carts) {
                CartProductVo productVo = new CartProductVo();
                productVo.setId(cartItem.getId());
                productVo.setUserId(cartItem.getUserId());
                productVo.setProductChecked(cartItem.getChecked());
                Product product = productMapper.selectByPrimaryKey(cartItem.getProductId());
                if (product != null) {
                    productVo.setProductId(product.getId());
                    productVo.setProductName(product.getName());
                    productVo.setProductSubtitle(product.getSubtitle());
                    productVo.setProductMainImage(product.getMainImage());
                    productVo.setProductPrice(product.getPrice());
                    productVo.setProductStatus(product.getStatus());
                    productVo.setProductStock(product.getStock());
                    int limitQuantity = 0;
                    if (cartItem.getQuantity() <= product.getStock()) {
                        limitQuantity = cartItem.getQuantity();
                        productVo.setLimitQuantity(Const.Cart.LIMIT_NUM_SUCCESS);
                    } else {
                        limitQuantity = product.getStock();
                        productVo.setLimitQuantity(Const.Cart.LIMIT_NUM_FAIL);
                    }
                    productVo.setQuantity(limitQuantity);
                    productVo.setProductTotalPrice(BigDecimalUtil.mul(product.getPrice().doubleValue(),
                            productVo.getQuantity().doubleValue()));
                }
                if (cartItem.getChecked() == Const.Cart.CHECKED) {
                    batchTotalPrice = BigDecimalUtil.add(batchTotalPrice.doubleValue(),
                            productVo.getProductTotalPrice().doubleValue());
                }
                cartProductVos.add(productVo);
            }
        }
        cartVo.setCartProductVoList(cartProductVos);
        cartVo.setCartTotalPrice(batchTotalPrice);
        cartVo.setAllChecked(this.getAllChecked(userId));
        cartVo.setImageHost(PropertyUtil.getProperty("ftp.server.http.prefix"));
        return cartVo;
    }

    private boolean getAllChecked(Integer userId) {
        return cartMapper.selectCheckedByUserId(userId) == 0;
    }
    //        {
//                "id": 1,
//                "userId": 13,
//                "productId": 1,
//                "quantity": 1,
//                "productName": "iphone7",
//                "productSubtitle": "双十一促销",
//                "productMainImage": "mainimage.jpg",
//                "productPrice": 7199.22,
//                "productStatus": 1,
//                "productTotalPrice": 7199.22,
//                "productStock": 86,
//                "productChecked": 1,
//                "limitQuantity": "LIMIT_NUM_SUCCESS"
//        },

//    private List<CartProductVo> cartProductVoList;
//    private BigDecimal cartTotalPrice;
//    private Boolean allChecked;//是否已经都勾选
//    private String imageHost;






















































}
