<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사원 정보수정</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script type="text/javascript" src="/js/employee/employeemodify.js"></script>
    <style>
        .hidden{
            display: none;
        }
        .modal{
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }
        .flex-col{
            display: flex;
            flex-direction: column;
            margin: 2rem;
        }
        .flex-col > div{
            margin: 1rem;
        }
        label{
            font-weight: bold;
        }

    </style>
</head>
<body>
    <h2>사원 정보수정</h2>
    <!-- <form 
    action="/employee/modify/${employeeVO.empId}"
    method="post"
    enctype="multipart/form-data"> -->

    <div class="grid" data-teamlist="${sessionScope._LOGIN_USER_.teamList}">
        <label for="empId">사원 ID</label>

        <input type="text" id="empId"
                name="empId" value="${sessionScope._LOGIN_USER_.empId}"/>

        <label for="empName">사원 이름</label>
        <input type="text" id="empName"
                name="empName" value="${sessionScope._LOGIN_USER_.empName}"/>

        <label for="workSts">재직 상태</label>
        <input type="text" id="workSts"
               name="workSts" value="${sessionScope._LOGIN_USER_.workSts}"/>

        <label for="hireYear">입사 연차</label>
        <input type="text" id="hireYear"
        name="hireYear" value="${sessionScope._LOGIN_USER_.hireYear}"/>

        <label for="hireDt">입사일</label>
        <input type="text" id="hireDt"
        name="hireDt" value="${sessionScope._LOGIN_USER_.hireDt}"/>

        <c:if test="${empty sessionScope._LOGIN_USER_.teamList}">
          <label for="noneTmName" >팀</label>
          <div id="noneTmName">소속된 팀이 존재하지 않습니다.</div>
        </c:if>
        <c:forEach items="${sessionScope._LOGIN_USER_.teamList}" var="teamList">
            <label for="tmName">팀</label>
            <div>
                <div id="tmName">${teamList.tmName}</div>
                <button class="delete-team" data-tmid="${teamList.tmId}">삭제</button>

        <c:choose>
            <c:when test="${sessionScope._LOGIN_USER_.admnCode eq '301'}">
                <label for="workSts">재직 상태</label>
                <input type="text" id="workSts" name="workSts" id="${employeeVO.workSts}" value="${employeeVO.workSts}"/>
                <label for="hireYear">입사 연차</label>
                <input type="text" id="hireYear"
                name="hireYear" value="${employeeVO.hireYear}"/>

        <label for="dept-select">부서</label>
        <div>
<%--            <select id="dept-select" class="dept-select" data-origin="${sessionScope._LOGIN_USER_.deptId}">--%>
                <c:forEach items="${departmentlist.departmentList}" var="department">
                    <option  value="${department.deptId}" >${department.deptName}</option>

                </c:forEach>
                <div id="will-add-team" class="hidden">
                    <div>추가 예정 팀</div>
        
                </div>
        
                <button id="add-team">팀 추가</button>
                <dialog class="team-modal modal">
                    <div class="flex-col">
                        <div>추가할 팀을 선택해주세요</div>
                        <select id="add-team-select">
                            <!-- <c:forEach items="${teamListinDept}" var="team"> 
                                <option value="${team.tmId}">${team.tmName}</option>
            
                            </c:forEach> -->
                        </select>
                        <div>
                            <button id="add-team-final">추가</button>
                            <button id="add-team-cancel">취소</button>
            
                        </div>
        
                    </div>
                </dialog>
                
                <label for="dept-select" >부서</label>
                <input class="origin-dept"  value="${employeeVO.departmentVO.deptName}" disabled/>
                <button class="change-dept-btn">부서변경</button>
                <div id="hidden-selectbox" class="hidden">
                    <select id="dept-select" class="dept-select" data-origin="${employeeVO.deptId}">
                        <c:forEach items="${departmentlist.departmentList}" var="department">
                            <option  value="${department.deptId}" >${department.deptName}</option>
                        </c:forEach>
                    </select>
            
                    <input type="text" placeholder="부서 변경 사유를 입력해주세요." id="dept-change-cmt" class="hidden"/>
        
                </div>
            </c:when>
            <c:otherwise>
                <label for="workSts">재직 상태</label>
                <input type="text" id="workSts" name="workSts" id="${employeeVO.workSts}" value="${employeeVO.workSts}" disabled/>
                <label for="hireYear">입사 연차</label>
                <input type="text" id="hireYear" name="hireYear" value="${employeeVO.hireYear}" disabled/>
        
                <label for="hireDt">입사일</label>
                <input type="text" id="hireDt" name="hireDt" value="${employeeVO.hireDt}" disabled/>
        
                <label for="posiHist">직급</label>
                <input type="text" id="posiHist" name="posiHist" value="${employeeVO.commonCodeVO.cmcdName}" disabled/>

                <c:if test="${empty employeeVO.teamList}">
                  <label for="noneTmName" >팀</label>
                  <div id="noneTmName">소속된 팀이 존재하지 않습니다.</div>
                </c:if>
                <c:forEach items="${employeeVO.teamList}" var="teamList">
                    <label for="tmName">팀</label>
                    <div id="tmName">${teamList.tmName}</div>
                </c:forEach>
                
                <label for="dept-select" >부서</label>
                <input class="origin-dept"  value="${employeeVO.departmentVO.deptName}" disabled/>
               
            </c:otherwise>
        </c:choose>

        
        <!-- <input type="text" id="deptName"
        name="deptName" value="${employeeVO.departmentVO.deptName}"/> -->

        <div class="btn-group">
            <div class="right-align">
                <!-- <input type="submit" value="저장"/> -->
                <button class="save-modify">저장</button>
            </div>
        </div>
    </div>
    <!-- </form> -->
</body>
</html>