<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>메인페이지</title>
    <jsp:include page="../commonheader.jsp" />
    <script type="text/javascript" src="/js/dashboard.js"></script>
    <script type="text/javascript" src="/js/commute/fnsh.js"></script>
    <style>
      .person-log-in {
        /* justify-content: space-between; */
        display: flex;
        overflow: scroll;
        gap: 3rem;
        margin: 2rem;
      }
     

      .flex {
        /* margin: 20px; */
      }

      .flex-col {
        flex-direction: column;
        /* margin: 0 1.2rem; */
      }

      .prfl-photo {
        width: 90px;
        height: 90px;
        border-radius: 50%;
        margin-bottom: 15px;
        background-position: center;
        background-size: cover;
        background-repeat: no-repeat;
        border: 3px solid var(--success);
      }

      .image-color-background {
        border: 3px solid var(--secondary);
        opacity: 0.5;
      }

      .prfl-photo-font {
        font-size: 15px;
        font-weight: 700;
        text-align: center;
      }

      .bold-color-background {
        color: var(--secondary);
      }

      .dept-id-data {
        font-size: 18px;
        font-weight: bold;
        margin: 1rem;
      }

      .commute-grid {
        display: grid;
        grid-template-columns: repeat(2, 1fr) 0.7fr;
        height: 100px;
        width: 100%;
      }
      tr {
        border-bottom: 1px solid lightgrey;
      }

      .commute-grid-time {
        display: grid;
        align-content: space-evenly;
        justify-items: center;
      }

      .commute-grid-time-in,
      .commute-grid-time-out {

      }

      .commute-grid-time-in {

      }

      .commute-grid-time-out {
        display: grid;
        align-items: center;
        justify-content: start;
      }

      .commute-grid-out {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin: 0 20px;
      }
      .table th {
        position: sticky;
        top: 0px;
        background-color: var(--body-bg);
      }
      .f-space-between {
        display: flex;
        justify-content: space-between;
      }
      .center {
        text-align: center;
      }
      .col-3-4 {
        width: 75%;
      }
      .meno-height {
        height: 132px;
      }
      .memobox {
        display: flex;
        justify-content: space-between;
        margin-right: 8px;
        margin-left: 8px;
        height: 100px;
      }
      .memoboxmargin {
        margin-top: 15px;
        margin-right: 6px;
      }
      .memotable {
        height: 100px;
        overflow-x: hidden;
      }
      .memoimg {
        height: 55px;
        margin-top: 8px;
        margin-right: 2px;
      }
      .ellipsis {
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        width: 7rem;
        display: inline-block;
      }

      .commute {
        display: grid;
        justify-content: center;
        gap: 10px;
      }
    </style>
  </head>
  <body>
    <div class="container">
      <div class="row">
        <div class="col-1-2">
          <div>
            <div class="card row-1-2">
              <%-- 박스 영역 최소넓이 지정필요 --%>
              <h6>출퇴근</h6>
              <div class="card-body">
                <div class="commute-grid">
                  <div class="commute-grid-time">
                    <div class="commute-grid-time-in">
                      <c:if test="${not empty sessionScope._LOGIN_USER_.commuteVO.cmmtTime}">
                        <div class="commute">출근시간</div>
                        <div>${sessionScope._LOGIN_USER_.commuteVO.cmmtTime}</div>
                      </c:if>
                    </div>
                  </div>
                  <div class="commute-grid-time-out">
                    <div>
                      <c:choose>
                        <c:when test="${not empty sessionScope._LOGIN_USER_.commuteVO.fnshTime}">
                          <div class="commute">퇴근시간</div>
                          <div>${sessionScope._LOGIN_USER_.commuteVO.fnshTime}</div>
                        </c:when>
                        <c:otherwise>
                          <div class="commute">퇴근시간</div>
                          <div>퇴근 처리되지 않음.</div>
                        </c:otherwise>
                      </c:choose>
                    </div>
                  </div>
                  <div class="commute-grid-out">
                    <a href="/employee/logout">
                      <button type="button" id="fnsh-btn">퇴근</button>
                    </a>
                  </div>
                </div>
              </div>
            </div>
            <div class="card row-1-2">
              <h6>쪽지</h6>
              <div class="card-body">
                <div class="row">
                  <div class="card col-1-4 meno-height">
                    <div class="card-body">
                      <div class="card memobox">
                        <span class="memobox"
                          ><img
                            src="/images/sidebar-memo.png"
                            class="memoimg"
                          />
                          <h2 class="memoboxmargin">${memoList.memoCnt}</h2>
                        </span>
                      </div>
                      <!-- <div class="memocnt card">${memoList.memoCnt}</div> -->
                    </div>
                  </div>
                  <div class="card col-3-4">
                    <div class="card-body overflow-scroll memotable">
                      <table class="memotable">
                        <thead>
                          <tr>
                            <th>보낸 사람</th>
                            <th class="memoboxttl">제목</th>
                            <th>보낸 날짜</th>
                          </tr>
                        </thead>
                        <tbody>
                          <c:choose>
                            <c:when test="${not empty memoList.memoList}">
                              <c:forEach
                                items="${memoList.memoList}"
                                var="memo"
                                varStatus="loop"
                              >
                                <tr>
                                  <td>
                                    <span
                                      >${memo.crtrName} :
                                      (${memo.crtrEmail})</span
                                    >
                                  </td>
                                  <td class="memoboxttl">
                                    <a
                                      class="ellipsis Receive-loadLink"
                                      href="/memo/receive"
                                      data-url=""
                                    >
                                      <!-- <span>${memo.crtrId}</span> -->

                                      <span>${memo.memoTtl}</span>
                                    </a>
                                  </td>
                                  <td class="center-align">${memo.crtDt}</td>
                                  <!-- </div> -->
                                </tr>
                              </c:forEach>
                            </c:when>
                            <c:otherwise>
                              <tr>
                                <td colspan="6">
                                  <a href="/memo/receive"
                                    >받은 쪽지가 없습니다.</a
                                  >
                                </td>
                              </tr>
                            </c:otherwise>
                          </c:choose>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
                <!-- 쪽지 content 영역 -->
              </div>
            </div>
          </div>
        </div>
        <div class="col-1-2">
          <div class="card row-1-1">
            <div class="f-space-between">
              <h6>부서 내 사원 로그인현황</h6>
              <!-- <h5 style="margin: 0 0.5rem; font-weight: bold;">${deptname}</h5> -->
            </div>
            <div class="card-body">
              <!-- 팀 content 영역 -->
              <p
                class="dept-id-data"
                data-dept-id="${sessionScope._LOGIN_USER_.deptId}"
              >
                <!--$(this).data("dept-id")-->
                ${deptname}
              </p>
              <div class="person-log-in"></div>
            </div>
          </div>
        </div>
        <div class="col-1-1">
          <div class="card row-1-1">
            <h6>프로젝트</h6>
            <div class="card-body overflow-scroll">
              <!-- 프로젝트 content 영역 -->
              <table class="table">
                <thead>
                  <tr>
                    <th>프로젝트</th>
                    <th>고객사</th>
                    <th>수행부서</th>
                    <th>프로젝트 상태</th>
                    <th class="center">시작일</th>
                    <th class="center">종료일</th>
                  </tr>
                </thead>
                <tbody>
                  <!-- jstl > choose when otherwise / if ~ elif ~ else-->
                  <c:choose>
                    <%-- projectList 내용이 존재한다면 --%>
                    <c:when test="${not empty projects}">
                      <%-- 내용을 반복해서 보여줌 --%>
                      <c:forEach items="${projects}" var="project">
                        <tr
                          class="project-row"
                          data-project-id="${project.prjId}"
                        >
                          <td>${project.prjName}</td>
                          <td>${project.clntInfo}</td>
                          <td>${project.deptVO.deptName}</td>
                          <td>${project.prjStsCode.cmcdName}</td>
                          <td class="center">${project.strtDt}</td>
                          <td class="center">${project.endDt}</td>
                        </tr>
                      </c:forEach>
                    </c:when>
                    <%-- projectList의 내용이 존재하지 않는다면 --%>
                    <c:otherwise>
                      <tr>
                        <td colspan="6" class="center" >진행 중인 프로젝트가 없습니다.</td>
                      </tr>

                    </c:otherwise>
                  </c:choose>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div class="col-1-1">
          <div class="card row-1-1">
            <h6>비품대여</h6>
            <div class="card-body overflow-scroll product-t">
              <!-- 비품대여 content 영역 -->
              <table class="table">
                <thead>
                  <tr>
                    <th>비품관리 ID</th>
                    <th>비품명</th>
                    <th>카테고리</th>
                    <th class="center">대여일</th>
                  </tr>
                </thead>
                <tbody>
                  <c:choose>
                    <c:when test="${not empty borrowList}">
                      <c:forEach items="${borrowList}" var="item">
                        <tr>
                          <td>${item.prdtMngId}</td>
                          <td>${item.productVO.prdtName}</td>
                          <td>${item.productVO.prdtCtgr}</td>
                          <td class="center">${item.brrwDt}</td>
                        </tr>
                      </c:forEach>
                    </c:when>
                    <c:otherwise>
                      <tr>
                        <td colspan="4" class="center">대여중인 비품이 없습니다.</td>
                      </tr>
                    </c:otherwise>
                  </c:choose>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
