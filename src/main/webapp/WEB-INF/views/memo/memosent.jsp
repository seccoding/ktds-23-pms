<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보낸 편지함 목록</title>
<jsp:include page="../commonheader.jsp" />
</head>
<body>


     <div> 총 ${memoList.memoCnt} 건의 메모가 검색되었습니다.</div>
    <table>
	  <colgroup>
          <col width="*" />
          <col width="*" />
          <col width="*" />
          <col width="*" />
          <col width="*" />
          <col width="*" />
          <col width="*" />
        </colgroup>
	  <thead>
        <tr>
            <th>
                <input type="checkbox" id="checkbox1" />
     	 		<label for="checkbox1"></label>
            </th>
            <th>받는 사람</th>
            <th>내용</th>
            <th>보낸 사람</th>
            <th>보낸 날짜</th>
            <th>확인 여부</th>
		</tr>
      </thead>

    <c:forEach items="${memoList.memoList}" var="memo" varStatus="loop">
     	<tr>
               <td>
                 <input type="checkbox" id="target-memo-id-${loop.index}" name="targetBoardId" value="${memo.memoId}" />
     	 		 <label for="target-memo-id-${loop.index}"></label>
               </td>
             <td class="center-align">${memo.rcvId}</td>
             <td class="left-align">
             	<a class="ellipsis" href="/memo/sent/view?id=${memo.memoId}">
             		쪽지를 보냈습니다.
             	</a>
             </td>
             <td class="center-align">${memo.empName} : (${memo.email})</td>
             <td class="center-align">${memo.crtDt}</td>
             <td class="center-align">${memo.readYn}</td>
        
     
         </tr>
    </c:forEach>
    </table>

</body>
</html>