<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table class="table">
		 <thead>
          	<tr>
				<th>결재ID</th>
				<th>결재상태</th>
				<th>신청일</th>
				<th>마지막 변경일</th>
				<th>신청일</th>
				<th>결재자ID</th>
			</tr>
        </thead>
        <tbody>
        	<c:choose>
				<c:when test="${not empty  apprList.apprList}">
					<c:forEach items="${apprList.apprList}" var="approval">
						<tr>
							<td>
								<a class="ellipsis" href="/approval/view?id=${approval.apprid}">
								${approval.apprId}</a>
								
							</td>
							<td>${approval.apprsts}</td>
							<td>${approval.dmddt}</td>
							<td>${approval.lastcndt}</td>
							<td>${approval.dmdid}</td>
							<td>${approval.apprmngid}</td>
						</tr>
						<tr>
							<c:if test="${approval.apprsts != '803'}">
				                <td colspan="6">
									<div class="selectBox">
									  <select name="fruits" class="select">
										  <option value="apple" selected>기대여 비품 변경</option>
										</select>
									</div>	
				                </td>
				            </c:if>
						</tr>
												
					</c:forEach>
									
				</c:when>
				
				
			</c:choose>			
        </tbody>      
	</table>
	
</body>
</html>