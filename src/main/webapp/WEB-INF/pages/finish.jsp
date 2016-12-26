<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
    <script language="JavaScript">
        window.history.forward(1); //禁用浏览器返回按钮
    </script>
    <!-- END PAGE LEVEL PLUGIN STYLES -->
</head>
<jsp:include page="attach/header.jsp"></jsp:include>
<body>
<jsp:include page="attach/menu.jsp"/>
<!-- BEGIN PLACE ORDER -->
<div class="container">
    <ol class="breadcrumb">
        <li><a href="#">商品页</a></li>
        <li class="active">购买结果</li>
    </ol>

    <div class="row">
        <div class="panel panel-default margin-top-20 margin-left-10 margin-right-10">
            <div class="panel-heading">
                <c:choose>
                <c:when test="${success}">
                <h3 style="color:#000000;"><em><i class="icon-ok color-green"></i></em> 感谢您使用BC集团服务，订单提交成功！</h3></div>
            </c:when>
            <c:otherwise>
                <h3 style="color:red"><em><i class="icon-lock color-red"></i></em>订单提交失败!</h3>

                <div class="alert alert-danger">
                    <h4 class="block">抱歉，订单出问题了...😓</h4>

                    <p>
                        失败原因：${errMsg}
                    </p>
                </div>
            </c:otherwise>
            </c:choose>
        </div>


        <div class="panel-body">
            <c:if test="${firstLogin}">
                <div class="alert alert-success">
                    <h4 class="block">欢迎使用BC集团服务</h4>

                    <p>
                        登录闪送服务后台，可以管理您的BC订单，查看您本次的消费记录。
                        您还可以查看您在使用BC集团服务时，收到的消息。
                    </p>
                </div>
            </c:if>


            <ul class="list-inline alert alert-warning">
                <li>订单号码：<a>${orderNumber}</a></li>
                <li>订单金额：${money}元</li>
                <li>购买数量：${buyCount}</li>
            </ul>

            <p>
                点这里查看<a href="/web/goods/purchase/query">【我的订单】</a> 或 <a href="/web/goods/list">【继续购买】</a>
            </p>

        </div>
    </div>
</div>
</div>

<!-- Site footer -->
<jsp:include page="attach/footer.jsp"></jsp:include>
</body>

</html>
