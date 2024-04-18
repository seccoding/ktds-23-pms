<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>결재목록</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <style>
        .title {
            margin-bottom: 3rem;
        }
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border-bottom: 1px solid #dddddd;
            text-align: center;
            padding: 8px;
        }
        .search-area {
            display: flex;
            justify-content: space-between;
            margin-bottom: 1.5rem;
        }
        .search-keyword {
            display: flex;
        }

        .search-keyword > * {
            padding-right: 0.825rem;
        }
    </style>
<script type="text/javascript">
function state(apprid) {
	console.log(apprid);
	var params = {
			apprid:apprid
	}

	$.ajax({
		type : "POST", 
		url : "/approval/approve",
		data : params,
		success : function(res){ // 비동기통신의 성공일경우 success콜백으로 들어옵니다. 'res'는 응답받은 데이터이다.
            // 응답코드 > 0000
            alert("결제 승인이 완료되어 습니다");
            
        },
        error : function(XMLHttpRequest, textStatus, errorThrown){ // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
            alert("결제 승인을 취소 하였습니다");
        }
	})
	
}
</script>
</head>
<body>
    <div class="container">
        <div class="title">
            <h2>결재 목록조회</h2>
        </div>
        <div class="search-area">
            <div class="search-date">
                <input type="text">
                <button>날짜검색</button>
            </div>
            <div class="search-keyword">
                <div class="search-category">
                    <select name="" id="">
                        <option value="">제목</option>
                        <option value="">사원명</option>
                    </select>
                </div>
                <div class="search-text">
                    <input type="text">
                    <button>검색</button>
                </div>
            </div>
        </div>
        <div class="table">
            <table>
                <thead>
                    <tr>
                        <th></th>
                        <th>작성일자</th>
                        <th>종류</th>
                        <th>문서번호</th>
                        <th>문서제목</th>
                        <th>기안자</th>
                        <th>결재상태</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty apprList.apprList}">
                            <c:forEach items="${apprList.apprList}" var="approval">
                            <tr>
                                <td>
                                    <input type="checkbox" class="target-appr-id" name="targetApprId" value="${approval.apprId}">
                                </td>
                                <td>${approval.dmdDt}</td>
                                <td>
                                    <c:if test="${approval.apprCtgr eq '902'}">
                                        <span>비품변경</span>
                                    </c:if>
                                </td>
                                <td>${approval.apprId}</td>
                                <td>
                                    <a href="/approval/approvalview?apprId=${approval.apprId}">
                                        ${approval.apprTtl}
                                    </a>
                                </td>
                                <td>${approval.employeeVO.empName}</td>
                                <td>
                                	 <c:if test="${approval.apprSts ne '802'}">
										<button onclick="state('${approval.apprId}')">
											결재
										</button>
										
									</c:if>
																		
									<c:if test="${approval.apprSts eq '801'}">
										<button>결재대기</button>
									</c:if>
									<c:if test="${approval.apprSts eq '802'}">
										<button>결재승인</button>
									</c:if>
									<c:if test="${approval.apprSts eq '803'}">
										<button>결재반려</button>
									</c:if>
                                </td>
                            </tr>
                            </c:forEach>
                        </c:when>
                    </c:choose>	
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>