<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

input[type="checkbox"] {
	width: 1.2rem;
	height: 1.2rem;
	border: 1px solid #000;
	background-color: var(--border-color);
	display: inline-block;
	cursor: pointer;
	vertical-align: middle;
	border-radius: 0.3rem;
	user-select: none;
	margin: 0.3rem;
}

input[type="checkbox"]:checked {
	background: var(--btn-color);
}

input[type="checkbox"]:disabled {
	background: var(--secondary);
}
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
				<div class="empId">
					<label for="empId">사원번호: </label>
				</div>
				<div>
					<input id="empId" type="text" name="empId"
						value="${employeeVO.empId}" placeholder="0000000 숫자 7자리" />
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
				<div class="empName">
					<label for="empName">사원이름: </label>
				</div>
				<div>
					<input id="empName" type="text" name="empName"
						value="${employeeVO.empName}" />
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
				<div class="email">
					<label for="email">이메일: </label>
				</div>
				<div>
					<input id="email" type="email" name="email"
						value="${employeeVO.email}" placeholder="test@test.com" />
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
						<option value="DEPT_230101_000001"
							${employeeVO.deptId eq DEPT_230101_000001 ? 'select' : ''}>사업기획</option>
						<option value="DEPT_230101_000002"
							${employeeVO.deptId eq DEPT_230101_000002 ? 'select' : ''}>디자인</option>
						<option value="DEPT_230101_000003"
							${employeeVO.deptId eq DEPT_230101_000003 ? 'select' : ''}>사업관리</option>
						<option value="DEPT_230101_000004"
							${employeeVO.deptId eq DEPT_230101_000004 ? 'select' : ''}>SW아키텍처</option>
						<option value="DEPT_230101_000005"
							${employeeVO.deptId eq DEPT_230101_000005 ? 'select' : ''}>데이터아키텍처</option>
						<option value="DEPT_230101_000006"
							${employeeVO.deptId eq DEPT_230101_000006 ? 'select' : ''}>어플리케이션운영</option>
						<option value="DEPT_230101_000007"
							${employeeVO.deptId eq DEPT_230101_000007 ? 'select' : ''}>SW개발</option>
						<option value="DEPT_230101_000008"
							${employeeVO.deptId eq DEPT_230101_000008 ? 'select' : ''}>HW개발</option>
						<option value="DEPT_230101_000009"
							${employeeVO.deptId eq DEPT_230101_000009 ? 'select' : ''}>보안</option>
						<option value="DEPT_230101_000010"
							${employeeVO.deptId eq DEPT_230101_000010 ? 'select' : ''}>경영지원</option>
						<option value="DEPT_230101_000011"
							${employeeVO.deptId eq DEPT_230101_000011 ? 'select' : ''}>재무</option>
						<option value="DEPT_230101_000012"
							${employeeVO.deptId eq DEPT_230101_000012 ? 'select' : ''}>인사</option>
						<option value="DEPT_230101_000013"
							${employeeVO.deptId eq DEPT_230101_000013 ? 'select' : ''}>구매</option>
					</select>
				</div>
				<div class="jobId">
					<label for="jobId">직무번호: </label>
				</div>
				<div>
					<%-- <input id="jobId" type="text" name="jobId" value="${employeeVO.jobId}"
                        placeholder="JOB_000000_000000" /> --%>
					<select id="jobId" name="jobId">
						<option value="JOB_240412_000001"
							${employeeVO.jobId eq JOB_240412_000001 ? 'select' : ''}>사업개발</option>
						<option value="JOB_240412_000002"
							${employeeVO.jobId eq JOB_240412_000002 ? 'select' : ''}>R&D</option>
						<option value="JOB_240412_000003"
							${employeeVO.jobId eq JOB_240412_000003 ? 'select' : ''}>컨설팅</option>
						<option value="JOB_240412_000004"
							${employeeVO.jobId eq JOB_240412_000004 ? 'select' : ''}>PJT관리</option>
						<option value="JOB_240412_000005"
							${employeeVO.jobId eq JOB_240412_000005 ? 'select' : ''}>아키텍처설계</option>
						<option value="JOB_240412_000006"
							${employeeVO.jobId eq JOB_240412_000006 ? 'select' : ''}>개발운영</option>
						<option value="JOB_240412_000007"
							${employeeVO.jobId eq JOB_240412_000007 ? 'select' : ''}>품질</option>
						<option value="JOB_240412_000008"
							${employeeVO.jobId eq JOB_240412_000008 ? 'select' : ''}>경영지원</option>
						<option value="JOB_240412_000009"
							${employeeVO.jobId eq JOB_240412_000009 ? 'select' : ''}>영업</option>
						<option value="JOB_240412_000010"
							${employeeVO.jobId eq JOB_240412_000010 ? 'select' : ''}>CS</option>
					</select>
				</div>
				<div>
					<label for="mngrYn">임원여부: </label>
				</div>
				<div>
					<input id="mngrYn" type="checkbox" name="mngrYn"
						value="${employeeVO.mngrYn == 'N'}" />
				</div>
				<div>
					<button type="button" id="regist-btn">회원가입</button>
				</div>

			</div>
		</form>
	</div>
	<div class="footer"></div>
</body>

</html>