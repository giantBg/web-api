<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<jsp:include page="attach/header.jsp"></jsp:include>
<body>
<jsp:include page="attach/menu.jsp"/>

<div class="container">
    <ol class="breadcrumb">
        <li><a href="#">主页</a></li>
        <li class="active">地图信息</li>
    </ol>

    <div class="row">
        <div class="col-md-6">
            <div class="widget">
                <div class="widget-content">
                    <div class="padd">
                        <div id="index_map_container">
                            <div id="index_map_chart" style="height: 500px;width: 1100px"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <hr/>

</div>

<script type="text/javascript" src="/static/chart/web_map.js?v=20150912113520"></script>
<!-- Site footer -->
<jsp:include page="attach/footer.jsp"></jsp:include>
</body>
</html>