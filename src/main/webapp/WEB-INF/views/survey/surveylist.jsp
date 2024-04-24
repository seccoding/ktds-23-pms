<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문 목록</title>
<jsp:include page="../commonheader.jsp"></jsp:include>
<script type="text/javascript" src="/js/survey/surveylist.js"></script>
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
                    <th>설문지 생성 여부</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty surveyList.projectList}">
                        <c:forEach items="${surveyList.projectList}" var="survey">
                            <tr class="prj-list" data-prj-id="${survey.prjId}" data-srv-sts="${survey.srvSts}"> 
                               
                                <td>${survey.prjName}</td>
                                <td>${survey.clntInfo}</td>
                                <td>${survey.deptVO.deptName}</td>
                                <td>${survey.prjSts}</td>
                                <td>${survey.strtDt}</td>
                                <td>${survey.endDt}</td>
                                <td>${survey.srvSts}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="7">
                                <div>담당 중인 프로젝트가 없거나 담당 중인 프로젝트의 설문 생성이 불가능한 상태인 것 같습니다.</div>
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</body>
</html>