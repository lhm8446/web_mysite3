<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
var isEnd = false;
var page=0;
var render = function(vo){
	var htmls = 
	"<li>" +
	"<table>"+
	"<tr>"+
	"<td>"+ vo.rank +"</td>"+
	"<td>"+ vo.name +"</td>"+
	"<td>"+ vo.reg_date +"</td>"+
	"<td>"+
	"<a href=''>삭제</a></td>" +
	"</tr>	"+						
	"<tr>"+
	"<td colspan=4>"+ vo.content +"</td>"+
	"</tr>"+
	"</table>"+
	"<br>"+
	"</li>";
	
	$("#list-guestbook").append(htmls);
}
var fetchList = function(){
	if(isEnd==true){
		return;
	}
	++page;
	$.ajax({
		url:"${pageContext.request.contextPath }/api/guestbook?a=ajax-list&p="+page,
		type:"get",
		dataType:"json",
		success:function(response){
			if(response.result != "success"){
				console.error(response.message);
				inEnd=true;
				return;
			}
			
			// redering
			$(response.data).each(function(index, vo){
				var htmls = 
					
				render(vo);

				console.log(vo);
			});
			
			if(response.data.length<5){
				isEnd =true;
				$("#btn-fetch").prop("disabled",true);
			}
		},
		error:function(jqXHR,status,e){
			console.log(status+":"+e);
		}
	});	
}

$(function(){
	$("#add-form").submit(function(event){
		event.preventDefault();
		
		//ajax insert
	});
	$(window).scroll(function(){
		var $window = $(this);
		var scrollTop = $window.scrollTop();
		var windowHeight = $window.height();
		var documentHeight = $(document).height();
		
		//스크롤 바가 바닥까지 왔을때(20px 덜왔을때)
		if(scrollTop + windowHeight + 20 > documentHeight){
			fetchList();
		}
	});
	$("#btn-fetch").click(function(){
		fetchList();
	});
	
	//1번째 리스트 가져오기
	fetchList();
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<form id="add-form" action="${pageContext.request.contextPath }/guestbook" method="post">
					<input type="hidden" name="a" value="add">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="pass"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="content" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul id="list-guestbook">
				</ul>
				<button id="btn-fetch" style="margin-top:20px">가져오기</button>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>