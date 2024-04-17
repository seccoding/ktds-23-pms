<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div class="right-align">
  <ul class="horizontal-list">
    <c:choose>
      <c:when test="${empty sessionScope._LOGIN_USER_}">
        <li>
          <a href="/member/login">로그인</a>
        </li>
      </c:when>
      <c:otherwise>
        <li style="margin-right: 1rem">
          <c:if test="${sessionScope._LOGIN_USER_.adminYn eq 'Y'}">
            <span>(Super Admin)</span>
          </c:if>
          <!-- 현재 로그인된 사용자 -->
          ${sessionScope._LOGIN_USER_.name} (<span id="login-email"
            >${sessionScope._LOGIN_USER_.email}</span
          >)
        </li>
        <li>
          <a href="/member/logout">로그아웃</a>
        </li>
        <li>
          <a class="deleteMe" href="javascript:void(0)">탈퇴</a>
        </li>
      </c:otherwise>
    </c:choose>
  </ul>
</div>
