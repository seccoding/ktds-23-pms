
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후기 목록</title>
<jsp:include page="../commonheader.jsp"></jsp:include>
<script type="text/javascript">
// 시간을 담을 변수 t
var t=0;

function test(t) {
	console.log("TEST: " + t);
	$.ajax({
        url : '/test',
        type : 'GET',
        dataType : "json",
        contentType:"application/json",
        success : function(data){
            console.log(data);
            
        },
        error : function(request, status, error){
        	console.log("error...")
        },
        complete:function(){
        	console.log("complete...")
        }
	});
	
}


function timer(){
	// 0.01(10ms)초마다 t 값을 0.01 증가시키고
	// 증가된 t 값을 timer 클래스 하위 html에 출력
	setTimeout(function(){
		t+=1;
		test(t);
		
		timer();
	}, 5000)	
};
	
// timer();

$(document).ready(function () {
	$('#submit-button').click(function(){
		console.log("click...")
		$.ajax({
	        url : '/test',
	        type : 'GET',
	        dataType : "json",
	        contentType:"application/json",
	        success : function(data){
	            console.log(data);
	            
	        },
	        error : function(request, status, error){
	        	console.log("error...")
	        },
	        complete:function(){
	        	console.log("complete...")
	        }
		});
	});

});
</script>
</head>
<body>
<button id="submit-button">Add to favorites</button>
</body>
</html>