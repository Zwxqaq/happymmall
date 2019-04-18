<%--
  Created by IntelliJ IDEA.
  User: VULCAN
  Date: 2019/2/16
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="http://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
</head>
<style>
    body{
        font-size: 12px;
        font-family: Arial;
    }
    div.cartDiv {
        max-width: 1080px;
        margin: 10px auto;
        color: black;
    }
    div.cartTitle button {
        background-color: #AAAAAA;
        border: 1px solid #AAAAAA;
        color: white;
        width: 53px;
        height: 25px;
        border-radius: 2px;
    }
    span.cartTitlePrice {
        color: #C40000;
        font-size: 14px;
        font-weight: bold;
        margin-left: 5px;
        margin-right: 3px;
    }
    div.cartFoot {
        background-color: #E5E5E5;
        line-height: 50px;
        margin: 20px 0px;
        color: black;
        padding-left: 20px;
    }
    div.cartFoot button {
        background-color: #AAAAAA;
        border: 0px solid #AAAAAA;
        color: white;
        width: 128px;
        height: 50px;
        font-size: 20px;
        text-align: center;
    }
    img.selectAllItem {
        cursor: pointer;
    }
    span.cartSumNumber {
        color: #C40000;
        font-weight: bold;
        font-size: 16px;
    }
    span.cartSumPrice {
        color: #C40000;
        font-weight: bold;
        font-size: 20px;
    }
    table.cartProductTable {
        width: 100%;
        font-size: 12px;
    }
    tr.cartProductItemTR {
        border: 1px solid #CCCCCC;
    }
    tr.cartProductItemTR td {
        padding: 20px 20px;
    }
    table.cartProductTable th {
        font-weight: normal;
        color: #3C3C3C;
        padding: 20px 20px;
    }
    img.cartProductImg {
        border: 1px solid #EEEEEE;
        width: 80px;
        height: 80px;
        padding: 1px;
    }
    table.cartProductTable th.selectAndImage {
        width: 140px;
    }
    table.cartProductTable th.operation {
        width: 30px;
    }
    div.cartProductLinkOutDiv {
        position: relative;
        height: 80px;
    }
    a.cartProductLink {
        color: #3C3C3C;
    }
    a.cartProductLink:hover {
        color: #C40000;
        text-decoration: underline;
    }
    div.cartProductLinkInnerDiv {
        position: absolute;
        bottom: 0;
        height: 20px;
    }
    span.cartProductItemOringalPrice {
        text-decoration: line-through;
        color: #9C9C9C;
        display: block;
        font-weight: bold;
        font-size: 14px;
    }
    span.cartProductItemPromotionPrice {
        font-family: Arial;
        font-size: 14px;
        font-weight: bold;
        color: #C40000;
    }
    div.cartProductChangeNumberDiv {
        border: 1px solid #E5E5E5;
        width: 80px;
    }
    div.cartProductChangeNumberDiv a {
        width: 14px;
        display: inline-block;
        text-align: center;
        color: black;
        text-decoration: none;
    }
    div.cartProductChangeNumberDiv input {
        border: 1px solid #AAAAAA;
        width: 42px;
        display: inline-block;
    }
    span.cartProductItemSmallSumPrice {
        font-family: Arial;
        font-size: 14px;
        font-weight: bold;
        color: #C40000;
    }
    img.cartProductItemIfSelected, img.selectAllItem {
        cursor: pointer;
    }
