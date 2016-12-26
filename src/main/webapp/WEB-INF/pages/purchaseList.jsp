<%@ page import="com.bingcore.web.enums.MapType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-cn">
<jsp:include page="attach/header.jsp"></jsp:include>
<body>
<jsp:include page="attach/menu.jsp"/>

<div class="js-comp container" data-module="TaskPurchase">
    <ol class="breadcrumb">
        <li><a href="#">商品页</a></li>
        <li class="active">购买记录</li>
    </ol>
    <div class="portlet box blue">
        <div class="portlet-body">
            <div class="portlet-body form">
                <form class="form-inline select-form" method="get" action="${ctx}/web/goods/purchase/query">
                    <div class="form-group col-xs-2">
                        <label class="control-label sr-only">起始日期</label>

                        <div class="input-group date" id="st">
                            <input id="startDate" type="text" class="form-control" data-date-format="YYYY-MM-DD"
                                   placeholder="起始日期" name="startDate" value="${startDate}"/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                        </div>
                    </div>
                    <div class="form-group col-xs-2">
                        <label class="control-label sr-only">结束日期</label>

                        <div class="input-group date" id="et">
                            <input id="endDate" type="text" class="form-control" data-date-format="YYYY-MM-DD"
                                   placeholder="结束日期" name="endDate" value="${endDate}"/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                        </div>
                    </div>
                    <div class="form-group">
                        <select name="searchName" class="form-control select-search-name"
                                data-search-name="${searchName}">
                            <option value="orderNumber" <c:if test="${searchName == 'orderNumber'}">selected</c:if>>
                                系统单号
                            </option>
                            <option value="goodsId" <c:if test="${searchName == 'goodsId'}">selected</c:if>>商品</option>
                            <option value="city" <c:if test="${searchName == 'city'}">selected</c:if>>城市
                            </option>
                        </select>
                    </div>
                    <div class="form-group search-item">
                        <input class="form-control search-field" type="text" name="searchField" value="${searchField}"
                               placeholder="关键字搜索" maxlength="20">
                    </div>
                    <button type="submit" class="btn btn-primary">搜索</button>
                    <button type="button" class="btn clear-form">清除</button>
                </form>
            </div>
        </div>

        <hr/>
        <div class="row">
            <table class="table table-striped table-bordered table-hover" id="role_table">
                <thead>
                <tr>
                    <th scope="col">订单号</th>
                    <th scope="col">商品ID</th>
                    <th scope="col">购买城市</th>
                    <th scope="col">购买数量</th>
                    <th scope="col">购买金额</th>
                    <th scope="col">购买结果</th>
                    <th scope="col">下单时间</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageData.data}" var="goodsDetail">
                    <tr>
                        <td>
                                ${goodsDetail.orderNumber}
                        </td>
                        <td>
                            <c:if test="${goodsDetail.goodsId==1}">
                                <%=MapType.getByType(1).getName()%>
                            </c:if>
                            <c:if test="${goodsDetail.goodsId==2}">
                                <%=MapType.getByType(2).getName()%>
                            </c:if>
                            <c:if test="${goodsDetail.goodsId==3}">
                                <%=MapType.getByType(3).getName()%>
                            </c:if>
                        </td>
                        <td>
                                ${goodsDetail.city}
                        </td>
                        <td>
                                ${goodsDetail.count}
                        </td>
                        <td>
                                ${goodsDetail.monetary}
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${goodsDetail.success}">
                                    成功
                                </c:when>
                                <c:otherwise>
                                    失败
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                                ${goodsDetail.placeTime}
                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="task-purchase-pagination" data-total="${pageData.total}">
            </div>
        </div>

    </div>
</div>
</div>
<hr/>

</div>

<!-- Site footer -->
<script src="<%=request.getContextPath()%>/static/js/task-purchase.js?v=1429865877"></script>
<jsp:include page="attach/footer.jsp"></jsp:include>
</body>
</html>