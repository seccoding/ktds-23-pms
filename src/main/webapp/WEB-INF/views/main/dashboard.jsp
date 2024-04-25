<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<jsp:include page="../commonheader.jsp" />
<script type="text/javascript" src="/js/commute/fnsh.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-1-2">
				<div>
					<div class="card row-1-2">
						<h6>출퇴근</h6>
						<div class="card-body">
							<c:if
								test="${not empty sessionScope._LOGIN_USER_.commuteVO.cmmtTime}">
								<div>출근시간: ${sessionScope._LOGIN_USER_.commuteVO.cmmtTime}</div>
							</c:if>
							<c:choose>
								<c:when
									test="${not empty sessionScope._LOGIN_USER_.commuteVO.fnshTime}">
									<div>퇴근시간:
										${sessionScope._LOGIN_USER_.commuteVO.fnshTime}</div>
								</c:when>
								<c:otherwise>
									<div>퇴근 처리되지 않았습니다</div>
								</c:otherwise>
							</c:choose>
							<div>
									<a href="/employee/logout">
									<button type="button" id="fnsh-btn">퇴근</button>
									</a> 
							</div>
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