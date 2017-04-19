<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<script type="text/javascript">

function copiar(item){
	
	nose = $(item).find(".contenido");
	console.log(nose);
	alert(nose.html());
	
	
	
}
</script>

</head>
<body>

	<ul class="list-group">
		<c:forEach items="${snipplets}" var="item">
			<li class="list-group-item" onclick="copiar(this)">${item.titulo} <br/>
				<div class="contenido">${item.contenido}</div>
			</li>
		</c:forEach>
	</ul>



</body>
</html>