package com.mmall.controller.portal;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.demo.trade.config.Configs;
import com.google.common.collect.Maps;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/order/")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private IOrderService iOrderService;

    @RequestMapping(value = "pay.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse orderPay(HttpSession session, String orderNo, HttpServletRequest request) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        String path = request.getSession().getServletContext().getRealPath("upload");
        return iOrderService.pay(user.getId(), orderNo, path);
    }

    @RequestMapping(value = "alipay_callback.do", method = RequestMethod.POST)
    @ResponseBody
    public Object orderCallback(HttpServletRequest request) {
        Map<String, String> params = Maps.newHashMap();
        Map<String, String[]> requestParameterMap = request.getParameterMap();
        for (Iterator iterator = requestParameterMap.keySet().iterator(); iterator.hasNext();) {
            String name = (String) iterator.next();
            String[] values = requestParameterMap.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i ++) {
                valueStr = (i == values.length - 1)?valueStr + values[i]:valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        logger.info("支付宝回调.sign:{},trade_status:{},params:{}", params.get("sign"), params.get("trade_status"),
                params.toString());
        params.remove("sign_type");
        try {
            boolean rsaV2 = AlipaySignature.rsaCheckV2(params, Configs.getAlipayPublicKey(), "utf-8", Configs.getSignType());
            if (!rsaV2) {
                return ServerResponse.createByErrorMsg("非法请求");
            }
        } catch (AlipayApiException e) {
            logger.error("支付宝回调异常", e);
        }
        ServerResponse response = iOrderService.aliCallback(params);
        if (response.isSuccess()) {
            return Const.AlipayCallback.RESPONSE_SUCCESS;
        }
        return Const.AlipayCallback.RESPONSE_FAILED;
    }

    @RequestMapping(value = "query_order_pay_status.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse queryOrderPayStatus(HttpSession session, String orderNo) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iOrderService.queryOrderStatus(user.getId(), orderNo);
    }

//####2.查询订单支付状态
//
///order/query_order_pay_status.do
//
//    http://localhost:8080/order/query_order_pay_status.do?orderNo=1485158676346
//
//    request
//
//            orderNo
//
//    response
//
//            success
//
//    {
//        "status": 0,
//            "data": true
//    }
//
//
//    fail
//
//    {
//        "status": 1,
//            "msg": "该用户并没有该订单,查询无效"
//    }


}