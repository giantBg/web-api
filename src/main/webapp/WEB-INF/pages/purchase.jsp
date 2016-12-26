<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-cn">
<jsp:include page="attach/header.jsp"></jsp:include>

<body>
<jsp:include page="attach/menu.jsp"/>

<div class="container">
    <ol class="breadcrumb">
        <li><a href="#">商品页</a></li>
        <li class="active">商品购买</li>
    </ol>

    <div>
        <form:form modelAttribute="merchant" role="form" action="${ctx}/web/goods/commit/${goods.id}" method="post"
                   class="form-horizontal">

        <h4>商品购买资料填写</h4>

        <table style="width: 100%;font-size: 14px;margin: 8px 0;background: #fff;">
            <tr>
                <th width="25%">所在城市：</th>
                <td>
                    <div class="form-group">
                        <div class="col-md-4">
                            <select name="ddlProvince" class="form-control" id="ddlProvince"
                                    onchange="selectMoreCity(this)">
                            </select>
                        </div>
                        <div class="col-md-4">
                            <select name="ddlCity" id="ddlCity" class="form-control">
                                <option selected value="">城市</option>
                            </select>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <th>购买类型：</th>

                <td><p><label class="control-label">${goods.name}</label></p></td>
            </tr>
            <tr>
                <th width="25%">商品价格：</th>

                <td><p><label class="control-label">${goods.price}</label></p></td>
            </tr>
            <tr>
                <th>商品详情：</th>

                <td><p><label class="control-label">${goods.remark}</label></p></td>
            </tr>
            <tr>
                <th>购买数量：</th>
                <td><p><input type="text" name="count" placeholder="请输入购买数量(数字类型)" maxlength="11"
                              style=" width: 50%;height: 35px;" onkeyup="this.value=this.value.replace(/\D/g,'')"></p>
                </td>
            </tr>
        </table>
        <div class="col-sm-offset-2 col-sm-12 ">
            <button type="submit" class="btn btn-success" style="margin-left: 80px;">确认购买</button>
            <button type="reset" class="btn btn-default " style="margin-left: 20px;">重&nbsp;&nbsp;置</button>
        </div>
        </form:form>

        <hr/>
        <script type="text/javascript" src="${ctx}/static/js/city-cascade.js?v=20150831"></script>
        <script type="text/javascript" language="javascript">
            BindProvince('北京');//只初始化省份
        </script>
        <!-- Site footer -->
        <jsp:include page="attach/footer.jsp"></jsp:include>
</body>
</html>