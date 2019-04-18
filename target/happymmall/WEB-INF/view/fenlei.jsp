<%--
  Created by IntelliJ IDEA.
  User: VULCAN
  Date: 2019/2/13
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>分类</title>
    <script src="http://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
    <link href="http://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="http://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<style>
    div.categoryPageDiv {
        max-width: 1013px;
        margin: 10px auto;
    }
    div.categorySortBar {
        background-color: #FAF9F9;
        margin: 40px 20px 20px 20px;
        padding: 4px;
    }
    table.categorySortBarTable {
        border-collapse: collapse;
        display: inline-block;
    }
    table.categorySortBarTable span.glyphicon {
        font-size: 10px;
    }
    table.categorySortBarTable td {
        height: 17px;
        font-size: 12px;
        border: 1px solid #CCCCCC;
        padding: 3px;
    }
    table.categorySortBarTable td.grayColumn {
        background-color: #F1EDEC;
    }
    table.categorySortBarTable td a {
        color: #806F66;
    }
    table.categorySortBarTable td a:hover {
        color: #C40000;
    }
    table.categorySortBarTable input {
        border-width: 0px;
        height: 15px;
        width: 50px;
    }
    table.categorySortBarTable td.priceMiddleColumn {
        width: 5px;
        vertical-align: middle;
        color: #CCCCCC;
    }
    table.categorySortTable td:hover {
        background-color: #F1EDEC;
    }
    body {
        font-size: 12px;
        font-family: Arial;
    }
    div.categoryProducts {
        max-width: 966px;
        margin: 10px auto;
    }
    div.productUnit {
        width: 230px;
        height: 338px;
        border: 3px solid #fff;
        background-color: white;
        margin: 12px 5px;
        float: left;
    }
    div.productUnit:hover {
        border: 3px solid #C40000;
    }
    div.productUnitFrame {
        border: 1px solid #eee;
        height: 100%;
    }
    div.productUnitFrame:hover {
        border: 1px solid #C40000;
    }
    div.productUnit img.productImage {
        width: 100%;
        height: 190px;
    }
    div.productUnit span.productPrice {
        font-size: 20px;
        color: #CC0000;
        display: block;
        padding-left: 4px;
    }
    div.productUnit a.productLink {
        margin: 10px 0px;
        color: #333333;
        display: block;
        height: 34px;
    }
    div.productUnit a.productLink:hover {
        text-decoration: underline;
        color: #C40000;
    }
    div.productUnit a.tmallLink {
        margin: 10px 0px;
        color: #999999;
        display: block;
        text-decoration: underline;
    }
    div.productUnit a.tmallLink:hover {
        text-decoration: underline;
        color: #C40000;
    }
    div.productUnit div.productInfo {
        border-top: 1px solid #EEEEEE;
        color: #999999;
    }
    div.productUnit span.productDealNumber {
        font-weight: bold;
        color: #B57C5B;
    }
    div.productUnit span.productReview {
        border-left: 1px solid #EEEEEE;
        border-right: 1px solid #EEEEEE;
    }
    div.productUnit span.productReviewNumber {
        font-weight: bold;
        color: #3388BB;
    }
    div.productUnit span.monthDeal, div.productUnit span.productReview {
        display: inline-block;
        width: 90px;
        height: 29px;
        padding-top: 5px;
        padding-left: 5px;
    }
    div.productUnit span.wangwang {
        padding-left: 3px;
    }
    div.change {
        max-width: 1013px;
        margin: 10px auto;
    }
