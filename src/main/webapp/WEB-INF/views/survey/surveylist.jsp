<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문 목록</title>
<jsp:include page="../commonheader.jsp"></jsp:include>
</head>
<body>
    <div>
        <table>
            <colgroup>
                <col width="150px" />
                <col width="150px" />
                <col width="150px" />
                <col width="150px" />
                <col width="150px" />
                <col width="150px" />
                <col width="150px" />
            </colgroup>
            <thead>
                <tr>
                    <th>프로젝트 명</th>
                    <th>고객사</th>
                    <th>수행부서</th>
                    <th>상태</th>
                    <th>시작일</th>
                    <th>종료일</th>
                    <th>설문지 작성 여부</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty surveyList.surveyList}">
                        <c:forEach items="${surveyList.surveyList}" var="survey">
                            <tr onclick="location.href='/survey/view?prjId=${survey.prjId}'">
                                <td>${survey.projectVO.prjName}</td>
                                <td>${survey.projectVO.clntInfo}</td>
                                <td>${survey.departmentVO.deptName}</td>
                                <td>${survey.projectVO.prjSts}</td>
                                <td>${survey.projectVO.strtDt}</td>
                                <td>${survey.projectVO.endDt}</td>
                                <td>${survey.srvSts}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="7">
                                <a href="/survey/write/${survey.prjId}">
                                    설문 작성 ㄱㄱ
                                </a>
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</body>
</html>