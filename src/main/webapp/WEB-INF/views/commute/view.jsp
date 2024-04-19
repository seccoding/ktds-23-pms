<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 출퇴근 관리 페이지</title>
</head>
<body>
    <table>
        <colgroup>
            <col width="200px">
            <col width="300px">
            <col width="300px">
            <col width="100px">
        </colgroup>
        <thead>
            <tr>
                <th>출근 날짜</th>
                <th>출근 시간</th>
                <th>퇴근 시간</th>
                <th>사원 이름</th>
            </tr>
        </thead>
    </table>
    <tbody>
        <c:choose>
            <c:when>
                <c:forEach>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="4">출퇴근 기록 X</td>
                </tr>
            </c:otherwise>
        </c:choose>
    </tbody>
</body>
</html>