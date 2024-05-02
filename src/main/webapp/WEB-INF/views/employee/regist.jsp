<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" />
<title>회원가입 페이지</title>
<jsp:include page="../commonheader.jsp"></jsp:include>
<jsp:include page="../commonmodal.jsp" />
<style type="text/css">
.grid {
	display: grid;
	grid-template-columns: 100px 1fr;
	column-gap: 0;
	gap: 5px;
	align-items: baseline;
	width: 500px;
}

input[type="text"], input[type="email"], input[type="password"], input[type="date"],
	input[type="file"], select {
	width: 85%;
	min-width: 431px;
	max-width: 600px;
	height: 2rem;
	border: 0;
	border-radius: var(--box-border-radius);
	padding-left: 10px;
	outline: none;
	background-color: var(--border-color);
}

#prfl {
	align-content: space-around;
}

.regist-btn {
	display: grid;
	justify-items: end;
}

.erro


</style>

<script type="text/javascript" src="/js/employee/regist.js"></script>

</head>

<body>
	<div>
		<h2>사원등록</h2>

	</div>
	<div class="content">
		<form id="registForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="next" id="next" value="${nextUrl}" />
			<div class="grid">
				<div class="empName">
					<label for="empName">사원이름: </label>
				</div>
				<div>
					<input id="empName" type="text" name="empName"
						value="${employeeVO.empName}" />
				</div>
				<div class="pwd">
					<label for="pwd">비밀번호: </label>
				</div>
				<div>
					<input id="pwd" type="password" name="pwd"
						value="${employeeVO.pwd}"
						placeholder="영문, 숫자, 특수문자로 이루어지고 한개 이상 포함, 10자리 이상" />
				</div>
				<div class="confirmPwd">
					<label for="confirmPwd">비밀번호 확인: </label>
				</div>
				<div>
					<input id="confirmPwd" type="password" name="confirmPwd"
						value="${employeeVO.confirmPwd}" placeholder="비밀번호를 다시한번 입력해 주세요" />
				</div>
				<div class="hireDt">
					<label for="hireDt">입사일: </label>
				</div>
				<div>
					<input id="hireDt" type="date" name="hireDt"
						value="${employeeVO.hireDt}" />
				</div>
				<div>
					<label for="prfl">프로필 사진: </label>
				</div>
				<div>
					<input id="prfl" type="file" name="fileName" />
				</div>
				<div>
					<label for="cntct">연락처: </label>
				</div>
				<div>
					<input id="cntct" type="text" name="cntct"
						value="${employeeVO.cntct}" placeholder="000-0000-0000" />
				</div>
				<div class="addr">
					<label for="addr">주소: </label>
				</div>
				<div>
					<input id="addr" type="text" name="addr" value="${employeeVO.addr}" />
				</div>
				<div class="brth">
					<label for="brth">생년월일: </label>
				</div>
				<div>
					<input id="brth" type="date" name="brth" value="${employeeVO.brth}" />
				</div>
				<div class="pstnId">
					<label for="pstnId">직급번호: </label>
				</div>
				<div>
					<%-- <input type="text" id="pstnId" name="pstnId" value="${employeeVO.pstnId}"
                        placeholder="101~110" /> --%>
					<select id="pstnId" name="pstnId">
						<option value="101" ${employeeVO.pstnId eq 101 ? 'selected' : ''}>인턴</option>
						<option value="102" ${employeeVO.pstnId eq 102 ? 'selected' : ''}>사원</option>
						<option value="103" ${employeeVO.pstnId eq 103 ? 'selected' : ''}>대리</option>
						<option value="104" ${employeeVO.pstnId eq 104 ? 'selected' : ''}>과장</option>
						<option value="105" ${employeeVO.pstnId eq 105 ? 'selected' : ''}>차장</option>
						<option value="106" ${employeeVO.pstnId eq 106 ? 'selected' : ''}>부장</option>
						<option value="107" ${employeeVO.pstnId eq 107 ? 'selected' : ''}>이사</option>
						<option value="108" ${employeeVO.pstnId eq 108 ? 'selected' : ''}>상무이사</option>
						<option value="109" ${employeeVO.pstnId eq 109 ? 'selected' : ''}>전무이사</option>
						<option value="110" ${employeeVO.pstnId eq 110 ? 'selected' : ''}>대표이사</option>
					</select>
				</div>
				<div class="deptId">
					<label for="deptId">부서번호: </label>
				</div>
				<div>
					<%-- <input id="deptId" type="text" name="deptId" value="${employeeVO.deptId}"
                        placeholder="DEPT_000000_000000" /> --%>
					<select id="deptId" name="deptId">
						<option value="" ${employeeVO.deptId eq '' ? 'select' : ''}>없음</option>
						<c:forEach items="${departmentList.departmentList}" var="dept">
							<option value="${dept.deptId}" ${employeeVO.deptId eq dept.deptId ? 'select' : ''}>${dept.deptName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="jobId">
					<label for="jobId">직무번호: </label>
				</div>
				<div>
					<%-- <input id="jobId" type="text" name="jobId" value="${employeeVO.jobId}"
                        placeholder="JOB_000000_000000" /> --%>
                    <select id="jobId" name="jobId">
                    	<option value="" ${employeeVO.jobId eq '' ? 'select' : ''}>없음</option>
						<c:forEach items="${jobList.jobList}" var="job">
							<option value="${job.jobId}" ${employeeVO.jobId eq job.jobId ? 'select' : ''}>${job.jobName}</option>
						</c:forEach>
					</select>
				</div>
				<div>
					<label for="mngrYn">임원여부: </label>
				</div>
				<div>
					<input id="mngrYn" type="checkbox" name="mngrYn"
						value="${employeeVO.mngrYn == 'N'}" />
					<label for="mngrYn"></label>
					<label for="mngrYn"></label>
				</div>
				<div>
				</div>
				<div class="regist-btn">
					<button type="button" id="regist-btn">회원가입</button>
				</div>
			</div>
		</form>
	</div>
	<div class="footer"></div>
</body>

</html>