</style>
<body>
    <div class="cartDiv">
        <div class="cartTitle pull-right">
            <span>已选商品  （不含运费）</span>
            <span class="cartTitlePrice">￥0.00</span>
            <button class="createOrderButton" style="background-color: rgb(170,170,170);" disabled="disabled">
                结算
            </button>
        </div>
        <div class="cartProductList">
            <table class="cartProductTable">
                <thead>
                <tr>
                    <th class="selectAndImage">
                        <img src="http://how2j.cn/tmall/img/site/cartNotSelected.png" class="selectAndItem" selectit="false">
                        全选
                    </th>
                    <th>商品信息</th>
                    <th>单价</th>
                    <th>数量</th>
                    <th width="120px">金额</th>
                    <th class="operation">操作</th>
                </tr>
                </thead>
                <tbody>
                <tr class="cartProductItemTR" oiid="936">
                    <td>
                        <img src="http://how2j.cn/tmall/img/site/cartNotSelected.png" class="cartProductItemIfSelected" oiid="936" selectit="false">
                        <a href="#" style="display: none">
                            <img src="http://how2j.cn/tmall/img/site/cartSelected.png">
                        </a>
                        <img width="40px" src="http://how2j.cn/tmall/img/productSingle_middle/3665.jpg" class="cartProductImg">
                    </td>
                    <td>
                        <div class="cartProductLinkOutDiv">
                            <a class="cartProductLink" href="#">美国iRobot扫地机器人吸尘器全自动家用智能扫地机650 天猫电器城</a>
                            <div class="cartProductLinkInnerDiv">
                                <img title="支持信用卡支付" src="http://how2j.cn/tmall/img/site/creditcard.png">
                                <img title="消费者保障服务,承诺7天退货" src="http://how2j.cn/tmall/img/site/7day.png">
                                <img title="消费者保障服务,承诺如实描述" src="http://how2j.cn/tmall/img/site/promise.png">
                            </div>
                        </div>
                    </td>
                    <td>
                        <span class="cartProductItemOringalPrice">￥7580.0</span>
                        <span class="cartProductItemPromotionPrice">￥5306.0</span>
                    </td>
                    <td>
                        <div class="cartProductChangeNumberDiv">
                            <span pid="365" class="hidden orderItemStock">75</span>
                            <span pid="365" class="hidden orderItemPromotePrice">5306.0</span>
                            <a href="#" class="numberMinus" pid="365">-</a>
                            <input value="4" autocomplete="off" class="orderItemNumberSetting" oiid="936" pid="365">
                            <a href="#" class="numberPlus" pid="365" stock="75">+</a>
                        </div>
                    </td>
                    <td>
                        <span pid="365" oiid="936" class="cartProductItemSmallSumPrice">￥5,306.00</span>
                    </td>
                    <td>
                        <a href="#" oiid="936" class="deleteOrderItem">删除</a>
                    </td>
                </tr>
                <tr class="cartProductItemTR" oiid="935">
                    <td>
                        <img src="http://how2j.cn/tmall/img/site/cartNotSelected.png" class="cartProductItemIfSelected" oiid="935" selectit="false">
                        <a href="#" style="display: none">
                            <img src="http://how2j.cn/tmall/img/site/cartSelected.png">
                        </a>
                        <img width="40px" src="http://how2j.cn/tmall/img/productSingle_middle/8510.jpg" class="cartProductImg">
                    </td>
                    <td>
                        <div class="cartProductLinkOutDiv">
                            <a class="cartProductLink" href="#">阔腿裤三件套装女夏装2016新款大码雪纺时尚休闲气质棉麻九分裤潮</a>
                            <div class="cartProductLinkInnerDiv">
                                <img title="支持信用卡支付" src="http://how2j.cn/tmall/img/site/creditcard.png">
                                <img title="消费者保障服务,承诺7天退货" src="http://how2j.cn/tmall/img/site/7day.png">
                                <img title="消费者保障服务,承诺如实描述" src="http://how2j.cn/tmall/img/site/promise.png">
                            </div>
                        </div>
                    </td>
                    <td>
                        <span class="cartProductItemOringalPrice">￥235.0</span>
                        <span class="cartProductItemPromotionPrice">￥152.75</span>
                    </td>
                    <td>
                        <div class="cartProductChangeNumberDiv">
                            <span pid="809" class="hidden orderItemStock">17</span>
                            <span pid="809" class="hidden orderItemPromotePrice">152.75</span>
                            <a href="#" class="numberMinus" pid="809">-</a>
                            <input value="1" autocomplete="off" class="orderItemNumberSetting" oiid="935" pid="809">
                            <a href="#" class="numberPlus" pid="809" stock="17">+</a>
                        </div>
                    </td>
                    <td>
                        <span pid="809" oiid="935" class="cartProductItemSmallSumPrice">￥152.75</span>
                    </td>
                    <td>
                        <a href="#" oiid="935" class="deleteOrderItem">删除</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="cartFoot">
            <img src="http://how2j.cn/tmall/img/site/cartNotSelected.png" class="selectAllItem" selectit="false">
            <span>全选</span>
            <div class="pull-right">
                <span>已选商品 <span class="cartSumNumber">0</span>件 </span>
                <span>合计(不含运费):</span>
                <span class="cartSumPrice">￥0.00</span>
                <button class="createOrderButton" style="background-color: rgb(170,170,170);" disabled="disabled">
                    结算
                </button>
            </div>
        </div>
    </div>
