package test;
import javax.naming.CommunicationException;

import BussinesDelegate.Delegate;
import DTO.PlatoDTO;
import DTO.RubroDTO;
import DTO.SectorDTO;
import java.util.List;
public class Test {

	public static void main(String[] args){
		try{
			System.out.println("Facturado en el sector: " + Delegate.getInstance().facturadoEnSector(new SectorDTO(1,"VIP")));
		}
		catch(CommunicationException e){
			System.out.println(e.getMessage());
		}
		try{
			System.out.println(" ");
			System.out.println("PLATOS DEL RUBRO: ");
			List<PlatoDTO> platos = Delegate.getInstance().platosDelRubro(new RubroDTO(1, "Carnes"));
			for(PlatoDTO plato: platos)
				System.out.println("Plato: "+plato.getDescripcion()+", con precio: "+plato.getPrecio());
			
		}
		catch(CommunicationException e){
			System.out.println(e.getMessage());
		}
		try{
			System.out.println(" ");
			System.out.println("PLATOS CON FACTURAS MAYORES A 150: ");
			List<PlatoDTO> platos = Delegate.getInstance().platosFacturasMayoresA150();
			for(PlatoDTO plato: platos)
				System.out.println("Plato: "+plato.getDescripcion()+", con precio: "+plato.getPrecio());
		}
		catch(CommunicationException e){
			System.out.println(e.getMessage());
		}
	}
}
