package dto;
import java.util.ArrayList;
import java.util.List;



public class DTO_Mozo extends DTO_Empleado{
	
	private static final long serialVersionUID = 1L;
	private float comision;
	private List<DTO_Liquidacion> liquidaciones;
	private List<DTO_Mesa> mesasAsignadas;
	
	public DTO_Mozo() {
		this.liquidaciones = new ArrayList<DTO_Liquidacion>();
		this.mesasAsignadas = new ArrayList<DTO_Mesa>();
	}

	public float getComision() {
		return comision;
	}

	public void setComision(float comision) {
		this.comision = comision;
	}

	public List<DTO_Liquidacion> getLiquidaciones() {
		return liquidaciones;
	}

	public void setLiquidaciones(List<DTO_Liquidacion> liquidaciones) {
		this.liquidaciones = liquidaciones;
	}

	public List<DTO_Mesa> getMesasAsignadas() {
		return mesasAsignadas;
	}

	public void setMesasAsignadas(List<DTO_Mesa> mesasAsignadas) {
		this.mesasAsignadas = mesasAsignadas;
	}


	
	
	
}
