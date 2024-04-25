<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<jsp:include page="../commonheader.jsp" />
<script type="text/javascript" src="/js/dashboard.js"></script>
<script type="text/javascript" src="/js/commute/fnsh.js"></script>
<style>
.person-log-in {
	justify-content: space-between;
	display: flex;
	overflow-y: scroll;
}

.flex {
	margin: 20px;
}

.flex-col {
	flex-direction: column;
}

.prfl-photo {
	width: 150px;
	height: 150px;
	border-radius: 50%;
	margin-bottom: 15px;
	background-position: center;
	background-size: cover;
	background-repeat: no-repeat;
	border: 3px solid var(--success);
}

.image-color-background {
	border: 3px solid var(--secondary);
	opacity: 0.5;
}

.prfl-photo-font {
	font-size: 20px;
	font-weight: 700;
	text-align: center;
}

.bold-color-background {
	color: var(--secondary);
}

.dept-id-data {
	font-size: 20px;
	font-weight: 700;
}

.commute-grid {
	display: grid;
	grid-template-columns: 1fr 100px;
	height: 100px;
	width: 100%;
}

.commute-grid-time {
	display: flex;
	flex-direction: column;
	justify-content: center;
	margin: 20px;
	border: 1px solid #000;
	border-radius: 10px;
	min-width: 230px;
}

.commute-grid-time-in,
.commute-grid-time-out {
	padding-left: 10px;
}

.commute-grid-time-in {
	border-bottom: 0.1px solid #000;
	padding-bottom: 5px;
}

.commute-grid-time-out {
	padding-top: 2px;
}

.commute-grid-out {
	display: flex;
	justify-content: center;
	align-items: center;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-1-2">
				<div>
					<div class="card row-1-2">
					<%-- 박스 영역 최소넓이 지정필요 --%>
						<h6>출퇴근</h6>
						<div class="card-body">
							<div class="commute-grid">
								<div class="commute-grid-time">
									<div class="commute-grid-time-in">
										<c:if
											test="${not empty sessionScope._LOGIN_USER_.commuteVO.cmmtTime}">
											<div>출근시간:
												${sessionScope._LOGIN_USER_.commuteVO.cmmtTime}</div>
										</c:if>
									</div>
									<div class="commute-grid-time-out">
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
									</div>
								</div>
								<div class="commute-grid-out">
									<a href="/employee/logout">
										<button type="button" id="fnsh-btn">퇴근</button>
									</a>
								</div>
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
					<h6>부서 내 사원 로그인현황</h6>
					<div class="card-body">
						<!-- 팀 content 영역 -->
						<p class="dept-id-data"
							data-dept-id="${sessionScope._LOGIN_USER_.deptId}">
							<!--$(this).data("dept-id")-->
							부서 : ${deptname}
						</p>
						<div class="person-log-in"></div>
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
