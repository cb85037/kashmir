package TestHibernate;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/*import DTOs.ProductoDTO; importo los dtos que voy a usar*/
import controlador.Controlador; //pongo el controlador
import dao.ProductoDAO; //importo el dao que voy a usar para testear

public class TestHibernate {

	public static void main(String[] args) {

		/** recuperar un producto por codigo de barras */
		ProductoDTO p = ProductoDAO.getInstancia().findProductoByCodigo(7790895000430l).toDTO();
		System.out.println(p.getNombre());
		if(p.getAditivos() != null){
			System.out.println("Aditivos");
			List<String> auxA = p.getAditivos();
			for(String a  : auxA)
				System.out.println("\t" + a);
			if(p.getCoeficientes() != null){
				System.out.println("Contenido");
				Map<String, Float> auxC = p.getCoeficientes();
				for (Entry<String, Float> e: auxC.entrySet()) {
					System.out.println("\t["+e.getKey() + "=" + e.getValue()+"]");
				}
			}
		}

		/** 
		 * Cargar un nuevo producto con 3 aditivos y todos los componentes 
		 * indicar un codigo de barras inexistente (tienen 13 dígitos)
		 * un producto y una marca (son dos cadenas de caracateres 
		 * */
		p = new ProductoDTO(9999999942095l, "Producto A", "Marca X");
		
		/** Agregar tres aditivos de los existentes. debe ingresar la descripcion de los mismos */
		p.setAditivo("Curcumina	");
		p.setAditivo("Riboflavina	");
		p.setAditivo("Tartrazina	");
		
		/** Agregar los coeficientes del producto. Estos son:
		 *  porcion, carbohidratos, azucares, proteinas, fibra, sodio, colesterol, totales, saturadas, trans, mono, poli
		 * */
		p.setCoeficiente("carbohidratos", 10.10f);
		p.setCoeficiente("azucares", 10.10f);
		p.setCoeficiente("proteinas", 10.10f);
		p.setCoeficiente("fibra", 10.10f);
		p.setCoeficiente("sodio", 10.10f);
		p.setCoeficiente("colesterol", 10.10f);
		p.setCoeficiente("totales", 10.10f);
		p.setCoeficiente("saturadas", 10.10f);
		p.setCoeficiente("trans", 10.10f);
		p.setCoeficiente("mono", 10.10f);
		p.setCoeficiente("poli", 10.10f);
		p.setCoeficiente("porcion", 10.10f);
		
		Controlador.getInstancia().agregarProducto(p);
	}
}
