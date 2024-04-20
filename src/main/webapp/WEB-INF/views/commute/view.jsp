<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>사원 출퇴근 관리 페이지</title>
<!-- <script type="text/javascript" src="/js/lib/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script> -->
<!-- <script type="text/javascript" src="/js/commute/view.js"></script> -->
</head>
<body>
	<div>
		<table>
			<colgroup>
				<col width="200px" />
				<col width="100px" />
				<col width="300px" />
				<col width="300px" />
			</colgroup>
			<thead>
				<tr>
					<th>출근 날짜</th>
					<th>사원 이름</th>
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
								<td>${commute.cmmtTime}</td>
								<td>${commute.fnshTime}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="4">출퇴근 기록 X</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>

		</table>
	</div>
</body>
</html>
