<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>결재HOME</title>
	<jsp:include page="../commonheader.jsp"></jsp:include>
	<style>
		.container {
			background-color: #FFF;
			padding: 1rem;
			border-radius: val(--box-border-radius);
		}
		.title {
            margin-bottom: 3rem;
        }
        .col-1-1 {
            width: 99%;
        }
        .col-1-2 {
            width: 50%;
        }
        .col-1-3 {
            width: 33.33%;
        }
        .col-2-3 {
            width: 66.66%;
        }
        .col-1-4 {
            width: 25%;
        }
        .col-1-8 {
            width: 12.5%
        }
		.row {
            display: flex;
            justify-content: space-between;
        }
        .card {
            display: flex;
            flex-direction: column;
            min-width: 0;
            word-wrap: break-word;
            background-color: #FFF;
            padding: 0 0.8125rem;
        }
        .card-body {
            flex: 1 1 auto;
            padding: 1.5rem 1.5rem;
            background-color: #f0f0f0;
            border-radius: 0.5rem;
        }
        .card-icon {
            margin-bottom: 0.875rem;
        }
        .card-list {
            display: flex;
            background-color: #f0f0f0;
            justify-content: space-between;
            align-items: center;
            padding: 1.5rem 1.5rem;
            margin: 1rem 0;
        }
        .card-list-user {
            display: flex;
            align-items: center;
        }
        .card-list-category > .category {
            display: flex;
            flex-direction: column;
            align-items: center;
        }	
	</style>
</head>
<body>
	<div class="container">
        <div class="title">
            <h2>결재 현황</h2>
        </div>
        <div class="row">
            <div class="card col-1-3">
                <div class="card-body">
                    <div class="card-icon">⚫</div>
                    <span>승인되지 않은 결재</span>
                    <h2>4</h2>
                </div>
            </div>
            <div class="card col-1-3">
                <div class="card-body">
                    <div class="card-icon">⚫</div>
                    <span>일주일 이상 지연된 결재</span>
                    <h2>0</h2>
                </div>
            </div>
            <div class="card col-1-3">
                <div class="card-body">
                    <div class="card-icon">⚫</div>
                    <span>한 달 이내 결재내역</span>
                    <h2>10</h2>
                </div>
            </div>
        </div>
        <div>
            <div class="card">
				<c:choose>
					<c:when test="${not empty apprList.apprList}">
						<c:forEach items="${apprList.apprList}" var="approval">
							<div class="card-list"">
								<div class="card-list-category">
									<div class="category">
										<img src="" alt="img">
										<c:if test="${approval.apprCtgr eq '902'}">
											<span>비품변경</span>
										</c:if>
									</div>
								</div>
								<div class="card-list-title">
                                    <a href="/approval/approvalview?apprId=${approval.apprId}">
									    <h6>${approval.apprTtl}</h6>
                                        <span>${approval.dmdDt}</span>
                                    </a>
								</div>
								<div class="card-list-user">
									<img src="" alt="img">
									<div class="user">
										<h6>${approval.employeeVO.empName}</h6>
										<span>직급</span>
									</div>
								</div>
								<div class="card-list-status">
									<c:if test="${approval.apprSts eq '801'}">
										<button>결재대기</button>
									</c:if>
									<c:if test="${approval.apprSts eq '802'}">
										<button>결재승인</button>
									</c:if>
									<c:if test="${approval.apprSts eq '803'}">
										<button>결재반려</button>
									</c:if>
								</div>
							</div>	
						</c:forEach>
					</c:when>
				</c:choose>		
            </div>
        </div>
    </div>
</body>
</html>