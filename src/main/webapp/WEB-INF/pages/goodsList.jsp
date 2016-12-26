<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-cn">
<jsp:include page="attach/header.jsp"></jsp:include>
<body>
<jsp:include page="attach/menu.jsp"/>

<div class="js-comp container">
    <ol class="breadcrumb">
        <li><a href="#">商品页</a></li>
        <li class="active">商品管理</li>
    </ol>
    <div class="portlet box blue">
        <hr/>

        <c:if test="${not empty error}">
            <div id="error" class="alert alert-danger">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">close me</button>
                <strong>警告：</strong>${error}
            </div>
        </c:if>
        <c:if test="${not empty message}">
            <div id="batmessage" class="alert alert-success">
                <button type="button" class="close" data-dismiss="alert"
                        aria-hidden="true">close me
                </button>
                <strong>成功：</strong>${message}
            </div>
        </c:if>

        <div class="row">
            <table class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th scope="col">物品名称</th>
                    <th scope="col">商品价格</th>
                    <th scope="col">库存</th>
                    <th scope="col">商品描述</th>
                    <th scope="col">是否下架</th>
                    <th scope="col">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${goodsList}" var="goodsDetail">
                    <tr>
                        <td>
                                ${goodsDetail.name}
                        </td>
                        <td>
                                ${goodsDetail.price}
                        </td>
                        <td>
                                ${goodsDetail.count}
                        </td>
                        <td>
                                ${goodsDetail.remark}
                        </td>
                        <td>
                            <c:if test="${goodsDetail.osk == 1}">
                                是
                            </c:if>
                            <c:if test="${goodsDetail.osk == 0}">
                                否
                            </c:if>
                        </td>
                        <td>
                            <a href="#ModifyGoods${goodsDetail.id}" class="btn btn-sm red" data-toggle="modal">修改</a>
                        </td>
                    </tr>
                    <%--修改弹框--%>
                    <div id="ModifyGoods${goodsDetail.id}" class="modal fade" tabindex="-1" aria-hidden="true">
                        <div class="modal-dialog">
                            <form id="update_form" class="form-horizontal form-bordered form-row-stripped"
                                  action="${ctx}/web/goods/manage/modify/${goodsDetail.id}" method="POST"
                                  role="form">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-hidden="true"></button>
                                        <h4 class="modal-title">商品信息记录修改</h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <label class="control-label">商品名称：${goodsDetail.name}</label>
                                            </div>
                                            <div class="col-md-12">
                                                <label class="control-label">商品描述：${goodsDetail.remark} </label>
                                            </div>
                                            <div class="col-md-12">
                                                <label class="control-label">价格(元)：</label>
                                                <input type="text" id="taskMoney${goodsDetail.id}" name="goodsMoney"
                                                       value="${goodsDetail.price}"
                                                       class="form-control input-medium"/>
                                            </div>
                                            <div class="col-md-12">
                                                <label class="control-label">库存： </label>
                                                <input type="text" id="taskCount${goodsDetail.id}" name="goodsCount"
                                                       value="${goodsDetail.count}"
                                                       class="form-control input-medium"/>
                                            </div>
                                            <div class="col-md-12">
                                                <label class="control-label">是否下架： </label>
                                                <select name="oskFlag" class="form-control">
                                                    <option value="1"
                                                            <c:if test="${goodsDetail.osk == 1}">selected</c:if>>
                                                        是
                                                    </option>
                                                    <option value="0"
                                                            <c:if test="${goodsDetail.osk == 0}">selected</c:if>>
                                                        否
                                                    </option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" data-dismiss="modal" class="btn btn-default">返回操作</button>
                                        <button type="submit" class="btn btn-primary"
                                                onclick="return checkModify('${goodsDetail.id}')">确定修改
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</div>
<hr/>

</div>

<!-- Site footer -->
<script type=text/javascript>
    //修改表单提交检测
    function checkModify(recordid) {
        var p3 = document.getElementById("taskMoney" + recordid);
        var p4 = document.getElementById("taskCount" + recordid);
        if (!p3.value) {
            alert("请填写金额！");
            return false;
        }
        if (!p4.value) {
            alert("请填写库存！");
            return false;
        }

        //检测金额格式
        var a = /^[0-9]*(\.[0-9]{1,2})?$/;
        if (!a.test(p3.value)) {
            alert("金额格式不正确，请核查后再提交");
            return false;
        }
    }

</script>
<jsp:include page="attach/footer.jsp"></jsp:include>
</body>
</html>