package entity;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@DiscriminatorValue("mozo")
public class Entity_Mozo extends Entity_Empleado{
	
	private static final long serialVersionUID = 1L;
	@Column(name = "comision")
	private float comision;
	
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "empleado", referencedColumnName = "legajo")
	private List<Entity_Liquidacion> liquidaciones;
	
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "mozo", referencedColumnName = "legajo")
	private List<Entity_MesaSimple> mesasAsignadas;
	
	public Entity_Mozo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public float getComision() {
		return comision;
	}

	public void setComision(float comision) {
		this.comision = comision;
	}

	public List<Entity_Liquidacion> getLiquidaciones() {
		return liquidaciones;
	}

	public void setLiquidaciones(List<Entity_Liquidacion> liquidaciones) {
		this.liquidaciones = liquidaciones;
	}

	public List<Entity_MesaSimple> getMesasAsignadas() {
		return mesasAsignadas;
	}

	public void setMesasAsignadas(List<Entity_MesaSimple> mesasAsignadas) {
		this.mesasAsignadas = mesasAsignadas;
	}
	
	public void agregarMesa(Entity_MesaSimple m){
		this.mesasAsignadas.add(m);
	}
	
}
