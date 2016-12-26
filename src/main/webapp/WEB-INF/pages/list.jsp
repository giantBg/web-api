<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-cn">
<jsp:include page="attach/header.jsp"></jsp:include>

<body>
<jsp:include page="attach/menu.jsp"/>

<div class="container">
    <ol class="breadcrumb">
        <li><a href="#">商品页</a></li>
        <li class="active">商品列表</li>
    </ol>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">集团产品</h3>
        </div>
        <div class="panel-body">
            <c:forEach items="${goodsList}" var="goods">
                <div class="col-sm-6 col-md-3">
                    <div class="thumbnail">
                        <img src="${ctx}/static/images/${goods.image}"
                             alt="${goods.name}">
                    </div>
                    <div class="caption">
                        <br/>

                        <h3 style="color: red;">￥${goods.price}</h3>

                        <p><a href="${goods.goodsUrl}" target="_blank">${goods.remark}<span
                                class="badge pull-right">${goods.count}</span></a></p>

                        <p>
                            <a href="/web/goods/purchase?id=${goods.id}" class="btn btn-primary"
                               role="button">
                                立即购买
                            </a>
                            <a href="javascript:void(0);" class="btn btn-default" role="button"
                               onclick="alert('加入成功!')">
                                加入购物车
                            </a>
                        </p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<hr/>

</div>

<!-- Site footer -->
<jsp:include page="attach/footer.jsp"></jsp:include>
</body>
</html>