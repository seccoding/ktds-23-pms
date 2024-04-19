<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>이슈 작성 페이지</title>
        <jsp:include page="../commonheader.jsp"></jsp:include>
        <script type="text/javascript" src="/js/issuewrite/js"></script>
    </head>
    <body>
        <form action="/issue/write" method="post" enctype="multipart/form-data">
            <div>
                <label for="=rqm-id">요구사항명</label>
                <select name="rqmId" id="rqm-id">
                <c:forEach items="${requirement}" var="requirement">
                    <option value="${requirement.rqmId}">
                    ${requirement.rqmTtl}
                    </option>
                </c:forEach>
                </select>
            </div>
            <div class="grid">
                <label for="issue-title">이슈명</label>
                <input type="text" id="issue-title" name="isTtl" value="${issueVO.isTtl}"/>

                <label for="file">첨부파일</label>
                <input type="file" name="file" id="file" />

                <label for="content">내용</label>
                <textarea type="text" id="is-content" name="isCntnt" value="${issueVO.isCntnt}">${issueVO.isCntnt}</textarea>
            </div>
            <div>
                <div>
                <input type="submit" value="저장" />
                </div>
            </div>
        </form>
    </body>
</html>