</style>
<body>
    <div class="categoryPageDiv">
        <img src="http://how2j.cn/tmall/img/category/72.jpg">
        <div class="categorySortBar">
            <table class="categorySortBarTable categorySortTable">
                <tbody>
                    <tr>
                        <td class="grayColumn">
                            <a href="#">
                                综合<span class="glyphicon glyphicon-arrow-down"></span>
                            </a>
                        </td>
                        <td>
                            <a href="#">
                                人气<span class="glyphicon glyphicon-arrow-down"></span>
                            </a>
                        </td>
                        <td>
                            <a href="#">
                                新品<span class="glyphicon glyphicon-arrow-down"></span>
                            </a>
                        </td>
                        <td>
                            <a href="#">
                                销量<span class="glyphicon glyphicon-arrow-down"></span>
                            </a>
                        </td>
                        <td>
                            <a href="#">
                                价格<span class="glyphicon glyphicon-arrow-down"></span>
                            </a>
                        </td>
                    </tr>
                </tbody>
            </table>
            <table class="categorySortBarTable">
                <tbody>
                    <tr>
                        <td>
                            <input type="text" placeholder="请输入" class="sortBarPrice beginPrice">
                        </td>
                        <td class="grayColumn priceMiddleColumn">
                            -
                        </td>
                        <td>
                            <input type="text" placeholder="请输入" class="sortBarPrice endPrice">
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="categoryProducts">
        <div price="799.2" class="productUnit">
            <div class="productUnitFrame">
                <a href="#">
                    <img width="100px" src="http://how2j.cn/tmall/img/productSingle_middle/7058.jpg" class="productImage">
                </a>
                <span class="productPrice">¥799.20</span>
                <a href="#" class="productLink">
                    MAXFEEL休闲男士手包真皮手拿包大容量信封包手抓包夹包软韩版潮
                </a>
                <a href="#" class="tmallLink">天猫专卖</a>
                <div class="show1 productInfo">
                    <span class="monthDeal">月成交<span class="productDealNumber">16笔</span></span>
                    <span class="productReview">评价<span class="productReviewNumber">14</span></span>
                    <span class="wangwang">
                        <a href="#" class="wangwanglink">
                            <img src="http://how2j.cn/tmall/img/site/wangwang.png">
                        </a>
                    </span>
                </div>
            </div>
        </div>
        <div price="511.2" class="productUnit">
            <div class="productUnitFrame">
                <a href="#">
                    <img width="100px" src="http://how2j.cn/tmall/img/productSingle_middle/7047.jpg" class="productImage">
                </a>
                <span class="productPrice">¥511.20</span>
                <a href="#nowhere" class="productLink">
                    宾度 男士手包真皮大容量手拿包牛皮个性潮男包手抓包软皮信封包
                </a>
                <a href="#" class="tmallLink">天猫专卖</a>
                <div class="show1 productInfo">
                    <span class="monthDeal">月成交<span class="productDealNumber">49笔</span></span>
                    <span class="productReview">评价<span class="productReviewNumber">18</span></span>
                    <span class="wangwang">
                        <a href="#" class="wangwanglink">
                            <img src="http://how2j.cn/tmall/img/site/wangwang.png">
                        </a>
                    </span>
                </div>
            </div>
        </div>
        <div price="448.2" class="productUnit">
            <div class="productUnitFrame">
                <a href="#nowhere">
                    <img width="100px" src="http://how2j.cn/tmall/img/productSingle_middle/7036.jpg" class="productImage">
                </a>
                <span class="productPrice">¥448.20</span>
                <a href="#nowhere" class="productLink">
                    唯美诺新款男士手包男包真皮大容量小羊皮手拿包信封包软皮夹包潮
                </a>
                <a href="#nowhere" class="tmallLink">天猫专卖</a>
                <div class="show1 productInfo">
                    <span class="monthDeal ">月成交 <span class="productDealNumber">34笔</span></span>
                    <span class="productReview">评价<span class="productReviewNumber">16</span></span>
                    <span class="wangwang">
                    <a href="#nowhere" class="wangwanglink">
                        <img src="http://how2j.cn/tmall/img/site/wangwang.png">
                    </a>
                    </span>
                </div>
            </div>
        </div>
        <div price="411.60" class="productUnit">
        <div class="productUnitFrame">
            <a href="#nowhere">
                <img width="100px" src="http://how2j.cn/tmall/img/productSingle_middle/7025.jpg" class="productImage">
            </a>
            <span class="productPrice">¥411.60</span>
            <a href="#nowhere" class="productLink">
                英伦邦纹男士手包牛皮大容量真皮手拿包手抓包双拉链商务正品软皮
            </a>
            <a href="#nowhere" class="tmallLink">天猫专卖</a>
            <div class="show1 productInfo">
                <span class="monthDeal ">月成交 <span class="productDealNumber">25笔</span></span>
                <span class="productReview">评价<span class="productReviewNumber">19</span></span>
                <span class="wangwang">
                    <a href="#nowhere" class="wangwanglink">
                        <img src="http://how2j.cn/tmall/img/site/wangwang.png">
                    </a>
                </span>
            </div>
        </div>
    </div>
    <div price="157.25" class="productUnit">
        <div class="productUnitFrame">
            <a href="#nowhere">
                <img width="100px" src="http://how2j.cn/tmall/img/productSingle_middle/7014.jpg" class="productImage">
            </a>
            <span class="productPrice">¥157.25</span>
            <a href="#nowhere" class="productLink">
                劳迪莱斯男士手包休闲手拿包牛皮大容量钱包男包软面小包包手抓包
            </a>
            <a href="#nowhere" class="tmallLink">天猫专卖</a>
            <div class="show1 productInfo">
                <span class="monthDeal ">月成交 <span class="productDealNumber">17笔</span></span>
                <span class="productReview">评价<span class="productReviewNumber">16</span></span>
                <span class="wangwang">
                    <a href="#nowhere" class="wangwanglink">
                        <img src="http://how2j.cn/tmall/img/site/wangwang.png">
                    </a>
                    </span>
            </div>
        </div>
    </div>
    <div price="268.2" class="productUnit">
        <div class="productUnitFrame">
            <a href="#nowhere">
                <img width="100px" src="http://how2j.cn/tmall/img/productSingle_middle/7003.jpg" class="productImage">
            </a>
            <span class="productPrice">¥268.20</span>
            <a href="#nowhere" class="productLink">
                帕朗尼男士手拿包真皮手包商务休闲头层牛皮软牛皮大容量休闲钱包
            </a>
            <a href="#nowhere" class="tmallLink">天猫专卖</a>
            <div class="show1 productInfo">
                <span class="monthDeal ">月成交 <span class="productDealNumber">5笔</span></span>
                <span class="productReview">评价<span class="productReviewNumber">19</span></span>
                <span class="wangwang">
                    <a href="#nowhere" class="wangwanglink">
                        <img src="http://how2j.cn/tmall/img/site/wangwang.png">
                    </a>
                    </span>
            </div>
        </div>
    </div>
    <div price="233.4" class="productUnit">
        <div class="productUnitFrame">
            <a href="#nowhere">
                <img width="100px" src="http://how2j.cn/tmall/img/productSingle_middle/6992.jpg" class="productImage">
            </a>
            <span class="productPrice">¥233.40</span>
            <a href="#nowhere" class="productLink">
                编织手包手拿包男信封大容量手抓包真皮韩版潮商务休闲牛皮男包
            </a>
            <a href="#nowhere" class="tmallLink">天猫专卖</a>
            <div class="show1 productInfo">
                <span class="monthDeal ">月成交 <span class="productDealNumber">8笔</span></span>
                <span class="productReview">评价<span class="productReviewNumber">19</span></span>
                <span class="wangwang">
                    <a href="#nowhere" class="wangwanglink">
                        <img src="http://how2j.cn/tmall/img/site/wangwang.png">
                    </a>
                    </span>
            </div>
        </div>
    </div>
    <div price="952.0" class="productUnit">
        <div class="productUnitFrame">
            <a href="#nowhere">
                <img width="100px" src="http://how2j.cn/tmall/img/productSingle_middle/6981.jpg" class="productImage">
            </a>
            <span class="productPrice">¥952.00</span>
            <a href="#nowhere" class="productLink">
                犟牛男士手包真皮手拿包头层牛皮商务大容量手抓包软皮夹包信封包
            </a>
            <a href="#nowhere" class="tmallLink">天猫专卖</a>
            <div class="show1 productInfo">
                <span class="monthDeal ">月成交 <span class="productDealNumber">20笔</span></span>
                <span class="productReview">评价<span class="productReviewNumber">16</span></span>
                <span class="wangwang">
                    <a href="#nowhere" class="wangwanglink">
                        <img src="http://how2j.cn/tmall/img/site/wangwang.png">
                    </a>
                    </span>
            </div>
        </div>
    </div>
    <div style="clear: both;"></div>
    </div>
    <div class="change">
        <button class="btn btn-info btn-lg" id="home">首页</button>
        <button class="btn btn-primary btn-lg" id="public">公共页</button>
        <button class="btn btn-success btn-lg" id="product">产品页</button>
        <button class="btn btn-danger btn-lg" id="gouwuche">购物车</button>
        <button class="btn btn-info btn-lg" id="order">订单</button>
    </div>
