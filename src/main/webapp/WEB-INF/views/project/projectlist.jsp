<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>프로젝트 리스트 페이지</title>
            <jsp:include page="../commonheader.jsp"></jsp:include>
            <style type="text/css">
                div.grid {
                    display: grid;
                    grid-template-columns: 1fr;
                    grid-template-rows: 28px 1fr auto 28px;
                    row-gap: 10px;
                }
            </style>
        </head>

        <div class="grid">
            <div>
                총 ${projectList.projectCount} 건의 게시글이 검색되었습니다.
            </div>
            <table class="table">
                <colgroup>
                    <col width="80px" />
                    <%-- 남는 공간, colgroup 은 width만 정의한다. --%>
                        <col width="80px" />
                        <col width="150px" />
                        <col width="80px" />
                        <col width="150px" />
                        <col width="150px" />
                </colgroup>
                <thead>
                    <tr>
                        <th>프로젝트</th>
                        <th>고객사</th>
                        <th>수행부서</th>
                        <th>프로젝트 상태</th>
                        <th>시작일</th>
                        <th>종료일</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- jstl > choose when otherwise / if ~ elif ~ else-->
                    <c:choose>
                        <%-- projectList 내용이 존재한다면 --%>
                            <c:when test="${not empty projectList.projectList}">
                                <%-- 내용을 반복해서 보여줌 --%>
                                    <c:forEach items="${projectList.projectList}" var="project">
                                        <tr>
                                            <td>
                                                <a
                                                    href="/project/view?projectId=${project.prjId}">${project.prjName}</a>
                                            </td>
                                            <td>${project.clntInfo}</td>
                                            <td>${project.deptVO.deptName}</td>
                                            <td>${project.prjStsCode.cmcdName}</td>
                                            <td>${project.strtDt}</td>
                                            <td>${project.strtDt}</td>
                                        </tr>
                                    </c:forEach>
                            </c:when>
                            <%-- projectList의 내용이 존재하지 않는다면 --%>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="6">
                                            <a href="/project/write">
                                                등록된 프로젝트가 없습니다.
                                            </a>
                                        </td>
                                    </tr>
                                </c:otherwise>
                    </c:choose>
                </tbody>
            </table>


            <div class="right-align">
                <a href="/project/write">게시글 등록</a>
            </div>
        </div>
        </body>

        </html>