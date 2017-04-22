<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="resources/jquery.js"></script>
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
<link href="resources/jquery-ui-1.12.1.custom/jquery-ui.css"
	rel="stylesheet">






<title>Insert title here</title>

<style>
.list-group-item:hover {
	background-color: grey;
}

.limit {
	height: 100px;
}

nav ul {
	height: 500px;
	width: 99%;
}

nav ul {
	overflow: hidden;
	overflow-y: scroll;
}
</style>

<script type="text/javascript">
	function getSnipplet(item) {

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "devolverCategoria",
			data : item,
			success : function(data) {
				$("#frameSnipplet").empty();
				$("#frameSnipplet").append(data);

			},
			error : function(e) {
				console.log("ERROR: ", e);
				console.log(e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});

	}
	
	function modal(){
		$('#myModal').modal('show');
	}
</script>

</head>
<body>



<a href="<c:url value="/logout" />">
			<button class="btn btn-md btn-danger btn-block" name="logout"
				>Logout</button>
  </a>
		
	<div class="limit"></div>
	<nav>
	<ul>
		<div class="row limit">
			<div class="col-md-2"></div>
			<div class="col-md-4">
				<ul class="list-group">
					<c:forEach items="${lista}" var="item">
						<li class="list-group-item" onclick="getSnipplet('${item}')">${item}</li>
					</c:forEach>
				</ul>
			</div>
			<div class="col-md-4" id="frameSnipplet"></div>
			<div class="col-md-2"></div>
		</div>
	</ul>
	</nav>



</body>
</html>