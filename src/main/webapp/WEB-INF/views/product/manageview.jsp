<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>비품관리 정보</title>
    <jsp:include page="../commonheader.jsp" />
    <jsp:include page="../commonmodal.jsp" />
    <script type="text/javascript" src="/js/product/manageview.js"></script>
    <script src="https://kit.fontawesome.com/ac0e78004e.js" crossorigin="anonymous"></script>
    <style>
      .main-grid {
        display: grid;
        grid-template-columns: 6rem 1fr 6rem 1fr;
        grid-template-rows: repeat(3, 2rem);
        gap: 1rem;
        margin: 2rem 1rem 5rem;
      }
      .bar {
        border-bottom: 2px solid gray;
        display: flex;
        justify-content: flex-end;
      }

      /* .modify-modal {
        top: 10rem;
        left: 40%;
      } */
      .modal-grid {
        display: grid;
        grid-template-columns: 6rem 1fr;
        grid-template-rows: repeat(6, 1fr);
        gap: 1rem;
        margin: 2rem 1rem;
      }
      .hidden {
        display: none;
      }
      table.table > tbody td[colspan] {
        text-align: center;
      }
      .modal-btn-group {
        text-align: right;
      }
      .center {
        text-align: center;
      }
      .modal-window {
        height: 450px;
        width: 400px;
        top: 10%;
      }
      .bold {
        font-weight: bold;
        margin: auto 0;
      }
      .fa-regular {
        margin: 1rem 0.8rem;
        color: gray;
      }
    </style>
    <link
      rel="stylesheet"
      href="https://cdn-uicons.flaticon.com/2.3.0/uicons-regular-rounded/css/uicons-regular-rounded.css"
    />
    <link
      rel="stylesheet"
      href="https://cdn-uicons.flaticon.com/2.3.0/uicons-regular-straight/css/uicons-regular-straight.css"
    />
  </head>
  <body>
    <div class="body" data-paramid="${productVO.prdtId}">
      <dialog class="modify-modal modal-window">
        <div class="modal-grid">
          <p class="bold">비품관리ID</p>
          <p style="margin: auto 0" class="manage-id"></p>
          <p class="bold">비품명</p>
          <p style="margin: auto 0" class="product-name"></p>
          <p class="bold">구매가격</p>
          <input type="number" class="price" min="0" />
          <p class="bold">구매일</p>
          <input type="date" class="buy-day" />
          <p class="bold">분실상태</p>
          <select class="select">
            <option value="O">O</option>
            <option value="X">X</option>
          </select>
          <p class="bold">분실신고일</p>
          <input type="date" class="lost-day" />
        </div>
        <div class="modal-btn-group">
          <button type="button" value="수정" id="modify-btn">수정</button>
          <button type="button" value="취소" id="cancel-btn">취소</button>
        </div>
      </dialog>
      <h2 style="margin: 0">비품 관리 정보</h2>
      <div class="bar">
        <!-- <i class="fi fi-rr-pencil"></i> -->
        <i class="fa-regular fa-pen-to-square fa-2xl"></i>
        <!-- <i class="fi fi-rs-disk hidden"></i> -->
        <i class="fa-regular fa-floppy-disk hidden fa-2xl"></i>
      </div>
      <div class="main-grid hidden">
        <div class="bold">비품 ID</div>
        <div style="margin: auto 0">${productVO.prdtId}</div>
        <div class="bold">재고수</div>
        <div style="margin: auto 0">${productVO.curStr}</div>
        <div class="bold">비품명</div>
        <input type="text" class="product-name-modify" />
        <div class="bold">소모품 분류</div>
        <select class="product-onceyn-modify">
          <option value="Y">소모품</option>
          <option value="N">비소모품</option>
        </select>
        <div class="bold">카테고리</div>
        <input type="text" class="product-ctgr-modify" />
      </div>
      <div class="main-grid">
        <div class="bold">비품 ID</div>
        <div style="margin: auto 0">${productVO.prdtId}</div>
        <div class="bold">재고수</div>
        <div style="margin: auto 0">${productVO.curStr}</div>
        <div class="bold">비품명</div>
        <div style="margin: auto 0" class="product-name-origin">${productVO.prdtName}</div>
        <div class="bold">소모품 분류</div>
        <div style="margin: auto 0" class="product-onceyn-origin">${productVO.onceYn}</div>
        <div class="bold">카테고리</div>
        <div style="margin: auto 0" class="product-ctgr-origin">${productVO.prdtCtgr}</div>
      </div>

      <div class="bold" style="margin: 0.5rem; color: var(--dark)">
        ${productVO.prdtName}에 대한 상세 정보가 ${productDetailList.productManagementCnt}건 존재합니다.
      </div>
      <table class="table">
        <thead>
          <tr>
            <th width="15%">비품관리 ID</th>
            <th width="18%">비품명</th>
            <th width="11%" class="center">가격</th>
            <th width="10%" class="center">구매일</th>
            <th width="8%" class="center">대여여부</th>
            <th width="8%" class="center">분실상태</th>
            <th width="10%" class="center">분실신고일</th>
            <th width="20%" class="center">관리</th>
          </tr>
        </thead>
        <tbody>
          <c:choose>
            <%-- productManagementList의 내용이 존재한다면 (1개 이상 있다면) --%>
            <c:when test="${not empty productDetailList.productManagementList}">
              <%-- 내용을 반복하면서 보여주고 --%>
              <c:forEach items="${productDetailList.productManagementList}" var="product">
                <tr>
                  <td>${product.prdtMngId}</td>
                  <td>${product.productVO.prdtName}</td>
                  <td class="center">${product.prdtPrice}</td>
                  <td class="center">${product.buyDt}</td>
                  <c:choose>
                    <c:when test="${product.brrwYn eq 'Y'}">
                      <td class="center">O</td>
                    </c:when>
                    <c:otherwise>
                      <td class="center">X</td>
                    </c:otherwise>
                  </c:choose>
                  <c:choose>
                    <c:when test="${product.lostYn eq 'Y'}">
                      <td class="center lostdata">O</td>
                      <td class="center">${product.lostDt}</td>
                    </c:when>
                    <c:otherwise>
                      <td class="center lostdata">-</td>
                      <td class="center">-</td>
                    </c:otherwise>
                  </c:choose>
                  <td class="center">
                    <button
                      class="modify lostData"
                      data-product="${product.prdtMngId}"
                      data-name="${product.productVO.prdtName}"
                      data-lost-status="${product.lostYn}"
                    >
                      수정
                    </button>
                    <button
                      class="remove"
                      data-product="${product.prdtMngId}"
                      data-brrwyn="${product.brrwYn}"
                      data-lostyn="${product.lostYn}"
                      data-onceyn="${productVO.onceYn}"
                    >
                      삭제
                    </button>
                  </td>
                </tr>
              </c:forEach>
            </c:when>
            <%-- productManagementList의 내용이 존재하지 않는다면 --%>
            <c:otherwise>
              <tr>
                <td colspan="8" class="center">등록된 비품이 존재하지 않습니다.</td>
              </tr>
            </c:otherwise>
          </c:choose>
        </tbody>
      </table>
    </div>
  </body>
</html>
