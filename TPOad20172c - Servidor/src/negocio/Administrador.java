package Negocio;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("A")
public class Administrador extends Rol {
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean soyEmpleado() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean soyAdministrador() {
		// TODO Auto-generated method stub
		return true;
	}

}
