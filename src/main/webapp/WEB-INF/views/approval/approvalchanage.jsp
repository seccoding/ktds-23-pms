<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
* {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}

#btnWrap {
  width: 500px;
  margin: 100px auto;
}
#popupBtn {
  width: 150px;
  height: 50px;
  padding: 10px 5px;
  background-color: #007bff;
  color: #fff; /* 텍스트 색상을 지정하세요 */
  border: none; /* 테두리 제거 */
  cursor: pointer;
}
#modalWrap {
  position: fixed; /* 화면에 고정 */
  z-index: 1; /* 상위에 위치 */
  padding-top: 100px;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.7); /* 반투명한 배경색 */
  display: none; /* 초기에는 숨김 */
}

#modalBody {
  width: 500px;
  height: 300px;
  padding: 30px 30px;
  margin: 0 auto;
  border: 1px solid #777;
  background-color: #fff;
}

#closeBtn {
  float: right;
  font-weight: bold;
  color: #777;
  font-size: 25px;
  cursor: pointer;
}
</style>
<script type="text/javascript" src=/js/lib/jquery-3.7.1.min.js></script>	
<script type="text/javascript">
$(document).ready(function(){
	// "행 추가" 버튼 클릭 시 새로운 행 추가
	  $("#addRowBtn").click(function(){
	    var newRow = $("<tr></tr>");
	    for(var i = 0; i < 2; i++) {
	      var newInput = $("<input type='text'>");
	      var newCell = $("<td></td>").append(newInput);
	      newRow.append(newCell);
	    }
	    newRow.append("<td><button class='deleteRowBtn'>삭제</button></td>");
	    newRow.append("<td><button class='openModalBtn'>모달 열기</button></td>"); // 모달 열기 버튼 추가
	    $("#myTable").append(newRow);
	  });

	  // "삭제" 버튼 클릭 시 해당 행 삭제
	  $(document).on("click", ".deleteRowBtn", function(){
	    $(this).closest("tr").remove();
	  });

	  // 모달 관련 이벤트 처리
	  const $modal = $("#modalWrap"); // 모달 창 요소 가져오기
	  const $closeBtn = $("#closeBtn"); // 모달을 닫는 버튼(X) 요소 가져오기

	  $(document).on("click", ".openModalBtn", function() {
	    $modal.css("display", "block"); // 모달을 열기 버튼을 클릭하면 모달을 보이게 함
	  });

	  $closeBtn.click(function() {
	    $modal.css("display", "none"); // 모달을 닫는 버튼(X)을 클릭하면 모달을 숨김
	  });

	  $(window).click(function(event) {
	    if (event.target === $modal[0]) {
	      $modal.css("display", "none"); // 모달 외부를 클릭하면 모달을 숨김
	    }
	  });
})
</script>



</head>
<body>
	<form>
	    <fieldset>
	    <legend>Employee Details</legend>
	     <p>
	        산청자ID: <input type = "text" name = "fname" />
		  </p>
		   <p>
		    결제자ID: <input type = "text" name = "lname" />
		   </p> 
		 	 	
		</fieldset>
	</form>
	
	<table id="myTable">
	  <tr>
	    <td><input type="text"></td>
	    <td><input type="text"></td>
	    <td><button class="deleteRowBtn">삭제</button></td>
	  </tr>
	</table>
	
	<table>
			<thead>
				<tr>
					<th>결재 상세ID</th>
					<th>결재ID</th>
					<th>비품ID</th>
					<th>수량</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty  approvalDetailList.approvalDetailList}">
						<c:forEach items="${approvalDetailList.approvalDetailList}" var="approvalderail">
							
								<tr>
									<th>${approvalderail.apprDtlId}</th>
									<th>${approvalderail.apprId}</th>
									<th>${approvalderail.prdtId}</th>
									<th>${approvalderail.curStr}</th>
								</tr>
								
								 
							
										
						</c:forEach>
					</c:when>
				</c:choose>
			</tbody>
		</table>
	
	<button id="addRowBtn">행 추가</button>
	

	
    <div id="modalWrap"> <!-- 모달 창을 감싸는 div -->
      <div id="modalBody">
        <span id="closeBtn">&times;</span> <!-- 모달을 닫는 X 버튼 -->
        <p>
        	<form id="searchForm" action="/search" method="GET">
			  <input type="text" id="searchInput" name="q" placeholder="검색어를 입력하세요">
			  <button type="submit" id="searchButton">검색</button>
			</form>
        </p> <!-- 모달 창 내용 -->
      </div>
    </div>
</body>
</html>