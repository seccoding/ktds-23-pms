<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>결재작성</title>
	<jsp:include page="../commonheader.jsp"></jsp:include>
	
	<script type="text/javascript" src="/js/approval/approvalwrite.js" ></script>
	<style>
		table { 
			table-layout:fixed;
			width: 100%;
		}
		th:first-child,
		td:first-child {
			width: 5rem;
		}
		.appr-prdt-info {
            width: 100%;
        }
        .appr-prdt-info td, th {
            border-bottom: 1px solid #dddddd;
            text-align: center;
            padding: 8px;
        }
		.container {
			background-color: #FFF;
			padding: 1rem;
			border-radius: val(--box-border-radius);
		}
		.title {
            margin-bottom: 3rem;
        }
		.grid-container-user {
            display: grid;
            grid-template-columns: 1fr 2fr 1fr 2fr;
            padding: 10px;
        }
		.grid-item {
			padding: 0.5rem;
        }
		.grid-container-user > .grid-item:nth-child(odd) {
			background-color: var(--body-bg);
		}
		.grid-container-user > .grid-item:nth-child(14),
		.grid-container-user > .grid-item:nth-child(16) {
			grid-column: 2/-1;
		}
		.dmd-info-user,
		.dmd-info-prdt,
		.dmd-info-btn {
			padding-top: 1rem;
		}
		.dmd-info-btn {
			text-align: end;
		}
		.modal-window,
		.modal-confirm-window {
			position: absolute;
			border-radius: 20px;
			border: 0;
			box-shadow: 0px 8px 15px 0px rgba(0, 0, 0, 0.1);
			transition: box-shadow 0.3s ease;

			justify-content: center;
			top: 30%;
			left: 30%;

			width: 600px;
			height: fit-content;
			padding: 1rem;

			background-color: rgba(F, F, F, 0.8);
		}
		.modal-close,
		.modal-confirm-close {
			text-align: right;
			color: gray;
		}
		.modal-close:hover,
		.modal-confirm-close:hover {
			color: black;
		}
		.grid-modal,
		.grid-confirm-modal {
			display: grid;
			grid-template-rows: 1fr 6fr 1fr;
		}
		.modal-content {
			align-items: center;
			display: flex;
			justify-content: space-around;
			vertical-align: middle;
			overflow-y: auto;
			max-height: 11rem;
		}
		.modal-confirm-content {
			display: flex;
			flex-direction: column;
			justify-content: flex-start;
			align-items: center;
			overflow-y: auto;
			max-height: 11rem;
		}
		.input-space,
		.input-confirm-space {
			text-align: right;
			align-items: end;
		}
		.text-width {
			width: -webkit-fill-available;
			background-color: var(--body-bg);
			border : 0px;
			border-radius: 0.3rem;
		}
		.text-height1 {
			height: -webkit-fill-available;
		}
		.text-height2 {
			height: 10rem;
		}
		input[type="text"], 
		input[type="textarea"] {
			width: -webkit-fill-available;
			padding-left : 1rem;
		}
		input[type="textarea"]:focus {
			padding-left : 1rem;
			outline: none !important;
			box-shadow: rgba(253, 54, 54, 0.27) 0px 0px 0.25em, rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
		}
		input::placeholder {
			color: var(--main-color);
			font-size: 0.825rem;
		}
	</style>
