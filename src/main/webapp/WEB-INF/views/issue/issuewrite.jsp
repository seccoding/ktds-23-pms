<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>이슈 작성 페이지</title>
        <jsp:include page="../commonheader.jsp"></jsp:include>
        <jsp:include page="../ckeditor.jsp" />
        <script type="text/javascript" src="/js/issue/issuewrite.js"></script>
        <style>
        </style>
    </head>
    <body>
        <form action="/issue/write" method="post" enctype="multipart/form-data">
            <div class="grid">
                <label for="=rqm-id">요구사항</label>
                <div>
                    <select name="rqmId" id="rqm-id">
                        <option value="">요구사항을 선택해주세요</option>
                        <c:forEach items="${requirement}" var="requirement">
                            <option value="${requirement.rqmId}">
                            ${requirement.rqmTtl}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div>
                    <label for="issue-title">이슈 제목</label>
                    <input type="text" id="issue-title" name="isTtl" value="${issueVO.isTtl}"/>
                </div>

                <div>
                    <label for="file">첨부파일</label>
                    <input type="file" name="file" id="file" />
                </div>

                <div>
                    <label for="content">이슈 내용</label>
                    <div class="hereCkEditor5">
                        <%-- 여기가 editor 생성부 --%>
                        <div class="editor" data-name="isCntnt"></div>
                        <input
                          type="text"
                          id="is-content"
                          name="isCntnt"
                          style="visibility: hidden"
                        />
                    </div>
                </div>

                <button type="button">등록</button>
            </div>
        </form>
    </body>
</html>