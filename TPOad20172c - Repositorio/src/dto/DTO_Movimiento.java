package dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;


public class DTO_Movimiento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idMovimiento;
	private List<DTO_Lote> lotes;
	private DTO_Deposito origen;
	private DTO_Deposito destino;
	private Date fecha;
	private String motivo;
	private DTO_Empleado usuario;
	private DTO_Deposito area;
	private boolean planProduccion;
	private String estado;
	
	public DTO_Movimiento() {
	}

	public int getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}


	public List<DTO_Lote> getLotes() {
		return lotes;
	}

	public void setLotes(List<DTO_Lote> lotes) {
		this.lotes = lotes;
	}

	public DTO_Deposito getOrigen() {
		return origen;
	}

	public void setOrigen(DTO_Deposito origen) {
		this.origen = origen;
	}

	public DTO_Deposito getDestino() {
		return destino;
	}

	public void setDestino(DTO_Deposito destino) {
		this.destino = destino;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public DTO_Empleado getUsuario() {
		return usuario;
	}

	public void setUsuario(DTO_Empleado usuario) {
		this.usuario = usuario;
	}

	

	public DTO_Deposito getArea() {
		return area;
	}

	public void setArea(DTO_Deposito area) {
		this.area = area;
	}

	public boolean isPlanProduccion() {
		return planProduccion;
	}

	public void setPlanProduccion(boolean planProduccion) {
		this.planProduccion = planProduccion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
}
