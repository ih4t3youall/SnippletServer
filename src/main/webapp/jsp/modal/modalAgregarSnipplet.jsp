<script type="text/javascript">

function cerrarModalCategoria(){
	
	var contenidoModal = $(".modal-agregar-snipplet-contenido").val();
	var tituloModal = $(".modal-agregar-snipplet-title").val();
	
	
	$('#modal-agregar-categoria').modal('hide');
	
	
}

</script>


<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="modal-agregar-snipplet" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 style="visibility: hidden;" class="modal-snipplet"></h4>
						<h4 style="visibility: hidden;" class="modal-title-bkup"></h4>
						<h4 class="modal-agegar-snipplet-title">
							<input type="text" class="modal-title-input"></input>
						</h4>
					</div>
					<div class="modal-agregar-snipplet-body">
						<textarea rows="4" cols="50" class="modal-agregar-snipplet-contenido"></textarea>
					</div>
					<div class="modal-agregar-snipplet-footer">
						<button type="button" class="btn btn-default"
							onclick="cerrarModalSnipplet()">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div></html>