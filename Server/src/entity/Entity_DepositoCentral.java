package entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@DiscriminatorValue("central")
public class Entity_DepositoCentral extends Entity_Deposito {

	private static final long serialVersionUID = 1L;

	public Entity_DepositoCentral() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
