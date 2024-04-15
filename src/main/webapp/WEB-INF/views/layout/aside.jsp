<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div class="sidebar">
      <img class="user-img" src="/images/login.png" alt="" />
      <div class="sidebar-user">
        <div class="sidebar-user-info">
          <div class="user-name">아무개</div>
          <div class="user-team">인사팀</div>
        </div>
      </div>
      <div class="sidebar-menu">
        <div class="sidebar-submenu">
          <div class="sidebar-submenu-content">
            <img
              class="sidebar-common-icon"
              src="/images/sidebar-employee.png"
              alt=""
            />
            <span class="sidedbar-menu-name">사원</span>
            <div class="dropdown-icon"></div>
          </div>
          <div class="dropdown-menu">
            <a href="#">사원 조회</a>
            <a href="#">이력 조회</a>
          </div>
        </div>
        <div class="sidebar-submenu">
          <div class="sidebar-submenu-content">
            <img
              class="sidebar-common-icon"
              src="/images/sidebar-department.png"
              alt=""
            />
            <span class="sidedbar-menu-name">부서&팀</span>
          </div>
        </div>
        <div class="sidebar-submenu">
          <div class="sidebar-submenu-content">
            <img
              class="sidebar-common-icon"
              src="/images/sidebar-product.png"
              alt=""
            />
            <span class="sidedbar-menu-name">비품</span>
            <div class="dropdown-icon"></div>
          </div>
          <div class="dropdown-menu">
            <a href="#" class="active">비품 조회</a>
            <a href="#" class="active">비품 대여</a>
            <a href="#" class="active">비품 관리(admin)</a>
            <a href="#" class="active">비품 상세(admin)</a>
            <a href="#" class="active">비품 대여현황(admin)</a>
          </div>
        </div>
        <div class="sidebar-submenu">
          <div class="sidebar-submenu-content">
            <img
              class="sidebar-common-icon"
              src="/images/sidebar-approval.png"
              alt=""
            />
            <span class="sidedbar-menu-name">결재</span>
            <div class="dropdown-icon"></div>
          </div>
          <div class="dropdown-menu">
            <a href="#">결재 조회</a>
            <a href="#">결재 상신</a>
            <a href="#">결재 수신(admin)</a>
          </div>
        </div>
        <div class="sidebar-submenu">
          <div class="sidebar-submenu-content">
            <img
              class="sidebar-common-icon"
              src="/images/sidebar-project.png"
              alt=""
            />
            <span class="sidedbar-menu-name">프로젝트</span>
            <div class="dropdown-icon"></div>
          </div>
          <div class="dropdown-menu">
            <a href="#">프로젝트 관리</a>
            <a href="#">요구사항 관리</a>
            <a href="#">이슈 관리</a>
            <a href="#">QnA</a>
            <a href="#">지식 관리</a>
            <a href="#">산출물 관리</a>
            <a href="#">설문 관리</a>
            <a href="#">후기 관리</a>
          </div>
        </div>
        <div class="sidebar-submenu">
          <div class="sidebar-submenu-content">
            <img
              class="sidebar-common-icon"
              src="/images/sidebar-memo.png"
              alt=""
            />
            <span class="sidedbar-menu-name">쪽지</span>
            <div class="dropdown-icon"></div>
          </div>
          <div class="dropdown-menu">
            <a href="#">쪽지 작성</a>
            <a href="#">받은 쪽지함</a>
            <a href="#">보낸 쪽지함</a>
            <a href="#">쪽지 보관함</a>
          </div>
        </div>
        <div class="sidebar-submenu">
          <div class="sidebar-submenu-content">
            <img
              class="sidebar-common-icon"
              src="/images/sidebar-commute.png"
              alt=""
            />
            <span class="sidedbar-menu-name">출퇴근</span>
            <div class="dropdown-icon"></div>
          </div>
          <div class="dropdown-menu">
            <a href="#">출퇴근 조회</a>
            <a href="#">사원 출퇴근 조회</a>
          </div>
        </div>
      </div>
    </div>