</head>
<body>
	<div class="container">
		<div class="title">
            <h2>비품 변경 신청</h2>
        </div>
		<form class="form-group">
			<div class="card col-1-1">
				<div class="dmd-info-btn">
					<button type="button" id="btn-appr-regist" >상신</button>
					<button type="button" id="btn-appr-cancel">취소</button>
				</div>
			</div>
			<div class="card col-1-1">
				<div class="dmd-info-user">
					<h5 class="dmd-info-title">기본정보</h5>
					<div class="grid-container-user">
						<div class="grid-item">
							<label for="empName"><h6>작성자</h6></label>
						</div>
						<div class="grid-item">
							<input id="empName" type="text" name="empName" value="${employee.empName}" readonly>
							<input id="empId" type="text" name="empId" value="${employee.empId}" hidden>
						</div>
						<div class="grid-item">
							<label for="tmName"><h6>소속팀</h6></label>
						</div>
						<div class="grid-item">
							<c:forEach items="${sessionScope._LOGIN_USER_.teamList}" var="team">
								<span>${team.tmName}</span>
							</c:forEach>
						</div>
						<div class="grid-item">
							<label for="deptName"><h6>작성부서</h6></label>
						</div>
						<div class="grid-item">
							<input id="deptName" type="text" name="deptName" value="${employee.departmentVO.deptName}" readonly>
						</div>
						<div class="grid-item">
							<label for="apprCtgr"><h6>결재종류</h6></label>
						</div>
						<div class="grid-item">
							<input id="apprCtgr" type="text" name="apprCtgr" value="비품변경" readonly>
						</div>
						<div class="grid-item">
							<label for="apprMngName"><h6>결재자</h6></label>
						</div>
						<div class="grid-item">
							<input id="apprMngName" type="text" name="apprMngName" value="경영지원부장" readonly>
							<!-- <input id="apprMngId" type="text" name="apprMngId" value="0005023" hidden> -->
						</div>
						<div class="grid-item">
							<label for="apprMngdeptName"><h6>결재부서</h6></label>
						</div>
						<div class="grid-item">
							<input id="apprMngdeptName" type="text" name="apprMngdeptName" value="경영지원" readonly>
						</div>
						<div class="grid-item">
							<label for="apprTtl"><h6>결재 문서 제목</h6></label>
						</div>
						<div class="grid-item">
							<input id="apprTtl" type="textarea" name="apprTtl" class="text-width text-height1">
						</div>
						<div class="grid-item">
							<label for="apprCntnt"><h6>결재 내용</h6></label>
						</div>
						<div class="grid-item">
							<input id="apprCntnt" type="textarea" name="apprCntnt" class="text-width text-height2">
						</div>
					</div>
				</div>
			</div>
			<div class="card col-1-1">
				<div class="dmd-info-prdt">
					<h5>비품변경정보</h5>
				</div>
				<div class="prdt-info">
					<table class="appr-prdt-info">
						<thead>
							<tr>
								<th>
									<input type="checkbox" id="appr-item-checked-all"  data-target-class="target-prdt-dtl-id">
									<label for="appr-item-checked-all"></label>
								</th>
								<th>비품번호</th>
								<th>종류</th>
								<th>품목</th>
								<th>대여일</th>
								<th><button type="button" id="btn-add-prdt-modal">추가</button></th>
							</tr>
						</thead>
						<tbody id="prdt-list">
							<c:forEach items="${borrowList.borrowList}" var="borrow">
								<tr>
									<td>
										<input type="checkbox" id="x${borrow.prdtMngId}" class="target-prdt-dtl-id" name="prdtId" value="${borrow.prdtMngId}"/>
										<label for="x${borrow.prdtMngId}"></label>
									</td>
									<td><input type="text" value="${borrow.prdtMngId}" readonly></td>
									<td><input type="text" value="${borrow.productVO.prdtCtgr}" readonly></td>
									<td><input type="text" value="${borrow.productVO.prdtName}" readonly></td>
									<td><input type="text" value="${borrow.brrwDt}" readonly></td>
									<td><button type="button" class="btn-remove-prdt" data-delete-item="${borrow.prdtMngId}">삭제</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</form>
	</div>
	<dialog class="modal-confirm-window">
		<div class="grid-confirm-modal">
		<div class="modal-confirm-close">X</div>
		<div class="modal-confirm-content">
			<!-- <div class="modal-confirm-text"></div> -->
			<div class="modal-header">
				<h5>변경 신청 가능한 비품 목록</h5>
			</div>
			<div class="modal-body">
				<table class="modal-table" data-brrw-id="${employee.empId}">
					<thead>
						<tr>
							<th>
								<input type="checkbox" id="m-appr-item-checked-all"  data-target-class="modal-item modal">
								<label for="m-appr-item-checked-all"></label>
								<!-- <input type="checkbox" id="m-appr-item-checked-all"  data-target-class="modal-item modal"> -->
							</th>
							<th>비품코드</th>
							<th>종류</th>
							<th>품목</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${borrowList.borrowList}" var="borrow">
							<tr>
								<th id="modal-prdt-th">
									<input type="checkbox" id="${borrow.prdtMngId}" class="modal-item modal" name="mPrdtId" value="${borrow.prdtMngId}">
									<label for="${borrow.prdtMngId}"></label>
									<!-- <input type="checkbox" id="${borrow.prdtMngId}" class="modal-item modal" name="mPrdtId" value="${borrow.prdtMngId}"/> -->
								</th>
								<th>${borrow.prdtMngId}</th>
								<th>${borrow.productVO.prdtCtgr}</th>
								<th>${borrow.productVO.prdtName}</th>	
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="input-confirm-space">
			<button type="button" class="confirm-confirm-button button">추가</button>
			<button type="button" class="cancel-confirm-button button">취소</button>
		</div>
		</div>
	</dialog>
</body>
</html>