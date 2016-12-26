<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                            <option value="goods" <c:if test="${searchName == 'goods'}">selected</c:if>>商品</option>
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
                    <th scope="col">商品</th>
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
                                ${goodsDetail.goods.name}
                        </td>
                        <td>
                                ${goodsDetail.city}
                        </td>
                        <td>
                                ${goodsDetail.count}
                        </td>
                        <td>
                                ${goodsDetail.amt/100}
                        </td>
                        <td>
                                ${goodsDetail.result}
                        </td>
                        <td>
                            <fmt:formatDate value="${goodsDetail.placeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="task-purchase-pagination" data-total="${pageData.total}">
            </div>
        </div>

        <div class="row">
            <div class="panel-group" id="accordion">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseexample">
                                <strong style="font-size: 13px;">备注</strong>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseexample" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <ol>
                                <li>此页展示了选中时间段内，所有的购买记录。</li>
                                <li>
                                    个别数据项含义:
                                    <ul>
                                        <li>订单号：集团订单唯一标识</li>
                                        <li>购买金额：商品单价(元)</li>
                                        <li>下单时间：精确到秒</li>
                                    </ul>
                                </li>
                            </ol>
                        </div>
                    </div>
                </div>
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