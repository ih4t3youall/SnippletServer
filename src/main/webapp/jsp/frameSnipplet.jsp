<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<script type="text/javascript">
	function copiar(item) {

		contenido = $(item).find(".contenido").html().trim();
		titulo = $(item).find("#titulo").html().trim();
		categoria = $(item).find(".categoriaId").html().trim();

		clear();
		$(".modal-categoria").append(categoria);
		$(".modal-title-input").val(titulo);
		$(".modal-title-bkup").html(titulo);
		$(".modal-contenido").val(contenido);
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
		var aborrar = $(".modal-title-input").val();
		$("li").each(function(index, item) {
			if ($(item).find("#titulo").html() == aborrar) {
				$(item).remove();
			}

		});
		cerrarModalConModificaciones();
	}

	function modalClose() {
		var snippletName = $(".modal-title-input").val();
		var snippletContent = $(".modal-contenido").val();
		var categoriaName = $(".modal-categoria").html();

		$(".list-group-item.snipplets").each(function(index, item) {

			if (snippletName == $(item).find("#titulo").html()) {
				$(item).find(".contenido").empty();
				$(item).find(".contenido").append(snippletContent);

			}

		});

		$('#myModal').modal('hide');
		cerrarModalConModificaciones();
		clear();

	}

	function actualizarTitulo(){
		
		var tituloViejo = $(".modal-title-bkup").html();
		
		$(".snipplets").each(function(index, item) {
			var snipplet = new Object();
			var titulo = $(item).find("#titulo").html();
			if(titulo == tituloViejo){
				$(item).find("#titulo").html($(".modal-title-input").val());
				
			}
		});
		
	}
	
	function cerrarModalConModificaciones() {

		actualizarTitulo();
		
		var categoria = new Object();
		var categoriaNombre = $(".modal-categoria").html();
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

			}
		});
	}
	
	

	function clear() {
		$("#categoriaId").empty();
		$("#snippletId").empty();
		$(".modal-title-input").val("");
		$(".modal-contenido").val("");
		$(".modal-categoria").empty();
		$(".modal-title-bkup").empty();
	}
	
	
	
</script>

</head>
<body>
	<!-- modal mostrar snipplet -->
	<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 style="visibility: hidden;" class="modal-categoria"></h4>
						<h4 style="visibility: hidden;" class="modal-title-bkup"></h4>
						<h4 class="modal-title">
							<input type="text" class="modal-title-input"></input>
						</h4>
					</div>
					<div class="modal-body">
						<textarea rows="4" cols="50" class="modal-contenido">
 							
							</textarea>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default"
							onclick="modalClose()">Close</button>
						<button type="button" class="btn btn-default" onclick="copy()">Copy</button>
						<button type="button" class="btn btn-default"
							onclick="deleteModal(this)">Delete</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- fin mostrando snipplet -->



	<ul id="list-snipplets" class="list-group ">
		<c:forEach items="${snipplets}" var="item">
			<li class="list-group-item snipplets" onclick="copiar(this)"><span
				id="titulo">${item.titulo}</span> <span style="visibility: hidden;"
				class="categoriaId">${categoriaId} </span><br />
				<div class="contenido">${item.contenido}</div></li>
		</c:forEach>
	</ul>
</body>
</html>