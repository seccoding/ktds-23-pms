<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8" />
			<title>회원가입 페이지</title>
			<jsp:include page="../commonheader.jsp"></jsp:include>
			<style type="text/css">
				.grid {
					display: grid;
					grid-template-columns: 100px 1fr;
					column-gap: 0;
					gap: 5px;
				}

				input[type="text"],
				input[type="email"],
				input[type="password"],
				input[type="date"],
				input[type="file"] {
					width: 85%;
					min-width: 385px;
					max-width: 600px;
					height: 2rem;
					border: 0;
					border-radius: var(--box-border-radius);
					padding-left: 10px;
					outline: none;
					background-color: var(--border-color);
				}

				input[type="checkbox"] {
					/* 체크박스 만들기 */
					width: 1.2rem;
					height: 1.2rem;
					border: 1px solid #000;
					background-color: var(--border-color);
					display: inline-block;
					cursor: pointer;
					vertical-align: middle;
					border-radius: 0.3rem;
					user-select: none;
					/* 드래그 금지 */
					margin: 0.3rem;
				}

				input[type="checkbox"]:checked {
					/* 체크되었을 때 */
					background: var(--btn-color);
				}

				input[type="checkbox"]:disabled {
					/* 체크되었을 때 */
					background: var(--secondary);
				}

			</style>

			<script type="text/javascript" src="/js/employee/regist.js"></script>

		</head>

		<body>
			<div class="header">
				<h2>홈페이지 로고 + 회원가입</h2>
				<div>경영지원부 소속 사원들만 회원가입을 진행 할 수 있습니다</div>
			</div>
			<div class="content">
				<form id="registForm" method="post">
					<input type="hidden" name="next" id="next" value="${nextUrl}" />
					<div class="grid">
						<div class="empId">
							<label for="empId">사원번호: </label>
						</div>
						<div>
							<input id="empId" type="text" name="empId" value="${employeeVO.empId}"
								placeholder="0000000 숫자 7자리" />
						</div>
						<div class="pwd">
							<label for="pwd">비밀번호: </label>
						</div>
						<div>
							<input id="pwd" type="password" name="pwd" value="${employeeVO.pwd}"
								placeholder="영문, 숫자, 특수문자로 이루어지고 한개 이상 포함, 10자리 이상" />
						</div>
						<div class="empName">
							<label for="empName">사원이름: </label>
						</div>
						<div>
							<input id="empName" type="text" name="empName" value="${employeeVO.empName}" />
						</div>
						<div class="hireDt">
							<label for="hireDt">입사일: </label>
						</div>
						<div>
							<input id="hireDt" type="date" name="hireDt" value="${employeeVO.hireDt}" />
						</div>
						<div>
							<label for="prfl">프로필 사진: </label>
						</div>
						<div>
							<input id="prfl" type="file" name="prfl" value="${employeeVO.prfl}" />
						</div>
						<div>
							<label for="cntct">연락처: </label>
						</div>
						<div>
							<input id="cntct" type="text" name="cntct" value="${employeeVO.cntct}"
								placeholder="000-0000-0000" />
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
							<input id="email" type="email" name="email" value="${employeeVO.email}"
								placeholder="test@test.com" />
						</div>
						<div class="pstnId">
							<label for="pstnId">직급번호: </label>
						</div>
						<div>
							<input id="pstnId" type="text" name="pstnId" value="${employeeVO.pstnId}"
								placeholder="101~110" />
						</div>
						<div class="deptId">
							<label for="deptId">부서번호: </label>
						</div>
						<div>
							<input id="deptId" type="text" name="deptId" value="${employeeVO.deptId}"
								placeholder="DEPT_000000_000000" />
						</div>
						<div class="jobId">
							<label for="jobId">직무번호: </label>
						</div>
						<div>
							<input id="jobId" type="text" name="jobId" value="${employeeVO.jobId}"
								placeholder="JOB_000000_000000" />
						</div>
						<div>
							<label for="mngrYn">임원여부: </label>
						</div>
						<div>
							<input id="mngrYn" 
							<%-- type="checkbox" --%> 
							type="text"
							name="mngrYn" value="${employeeVO.mngrYn}" />
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