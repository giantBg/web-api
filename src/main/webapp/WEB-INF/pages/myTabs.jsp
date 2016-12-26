<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<jsp:include page="attach/header.jsp"></jsp:include>
<body>
<jsp:include page="attach/menu.jsp"/>
<div class="container">
    <form id="exchangeForm" class="exchange-form" action="${ctx}/admin/my-exchange">
        <div class="input-group input-medium margin-bottom-20">
            <input type="text" placeholder="请输入兑换码" class="form-control coupon-number" id="Number" name="Number"/>
            <span class="input-group-btn"><button class="btn yellow exchange" type="button" id="exchange">兑换
            </button></span>
        </div>
    </form>
    <ul class="nav nav-tabs margin-top-10" id="subtask">
        <li <c:if test="${couponFlag==1}">class="active"</c:if>><a id="today" href="${ctx}/admin/my-coupons-statistics">可用优惠券</a>
        </li>
        <li <c:if test="${couponFlag==2}">class="active"</c:if>><a id="pending"
                                                                   href="${ctx}/admin/my-coupons-statistics-expired">过期优惠券</a>
        </li>
        <li <c:if test="${couponFlag==3}">class="active"</c:if>><a id="tip" href="javascript:;">使用规则</a>
        </li>
    </ul>
    <c:if test="${couponFlag==1 || couponFlag==2}">
        <c:if test="${userCouponStatisticsDtoList!=null}">
            <div id="myCouponContent" class="tab-content">

                table content
            </div>
        </c:if>
    </c:if>
    <c:if test="${couponFlag==3}">
        <div id="helpTip">
            <div class="text-div">
                <b style="color:#04a5e9">1、闪送有哪几种优惠券？</b>

                <p>
                    <c:if test="${couponUnenableCity}">
                        我们有三种优惠券：<br/>
                        <b>1）一口价优惠券：</b>在优惠券限定公里数和重量的范围内，只需支付优惠券面值的金额；<br/>
                        <b>2）直减优惠券：</b>在订单金额上直接减掉优惠券的金额；<br/>
                        <b>3）满减优惠券：</b>当订单金额不低于优惠券的使用金额，可以优惠优惠券对应金额。<br/>
                    </c:if>

                    <c:if test="${!couponUnenableCity}">
                        我们有四种优惠券：<br/>
                        <b>1）一口价优惠券：</b>在优惠券限定公里数和重量的范围内，只需支付优惠券面值的金额；<br/>
                        <b>2）直减优惠券：</b>在订单金额上直接减掉优惠券的金额；<br/>
                        <b>3）满减优惠券：</b>当订单金额不低于优惠券的使用金额，可以优惠优惠券对应金额。<br/>
                        <b>4）折扣卡：</b>在订单金额之上享受折扣卡对应的折扣。<br/>
                    </c:if>
                </p>

                <b style="color:#04a5e9">2、优惠券使用规则？</b>

                <p>

                    1）优惠券只能在其有效期内使用；<br/>
                    2）优惠券每天最多可使用3张；<br/>
                    3）按城市领取的优惠券只能在领取城市使用；<br/>
                    4）新用户首单优惠券只能在微信或app下单使用；<br/>
                    5）优惠券不能转让，不能折现，不能退换；<br/>
                    6）优惠券不能与其他官方优惠活动同时使用，官方活动期间以官方活动为准；<br/>
                    7）优惠券不能抵扣加价费和一取多投的上门费用；<br/>
                    8）<b>被未支付订单占用：</b>未支付的订单中使用的优惠券为被占用状态，如果订单取消，优惠券会立即返还您的账户；<br/>
                    9）优惠券在节假日期间不可用，包括情人节、七夕、母亲节、中秋、圣诞、双十一、双十二、春节。<br/>

                <div class="festivalInfo" style="display: block"><br/>
                    具体不可用日期：<br/>
                    2015.11.10-2015.11.11<br/>
                    2015.12.11-2015.12.12<br/>
                    2015.12.23-2012.12.25<br/>
                    2016.02.02-2016.02.14<br/>
                    2016.05.06-2016.05.08<br/>
                    2016.08.07-2016.08.09<br/>
                    2016.09.14-2016.09.17<br/>
                    2016.11.10-2016.11.11、2016.12.12<br/>
                    2016.12.23-2016.12.25<br/>
                    2017.01.21-2017.02.04<br/>
                </div>


                </p>

                <b style="color:#04a5e9">3、如何获取优惠券？</b>

                <p>
                    1）新用户可以在微信或最新版app领取首单优惠券；<br/>
                    2）已下过单用户在微信或app内晒单，可以获得闪送赠送的优惠券；每人每天最多可获得一张。<br/>
                    3）每完成一单可以给好友分享红包，分享完成后可获得闪送赠送的优惠券。每人每天最多可获得一张。<br/>
                </p>
            </div>
        </div>
    </c:if>
</div>
<!-- Site footer -->
<jsp:include page="attach/footer.jsp"></jsp:include>
</body>
</html>


