package dto;

import java.io.Serializable;
import java.sql.Date;

public class DTO_Liquidacion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idLiquidacion;
	private Date fecha;
	private float monto;
	
	public DTO_Liquidacion() {
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public int getIdLiquidacion() {
		return idLiquidacion;
	}

	public void setIdLiquidacion(int idLiquidacion) {
		this.idLiquidacion = idLiquidacion;
	}
	
	
	
}
