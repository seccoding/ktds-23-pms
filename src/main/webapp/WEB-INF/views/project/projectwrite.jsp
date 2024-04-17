<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>프로젝트 생성 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>

    <script type="text/javascript" src="/js/project/projectwrite.js"></script>
    <style>
        /* 투명도가 점점 증가하는 키프레임 정의 */
        @keyframes fadeIn {
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }

        /* .error 클래스에 애니메이션 적용 */
        .error {
            grid-column: 1 / -1;
            color: #f00;
            padding-left: 1rem;
            margin: 0;
            animation: fadeIn 0.5s ease-out; /* 애니메이션 지속시간 및 효과 지정 */
        }

        .input-custom {
            padding: 5px;
            outline: none;
            width: 25%; /* 입력 필드의 폭을 조정합니다. */
            height: 30px; /* 높이 조정 */
            border: 1px solid #CCCCCC; /* 테두리 색 변경 */
            border-radius: 4px; /* 테두리 둥근 정도 변경 */
            color: #333333; /* 글자 색 변경 */
        }

        .datalist-custom {
            margin-left: 48px;
            position: absolute;
            background-color: white;
            border: 1px solid #CCCCCC;
            border-radius: 0 0 5px 5px;
            border-top: none;
            overflow-y: auto;
            box-shadow: 0px 6px 12px rgba(0, 0, 0, 0.15); /* 드롭다운에 그림자 추가 */
            max-height: 200px; /* 드롭다운의 최대 높이 조정 */
            width: 25%; /* 드롭다운의 폭을 입력 필드와 일치시킵니다. */
            z-index: 1000; /* 다른 요소 위에 표시되도록 z-index 조정 */
            padding: 2px 10px; /* 패딩을 줄입니다. */
        }

        .option-custom {
            background-color: white;
            margin-bottom: 1px;
            cursor: pointer;
            border-bottom: 1px solid #ddd;
            padding: 8px 10px; /* 옵션의 패딩 조정 */
            color: #333333; /* 옵션의 글자 색 변경 */
        }

        .option-custom:hover, .option-custom:active {
            background-color: #E6E6E6; /* 호버 및 활성 옵션 배경색 변경 */
        }

    </style>
</head>
<body>
<div>
    <h4>프로젝트 생성</h4>
    <form>
        <div class="grid" data-grid-columns="auto"
             data-grid-rows="auto"
             data-column-gap="1rem"
             data-row-gap="1rem">
            <%-- 프로젝트 명, 고객사명 --%>
            <div>
                <label for="project-name">프로젝트명</label>
                <input type="text" id="project-name" name="prjName" value="${project.prjName}" autocomplete="off"/>
            </div>

            <div>
                <label for="client-info">고객사명</label>
                <input type="text" id="client-info" name="clntInfo" value="${project.clntInfo}" autocomplete="off"/>
            </div>

            <%-- 게시판 생성 여부 --%>
            <div>
                <div>
                    <input type="checkbox" id="requirement-check" name="reqYn" value="Y"/>
                    <label for="requirement-check"></label>
                    <label for="requirement-check">요구사항 관리 필요여부</label>
                    <input type="checkbox" id="output-check" name="outYn" value="Y"/>
                    <label for="output-check"></label>
                    <label for="output-check">산출물 관리 필요여부</label>
                </div>
                <div>
                    <input type="checkbox" id="issue-check" name="isYn" value="Y" disabled/>
                    <label for="issue-check"></label>
                    <label for="issue-check">이슈관리</label>
                    <input type="checkbox" id="knowledge-check" name="knlYn" value="Y" disabled/>
                    <label for="knowledge-check"></label>
                    <label for="knowledge-check">지식관리</label>
                    <input type="checkbox" id="qna-check" name="qaYn" value="Y" disabled/>
                    <label for="qna-check"></label>
                    <label for="qna-check">묻고 답하기</label>
                </div>
            </div>

            <%-- 담당 부서 선택 --%>
            <div>
                <label for="dept-list">담당부서 </label>
                <select id="dept-list">
                    <option value="" selected disabled hidden>부서 선택</option>
                    <c:forEach items="${department}" var="department">
                        <option value="${department.deptId}">${department.deptName}</option>
                    </c:forEach>
                </select>
                <input id="hidden-dept-id" type="hidden" name="deptId"/>
            </div>

            <%-- 담당자 (PM) 선택 --%>
            <%--            <div>--%>
            <%--                <label for="hidden-pm-id">담당자 </label>--%>
            <%--                <input list="employee-list" id="pm-search" autocomplete="off" placeholder="담당자 검색">--%>
            <%--                <input id="hidden-pm-id" type="hidden" name="pmId"/>--%>

            <%--                <datalist id="employee-list">--%>
            <%--                    <c:forEach items="${employee}" var="employee">--%>
            <%--                        <option value="${employee.empName} : ${employee.departmentVO.deptName}" data-emp-id="${employee.empId}"></option>--%>
            <%--                    </c:forEach>--%>
            <%--                </datalist>--%>
            <%--            </div>--%>

            <%-- 담당자 (PM) 선택 --%>
            <div>
                <label for="hidden-pm-id">담당자 </label>
                <input id="pm-search" autocomplete="off" placeholder="담당자 검색" class="input-custom">
                <input id="hidden-pm-id" type="hidden" name="pmId"/>
                <div id="employee-list" class="datalist-custom">
                    <c:forEach items="${employee}" var="employee">
                        <div class="option-custom" data-emp-id="${employee.empId}">${employee.empName} : ${employee.departmentVO.deptName}</div>
                    </c:forEach>
                </div>
            </div>


            <%--    html date type 사용으로 해결, datepicker는 미사용    --%>
            <div>
                <div>
                    <label for="start-date">시작일</label>
                    <input type="date" id="start-date" name="strtDt" value="${project.strtDt}"/>
                </div>
                <div>
                    <label for="end-date">종료일</label>
                    <input type="date" id="end-date" name="endDt" value="${project.endDt}"/>
                </div>
            </div>

            <div>
                <div>
                    <button id="btn-create" type="button">생성</button>
                </div>
            </div>
        </div>
    </form>
</div>
</body>

</html>