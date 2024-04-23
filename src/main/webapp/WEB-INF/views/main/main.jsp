<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>메인페이지</title>
    <jsp:include page="../commonheader.jsp" />
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-1-2">
                <div>
                    <div class="card row-1-2" style="background-color: #f0f0f0;">
                        <div class="card-body" style="background-color: #FFF;">출퇴근</div>
                    </div>
                    <div class="card row-1-2" style="background-color: #f0f0f0;">
                        <div class="card-body" style="background-color: #FFF;">쪽지</div>
                    </div>
                </div>
            </div>
            <div class="col-1-2">
                <div class="card row-1-1" style="background-color: #f0f0f0;">
                    <div class="card-body" style="background-color: #FFF;">팀</div>
                </div>
            </div>
            <div class="col-1-1">
                <div class="card row-1-1" style="background-color: #f0f0f0;">
                    <div class="card-body" style="background-color: #FFF;">프로젝트</div>
                </div>
            </div>
            <div class="col-1-1">
                <div class="card row-1-1" style="background-color: #f0f0f0">
                    <div class="card-body" style="background-color: #FFF;">비품대여</div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>