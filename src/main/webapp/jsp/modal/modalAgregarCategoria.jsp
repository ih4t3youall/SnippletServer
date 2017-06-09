<script type="text/javascript">

function cerrarModalCategoria(){
	
	var contenidoModal = $(".modal-agregar-categoria-contenido").val();
	var nuevaCategoria = getDynamicContent("getHtmlCategoria",contenidoModal);
	
	$('#modal-agregar-categoria').modal('hide');
	
	
}

</script>


<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="modal-agregar-categoria" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 style="visibility: hidden;" class="modal-categoria"></h4>
						<h4 style="visibility: hidden;" class="modal-title-bkup"></h4>
						<h4 class="modal-agegar-categoria-title">
							<input type="text" class="modal-title-input"></input>
						</h4>
					</div>
					<div class="modal-agregar-categoria-body">
						<textarea rows="4" cols="50" class="modal-agregar-categoria-contenido"></textarea>
					</div>
					<div class="modal-agregar-categoria-footer">
						<button type="button" class="btn btn-default"
							onclick="cerrarModalCategoria()">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div></html>