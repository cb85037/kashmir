package dto;

public class DTO_ElaboradoVenta extends DTO_Producto {
	

	private static final long serialVersionUID = 1L;
	private DTO_Receta receta;
	
	public DTO_ElaboradoVenta() {
		
		
	}

	public DTO_Receta getReceta() {
		return receta;
	}

	public void setReceta(DTO_Receta receta) {
		this.receta = receta;
	}
	
	
	
}
