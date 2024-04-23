<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
    <jsp:include page="./commonheader.jsp"></jsp:include>
<script type="text/javascript" src="/js/commonemployeelist.js"></script>
<style>

    .modal-employee-list {
        top: 10%;
        left: 10%;

        width: 80%;
        height: 80%;
    }
</style>
<dialog class="modal-employee-list">
    <div class="grid-modal-list">
      <div class="modal-list-close">X</div>
      <div class="modal-emp-content">
        <div>
            <form id="search-form">
              <input type="hidden" id="page-no" name="pageNo" value="0" />
              <select id="list-size" name="listSize">
                <option value="10" ${searchEmployeeVO.listSize eq 10 ? 'selected' : ''}>10개</option>
                <option value="20" ${searchEmployeeVO.listSize eq 20 ? 'selected' : ''}>20개</option>
                <option value="30" ${searchEmployeeVO.listSize eq 30 ? 'selected' : ''}>30개</option>
                <option value="50" ${searchEmployeeVO.listSize eq 50 ? 'selected' : ''}>50개</option>
              </select>
    
              <select id="search-type" name="searchType">
                <option value="employee_name" ${searchEmployeeVO.searchType eq 'employee_name' ? 'selected' : ''}>사원명</option>
                <option value="department_name" ${searchEmployeeVO.searchType eq 'department_name' ? 'selected' : ''}>부서명</option>
                <option value="job_name" ${searchEmployeeVO.searchType eq 'job_name' ? 'selected' : ''}>직무명</option>
              </select>
    
              <input type="text" id="search-keyword" name="searchKeyWord" value="${searchEmployeeVO.searchKeyWord}" />
              <button type="button" id="search-btn">검색</button>
            </form>
          </div>
      </div>

      <table class="emp-table">
        <thead>
            <tr>
                <th><input type="checkbox" id="checked-all" data-target-class="target-emp-id" />
                    <label for="checked-all"></label></th>
                <th>사원ID</th>
                <th>사원명</th>
                <th>부서명</th>
                <th>직무명</th>
                <th>생년월일</th>
            </tr>
        </thead>
    
    <tbody>
        
    </tbody>
</table>
      
      <div class="input-emplist-space">
        <button class="confirm-emp-button button"></button>
      </div>
    </div>
  </dialog>