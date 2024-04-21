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
                    <th>설문지 작성 여부</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty surveyList.surveyList}">
                        <c:forEach items="${surveyList.surveyList}" var="survey">
                            <tr class="prj-list" data-prj-id="${survey.prjId}" data-srv-sts="${survey.srvSts}">
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