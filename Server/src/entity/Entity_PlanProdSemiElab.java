package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import dto.DTO_PlanProdSemiElab;

@Entity
@Table(name = "PlanProdSemiElab")
public class Entity_PlanProdSemiElab implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	private int idPlanProduccion;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "idPlanProduccion", referencedColumnName = "id")
	private List<Entity_Tarea> tareas;
	
	@Column(name = "estado")
	private String estado;
	
	
	public Entity_PlanProdSemiElab() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdPlanProduccion() {
		return idPlanProduccion;
	}

	public void setIdPlanProduccion(int idPlanProduccion) {
		this.idPlanProduccion = idPlanProduccion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<Entity_Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(List<Entity_Tarea> tareas) {
		this.tareas = tareas;
	}

	public DTO_PlanProdSemiElab getDTO() {
		DTO_PlanProdSemiElab p = new DTO_PlanProdSemiElab();
		
		p.setIdPlanProduccion(this.idPlanProduccion);
		p.setFecha(this.fecha);
		p.setEstado(this.estado);
		
		return p;
	}
	
	
	
	
}
