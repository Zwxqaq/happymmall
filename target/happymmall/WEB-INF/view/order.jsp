<%--
  Created by IntelliJ IDEA.
  User: VULCAN
  Date: 2019/2/16
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="http://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
</head>
<style>
    div.boughtDiv {
        max-width: 1013px;
        margin: 10px auto;
    }
    div.orderType div {
        border-bottom: 2px solid #E8E8E8;
        float: left;
    }
    div.orderType a {
        border-right: 1px solid #E8E8E8;
        display: inline-block;
        font-size: 16px;
        font-weight: bold;
        color: black;
        margin-bottom: 10px;
        padding: 0px 20px;
        text-decoration: none;
    }
    div.orderType a:hover {
        color: #C40000;
        text-decoration: none;
    }
    div.orderType div.selectOrderType {
        border-bottom: 2px solid #C40000;
    }
    div.orderType div.selectOrderType a {
        color: #C40000;
    }
    div.orderTypeLastOne {
        overflow: hidden;
        float: none !important;
        border-bottom: 2px solid #E8E8E8;
        height: 35px;
    }
    a.noRightborder {
        border-right-width: 0px !important;
    }
    table.orderListTitleTable {
        border: 1px solid #E8E8E8;
        width: 100%;
        margin: 20px 0px;
        background-color: #F5F5F5;
        text-align: center;
        font-size: 12px;
    }
    table.orderListTitleTable td{
        padding: 12px 0px;
    }
    table.orderListItemTable {
        border: 2px solid #ECECEC;
        width: 100%;
        margin: 20px 0px;
        font-size: 12px;
    }
    table.orderListItemTable:hover {
        border: 2px solid #aaa !important;
    }
    table.orderListItemTable td {
        padding: 8px 10px;
    }
    tr.orderListItemFirstTR {
        background-color: #F1F1F1;
    }
    div.orderItemWangWangGif {
        display: inline-block;
        width: 67px;
        height: 22px;
        background-image: url(http://how2j.cn/tmall/img/site/wangwang.gif);
        background-repeat: no-repeat;
        background-color: transparent;
        background-attachment: scroll;
        background-position: -0px -0px;
        position: relative;
        top: 0px;
        left: 2px;
    }
    td.orderItemDeleteTD {
        text-align: right;
    }
    span.orderListItemDelete {
        display: inline-block;
        margin: 0px 10px;
        color: #999999;
        font-size: 16px;
    }
    div.orderListItemProductLinkOutDiv {
        position: relative;
        height: 80px;
    }
    div.orderListItemProductLinkInnerDiv {
        position: absolute;
        bottom: 0px;
    }
    div.orderListItemProductOriginalPrice {
        color: #999999;
        font-size: 14px;
    }
    div.orderListItemProductPrice {
        color: #3C3C3C;
        font-size: 14px;
    }
    td.orderListItemNumberTD {
        text-align: center;
        padding: 8px 10px;
        border-right: 1px solid #ECECEC;
    }
    td.orderItemProductInfoPartTD {
        border: 1px solid #ECECEC;
    }
    span.orderListItemNumber {
        color: #3C3C3C;
        line-height: 80px;
    }
    td.orderListItemProductRealPriceTD {
        text-align: center;
        border-right: 1px solid #ECECEC;
    }
    div.orderListItemProductRealPrice {
        color: #3C3C3C;
        font-size: 14px;
        font-weight: bold;
        line-height: 40px;
    }
    div.orderListItemPriceWithTransport {
        color: #6C6C6C;
        font-size: 12px;
        line-height: 30px;
    }
    td.orderListItemButtonTD {
        text-align: center;
    }
    button.orderListItemReview {
        border: 1px solid #DCDCDC;
        background-color: #fff;
        border-radius: 2px;
        color: #3C3C3C;
        font-size: 12px;
        font-weight: bold;
        padding: 6px 12px;
        margin-top: 20px;
    }
    button.orderListItemReview:hover {
        border-color: #C40000;
        color: #C40000;
    }
    button.orderListItemConfirm {
        background-color: #66B6FF;
        border-radius: 2px;
        color: white;
        font-size: 12px;
        font-weight: bold;
        padding: 6px 12px;
        border-width: 0px;
        margin-top: 24px;
    }
    button.orderListItemConfirm:hover {
        background-color: #118ADB;
    }
    a {
        color: #999;
    }
    a:hover {
        text-decoration: none;
        color: #C40000;
    }
    span.waitDeliverySpan {
        margin-top: 20px;
        display: inline-block;
    }
</style>
<body>
    <div class="boughtDiv">
        <div class="orderType">
            <div class="selectOrderType"><a href="#" orderstatus="all">所有订单</a></div>
            <div><a href="#" orderstatus="waitPay">待付款</a></div>
            <div><a href="#" orderstatus="waitDelivery">待发货</a></div>
            <div><a href="#" orderstatus="waitConfirm">待收货</a></div>
            <div><a class="noRightborder" href="#" orderstatus="waitReview">待评价</a></div>
            <div class="orderTypeLastOne"></div>
        </div>
        <div style="clear: both"></div>
        <div class="orderListTitle">
            <table class="orderListTitleTable">
                <tbody>
                    <tr>
                        <td>宝贝</td>
                        <td width="100px">单价</td>
                        <td width="100px">数量</td>
                        <td width="100px">实付款</td>
                        <td width="100px">交易操作</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="orderListItem">
            <table oid="946" orderstatus="waitReview" class="orderListItemTable">
                <tbody>
                    <tr class="orderListItemFirstTR">
                        <td colspan="2">
                            <b>2016-09-12 17:00:41</b>
                            <span>
                                订单号: 20160912170041674794
                            </span>
                        </td>
                        <td colspan="2">
                            <img width="13px" src="http://how2j.cn/tmall/img/site/orderItemTmall.png">天猫商场
                        </td>
                        <td colspan="1">
                            <a href="#" class="wangwanglink">
                                <div class="orderItemWangWangGif"></div>
                            </a>
                        </td>
                        <td class="orderItemDeleteTD">
                            <a href="#" oid="946" class="deleteOrderLink">
                                <span class="orderListItemDelete glyphicon glyphicon-trash"></span>
                            </a>
                        </td>
                    </tr>
                    <tr class="orderItemProductInfoPartTR">
                        <td class="orderItemProductInfoPartTD">
                            <img width="90" height="80" src="http://how2j.cn/tmall/img/productSingle_middle/3796.jpg">
                        </td>
                        <td class="orderItemProductInfoPartTD">
                            <div class="orderListItemProductLinkOutDiv">
                                <a href="#">公众智能扫地机器人家用全自动电动清洁地毯擦拖地一体机吸尘器</a>
                                <div class="orderListItemProductLinkInnerDiv">
                                    <img title="支持信用卡支付" src="http://how2j.cn/tmall/img/site/creditcard.png">
                                    <img title="消费者保障服务,承诺7天退货" src="http://how2j.cn/tmall/img/site/7day.png">
                                    <img title="消费者保障服务,承诺如实描述" src="http://how2j.cn/tmall/img/site/promise.png">
                                </div>
                            </div>
                        </td>
                        <td width="100px" class="orderItemProductInfoPartTD">
                            <div class="orderListItemProductOriginalPrice">￥2,499.00</div>
                            <div class="orderListItemProductPrice">￥2,124.15</div>
                        </td>
                        <td width="100px" valign="top" class="orderListItemNumberTD orderItemOrderInfoPartTD" rowspan="1">
                            <span class="orderListItemNumber">1</span>
                        </td>
                        <td width="120px" valign="top" class="orderListItemProductRealPriceTD orderItemOrderInfoPartTD" rowspan="1">
                            <div class="orderListItemProductRealPrice">￥2,124.15</div>
                            <div class="orderListItemPriceWithTransport">(含运费：￥0.00)</div>
                        </td>
                        <td width="100px" valign="top" class="orderListItemButtonTD orderItemOrderInfoPartTD" rowspan="1">
                            <a href="#">
                                <button class="orderListItemReview">评价</button>
                            </a>
                        </td>
                    </tr>
                </tbody>
            </table>
            <table oid="945" orderstatus="waitConfirm" class="orderListItemTable">
                <tbody><tr class="orderListItemFirstTR">
                    <td colspan="2">
                        <b>2016-09-12 17:00:33</b>
                        <span>订单号: 201609121700333128784
                    </span>
                    </td>
                    <td colspan="2"><img width="13px" src="http://how2j.cn/tmall/img/site/orderItemTmall.png">天猫商场</td>
                    <td colspan="1">
                        <a href="#nowhere" class="wangwanglink">
                            <div class="orderItemWangWangGif"></div>
                        </a>
                    </td>
                    <td class="orderItemDeleteTD">
                        <a href="#nowhere" oid="945" class="deleteOrderLink">
                            <span class="orderListItemDelete glyphicon glyphicon-trash"></span>
                        </a>
                    </td>
                </tr>
                <tr class="orderItemProductInfoPartTR">
                    <td class="orderItemProductInfoPartTD"><img width="90" height="80" src="http://how2j.cn/tmall/img/productSingle_middle/6651.jpg"></td>
                    <td class="orderItemProductInfoPartTD">
                        <div class="orderListItemProductLinkOutDiv">
                            <a href="#nowhere">ULIFE原创信封包男个性真皮手包男士手拿包休闲男包手抓包拉链潮</a>
                            <div class="orderListItemProductLinkInnerDiv">
                                <img title="支持信用卡支付" src="http://how2j.cn/tmall/img/site/creditcard.png">
                                <img title="消费者保障服务,承诺7天退货" src="http://how2j.cn/tmall/img/site/7day.png">
                                <img title="消费者保障服务,承诺如实描述" src="http://how2j.cn/tmall/img/site/promise.png">
                            </div>
                        </div>
                    </td>
                    <td width="100px" class="orderItemProductInfoPartTD">
                        <div class="orderListItemProductOriginalPrice">￥1,188.00</div>
                        <div class="orderListItemProductPrice">￥1,128.60</div>
                    </td>
                    <td width="100px" valign="top" class="orderListItemNumberTD orderItemOrderInfoPartTD" rowspan="1">
                        <span class="orderListItemNumber">1</span>
                    </td>
                    <td width="120px" valign="top" class="orderListItemProductRealPriceTD orderItemOrderInfoPartTD" rowspan="1">
                        <div class="orderListItemProductRealPrice">￥1,128.60</div>
                        <div class="orderListItemPriceWithTransport">(含运费：￥0.00)</div>
                    </td>
                    <td width="100px" valign="top" class="orderListItemButtonTD orderItemOrderInfoPartTD" rowspan="1">
                        <a href="#nowhere">
                            <button class="orderListItemConfirm">确认收货</button>
                        </a>
                    </td>
                </tr>
                </tbody>
            </table>
            <table oid="944" orderstatus="waitDelivery" class="orderListItemTable" style="display: table;">
                <tbody><tr class="orderListItemFirstTR">
                    <td colspan="2">
                        <b>2016-09-12 17:00:16</b>
                        <span>订单号: 201609121700167279660
                    </span>
                    </td>
                    <td colspan="2"><img width="13px" src="http://how2j.cn/tmall/img/site/orderItemTmall.png">天猫商场</td>
                    <td colspan="1">
                        <a href="#nowhere" class="wangwanglink">
                            <div class="orderItemWangWangGif"></div>
                        </a>
                    </td>
                    <td class="orderItemDeleteTD">
                        <a href="#nowhere" oid="944" class="deleteOrderLink">
                            <span class="orderListItemDelete glyphicon glyphicon-trash"></span>
                        </a>
                    </td>
                </tr>
                <tr class="orderItemProductInfoPartTR">
                    <td class="orderItemProductInfoPartTD"><img width="80" height="80" src="http://how2j.cn/tmall/img/productSingle_middle/8697.jpg"></td>
                    <td class="orderItemProductInfoPartTD">
                        <div class="orderListItemProductLinkOutDiv">
                            <a href="#nowhere">2016夏季新款女装雪纺衫短裤两件套 韩版时尚名媛休闲套装女夏装</a>
                            <div class="orderListItemProductLinkInnerDiv">
                                <img title="支持信用卡支付" src="http://how2j.cn/tmall/img/site/creditcard.png">
                                <img title="消费者保障服务,承诺7天退货" src="http://how2j.cn/tmall/img/site/7day.png">
                                <img title="消费者保障服务,承诺如实描述" src="http://how2j.cn/tmall/img/site/promise.png">
                            </div>
                        </div>
                    </td>
                    <td width="100px" class="orderItemProductInfoPartTD">
                        <div class="orderListItemProductOriginalPrice">￥263.00</div>
                        <div class="orderListItemProductPrice">￥236.70</div>
                    </td>
                    <td width="100px" valign="top" class="orderListItemNumberTD orderItemOrderInfoPartTD" rowspan="1">
                        <span class="orderListItemNumber">1</span>
                    </td>
                    <td width="120px" valign="top" class="orderListItemProductRealPriceTD orderItemOrderInfoPartTD" rowspan="1">
                        <div class="orderListItemProductRealPrice">￥236.70</div>
                        <div class="orderListItemPriceWithTransport">(含运费：￥0.00)</div>
                    </td>
                    <td width="100px" valign="top" class="orderListItemButtonTD orderItemOrderInfoPartTD" rowspan="1">
                        <span class="waitDeliverySpan">待发货</span>
                    </td>
                </tr>
                </tbody></table>
            <table oid="943" orderstatus="waitPay" class="orderListItemTable" style="display: table;">
                <tbody><tr class="orderListItemFirstTR">
                    <td colspan="2">
                        <b>2016-09-12 17:00:09</b>
                        <span>订单号: 201609121700098025374
                    </span>
                    </td>
                    <td colspan="2"><img width="13px" src="http://how2j.cn/tmall/img/site/orderItemTmall.png">天猫商场</td>
                    <td colspan="1">
                        <a href="#nowhere" class="wangwanglink">
                            <div class="orderItemWangWangGif"></div>
                        </a>
                    </td>
                    <td class="orderItemDeleteTD">
                        <a href="#nowhere" oid="943" class="deleteOrderLink">
                            <span class="orderListItemDelete glyphicon glyphicon-trash"></span>
                        </a>
                    </td>
                </tr>
                <tr class="orderItemProductInfoPartTR">
                    <td class="orderItemProductInfoPartTD"><img width="80" height="80" src="http://how2j.cn/tmall/img/productSingle_middle/5266.jpg"></td>
                    <td class="orderItemProductInfoPartTD">
                        <div class="orderListItemProductLinkOutDiv">
                            <a href="#nowhere">米纳专柜新款女式手表时尚简约陶瓷表女士手表女表正品防水石英表</a>
                            <div class="orderListItemProductLinkInnerDiv">
                                <img title="支持信用卡支付" src="http://how2j.cn/tmall/img/site/creditcard.png">
                                <img title="消费者保障服务,承诺7天退货" src="http://how2j.cn/tmall/img/site/7day.png">
                                <img title="消费者保障服务,承诺如实描述" src="http://how2j.cn/tmall/img/site/promise.png">
                            </div>
                        </div>
                    </td>
                    <td width="100px" class="orderItemProductInfoPartTD">
                        <div class="orderListItemProductOriginalPrice">￥1,880.00</div>
                        <div class="orderListItemProductPrice">￥1,316.00</div>
                    </td>
                    <td width="100px" valign="top" class="orderListItemNumberTD orderItemOrderInfoPartTD" rowspan="1">
                        <span class="orderListItemNumber">1</span>
                    </td>
                    <td width="120px" valign="top" class="orderListItemProductRealPriceTD orderItemOrderInfoPartTD" rowspan="1">
                        <div class="orderListItemProductRealPrice">￥1,316.00</div>
                        <div class="orderListItemPriceWithTransport">(含运费：￥0.00)</div>
                    </td>
                    <td width="100px" valign="top" class="orderListItemButtonTD orderItemOrderInfoPartTD" rowspan="1">
                        <a href="#nowhere">
                            <button class="orderListItemConfirm">付款</button>
                        </a>
                    </td>
                </tr>
                </tbody></table>
        </div>
    </div>
</body>
<script src="http://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<script src="http://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
<script>
    $("div.orderType div a").click(function () {
        //获取状态值
        //如果是相同状态，那么显示
        console.log("这里是中央电视台");
        var content = $(this).attr("orderstatus");
        console.log(content);
        if (content == "all") {
            $("div.orderType div a[orderstatus='all']").parent().addClass("selectOrderType");
            $("div.orderType div a[orderstatus="+content+"]").parent().siblings().removeClass("selectOrderType");
            location.href = "order.jsp";
        }
        $(".orderListItem table").each(function () {
            var compContent = $(this).attr("orderstatus");
            if (content == compContent) {
                $(this).show();
                $("div.orderType div a[orderstatus="+content+"]").parent().siblings().removeClass("selectOrderType");
                $("div.orderType div a[orderstatus="+content+"]").parent().addClass("selectOrderType");
                // $("div.orderType div a[orderstatus="+content+"]").parent().css("border-bottom", "2px solid #C40000");
            } else {
                $(this).hide();
            }
        });
    });
</script>
</html>
