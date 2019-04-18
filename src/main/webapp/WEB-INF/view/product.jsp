<%--
  Created by IntelliJ IDEA.
  User: VULCAN
  Date: 2019/2/13
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>产品页</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="http://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
</head>
<style>
    body {
        font-size: 12px;
        font-family: Arial;
    }
    div.imgAndInfo {
        margin: 40px 20px;
    }
    div.imgInimgAndInfo {
        display: inline-block;
        width: 400px;
        float: left;
    }
    div.imgAndInfo img.bigImg {
        width: 400px;
        height: 400px;
        padding: 20px;
        border: 1px solid #f2f2f2;
    }
    div.imgAndInfo div.smallImageDiv {
        width: 80%;
        margin: 20px auto;
    }
    div.imgAndInfo img.smallImage {
        width: 60px;
        height: 60px;
        border: 2px solid white;
    }
    div.imgAndInfo img.smallImage:hover {
        border: 2px solid black;
    }
    div.infoInimgAndInfo {
        padding: 0px 20px;
        overflow: hidden;
    }
    div.infoInimgAndInfo div.productTitle {
        color: black;
        font-size: 16px;
        font-weight: bold;
        margin: 0px 10px;
    }
    div.infoInimgAndInfo div.productSubTitle {
        color: #DD2727;
        margin: 0px 10px;
    }
    div.infoInimgAndInfo div.juhuasuan {
        background-color: #2DA77A;
        color: white;
        text-align: center;
        line-height: 40px;
        margin-top: 10px;
    }
    div.infoInimgAndInfo span.juhuasuanBig {
        font-size: 18px;
        font-weight: bold;
        font-family: 黑体;
    }
    div.infoInimgAndInfo span.juhuasuanTime {
        color: #FFC057;
        font-weight: bold;
    }
    div.infoInimgAndInfo div.productPriceDiv {
        background-image: url(http://how2j.cn/tmall/img/site/priceBackground.png);
        height: 102px;
        padding: 10px;
        color: #666666;
    }
    div.infoInimgAndInfo div.gouwujuanDiv {
        margin-top: 5px;
    }
    div.infoInimgAndInfo div.originalDiv {
        margin-top: 5px;
    }
    div.infoInimgAndInfo span.originalPriceDesc {
        color: #999999;
        display: inline-block;
        width: 68px;
    }
    div.infoInimgAndInfo span.originalPrice {
        font-family: Arial;
        font-size: 12px;
        color: #333333;
        text-decoration: line-through;
    }
    div.infoInimgAndInfo span.promotionPriceYuan {
        font-family: Arial;
        font-size: 18px;
        color: #C40000;
    }
    div.infoInimgAndInfo span.promotionPrice {
        color: #C40000;
        font-family: Arial;
        font-size: 30px;
        font-weight: bold;
    }
    div.infoInimgAndInfo span.promotionPriceDesc {
        color: #999999;
        display: inline-block;
        width: 68px;
        position: relative;
        left: 0px;
        top: -10px;
    }
    div.infoInimgAndInfo div.productSaleAndReviewNumber {
        margin: 20px 0px;
        border-top: 1px dotted #C9C9C9;
        border-bottom: 1px dotted #C9C9C9;
        padding: 10px;
    }
    div.infoInimgAndInfo div.productSaleAndReviewNumber div {
        display: inline-block;
        width: 49%;
        text-align: center;
        color: #999999;
        font-size: 12px;
    }
    div.infoInimgAndInfo div.productSaleAndReviewNumber div:first-child {
        border-right: 1px solid #E5DFDA;
    }
    div.infoInimgAndInfo div.productNumber {
        color: #999999;
    }
    div.infoInimgAndInfo span.productNumberSettingSpan {
        border: 1px solid #999;
        display: inline-block;
        width: 43px;
        height: 32px;
        padding-top: 7px;
    }
    div.infoInimgAndInfo input.productNumberSetting {
        border: 0px;
        height: 80%;
        width: 80%;
    }
    div.productNumber span.updown img{
        display: inline-block;
        vertical-align: top;
    }
    div.productNumber span.updown {
        border: 1px solid #999;
        display: block;
        width: 20px;
        height: 14px;
        text-align: center;
        padding-top: 4px;
    }
    div.productNumber span.updownMiddle {
        height: 4px;
        display: block;
    }
    div.productNumber span.arrow {
        display: inline-block;
        width: 22px;
        height: 32px;
        vertical-align: top;
    }
    div.serviceCommitment {
        margin: 20px 0px;
    }
    div.infoInimgAndInfo span.serviceCommitmentDesc {
        color: #999999;
    }
    div.infoInimgAndInfo span.serviceCommitmentLink a {
        color: #666666;
    }
    div.buyDiv {
        margin: 20px auto;
        text-align: center;
    }
    div.buyDiv button {
        display: inline-block;
        margin: 0px 10px;
        width: 180px;
        height: 40px;
    }
    button.buyButton {
        border: 1px solid #C40000;
        background-color: #FFEDED;
        text-align: center;
        line-height: 40px;
        font-size: 16px;
        color: #C40000;
        font-family: Arial;
    }
    button.addCartButton {
        border: 1px solid #C40000;
        background-color: #C40000;
        text-align: center;
        font-size: 16px;
        line-height: 40px;
        color: white;
        font-family: Arial;
    }
    button.addCartButton span.glyphicon {
        font-size: 12px;
        margin-right: 8px;
    }
    a:hover {
        text-decoration: none;
    }
    .redColor {
        color: #C40000;
    }
    .boldWord {
        font-weight: bold;
    }
    div.productDetailDiv {
        width: 790px;
        margin: 40px auto;
    }
    div.productDetailTopPart {
        border: 1px solid #DFDFDF;
        border-left-width: 0px;
    }
    a.selected {
        border-left: 1px solid #CFBFB1;
        border-right: 1px solid #CFBFB1;
        color: #B10000;
        display: inline-block;
        font-weight: bold;
        line-height: 46px;
        width: 90px;
        text-align: center;
        position: relative;
    }
    a.selected:before {
        content: "";
        display: block;
        border: 1px solid #B00000;
        width: 90px;
        height: 0;
        position: absolute;
        top: -1px;
        margin-left: -1px;
    }
    a.selected:after {
        content: "";
        display: block;
        border-color: #B00000 transparent transparent;
        border-style: solid;
        border-width: 5px;
        width: 0;
        height: 0;
        position: absolute;
        top: -1px;
        left: 50%;
        margin-left: -5px;
    }
    a.productDetailReviewLink {
        padding: 0px 20px;
        border-right: 1px dotted #D2D2D2;
        color: #333333;
    }
    span.productDetailTopReviewLinkNumber {
        color: #3355B9;
    }
    div.productParamterPart {
        border: 1px solid #DFDFDF;
        padding: 40px;
    }
    div.productParamter {
        color: #999999;
        font-weight: bold;
        margin-bottom: 20px;
    }
    div.productParamterList span{
        display: block;
        width: 220px;
        float: left;
        padding: 8px 0px;
        color: #666666;
    }
    div.productDetailImagesPart img {
        display: block;
        width: 790px;
        margin: 20px 0px;
    }
    body {
        font-size: 12px;
        font-family: Arial;
    }
    a:hover {
        text-decoration: none;
        color: #C40000;
    }
    div.productReviewDiv {
        width: 790px;
        margin: 40px auto;
    }
    div.productReviewTopPart {
        border-right: 1px solid #DFDFDF;
    }
    a.productReviewTopPartSelectedLink {
        padding: 0px 20px;
        color: #333333;
    }
    div.productReviewContentPart {
        padding-top: 50px;
    }
    div.productReviewItem {
        border-bottom: 1px solid #E3E3E3;
        margin: 10px 0px;
    }
    div.productReviewItem div.productReviewItemDesc {
        width: 80%;
        display: inline-block;
        color: #333333;
        margin: 5px 20px;
        float: left;
    }
    div.productReviewItemDate {
        margin: 15px 0px 0px;
        color: #CCCCCC;
    }
    div.productReviewItem div.productReviewItemUserInfo {
        color: #404040;
        margin: 5px 20px;
        padding: 20px 0;
    }
    span.userInfoGrayPart {
        color: #999999;
    }
</style>
<body>
    <div class="imgAndInfo">
        <div class="imgInimgAndInfo">
            <img width="100px" class="bigImg" src="http://how2j.cn/tmall/img/productSingle/8619.jpg">
            <div class="smallImageDiv">
                <img width="56px" class="smallImage"
                     bigImageURL = "http://how2j.cn/tmall/img/productSingle/8620.jpg"
                     src="http://how2j.cn/tmall/img/productSingle_small/8620.jpg">
                <img width="56px" class="smallImage"
                     bigImageURL = "http://how2j.cn/tmall/img/productSingle/8619.jpg"
                     src="http://how2j.cn/tmall/img/productSingle_small/8619.jpg">
                <img width="56px" class="smallImage"
                     bigImageURL = "http://how2j.cn/tmall/img/productSingle/8618.jpg"
                     src="http://how2j.cn/tmall/img/productSingle_small/8618.jpg">
                <img width="56px" class="smallImage"
                     bigImageURL = "http://how2j.cn/tmall/img/productSingle/8617.jpg"
                     src="http://how2j.cn/tmall/img/productSingle_small/8617.jpg">
                <img width="56px" class="smallImage"
                     bigImageURL = "http://how2j.cn/tmall/img/productSingle/8616.jpg"
                     src="http://how2j.cn/tmall/img/productSingle_small/8616.jpg">
            </div>
            <div class="img4load hidden"></div>
        </div>
        <div style="clear: both;"></div>
        <div class="infoInimgAndInfo">
            <div class="productTitle">
                名媛2016新款女装夏两件套裤韩版雪纺七分裤阔腿裤休闲时尚套装女
            </div>
            <div class="productSubTitle">
                高端定制 高档棉麻 现货即发 加购物车 领优惠卷
            </div>
            <div class="productPrice">
                <div class="juhuasuan">
                    <span class="juhuasuanBig">聚划算</span>
                    <span>此商品即将参加聚划算，<span class="juhuasuanTime">1天19小时</span>后开始</span>
                </div>
                <div class="productPriceDiv">
                    <div class="gouwujuanDiv">
                        <img height="16px" src="http://how2j.cn/tmall/img/site/gouwujuan.png">
                        <span>全天猫实物商品通用</span>
                    </div>
                    <div class="originalDiv">
                        <span class="originalPriceDesc">价格</span>
                        <span class="originalPriceYuan">¥</span>
                        <span class="originalPrice">
                            399.00
                        </span>
                    </div>
                    <div class="promotionDiv">
                        <span class="promotionPriceDesc">促销价</span>
                        <span class="promotionPriceYuan">¥</span>
                        <span class="promotionPrice">
                            379.05
                        </span>
                    </div>
                </div>
            </div>
            <div class="productSaleAndReviewNumber">
                <div>销量<span class="redColor boldWord">50</span></div>
                <div>累计评价<span class="redColor boldWord">19</span></div>
            </div>
            <div class="productNumber">
                <span>数量</span>
                <span>
                    <span class="productNumberSettingSpan">
                        <input type="text" value="1" class="productNumberSetting">
                    </span>
                    <span class="arrow">
                        <a class="increaseNumber" href="#">
                            <span class="updown">
                                <img src="http://how2j.cn/tmall/img/site/increase.png">
                            </span>
                        </a>
                        <span class="updownMiddle"></span>
                        <a class="decreaseNumber" href="#">
                            <span class="updown">
                                <img src="http://how2j.cn/tmall/img/site/decrease.png">
                            </span>
                        </a>
                    </span>
                    件
                </span>
                <span>库存66件</span>
            </div>
            <div class="serviceCommitment">
                <span class="serviceCommitmentDesc">服务承诺</span>
                <span class="serviceCommitmentLink">
                    <a href="#">正品保证</a>
                    <a href="#">极速退款</a>
                    <a href="#">赠运费险</a>
                    <a href="#">七天无理由退换</a>
                </span>
            </div>
            <div class="buyDiv">
                <a href="#" class="buyLink">
                    <button class="buyButton">立即购买</button>
                </a>
                <a class="addCartLink" href="#">
                    <button class="addCartButton">
                        <span class="glyphicon glyphicon-shopping-cart"></span>加入购物车
                    </button>
                </a>
            </div>
            <div style="clear: both"></div>
        </div>
    </div>
    <div class="productDetailDiv" style="display: block;">
        <div class="productDetailTopPart">
            <a class="productDetailTopPartSelectedLink selected" href="#">商品详情</a>
            <a class="productDetailReviewLink" href="#">
                累计评价<span class="productDetailTopReviewLinkNumber">19</span>
            </a>
        </div>
        <div class="productParamterPart">
            <div class="productParamter">产品参数:</div>
            <div class="productParamterList">
                <span>材质成分:  聚酯纤维100% </span>
                <span>品牌:  Emyche/艾米秋 </span>
                <span>货号:  129668 </span>
                <span>尺码:  L M S XL X </span>
                <span>上市年份季节:  2016年夏季 </span>
                <span>颜色分类:  白色 黑色 浅蓝 </span>
                <span>适用年龄:  18-25周岁 </span>
            </div>
            <div style="clear:both;"></div>
        </div>
        <div class="productDetailImagesPart">
            <img src="http://how2j.cn/tmall/img/productDetail/8626.jpg">
        </div>
    </div>
    <div class="productPageDiv">
        <div class="productReviewDiv" style="display: none;">
            <div class="productReviewTopPart">
                <a class="productReviewTopPartSelectedLink" href="#">商品详情</a>
                <a class="selected" href="#">
                    累计评价<span class="productReviewTopReviewLinkNumber">19</span>
                </a>
            </div>
            <div class="productReviewContentPart">
                <div class="productReviewItem">
                    <div class="productReviewItemDesc">
                        <div class="productReviewItemContent">
                            不错的购物，包装很精细，布料也不错舒服，因为之前一直购买品牌装，很好的一次网购，生完宝宝刚刚一个多月我一百五十斤穿2XL不错
                        </div>
                        <div class="productReviewItemDate">
                            2016-08-10
                        </div>
                    </div>
                    <div class="productReviewItemUserInfo">
                        哀****莉<span class="userInfoGrayPart">（匿名）</span>
                    </div>
                    <div style="clear: both"></div>
                </div>
                <div class="productReviewItem">
                    <div class="productReviewItemDesc">
                        <div class="productReviewItemContent">
                            面料很好，大小也刚刚好，本来买之前还担心，犹豫，等货到了，比我心中想象的好很多，大家放心购买，很满意的购物，以后还会来??
                        </div>
                        <div class="productReviewItemDate">2016-08-23</div>
                    </div>
                    <div class="productReviewItemUserInfo">
                        贤*闲<span class="userInfoGrayPart">（匿名）</span>
                    </div>
                    <div style="clear:both"></div>
                </div>
                <div class="productReviewItem">
                    <div class="productReviewItemDesc">
                        <div class="productReviewItemContent">
                            <span>商品：</span>宝贝料子和手感都不错，是值这价，卖家很贴心的送了内衣带，5分好评是必须的。喜欢的可以 下手了
                        </div>
                        <div class="productReviewItemDate">2016-08-16</div>
                    </div>
                    <div class="productReviewItemUserInfo">
                        孤*****爱<span class="userInfoGrayPart">（匿名）</span>
                    </div>
                    <div style="clear:both"></div>
                </div>
                <div class="productReviewItem">
                    <div class="productReviewItemDesc">
                        <div class="productReviewItemContent">
                            衣服收到了，给朋友买的，真心不错，料子也特别好，穿起来特别有气质，跟模特穿的效果差不多。。。好评，好评
                        </div>
                        <div class="productReviewItemDate">2016-08-19</div>
                    </div>
                    <div class="productReviewItemUserInfo">
                        哀****莉<span class="userInfoGrayPart">（匿名）</span>
                    </div>
                    <div style="clear:both"></div>
                </div>
                <div class="productReviewItem">
                    <div class="productReviewItemDesc">
                        <div class="productReviewItemContent">
                            衣服不错，手感不错，试穿很有型，真心的喜欢，谢谢老板
                        </div>
                        <div class="productReviewItemDate">2016-07-19</div>
                    </div>
                    <div class="productReviewItemUserInfo">
                        孤*****爱<span class="userInfoGrayPart">（匿名）</span>
                    </div>
                    <div style="clear:both"></div>
                </div>
                <div class="productReviewItem">
                    <div class="productReviewItemDesc">
                        <div class="productReviewItemContent">
                            喜欢(⊙o⊙)哦，美美哒，值得一试！！！宽松雪纺，很舒服
                        </div>
                        <div class="productReviewItemDate">2016-08-10</div>
                    </div>
                    <div class="productReviewItemUserInfo">
                        这****0<span class="userInfoGrayPart">（匿名）</span>
                    </div>
                    <div style="clear:both"></div>
                </div>
                <div class="productReviewItem">
                    <div class="productReviewItemDesc">
                        <div class="productReviewItemContent">
                            衣服已经收到，大小很合适，面料也可以，就是感觉衣服面料有一点厚，其他都还不错，款式也好看，希望店家多出一点新款，以后回来光顾你们。?
                        </div>
                        <div class="productReviewItemDate">2016-07-10</div>
                    </div>
                    <div class="productReviewItemUserInfo">
                        y****心<span class="userInfoGrayPart">（匿名）</span>
                    </div>
                    <div style="clear:both"></div>
                </div>
                <div class="productReviewItem">
                    <div class="productReviewItemDesc">
                        <div class="productReviewItemContent">
                            很漂亮的衣服，料子有垂感，我很满意，老公也说好呢
                        </div>
                        <div class="productReviewItemDate">2016-07-27</div>
                    </div>
                    <div class="productReviewItemUserInfo">
                        阿*丶<span class="userInfoGrayPart">（匿名）</span>
                    </div>
                    <div style="clear:both"></div>
                </div>
                <div class="productReviewItem">
                    <div class="productReviewItemDesc">
                        <div class="productReviewItemContent">
                            真不错，已经迫不及待的穿上了，朋友们都说好，挺有气质的，就是上衣稍微有点肥，不过肉肉被藏起来了！有看上的赶紧下单吧！
                        </div>
                        <div class="productReviewItemDate">2016-08-12</div>
                    </div>
                    <div class="productReviewItemUserInfo">
                        名*****_<span class="userInfoGrayPart">（匿名）</span>
                    </div>
                    <div style="clear:both"></div>
                </div>
                <div class="productReviewItem">
                    <div class="productReviewItemDesc">
                        <div class="productReviewItemContent">
                            质量是真的不错，适合个子高一点的美眉穿，很漂亮?
                        </div>
                        <div class="productReviewItemDate">2016-08-03</div>
                    </div>
                    <div class="productReviewItemUserInfo">
                        安**°<span class="userInfoGrayPart">（匿名）</span>
                    </div>
                    <div style="clear:both"></div>
                </div>
                <div class="productReviewItem">
                    <div class="productReviewItemDesc">
                        <div class="productReviewItemContent">
                            再买的时候纠结了好久，最后还是选择在这里购买，一次满意的网购，让我对网购有了希望，或刚收到，因觉得物超所值，就迫不及待拍了照片，那个玻璃的声音非常脆，我个人觉得挺好的，值得拥有！
                        </div>
                        <div class="productReviewItemDate">2016-08-08</div>
                    </div>
                    <div class="productReviewItemUserInfo">
                        这****0<span class="userInfoGrayPart">（匿名）</span>
                    </div>
                    <div style="clear:both"></div>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="http://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<script src="http://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
<script>
    $(".productReviewTopPartSelectedLink").click(function () {
        $(".productReviewDiv").hide();
        $(".productDetailDiv").show();
    });
    $(".productDetailReviewLink").click(function () {
        $(".productReviewDiv").show();
        $(".productDetailDiv").hide();
    });
        var stock = 66;
        $(".productNumberSetting").keyup(function () {
            var num = $(".productNumberSetting").val();
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
            $(".productNumberSetting").val(num);
        });
        $(".increaseNumber").click(function () {
            var num = $(".productNumberSetting").val();
            num = parseInt(num);
            num++;
            if (num > stock) {
                num = stock;
            }
            $(".productNumberSetting").val(num);
        });
       $(".decreaseNumber").click(function () {
           var num = $(".productNumberSetting").val();
           num = parseInt(num);
           num--;
           if (num <= 0) {
               num = 1;
           }
           $(".productNumberSetting").val(num);
       });
    // $("img.bigImg").load(
    //   function () {
    //       $("img.smallImage").each(function () {
    //          var bigImageURL = $(this).attr("bigImageURL");
    //          img = new Image();
    //          img.src = bigImageURL;
    //          img.onload = function (ev) {
    //              // console.log(bigImageURL);
    //              $("div.img4load").append($(img));
    //          };
    //       });
    //   }
    // );
    $("img.smallImage").mouseenter(function () {
        var bigImageURL = $(this).attr("bigImageURL");
        $("img.bigImg").attr("src", bigImageURL);
    });
</script>
</html>
