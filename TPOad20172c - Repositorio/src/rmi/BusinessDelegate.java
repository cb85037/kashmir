package rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;

import DTOs.ProductoDTO;
import businessDelegate.BusinessDelegate;
import exceptions.*;
import interfaceRemota.negocioTDA;
/*import Dto.algunocapaz;  tenemos que definir que dtos necesitamos*/
import interfaces.TDAControladorRemoto;;

public class BusinessDelegate implements TDAControladorRemoto{
	
	private static BusinessDelegate instancia;
	private TDAControladorRemoto negocioRemoto;
	
	private BusinessDelegate() throws RemoteException{
		try{
			negocioRemoto = (TDAControladorRemoto)Naming.lookup("//localhost/NegocioRemoto");
		} catch (Exception e){
			System.out.println("Error de BusinessDelegate: "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static BusinessDelegate getInstancia() throws RemoteException{
		if(instancia == null)
			instancia = new BusinessDelegate();
		return instancia;
		
	}
	
	/* ACA LLAMAMOS A LOS DTOS QUE VAMOS A USAR Y QUE IMPORTAMOS ARRIBA
	public ProductoDTO getProductoPorCodigo(ProductoDTO producto) throws RemoteException {
		// TODO Auto-generated method stub
		return negocioRemoto.getProductoPorCodigo(producto);
		
	}
	
	/public ProductoDTO getProductoPorCodigo(long codigo){
		return negocioRemoto.getProductoPorCodigo(codigo);
	}

	public void CrearProducto(ProductoDTO producto) throws RemoteException {
		// TODO Auto-generated method stub
		negocioRemoto.CrearProducto(producto);
		
	}*/

}
