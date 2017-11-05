package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import dto.DTO_DepositoLocal;
import dto.DTO_Movimiento;


@Entity
@DiscriminatorValue("local")
public class Entity_DepositoLocal extends Entity_Deposito {

	private static final long serialVersionUID = 1L;

	public Entity_DepositoLocal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DTO_DepositoLocal toDTO() {
		
		List<DTO_Movimiento> mov = new ArrayList<DTO_Movimiento>();
		
		DTO_DepositoLocal d = new DTO_DepositoLocal();
		
		d.setIdDeposito(this.idDeposito);
		d.setDescripcion(this.descripcion);
		
		for(Entity_Movimiento m: this.movimientos){
			mov.add(m.toDTO());
		}
		
		d.setMovimientos(mov);
		
		return d;
	}
	
}
