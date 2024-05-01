<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>결재현황</title>
	<jsp:include page="../commonheader.jsp"></jsp:include>
	<style>
    img {
      width: 2rem;
      height: 2%;
    }
    h6 {
      margin-bottom: 0.4rem;
      font-weight: bold;
    }
		.container {
			background-color: #FFF;
			padding: 1rem;
			border-radius: val(--box-border-radius);
		}
		.title {
        margin-bottom: 3rem;
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
        padding: 0 0.8125rem;
    }
    .card-body {
        flex: 1 1 auto;
        padding: 1.5rem 1.5rem;
        background-color: #FFF;
        border-radius: 0.5rem;
    }
    .card-icon {
        text-align: right;
    }
    .card-list {
        display: flex;
        background-color: #FFF;
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
    .card-list-status {
      text-align: center;
    }
    .card-empty {
      height: 30rem;
      justify-content: center;
      flex-direction: column;
    }
    .count-appr {
      display: flex;
      align-items: baseline;
    }
    .right {
      justify-content: flex-end;
    }
    .col-2-4 {
      width: 50%;
    }
    .margin-top {
      margin-top: 0.25rem;
    }
    .margin-left {
      margin-left: 0.5rem;
    }
    .btn-width {
      width: 6rem;
    }
    .btn-padding {
      padding-right: 0.8125rem;
      margin-bottom: 1rem;
    }
	</style>
  <script type="text/javascript" src=/js/approval/approvalhome.js></script>
  <jsp:include page="../commonmodal.jsp"></jsp:include>
</head>
<body>
  <div class="hidden hidden-data">${errorMessage}</div>
	<div class="container">
        <div class="title">
            <h2>결재 현황</h2>
        </div>
        <div class="row right">
          <div class="btn-padding">
            <button type="button" id="btn-all-appr">전체목록</button>
          </div>
        </div>
        <div class="row">
            <div class="card col-1-3">
                <div class="card-body">
                    <a href="/approval/home/waiting">
                      <span>완료되지 않은 결재</span>
                      <div class="count-appr">
                        <c:choose>
                          <c:when test="${apprWaitingList.getApprCnt() ne 0}">
                            <h2>${apprWaitingList.getApprCnt()}</h2>
                          </c:when>
                          <c:otherwise><h2>0</h2></c:otherwise>
                        </c:choose>
                        <h6>건</h6>
                      </div>
                    </a>
                    <div class="card-icon">
                      <img src="/images/sidebar-approval.png"/>
                    </div>
                </div>
            </div>
            <div class="card col-1-3">
                <div class="card-body">
                  <a href="/approval/home/delay">
                    <span>일주일 이상 지연된 결재</span>
                    <div class="count-appr">
                      <c:choose>
                        <c:when test="${approvalDelayList.getApprCnt() ne 0}">
                          <h2>${approvalDelayList.getApprCnt()}</h2>
                        </c:when>
                        <c:otherwise><h2>0</h2></c:otherwise>
                      </c:choose>
                      <h6>건</h6>
                    </div>
                  </a>
                  <div class="card-icon">
                    <img src="/images/sidebar-approval.png"/>
                  </div>
                </div>
            </div>
            <div class="card col-1-3">
                <div class="card-body">
                  <a href="/approval/home/oneMonth">
                    <span>한 달 이내 결재내역</span>
                    <div class="count-appr">
                      <c:choose>
                        <c:when test="${approvalOneMonthList.getApprCnt() ne 0}">
                          <h2>${approvalOneMonthList.getApprCnt()}</h2>
                        </c:when>
                        <c:otherwise><h2>0</h2></c:otherwise>
                      </c:choose>
                      <h6>건</h6>
                    </div>
                  </a>
                  <div class="card-icon">
                    <img src="/images/sidebar-approval.png"/>
                  </div>
                </div>
            </div>
        </div>
        <div>
            <div class="card">
              <c:choose>
                <c:when test="${not empty apprList.apprList}">
                  <c:forEach items="${apprList.apprList}" var="approval">
                    <a href="/approval/view?apprId=${approval.apprId}">
                      <div class="card-list card-body">
                        <div class="card-list-category col-1-8">
                          <div class="category">
                            <img src="/images/sidebar-approval.png" alt="img">
                            <c:if test="${approval.apprCtgr eq '902'}">
                              <span class="margin-top">비품변경</span>
                            </c:if>
                          </div>
                        </div>
                        <div class="card-list-title col-2-4">
                          <h6>${approval.apprTtl}</h6>
                          <span>${approval.dmdDt}</span>
                        </div>
                        <div class="card-list-user col-1-4">
                          <img src="/images/login.png" alt="prfl">
                          <div class="user margin-left">
                            <h6>${approval.employeeVO.empName} ${approval.commonCodeVO.cmcdName}</h6>
                            <c:choose>
                              <c:when test="${searchApproval.searchAuth}">
                                <span>${approval.departmentVO.deptName}</span>
                              </c:when>
                              <c:otherwise>
                                <c:forEach items="${sessionScope._LOGIN_USER_.teamList}" var="team">
                                  <span>${team.tmName}</span>
                                </c:forEach>
                              </c:otherwise>
                            </c:choose>
                          </div>
                        </div>
                        <div class="card-list-status col-1-8">
                          <c:if test="${approval.apprSts eq '801'}">
                            <span class="badge bg-label-warning">결재대기</span>
                          </c:if>
                          <c:if test="${approval.apprSts eq '802'}">
                            <span class="badge bg-success">결재승인</span>
                          </c:if>
                          <c:if test="${approval.apprSts eq '803'}">
                            <span class="badge bg-label-danger">결재반려</span>
                          </c:if>
                        </div>
                      </div>	
                    </a>
                  </c:forEach>
                </c:when>
                <c:otherwise>
                  <div class="card-list card-body card-empty">
                    <span>결재 진행 중인 문서가 없습니다.</span>
                    <c:if test="${!searchApproval.searchAuth}">
                      <button type="button" id="btn-appr-write" class="btn-width">기안서 작성</button>
                    </c:if>
                  </div>
                </c:otherwise>
              </c:choose>		
            </div>
        </div>
    </div>
</body>
</html>