<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--  상단 탭  --%>
<%-- 기본정보, 구성원, 산출물, 요구사항 id를 js에서 QueryParam을 뽑아서 Control 해야할 듯 --%>
<div class="project-tabs">
    <ul>
        <li>
            <a id="tab-show" href="/project/view?prjId=${project.prjId}">기본정보</a>
        </li>
        <li>
            <a id="tab-member" href="/project/team?prjId=${project.prjId}">구성원</a>
        </li>
    </ul>
</div>
