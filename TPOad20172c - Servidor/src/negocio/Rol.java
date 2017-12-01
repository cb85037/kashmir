package Negocio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import repositorio.RolDTO;
import repositorio.UsuarioDTO;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="rol",discriminatorType=DiscriminatorType.STRING)
@Table(name="roles")
public abstract class Rol implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_rol")
	private int id;
	
	public abstract boolean soyEmpleado();
	public abstract boolean soyAdministrador();
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
}