</body>
<script>
    $("button#home").click(function () {
       location.href = "http://localhost:8080/manage_war/tianmao/home.jsp#nowhere";
    });
    $("button#public").click(function () {
        location.href = "http://localhost:8080/manage_war/tianmao/public.jsp#nowhere";
    });
    $("button#product").click(function () {
        location.href = "http://localhost:8080/manage_war/tianmao/product.jsp#nowhere";
    });
    $("button#gouwuche").click(function () {
        location.href = "http://localhost:8080/manage_war/tianmao/gouwuche.jsp#nowhere";
    });
    $("button#order").click(function () {
        location.href = "http://localhost:8080/manage_war/tianmao/order.jsp#nowhere";
    });
    $("input.sortBarPrice").keyup(function () {
       var num = $(this).val();
       if (num.length == 0) {
           $("div.productUnit").show();
           return;
       }
       num = parseInt(num);
       if (isNaN(num)) {
           num = 1;
       }
       if (num <= 0) {
           num = 1;
       }
       //下面这句话什么意思
       // $(this).val(num);
       var begin = $("input.beginPrice").val();
       var end = $("input.endPrice").val();
       //两个都有数的时候
       if ((!isNaN(begin)) && (!isNaN(end))) {
           $("div.productUnit").hide();
           $("div.productUnit").each(function () {
               var price = $(this).attr("price");
               // console.log("before:" + price);
               price = new Number(price);
               // console.log("after" + price);
               if (price <= end && price >= begin) {
                   $(this).show();
               }
           })
       }
    });

</script>
</html>