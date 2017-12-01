package Negocio;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
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
@DiscriminatorValue("Central")
public class DepositoCentral extends Deposito {

	public List<ItemAReponer> RealizarReposicionARestaurante(Usuario usuarioActual, Restaurante restaurante) {
		// TODO Auto-generated method stub
		List<ItemAReponer> mandarAPedidos = new ArrayList<ItemAReponer>(); 
		List<ItemAReponer> mandarDesdeCentral = new ArrayList<ItemAReponer>();
		
		for (ItemAReponer itemAReponer : restaurante.getBarra().GetItemsAReponer()) {
			if(itemAReponer.isDepositoCentral()){
				mandarDesdeCentral.add(itemAReponer);
			} else {
				itemAReponer.setRestaurante(restaurante);
				mandarAPedidos.add(itemAReponer);
			}
		}
		
		this.RealizarMovimientoDeStock(mandarDesdeCentral, restaurante.getBarra());
		
		mandarDesdeCentral = new ArrayList<ItemAReponer>();
		
		for (ItemAReponer itemAReponer : restaurante.getCocina().GetItemsAReponer()) {
			if(itemAReponer.isDepositoCentral()){
				mandarDesdeCentral.add(itemAReponer);
			} else {
				itemAReponer.setRestaurante(restaurante);
				mandarAPedidos.add(itemAReponer);
			}
		}
		
		this.RealizarMovimientoDeStock(mandarDesdeCentral, restaurante.getCocina());
		mandarDesdeCentral = new ArrayList<ItemAReponer>();
		
		for (ItemAReponer itemAReponer : restaurante.getCafeteria().GetItemsAReponer()) {
			if(itemAReponer.isDepositoCentral()){
				mandarDesdeCentral.add(itemAReponer);
			} else {
				itemAReponer.setRestaurante(restaurante);
				mandarAPedidos.add(itemAReponer);
			}
		}
		
		this.RealizarMovimientoDeStock(mandarDesdeCentral, restaurante.getCafeteria());
		
			MovimientoStock mov = new MovimientoStock();
			mov.setFechaYHora(Calendar.getInstance().getTime());
			mov.setMotivo("Se realizo el proceso de reposicion de stock del deposito central al restaurante: "+restaurante.getId()+", por el usuario: " + usuarioActual.getNombre());
			mov.setUsuario(usuarioActual);
			this.AgregarMovimientoStock(mov);
			
		return mandarAPedidos;
	}

	public void RealizarReposicionADepositoLocal(
			Restaurante res) {
		// TODO Auto-generated method stub
		List<ItemAReponer> itemsAReponer = new ArrayList<ItemAReponer>();
		DepositoLocal depLocal = res.getDeposito();
		Iterator<ItemDeposito> depLocalItems = depLocal.getItemsDeposito().iterator();
		while(depLocalItems.hasNext()){
			ItemDeposito itDepositoLocal = depLocalItems.next();
			
			if(itDepositoLocal.getStock() < itDepositoLocal.getStockDeseado()){
				ItemDeposito itDepCentral = this.buscarItemDeposito(itDepositoLocal.getProducto());
				if(itDepCentral != null && itDepCentral.getStock() > 0){
					int cantidadAReponer = itDepositoLocal.getStockDeseado() - itDepositoLocal.getStock();
					Iterator<ItemProducto> items = itDepCentral.getItemProducto().iterator();
					while(cantidadAReponer > 0 && items.hasNext()){
						ItemProducto itemProducto = items.next();
						itDepositoLocal.AddItemProducto(itemProducto);
						items.remove();
						cantidadAReponer--;
					}
				}
			}
		}
	}

	public List<ItemAReponer> getItemsAReponer() {
		// TODO Auto-generated method stub
		Iterator<ItemDeposito> items = this.getItemsDeposito().iterator();
		List<ItemAReponer> itemsAReponer = new ArrayList<ItemAReponer>();
		while(items.hasNext()){
			ItemDeposito it = items.next();
			int diferencia = it.getStockDeseado() - it.getStock();
			if(diferencia > 0){
				ItemAReponer itRponer = new ItemAReponer();
				itRponer.setArea(null);
				itRponer.setCantidad(diferencia);
				itRponer.setDepositoCentral(true);
				Date d = new Date(Calendar.getInstance().getTime().getTime());
				itRponer.setFecha(d);
				itRponer.setProducto(it.getProducto());
				itRponer.setRestaurante(null);
				itemsAReponer.add(itRponer);
			}
		}
		
		return itemsAReponer;
	}

}
