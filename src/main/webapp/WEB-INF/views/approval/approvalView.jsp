<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src=src="/js/approval/approvalview.js"></script>
</head>
<body>
	<table class="table" href="/approval/view?id=${approval.apprid}">
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
			<tr>
				<td>${approvalvo.apprid}</td>					
				<td>${approvalvo.apprsts}</td>
				<td>${approvalvo.dmddt}</td>
				<td>${approvalvo.lastcndt}</td>
				<td>${approvalvo.dmdid}</td>
				<td>${approvalvo.apprmngid}</td>
			</tr>		
        </tbody>
     	
 
           
        <c:if test="${approval.apprsts ne '803'}">
             <td colspan="6">
                  <button class="delete-board"   href="javascript:void(${approvalvo.apprid} )">삭제</button>
	         </td>
		</c:if>
		
		
		
		<table>
			<thead>
				<tr>
					<th>결재 상세ID</th>
					<th>결재ID</th>
					<th>비품ID</th>
					<th>수량</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty  approvalDetailList.approvalDetailList}">
						<c:forEach items="${approvalDetailList.approvalDetailList}" var="approvalderail">
							<c:if test="${id  eq approvalderail.apprId}">
								<tr>
									<th>${approvalderail.apprDtlId}</th>
									<th>${approvalderail.apprId}</th>
									<th>${approvalderail.prdtId}</th>
									<th>${approvalderail.curStr}</th>
								</tr>
								
								 
							</c:if>
										
						</c:forEach>
					</c:when>
				</c:choose>
			</tbody>
		</table>
		
	</table>
</body>
</html>