<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
    <jsp:include page="./commonheader.jsp"></jsp:include>
<script type="text/javascript" src="/js/commonemployeelist.js"></script>
<style>
    .flex {
      display: flex;
      justify-content: flex-start;
    }

    thead {
      position: sticky;
      top: 0; /* 고정될 위치 조정 */
      background-color: var(--body-bg); /* 배경색 지정 */
      z-index: 1; 
    }
    table {
      width: 520px;
    }
    .table-overflow {
      height: 455px;
      width: 530px;
      overflow-y: scroll;
    }

    .small-overflow {
      height: 400px;
      width: 350px;
      overflow-y: scroll;
    }

    .modal-employee-list {
        top: 20%;
        left: 25%;

        width: 930px;
        height: 550px;
        padding: 1rem
    }

    .modal-list-close{
    text-align: right;
    color: gray;
  }
  .modal-list-close:hover {
    color: black;
  }
  
  .modal-emp-content {
    align-items: center;
    display: flex;
    justify-content: space-around;
    vertical-align: middle;
    overflow-y: auto;
    max-height: 11rem;
    justify-content: flex-start;
  }
  
  .checked-emp-table {
    margin-left: 2rem;
    width: 300px;
    align-self: self-start;
  }
  .checked-emp-table > thead {
    height: 54.91px;
  }

  .input-emplist-space {
    text-align: right;
    align-items: end;
  }
  .button-right-align {
    position: absolute;
    right: 3.6%;
    bottom: 5%;
  }

</style>
<jsp:include page="./commonmodal.jsp" />
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

      <div class="flex">
        <div class="table-overflow">

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
          </div>
      
    
          <div>
      <div class="small-overflow">

        <table class="checked-emp-table">
          <thead>
            <tr>
              <th>사원ID</th>
              <th>사원명</th>
              <th>취소</th>
            </tr>
          </thead>
          <tbody>
            
          </tbody>
        </table>
      </div>
    </div>
    
  </div>
  <div class="button-right-align">
    <button type="button" id="common-modal-search-btn">선택</button>
  </div>
  </dialog>