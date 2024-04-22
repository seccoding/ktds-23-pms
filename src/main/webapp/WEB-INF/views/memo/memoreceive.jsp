<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보낸 편지함 목록</title>
<jsp:include page="../commonheader.jsp" />
<script type="text/javascript" src="/js/memo/memolist.js"></script>
</head>
<body>


     <div> 총 ${memoList.memoCnt} 건의 메모가 검색되었습니다.</div>
     <button href="javascript:void(0)" id="deleteMassiveMemo">삭제</button>
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
                <input type="checkbox" id="checked-all" data-target-class="target-memo-id" />
     	 		      <label for="checked-all"></label>
            </th>
            <th>보낸 사람</th>
            <th>내용</th>
            <th>보낸 날짜</th>
            <th>확인 여부</th>
		</tr>
      </thead>

    <c:forEach items="${memoList.memoList}" var="memo" varStatus="loop">
     	<tr>
               <td>
                 <input type="checkbox" class = "target-memo-id" id="target-memo-id-${loop.index}" name="targetMemoId" value="${memo.memoId}" />
     	 		       <label for="target-memo-id-${loop.index}"></label>
               </td>
             <td class="center-align">${memo.crtrId}</td>
             <td class="left-align">
              <a class="ellipsis" href="/memo/receive/view?id=${memo.memoId}">
             		쪽지를 받았습니다.
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