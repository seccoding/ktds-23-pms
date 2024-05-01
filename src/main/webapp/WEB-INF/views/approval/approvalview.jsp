<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>결재상세</title>
	<style>
        table {
			table-layout: fixed;
            width: -webkit-fill-available;
		}
		h4 {
            padding: 0;
            margin: 0;
        }
        .title {
            margin-bottom: 3rem;
        }
        .btn-area {
            display: flex;
            justify-content: space-between;
            margin-bottom: 1.5rem;
            justify-content: flex-end;
        }
        .btn-change {
            margin-right: 1.5rem;
        }
        .grid-container {
            display: grid;
            grid-template-columns: 1fr 2fr;
            padding: 10px;
        }
        .grid-item {
            border: 0.0825rem solid rgba(171, 171, 171, 0.8);
            padding: 0.825rem;
        }
        .grid-item:nth-child(even) {
            display: flex;
            align-items: center;
        }
        .grid-item:nth-child(odd) {
            background-color: #f0f0f0;
        }
        .grid-appr-item {
            display: flex;
            flex-direction: column;
        }
        .btn-width {
            width: 6rem;
        }
        .bg-color {
            background-color: var(--box-bg);
        }
	</style>
	<jsp:include page="../commonheader.jsp"></jsp:include>
    <jsp:include page="../commonmodal.jsp"></jsp:include>
	<script type="text/javascript" src="/js/approval/approvalview.js" ></script>
</head>
<body>
	<div class="container">
        <div class="title">
            <h2>결재 상세정보</h2>
        </div>
        <div class="btn-area">
            <div class="btn-change">
                <c:if test="${!searchApproval.searchAuth 
                                && approvalVO.rntlSts eq '1101'}">
                    <button id="btn-return-prdt" data-rntl-sts="1102">비품반납</button>
                </c:if>
                <c:if test="${searchApproval.searchAuth
                                && approvalVO.rntlSts eq '1102' }">
                    <button class="btn-width" id="btn-brrw-prdt" data-rntl-sts="1103">신규비품대여</button>
                </c:if>
            </div>
            <div class="btn-change">
                <a href="javascript:history.back();">
                    <button id="btn-list-appr">목록</button>
                </a>
                <button id="btn-delete-appr">삭제</button>
            </div>
			<c:if test="${searchApproval.searchAuth && approvalVO.apprSts eq '801'}">
				<div class="btn-status">
					<button id="btn-appr-sts-ok" data-appr-id="${approvalVO.apprId}" data-appr-sts="802" data-rntl-sts="1101">승인</button>
					<button id="btn-appr-sts-no" data-appr-id="${approvalVO.apprId}" data-appr-sts="803">반려</button>
				</div>
			</c:if>
        </div>
        <div>
            <h5 class="dmd-info-title">기본정보</h5>
            <div class="grid-container" id="grid-container" data-appr-id="${approvalVO.apprId}">
                <div class="grid-item">
                    <h5>종류</h5>
                    <span>결재 종류</span>
                </div>
                <div class="grid-item">
                    <c:if test="${approvalVO.apprCtgr eq '902'}">
                        <span class="badge bg-label-info">비품변경</span>
                    </c:if>
                </div>
                <div class="grid-item">
                    <h5>상태</h5>
                    <span>결재 상태</span>
                </div>
                <div class="grid-item">
                    <c:if test="${approvalVO.apprSts eq '801'}">
                        <span class="badge bg-label-warning">결재대기</span>
                    </c:if>
                    <c:if test="${approvalVO.apprSts eq '802'}">
                        <span class="badge bg-success">결재승인</span>
                    </c:if>
                    <c:if test="${approvalVO.apprSts eq '803'}">
                        <span class="badge bg-label-danger">결재반려</span>
                    </c:if>
                </div>
                <div class="grid-item">
                    <h5>제목</h5>
                    <span>결재 문서의 제목</span>
                </div>
                <div class="grid-item">
                    <span>${approvalVO.apprTtl}</span>
                </div>
                <div class="grid-item">
                    <h5>작성일자</h5>
                    <span>결재 문서 작성일자</span>
                </div>
                <div class="grid-item">
                    <span>${approvalVO.dmdDt}</span>
                </div>
                <div class="grid-item">
                    <h5>기안자</h5>
                    <span>문서 기안자</span>
                </div>
                <div class="grid-item">
                    <span>${approvalVO.employeeVO.empName} ${approvalVO.commonCodeVO.cmcdName}</span>
                </div>
                <div class="grid-item">
                    <h5>시행자</h5>
                    <span>결재에 대한 시행자</span>
                </div>
                <div class="grid-item">
                    <span>${approvalVO.employeeManagerVO.empName}</span>
                </div>
                <div class="grid-item">
                    <h5>내용</h5>
                    <span>결재 문서 내용</span>
                </div>
                <div class="grid-item">
                    <div class="grid-item-content">
                        <span>${approvalVO.apprCntnt}</span>
                    </div>
                </div>
                <c:if test="${not empty approvalVO.arrpRjct}">
                    <div class="grid-item">
                        <h5>반려사유</h5>
                        <span>결재 반려 사유</span>
                    </div>
                    <div class="grid-item">
                        <div class="grid-item-content">
                            <span>${approvalVO.arrpRjct}</span>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="card col-1-1">
            <h5 class="dmd-info-title">비품 목록</h5>
            <div class="bg-color">
                <div class="table">
                    <table>
                        <thead>
                            <tr>
                                <th>비품코드</th>
                                <th>비품종류</th>
                                <th>비품명</th>
                                <th>변경신청수량</th>
                                <c:if test="${searchApprovalVO.searchAuth
                                                && (approvalVO.apprSts eq '801' || approvalVO.rntlSts eq '1102')}">
                                    <th>재고수량</th>
                                    <th>변경가능여부</th>
                                </c:if>
                                <c:if test="${searchApprovalVO.searchAuth && approvalVO.apprSts ne '801'}">
                                    <th>반납여부</th>
                                </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${not empty approvalVO.approvalDetailVOList}">
                                    <c:forEach items="${approvalVO.approvalDetailVOList}" var="approvalDetail">
                                        <tr>
                                            <td>${approvalDetail.productManagementVO.prdtMngId}</td>
                                            <td>${approvalDetail.productVO.prdtCtgr}</td>
                                            <td>${approvalDetail.productVO.prdtName}</td>
                                            <td>${approvalDetail.curStr}</td>
                                            <c:if test="${searchApprovalVO.searchAuth
                                                            && (approvalVO.apprSts eq '801' || approvalVO.rntlSts eq '1102')}">
                                                <td>${approvalDetail.productVO.curStr}</td>
                                                <td>
                                                    <c:set var="apprStr" value="${approvalDetail.curStr}"/>
                                                    <c:set var="prdtStr" value="${approvalDetail.productVO.curStr}"/>
                                                    <c:choose>
                                                        <c:when test="${apprStr le prdtStr}">
                                                            <span class="badge bg-success appr-rntl" data-appr-rntl="Y"><c:out value="비품변경가능"/></span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="badge bg-label-danger appr-rntl" data-appr-rntl="N"><c:out value="비품변경불가"/></span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </c:if>
                                            <c:if test="${searchApprovalVO.searchAuth && approvalVO.apprSts ne '801'}">
                                                <td>
                                                    <c:set var="returnYn" value="${approvalDetail.productManagementVO.brrwYn eq 'Y' ? '미반납' : '반납'}"/>
                                                    <span class="badge bg-label-info"><c:out value="${returnYn}"/></span>
                                                </td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                            </c:choose>	
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>