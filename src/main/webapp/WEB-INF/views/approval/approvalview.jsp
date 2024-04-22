<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>결재상세</title>
	<style>
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
            padding: 20px;
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
         #modalWrap {
        display: none; /* 초기에는 모달창을 숨김 */
        height: 270px;
        width: 401px;
        margin: auto;
        border: 1px solid #777;
        border-radius: 4px;
        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        position: fixed;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
        background-color: #fff;
        z-index: 1000;
        text-align: center;
        padding: 40px 10px 10px;
        animation: slidefade 0.5s ease-in-out;
    }
    #closeBtn {
        position: absolute;
        top: 10px;
        right: 10px;
        cursor: pointer;
    }
    .modalWrap.show {
        display: block;
    }
	</style>
	<jsp:include page="../commonheader.jsp"></jsp:include>
	<script type="text/javascript" src="/js/approval/approvalview.js" ></script>
</head>
<body>
	<div class="container">
        <div class="title">
            <h2>결재 상세정보</h2>
        </div>
        <div class="btn-area">
            <div class="btn-change">
                <button>수정이력</button>
                <button>삭제</button>
            </div>
			<c:if test="${approvalVO.apprSts eq '801'}">
				<div class="btn-status">
					<button id="btn-appr-sts-ok" data-appr-id="${approvalVO.apprId}" data-appr-sts="ok">승인</button>
					<button id="modal-button" data-appr-id="${approvalVO.apprId}" data-appr-sts="no">반려</button>
				</div>
			</c:if>
        </div>
        <div class="grid-container">
            <div class="grid-item">
                <h5>종류</h5>
				<span>결재 종류</span>
            </div>
            <div class="grid-item">
				<c:if test="${approvalVO.apprCtgr eq '902'}">
                	<button>비품변경</button>
				</c:if>
            </div>
			<div class="grid-item">
                <h5>상태</h5>
                <span>결재 상태</span>
            </div>
            <div class="grid-item">
				<c:if test="${approvalVO.apprSts eq '801'}">
					<button>결재대기</button>
				</c:if>
				<c:if test="${approvalVO.apprSts eq '802'}">
					<button>결재승인</button>
				</c:if>
				<c:if test="${approvalVO.apprSts eq '803'}">
					<button>결재반려</button>
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
                <h5>품목</h5>
                <span>결재 대상 품목 및 수량</span>
            </div>
            <div class="grid-item">
                <div class="grid-appr-item">
					<c:forEach items="${approvalVO.approvalDetailVOList}" var="approvalDetail">
						<div class="grid-appr-item-prdt">
							<span><c:out value="${approvalDetail.productVO.prdtCtgr}"/></span>
							<span><c:out value="${approvalDetail.productVO.prdtName}"/></span>
							<span><c:out value="${approvalDetail.productVO.curStr}"/></span>
						</div>
					</c:forEach>
                </div>
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
        </div>
    </div>
    
    <!--모달창 시작-->
    <div id="modalWrap">
        <div id="modalBody">
            <span id="closeBtn">&times;</span>
            <div class="modal-title">반려사유</div>
            <div class="modal-body">
                <input type="text" id="rejectionReason">
                <button id="confirmButton">확인</button>
                <button id="cancelButton">취소</button>
            </div>
        </div>
    </div>
    <!--모달창 끝-->
</body>
</html>