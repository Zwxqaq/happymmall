package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mmall.common.ServerResponse;
import com.mmall.dao.ShippingMapper;
import com.mmall.pojo.Shipping;
import com.mmall.service.IShippingService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("iShippingService")
public class ShippingServiceImpl implements IShippingService {

    @Autowired
    private ShippingMapper shippingMapper;

    @Override
    public ServerResponse add(int userId, Shipping shipping) {
        Shipping updShipping = new Shipping();
        updShipping.setUserId(userId);
        updShipping.setReceiverName(shipping.getReceiverName());
        updShipping.setReceiverPhone(shipping.getReceiverPhone());
        updShipping.setReceiverMobile(shipping.getReceiverMobile());
        updShipping.setReceiverProvince(shipping.getReceiverProvince());
        updShipping.setReceiverCity(shipping.getReceiverCity());
        updShipping.setReceiverAddress(shipping.getReceiverAddress());
        updShipping.setReceiverZip(shipping.getReceiverZip());
        int res = shippingMapper.insert(updShipping);
        if (res > 0) {
            return ServerResponse.createBySuccessMsg("新建地址成功");
        }
        return ServerResponse.createByErrorMsg("新建地址失败");
    }

    @Override
    public ServerResponse del(Integer id, int shippingId) {
        int userId = shippingMapper.selectByShippingId(shippingId);
        if (userId == id) {
            int res = shippingMapper.deleteByPrimaryKey(shippingId);
            if (res > 0) {
                return ServerResponse.createBySuccessMsg("删除地址成功");
            }
        }
        return ServerResponse.createByErrorMsg("删除地址失败");
    }

    @Override
    public ServerResponse update(Integer id, Shipping shipping) {
        int userId = shippingMapper.selectByShippingId(shipping.getId());
        if (userId == id) {
            int res = shippingMapper.updateByUserIdAndIdSelective(shipping);
            if (res > 0) {
                return ServerResponse.createBySuccessMsg("更新地址成功");
            }
        } else {
            return ServerResponse.createByErrorMsg("小兄弟不是你的女人,你也要更新？？？");
        }
        return ServerResponse.createByErrorMsg("更新地址失败");
    }

    @Override
    public ServerResponse<Shipping> select(int userId, int shippingId) {
        Shipping shipping = shippingMapper.selectByUserIdAndId(userId, shippingId);
        if (shipping != null) {
            return ServerResponse.createBySuccess(shipping);
        }
        return ServerResponse.createByErrorMsg("查无此猪");
    }

    @Override
    public ServerResponse<PageInfo> list(Integer id, int pageSize, int pageNum) {
        PageHelper.startPage(pageSize, pageNum);
        List<Shipping> shippings = shippingMapper.selectAll(id);
        PageInfo pageInfo = new PageInfo(shippings);
        return ServerResponse.createBySuccess(pageInfo);
    }

//    /shipping/list.do
//
//    http://localhost:8080/shipping/list.do
//
//    request
//
//    pageNum(默认1),pageSize(默认10)
//
//    response
//
//            success
//
//    {
//        "status": 0,
//            "data": {
//        "pageNum": 1,
//                "pageSize": 10,
//                "size": 2,
//                "orderBy": null,
//                "startRow": 1,
//                "endRow": 2,
//                "total": 2,
//                "pages": 1,
//                "list": [
//        {
//            "id": 4,
//                "userId": 13,
//                "receiverName": "geely",
//                "receiverPhone": "010",
//                "receiverMobile": "18688888888",
//                "receiverProvince": "北京",
//                "receiverCity": "北京市",
//                "receiverAddress": "中关村",
//                "receiverZip": "100000",
//                "createTime": 1485066385000,
//                "updateTime": 1485066385000
//        },
//        {
//            "id": 5,
//                "userId": 13,
//                "receiverName": "AAA",
//                "receiverPhone": "010",
//                "receiverMobile": "18688888888",
//                "receiverProvince": "北京",
//                "receiverCity": "北京市",
//                "receiverAddress": "中关村",
//                "receiverZip": "100000",
//                "createTime": 1485066392000,
//                "updateTime": 1485075875000
//        }
//        ],
//        "firstPage": 1,
//                "prePage": 0,
//                "nextPage": 0,
//                "lastPage": 1,
//                "isFirstPage": true,
//                "isLastPage": true,
//                "hasPreviousPage": false,
//                "hasNextPage": false,
//                "navigatePages": 8,
//                "navigatepageNums": [
//        1
//        ]
//    }
//    }
//
//    fail
//
//    {
//        "status": 1,
//            "msg": "请登录之后查询"
//    }

}
