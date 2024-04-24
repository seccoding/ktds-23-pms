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
                    <div class="card row-1-2">
                        <h6>출퇴근</h6>
                        <div class="card-body">
                            <!-- 출퇴근 content 영역 -->
                        </div>
                    </div>
                    <div class="card row-1-2">
                        <h6>쪽지</h6>
                        <div class="card-body">
                            <!-- 쪽지 content 영역 -->
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-1-2">
                <div class="card row-1-1">
                    <h6>팀</h6>
                    <div class="card-body">
                        <!-- 팀 content 영역 -->
                    </div>
                </div>
            </div>
            <div class="col-1-1">
                <div class="card row-1-1">
                    <h6>프로젝트</h6>
                    <div class="card-body">
                        <!-- 프로젝트 content 영역 -->
                    </div>
                </div>
            </div>
            <div class="col-1-1">
                <div class="card row-1-1">
                    <h6>비품대여</h6>
                    <div class="card-body">
                        <!-- 비품대여 content 영역 -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>