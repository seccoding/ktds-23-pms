<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>사원 출퇴근 관리 페이지</title>
<jsp:include page="../commonheader.jsp" />
<style type="text/css"></style>
<script type="text/javascript" src="/js/commute/view.js"></script>
</head>
<body>
	<h2>출퇴근 관리</h2>
	<div class="grid">
		<form id="search-form">
			<input type="hidden" id="page-no" name="pageNo" value="0" />
			<div>
				<select id="search-type" name="searchType">
					<option value="cmmtDate" ${commuteVO.searchType eq 'cmmtDate' ? 'selected' : ''}>날짜</option>
					<c:if test="${sessionScope._LOGIN_USER_.admnCode eq '301'}">
						<option value="empName" ${commuteVO.searchType eq 'empName' ? 'selected' : ''}>이름</option>
						<option value="empId" ${commuteVO.searchType eq 'empId' ? 'selected' : ''}>사원번호</option>
					</c:if>
				</select>
				<select id="log-type" name="commuteType">
					<option value="today" ${commuteVO.commuteType eq 'today' ? 'selected' : ''}>오늘</option>
					<option value="oneMonth" ${commuteVO.commuteType eq 'oneMonth' ? 'selected' : ''}>1개월</option>
					<option value="twoMonth" ${commuteVO.commuteType eq 'twoMonth' ? 'selected' : ''}>2개월</option>
					<option value="thrMonth" ${commuteVO.commuteType eq 'thrMonth' ? 'selected' : ''}>3개월</option>
				</select>
				<input type="text" name="searchKeyword" value="${commuteVO.searchKeyword}" />
				<button type="button" id="search-btn">검색</button>
			</div>
		</form>
		<table class="table">
			<thead>
				<tr>
					<th>출근 날짜</th>
					<th>사원 이름</th>
					<th>사원 번호</th>
					<th>출근 시간</th>
					<th>퇴근 시간</th>
				</tr>
			</thead>

			<tbody>
				<c:choose>
					<%-- 출퇴근 기록이 비어있지 않다면 --%>
					<c:when test="${not empty commuteList.commuteList}">
						<%-- 출퇴근 기록의 리스트만큼 반복 --%>
						<c:forEach items="${commuteList.commuteList}" var="commute">
							<tr>
								<td>${commute.cmmtDate}</td>
								<td>${commute.empName}</td>
								<td>${commute.empId}</td>
								<td>${commute.cmmtTime}</td>
								<td>${commute.fnshTime}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="4">조회된 출퇴근 기록이 없습니다</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>

		</table>
	</div>
</body>
</html>
