<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>팀원 페이지</title>
    <jsp:include page="../commonheader.jsp"/>
    <script type="text/javascript" src="/js/project/teammate.js"></script>
    <style>
        .btn-group {
            margin: 15px;
            float: right;
        }

        .h-59 {
            height: 59px;
        }

        .modal {
            height: 190px;
            width: 365px;
            margin: auto;
            border: 1px solid #777;
            border-radius: 4px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            position: fixed;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            background-color: #fff;
            z-index: 1000;
            text-align: center;
            /* 모달 상자 내부에서 'X' 버튼이 차지하는 공간을 고려하여 상단 패딩을 조정합니다. */
            /* 'X' 버튼의 높이 + 원하는 여백 */
            padding: 40px 10px 10px;
        }

        .modal-content {
            /* 적절한 패딩 값을 설정하여 모달 내용과 'X' 버튼이 겹치지 않도록 합니다. */
            padding-top: 20px;
        }

        .modal-text {
            font-size: 20px;
            font-weight: bold;
            padding-top: 10px;
            padding-bottom: 30px;
        }

        #modal-cancel-button {
            line-height: 1; /* 높이를 정확히 조절하려면 'line-height'를 '1'로 설정합니다. */
            position: absolute;
            right: 20px; /* 오른쪽 테두리로부터 20px 떨어진 위치 */
            top: 20px; /* 상단 테두리로부터 20px 떨어진 위치 */
            background: none; /* 기본 버튼 배경 제거 */
            border: none; /* 기본 버튼 테두리 제거 */
            padding: 0; /* 내부 패딩 제거 */
            width: 30px; /* 'X' 버튼의 너비 */
            height: 30px; /* 'X' 버튼의 높이 */
        }

        .close:after {
            content: "\00d7";
            font-size: 25pt;
            position: absolute;
            left: 100%;
            transform: translate(-50%, -50%);
            width: 30px; /* 'X' 버튼과 같은 너비로 설정 */
            height: 30px; /* 'X' 버튼과 같은 높이로 설정 */
            text-align: center;
            vertical-align: middle;
            line-height: 30px; /* 'X' 문자의 라인 높이를 버튼 높이와 동일하게 설정 */
            color: #000;
            cursor: pointer;
        }
    </style>
</head>
<body>

<div class="modal" id="delete-alert-modal" style="display:none;">


    <div id="modal-cancel-button" class="close"></div>

    <div class="modal-content">
        <div class="modal-text">팀원을 삭제하시겠습니까?</div>
        <button id="modal-delete-button">삭제</button>
    </div>

</div>

<div>
    <div>
        <h4>
            프로젝트 : ${projectName}
        </h4>
    </div>

    <div>
        ${teammateCount}명
    </div>

    <table class="table">
        <thead>
        <tr>
            <%--      삭제기능, if 필요      --%>
            <th>
                <input type="checkbox"
                       id="checked-all"
                       data-target-class="target-teammate-id"/>
                <label for="checked-all"></label>
            </th>
            <th>부서</th>
            <th>역할</th>
            <th>이름</th>
            <th>이메일</th>

            <%--      삭제기능, if 필요      --%>
            <th></th>

        </tr>
        </thead>
        <tbody>
        <c:choose>
            <%-- projectList 내용이 존재한다면 --%>
            <c:when test="${not empty teammate}">
                <%-- 내용을 반복해서 보여줌 --%>
                <c:forEach items="${teammate}" var="teammate">
                    <tr class="teammate-row" data-teammate-id="${teammate.tmId}">
                            <%--      삭제기능, if 필요      --%>
                        <td>
                            <c:choose>
                                <c:when test="${teammate.role eq 'PM'}">
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox"
                                           class="target-teammate-id"
                                           name="targetTeammateId"
                                           value="${teammate.prjTmId}"
                                           id="${teammate.prjTmId}">
                                    <label for="${teammate.prjTmId}"></label>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${teammate.employeeVO.departmentVO.deptName}</td>
                        <td>${teammate.role}</td>
                        <td>${teammate.employeeVO.empName}</td>
                        <td>${teammate.employeeVO.email}</td>
                            <%--      삭제기능, if 필요      --%>

                        <c:choose>
                            <c:when test="${teammate.role eq 'PM'}">
                                <td class="h-59">
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    <button value="${teammate.prjTmId}"
                                            name="deleteTeammate"
                                            onclick="javascript:void(0);">삭제
                                    </button>

                                </td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
            </c:when>
            <%-- projectList의 내용이 존재하지 않는다면 --%>
            <c:otherwise>
                <tr>
                    <td colspan="6">
                        <p>
                            등록된 팀원이 없습니다.
                        </p>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>


    <div class="btn-group">
        <div>
            <button id="new-teammate" onclick="javascript:void(0);">팀원 등록</button>
            <button id="delete-massive-teammate" onclick="javascript:void(0);">일괄 삭제</button>
        </div>
    </div>
</div>
</body>
</html>
