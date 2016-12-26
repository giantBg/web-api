<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<hr/>
<footer>
    <div class="container">
        <div class="row">
            <div class="col-md-12" style="text-align:center;">
                <!-- Copyright info -->
                <p class="copy"><c:set var="now" value="<%=new java.util.Date()%>"/>
                    Copyright &copy; <fmt:formatDate pattern="yyyy" value="${now}"/> | 有志者事竟成产业集团</p>
            </div>
        </div>
    </div>
</footer>
<%--<span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span>--%>