package entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@DiscriminatorValue("area")
public class Entity_DepositoArea extends Entity_Deposito {

	private static final long serialVersionUID = 1L;

	public Entity_DepositoArea() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
