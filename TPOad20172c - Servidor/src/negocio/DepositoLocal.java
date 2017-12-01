package Negocio;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import dao.MovimientoStockDAO;
import exceptions.EntityInvalidaException;


@Entity
@DiscriminatorValue("Local")
public class DepositoLocal extends Deposito {
	
	public List<ItemAReponer> RealizarMovimientoDeStock(
			List<ItemAReponer> items, AreaPreparacion area, Usuario us) {
		// TODO Auto-generated method stub
		
		List<ItemAReponer> faltanteEnDepositoLocal = this.RealizarMovimientoDeStock(items, area, us);
		
		if(!(faltanteEnDepositoLocal.size() == items.size())){
			MovimientoStock mov = new MovimientoStock();
			mov.setFechaYHora(Calendar.getInstance().getTime());
			mov.setMotivo("El area " + area.getNombreSector() + " solicito un movimiento de stock al deposito local, por el usuario: " + us.getNombre());
			mov.setUsuario(us);
			this.AgregarMovimientoStock(mov);
		}
		
		return faltanteEnDepositoLocal;
	}

}
