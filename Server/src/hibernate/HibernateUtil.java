package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import entity.*;

 
public class HibernateUtil
{
    private static final SessionFactory sessionFactory;
    static
    {
        try
        {
        	 AnnotationConfiguration config = new AnnotationConfiguration();
        	 
             config.addAnnotatedClass(Entity_Local.class);
             config.addAnnotatedClass(Entity_Area.class);
             config.addAnnotatedClass(Entity_Cocina.class);
             config.addAnnotatedClass(Entity_Barra.class);
             config.addAnnotatedClass(Entity_Cafeteria.class);
             config.addAnnotatedClass(Entity_Salon.class);
             config.addAnnotatedClass(Entity_CajaDiaria.class);
             config.addAnnotatedClass(Entity_Empleado.class);
             config.addAnnotatedClass(Entity_EmpProduccion.class);
             config.addAnnotatedClass(Entity_Encargado.class);
             config.addAnnotatedClass(Entity_Mozo.class);
             config.addAnnotatedClass(Entity_Liquidacion.class);
             config.addAnnotatedClass(Entity_MesaSimple.class);
             config.addAnnotatedClass(Entity_MesaCompuesta.class);
             config.addAnnotatedClass(Entity_Mesa.class);
             config.addAnnotatedClass(Entity_CompraNoVenta.class);
             config.addAnnotatedClass(Entity_CompraVenta.class);
             config.addAnnotatedClass(Entity_ElaboracionNoVenta.class);
             config.addAnnotatedClass(Entity_ElaboracionVenta.class);
             config.addAnnotatedClass(Entity_Receta.class);
             config.addAnnotatedClass(Entity_ItemReceta.class);
             config.addAnnotatedClass(Entity_Producto.class);
             config.addAnnotatedClass(Entity_PlanProdSemiElab.class);
             config.addAnnotatedClass(Entity_Tarea.class);
             config.addAnnotatedClass(Entity_Lote.class);
             config.addAnnotatedClass(Entity_StockProducto.class);
             config.addAnnotatedClass(Entity_RubroCarta.class);
             config.addAnnotatedClass(Entity_DepositoArea.class);
             config.addAnnotatedClass(Entity_DepositoLocal.class);
             config.addAnnotatedClass(Entity_Deposito.class);
             config.addAnnotatedClass(Entity_DepositoCentral.class);
             config.addAnnotatedClass(Entity_Comanda.class);
             config.addAnnotatedClass(Entity_Carta.class);
             config.addAnnotatedClass(Entity_CajaDiaria.class);
             config.addAnnotatedClass(Entity_Caja.class);
             config.addAnnotatedClass(Entity_Sector.class);
             config.addAnnotatedClass(Entity_ItemComanda.class);
             config.addAnnotatedClass(Entity_Factura.class);
             config.addAnnotatedClass(Entity_ItemFactura.class);
             config.addAnnotatedClass(Entity_Movimiento.class);
             config.addAnnotatedClass(Entity_Plato.class);
             config.addAnnotatedClass(Entity_AdministracionCentral.class);
             config.addAnnotatedClass(Entity_EmpAdministracion.class);
             
             
             sessionFactory = config.buildSessionFactory();
        }
        catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}


