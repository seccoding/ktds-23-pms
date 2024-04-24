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
                    <h2>${approveList.getApprCnt()}</h2>
                </div>
            </div>
            <div class="card col-1-3">
                <div class="card-body">
                    <div class="card-icon">⚫</div>
                    <span>일주일 이상 지연된 결재</span>
                    <h2>${OneWeekApprovalList.getApprCnt()}</h2>
                </div>
            </div>
            <div class="card col-1-3">
                <div class="card-body">
                    <div class="card-icon">⚫</div>
                    <span>한 달 이내 결재내역</span>
                    <h2>${monthApprovalList.getApprCnt()}</h2>
                </div>
            </div>
        </div>
        <div>
            <div class="card">
				<c:choose>
					<c:when test="${not empty apprList.apprList}">
						<c:forEach items="${apprList.apprList}" var="approval">
              <a href="/approval/view?apprId=${approval.apprId}">
                <div class="card-list">
                  <div class="card-list-category">
                    <div class="category">
                      <img src="/images/sidebar-approval.png" alt="img">
                      <c:if test="${approval.apprCtgr eq '902'}">
                        <span>비품변경</span>
                      </c:if>
                    </div>
                  </div>
                  <div class="card-list-title">
                    <h6>${approval.apprTtl}</h6>
                    <span>${approval.dmdDt}</span>
                  </div>
                  <div class="card-list-user">
                    <img src="/images/login.png" alt="prfl">
                    <div class="user">
                      <h6>${approval.employeeVO.empName} ${approval.commonCodeVO.cmcdName}</h6>
                      <span>${employee.departmentVO.deptName}</span>
                    </div>
                  </div>
                  <div class="card-list-status">
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
				</c:choose>		
            </div>
        </div>
        

        <!-- Paginator 시작 -->
      <div>
        <form id="search-form">
          <input type="hidden" id="page-no" name="pageNo" value="0" />		
          <ul class="page-nav">
            <c:if test="${searchApproval.hasPrevGroup}">
              <li><a href="javascript:search(0);">처음</a></li>
              <li>
                <a
                  href="javascript:search(${searchApproval.prevGroupStartPageNo});"
                  >이전</a
                >
              </li>
            </c:if>

            <!-- Page 번호를 반복하며 노출한다. -->
            <c:forEach
              begin="${searchApproval.groupStartPageNo}"
              end="${searchApproval.groupEndPageNo}"
              step="1"
              var="p"
            >
              <li class="${searchApproval.pageNo eq p ? 'active' : ''}">
                <a href="javascript:search(${p});">${p+1}</a>
              </li>
            </c:forEach>

            <c:if test="${searchApproval.hasNextGroup}">
              <li>
                <a
                  href="javascript:search(${searchApproval.nextGroupStartPageNo});"
                  >다음</a
                >
              </li>
              <li>
                <a href="javascript:search(${searchApproval.pageCount - 1});"
                  >마지막</a
                >
              </li>
            </c:if>
          </ul>
        </form>
      </div>
      <!-- Paginator 끝 -->
    </div>
</body>
</html>