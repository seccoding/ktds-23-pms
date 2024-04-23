<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<div class="sidebar-wrapper">
	<div class="backboard"></div>
	<div class="sidebar">
		<img class="user-img" src="/images/login.png" alt="" />
		<div class="sidebar-user">
			<div class="sidebar-user-info">
				<div class="user-name">${sessionScope._LOGIN_USER_.empName}</div>
				<c:forEach items="${sessionScope._LOGIN_USER_.teamList}" var="team">
					<div class="user-team">${team.tmName}</div>
				</c:forEach>
			</div>
		</div>
		<div class="sidebar-menu"></div>
	</div>
</div>
