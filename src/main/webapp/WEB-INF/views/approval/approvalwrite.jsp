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
		select { 
			width: 90%;
		}
		.appr-prdt-info {
            width: 100%;
        }
        .appr-prdt-info td, th {
            border-bottom: 1px solid #dddddd;
            text-align: center;
            padding: 8px;
        }
		input[type="checkbox"] {
			display: flex;
		}
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
		.grid-container-user > .grid-item:nth-child(14) {
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
	</style>
</head>
<body>
	<div class="container">
		<div class="title">
            <h2>비품 변경 신청</h2>
        </div>
		<form action="/ajax/approval/approvalwrite" method="post" class="form-group">
			<div class="card col-1-1">
				<div class="dmd-info-btn">
					<input type="submit" value="상신" />
					<button id="btn-appr-cancle" type="button">취소</button>
				</div>
			</div>
			<div class="card col-1-1">
				<div class="dmd-info-user">
					<h5 class="dmd-info-title">기본정보</h5>
					<div class="grid-container-user">
						<div class="grid-item">
							<h6>작성자</h6>
						</div>
						<div class="grid-item">${employee.empName}</div>
						<div class="grid-item">
							<h6>작성부서</h6>
						</div>
						<div class="grid-item">${employee.departmentVO.deptName}부서</div>
						<div class="grid-item">
							<h6>결재종류</h6>
						</div>
						<div class="grid-item">
							<select name="apprCtgr" id="apprCtgr">
								<option value="" disabled>신규신청</option>
								<option value="">비품변경</option>
								<option value="" disabled>분실신고</option>
							</select>
						</div>
						<div class="grid-item">
							<h6>신청일</h6>
						</div>
						<div class="grid-item">8</div>
						<div class="grid-item">
							<h6>결재자</h6>
						</div>
						<div class="grid-item">10</div>
						<div class="grid-item">
							<h6>결재부서</h6>
						</div>
						<div class="grid-item">12</div>
						<div class="grid-item">
							<h6>제목</h6>
						</div>
						<div class="grid-item"><input type="text"></div>
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
								<th><input type="checkbox" id="appr-item-checked-all"  data-target-class="target-prdt-dtl-id"></th>
								<th>비품번호</th>
								<th>종류</th>
								<th>품목</th>
								<th>대여일</th>
								<th><!--<button id="btn-add-prdt">추가</button>--></th>
							</tr>
						</thead>
						<tbody id="prdt-list">
							<c:forEach items="${borrowList.borrowList}" var="borrow">
								<tr>
									<td><input type="checkbox" class="target-prdt-dtl-id" name="targetPrdtDtlId" value="${borrow.prdtMngId}"></td>
									<td><input type="text" value="${borrow.prdtMngId}" readonly></td>
									<td><input type="text" readonly></td>
									<td><input type="text" value="${borrow.productVO.prdtName}" readonly></td>
									<td><input type="text" value="${borrow.brrwDt}" readonly></td>
									<td><button id="btn-remove-prdt">삭제</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</form>
		<!-- 모달 시작 -->
		<!-- <div id="modalWrap">
			<div id="modalBody">
				<span id="closeBtn">&times;</span> 
				<p>
					<form id="searchForm" action="/search" method="GET">
						<input type="text" id="searchInput" name="q" placeholder="검색어를 입력하세요">
						<button type="submit" id="searchButton">검색</button>
					</form>
				</p> 
			</div>
		</div> -->
	</div>
</body>
</html>