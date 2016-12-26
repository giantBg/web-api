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
        <li><a href="#">主页</a></li>
        <li class="active">欢迎信息${tipMsg}</li>
    </ol>

    <div id="myCarousel" class="carousel slide">
        <!-- 轮播（Carousel）指标 -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0"
                class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <!-- 轮播（Carousel）项目 -->
        <div class="carousel-inner">
            <div class="item active">
                <div class="fill">
                    <img src="${ctx}/static/images/slide_4.jpg" alt="First slide">
                </div>
            </div>
            <div class="item">
                <div class="fill">
                    <img src="${ctx}/static/images/slide_5.jpg" alt="Second slide">
                </div>
            </div>
            <div class="item">
                <div class="fill">
                    <img src="${ctx}/static/images/slide_6.jpg" alt="Third slide">
                </div>
            </div>
        </div>
        <!-- 轮播（Carousel）导航 -->
        <a class="carousel-control left" href="#myCarousel"
           data-slide="prev">&lsaquo;</a>
        <a class="carousel-control right" href="#myCarousel"
           data-slide="next">&rsaquo;</a>

    </div>
    <script>
        $(function () {
            // 初始化轮播
            $("#myCarousel").carousel({
                interval: 5000 //changes the speed
            });
        });
    </script>
    <hr/>

</div>

<!-- Site footer -->
<jsp:include page="attach/footer.jsp"></jsp:include>
</body>
</html>