package Negocio;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import repositorio.TurnoDTO;
import repositorio.UsuarioDTO;

@Entity
@Table(name="usuarios")
public class Usuario  implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_usuario")
	private int id;
	private String nombre;
	private String contrasenia;
	private Date ultimaFechaIngreso;
	private Date ultimaFechaEgreso;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_rol")
	private Rol rol;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Date getUltimaFechaIngreso() {
		return ultimaFechaIngreso;
	}

	public void setUltimaFechaIngreso(Date ultimaFechaIngreso) {
		this.ultimaFechaIngreso = ultimaFechaIngreso;
	}

	public Date getUltimaFechaEgreso() {
		return ultimaFechaEgreso;
	}

	public void setUltimaFechaEgreso(Date ultimaFechaEgreso) {
		this.ultimaFechaEgreso = ultimaFechaEgreso;
	}
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	/***********************************
	 ******METODOS DTO******************
	 ***********************************/
	
	public UsuarioDTO generarDTOdeUsuario(){
		UsuarioDTO dto = new UsuarioDTO();
		if((this.id != 0)){
			dto.setId(id);
			dto.setContrasenia(contrasenia);
			dto.setUltimaFechaEgreso(ultimaFechaEgreso);
			dto.setNombre(nombre);
			dto.setUltimaFechaIngreso(ultimaFechaIngreso);
			dto.setSoyAdmin(this.rol.soyAdministrador());
			dto.setSoyEmpleado(this.rol.soyEmpleado());
		}
		return dto;
	}
	
	public static Usuario crearInstanciaDeMesa(UsuarioDTO dto){
		Usuario usuario = new Usuario();
		usuario.setContrasenia(dto.getContrasenia());
		usuario.setId(dto.getId());
		usuario.setNombre(dto.getNombre());
		usuario.setUltimaFechaEgreso(dto.getUltimaFechaEgreso());
		usuario.setUltimaFechaIngreso(dto.getUltimaFechaIngreso());
		return usuario;
	}
}
