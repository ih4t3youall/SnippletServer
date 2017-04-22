<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<script type="text/javascript">
	function copiar(item) {

		contenido = $(item).find(".contenido").html();
		titulo = $(item).find("#titulo").html();
		clear();

		$(".modal-title").append(titulo);
		$(".modal-body").append(contenido);
		$('#myModal').modal('show');

	}

	function copy() {
		var nose = $(".modal-body").html();
		copyToClipboard(nose);
	}

	function copyToClipboard(text) {
		var aux = document.createElement("input");
		aux.setAttribute("value", text);
		document.body.appendChild(aux);
		aux.select();
		document.execCommand("copy");
		document.body.removeChild(aux);
	}

	function deleteModal(este) {
		var aborrar = $(".modal-title").html();
		$("li").each(function(index, item) {
			if ($(item).find("#titulo").html()==aborrar) {
				$(item).remove();
				console.log("pase");
			}

		});
	}

	function cerrarModalConModificaciones() {

		var categoria = new Object();
		var categoriaNombre = $(".snipplets").find(".categoriaId").html();
		categoria.nombre = categoriaNombre;
		categoria.snipplets = [];
		$(".snipplets").each(function(index, item) {
			var snipplet = new Object();
			var titulo = $(item).find("#titulo").html();
			var contenido = $(item).find(".contenido").html();
			snipplet.titulo = titulo;
			snipplet.contenido = contenido;
			categoria.snipplets.push(snipplet);
		});

		var sendable = JSON.stringify(categoria);

		$.ajax({
			url : "salvarCategoria",
			type : "POST",
			contentType : "application/json",
			data : sendable,
			success : function(data) {
				$("#refreshMaquina").val($("#idMaquina").html());
				$("#refreshProyecto").val($("#idProyecto").html());
				$("#refreshForm").submit();
			}
		});
	}

	function clear() {
		$("#categoriaId").empty();
		$("#snippletId").empty();
		$(".modal-title").empty();
		$(".modal-body").empty();
	}
</script>

</head>
<body>
	<!-- probando modal -->
	<div class="container">
		<h2>Basic Modal Example</h2>
		<!-- Trigger the modal with a button -->
		<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
			data-target="#myModal">Open Modal</button>
		<!-- Modal -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Modal Header</h4>
					</div>
					<div class="modal-body">
						<p>Some text in the modal.</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-default" onclick="copy()">Copy</button>
						<button type="button" class="btn btn-default"
							onclick="deleteModal(this)">Delete</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- fin probando modal -->
	<ul class="list-group ">
		<c:forEach items="${snipplets}" var="item">
			<li class="list-group-item snipplets" onclick="copiar(this)"><span
				id="titulo">${item.titulo} </span> <span style="visibility: hidden;"
				class="categoriaId">${categoriaId} </span><br />
				<div class="contenido">${item.contenido}</div></li>
		</c:forEach>
	</ul>
</body>
</html>