</body>
<script src="http://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<script src="http://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
<script>
    function formatMoney(num) {
        num = num.toString().replace(/\$|,/g,'');
        if (isNaN(num)) {
            num = "0";
        }
        //sign 数的正负 作为标志位
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100 + 0.5000000001);
        //cents 小数的位数
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        //如果小数的位数只有一位那么在前补0
        if (cents < 10) {
            cents = "0" + cents;
        }
        //123456   ->  123,456
        for (var i = 0; i < Math.floor((num.length - (1+i))/ 3); i++) {
            num = num.substring(0, num.length - (4 * i + 3)) + ',' + num.substring(num.length - (4 * i + 3));
            console.log('遍历的每一次num值:' + num);
        }
        return(((sign)?'':'-') + num + "." + cents);
    }
    function synCreateOrderButton() {
        var selectAny = false;
        $(".cartProductItemIfSelected").each(
            function () {
                if ("selectit" == $(this).attr("selectit")) {
                    console.log('当前对象的selectit的属性值是:' + $(this).attr("selectit"));
                    selectAny = true;
                }
            }
        );
        if (selectAny) {
            $("button.createOrderButton").css("background-color", "#C40000");
            $("button.createOrderButton").removeAttr("disabled");
        } else {
            $("button.createOrderButton").css("background-color", "#AAAAAA");
            $("button.createOrderButton").attr("disabled","disabled");
        }
    }
    function synSelect() {
        var selectAll = true;
        $(".cartProductItemIfSelected").each(function () {
            if ("false" == $(this).attr("selectit")) {
                selectAll = false;
            }
        });
        if (selectAll) {
            $("img.selectAllItem").attr("src", "http://how2j.cn/tmall/img/site/cartSelected.png");
        } else {
            $("img.selectAllItem").attr("src","http://how2j.cn/tmall/img/site/cartNotSelected.png");
        }
    }
    function calcCartSumPriceAndNumber() {
        var sum = 0;
        var totalNumber = 0;
        $(".cartProductItemIfSelected[selectit='selectit']").each(function () {
            console.log("已经调用此方法");
            //oiid 获取商品id
            //price 获取商品价格
            var oiid= $(this).attr("oiid");
            var price = $(".cartProductItemSmallSumPrice[oiid="+oiid+"]").text();
            //price 订单的价格  将一些字符给替换掉
            price = price.replace(/,/g,"");
            price = price.replace(/￥/g,"");
            sum += new Number(price);
            console.log("price ---> " + price);
            console.log("sum ----> " + sum);
            //num  订单的数量
            var num = $(".orderItemNumberSetting[oiid="+oiid+"]").val();
            totalNumber += new Number(num);
        });
        $("span.cartSumPrice").html("￥"+formatMoney(sum));
        $("span.cartTitlePrice").html("￥"+formatMoney(sum));
        $("span.cartSumNumber").html(totalNumber);
    }
    function syncPrice(pid, num, price) {
        $(".orderItemNumberSetting[pid="+pid+"]").val(num);
        var cartProductItemSmallSumPrice = formatMoney(num * price);
        $(".cartProductItemSmallSumPrice[pid="+pid+"]").html("￥" + cartProductItemSmallSumPrice);
        calcCartSumPriceAndNumber();
    }
    //选择单个商品
    $("img.cartProductItemIfSelected").click(function () {
        var selectit = $(this).attr("selectit");
        if ("selectit" == selectit) {
            $(this).attr("src", "http://how2j.cn/tmall/img/site/cartNotSelected.png");
            $(this).attr("selectit", "false");
            $(this).parents("tr.cartProductItemTR").css("background-color","#fff");
        } else {
            $(this).attr("src", "http://how2j.cn/tmall/img/site/cartSelected.png");
            $(this).attr("selectit", "selectit");
            $(this).parents("tr.cartProductItemTR").css("background-color","#FFF8E1");
        }
        synSelect();
        synCreateOrderButton();
        calcCartSumPriceAndNumber();
    });
    //全选
    $("img.selectAllItem").click(function () {
        var selectit = $(this).attr("selectit");
        if ("selectit" == selectit) {
            $("img.selectAllItem").attr("src", "http://how2j.cn/tmall/img/site/cartNotSelected.png");
            $("img.selectAllItem").attr("selectit", "false");
            $("img.selectAndItem").attr("src", "http://how2j.cn/tmall/img/site/cartNotSelected.png");
            $("img.selectAndItem").attr("selectit", "false");
            $(".cartProductItemIfSelected").each(function () {
               $(this).attr("src", "http://how2j.cn/tmall/img/site/cartNotSelected.png");
               $(this).attr("selectit", "false");
               $(this).parents("tr.cartProductItemTR").css("background-color", "#fff");
            });
        } else {
            $("img.selectAllItem").attr("src", "http://how2j.cn/tmall/img/site/cartSelected.png");
            $("img.selectAllItem").attr("selectit", "selectit");
            $("img.selectAndItem").attr("src", "http://how2j.cn/tmall/img/site/cartSelected.png");
            $("img.selectAndItem").attr("selectit", "selectit");
            $(".cartProductItemIfSelected").each(function () {
                $(this).attr("src", "http://how2j.cn/tmall/img/site/cartSelected.png");
                $(this).attr("selectit", "selectit");
                $(this).parents("tr.cartProductItemTR").css("background-color","#FFF8E1");
            });
        }
        synCreateOrderButton();
        calcCartSumPriceAndNumber();
    });
    $(".numberPlus").click(function () {
       var pid = $(this).attr("pid");
       var stock = $("span.orderItemStock[pid="+pid+"]").text();
       var price = $("span.orderItemPromotePrice[pid="+pid+"]").text();
       var num = $(".orderItemNumberSetting[pid="+pid+"]").val();
       num++;
       if (num > stock) {
           num = stock;
       }
       syncPrice(pid, num, price);
    });
    $(".numberMinus").click(function () {
        var pid = $(this).attr("pid");
        var price= $("span.orderItemPromotePrice[pid="+pid+"]").text();
        var num= $(".orderItemNumberSetting[pid="+pid+"]").val();
        num--;
        if (num <= 0) {
            num = 1;
        }
        syncPrice(pid, num, price);
    });
    $(".orderItemNumberSetting").keyup(function () {
        var pid = $(this).attr("pid");
        var stock= $("span.orderItemStock[pid="+pid+"]").text();
        var price= $("span.orderItemPromotePrice[pid="+pid+"]").text();
        var num= $(".orderItemNumberSetting[pid="+pid+"]").val();
        num = parseInt(num);
        if (isNaN(num)) {
            num = 1;
        }
        if (num <= 0) {
            num = 1;
        }
        if (num > stock) {
            num = stock;
        }
        syncPrice(pid, num, price);
    })
</script>
</html>
