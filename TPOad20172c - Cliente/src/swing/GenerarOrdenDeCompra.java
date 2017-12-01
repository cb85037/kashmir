package ClienteSwing.vistas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import ClienteSwing.Cliente;
import Negocio.Producto;

import repositorio.*;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class GenerarOrdenDeCompra extends javax.swing.JFrame {
	private JLabel jLabel1;
	private JButton buscarProducto;
	private JTextField txtProducto;
	private JButton btnCancelar;
	private JLabel estado;
	private JButton btnConfirmar;
	private JLabel Calidad;
	private JButton btnAgregar;
	private JCheckBox faltante;
	private JTextField txtMinVencimiento;
	private JLabel minDias;
	private JTextField txtProv;
	private JButton btnProd;
	private JTextField txtCalidad;
	private JTextField txtCantidad;
	private JLabel Cantidad;
	private ItemCompraDTO item;
	private List<ItemCompraDTO> items;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GenerarOrdenDeCompra inst = new GenerarOrdenDeCompra();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public GenerarOrdenDeCompra() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Generar Orden de Compra");
				jLabel1.setBounds(68, 6, 345, 53);
				jLabel1.setFont(new java.awt.Font("Tahoma",3,18));
			}
			{
				buscarProducto = new JButton();
				getContentPane().add(buscarProducto);
				buscarProducto.setText("Buscar Producto");
				buscarProducto.setBounds(20, 104, 178, 21);
				buscarProducto.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						buscarProductoActionPerformed(evt);
					}
				});
			}
			{
				txtProducto = new JTextField();
				getContentPane().add(txtProducto);
				txtProducto.setBounds(219, 104, 229, 21);
			}
			{
				Cantidad = new JLabel();
				getContentPane().add(Cantidad);
				Cantidad.setText("Cantidad");
				Cantidad.setBounds(50, 143, 134, 14);
			}
			{
				txtCantidad = new JTextField();
				getContentPane().add(txtCantidad);
				txtCantidad.setBounds(219, 140, 229, 21);
				txtCantidad.setEnabled(false);
			}
			{
				Calidad = new JLabel();
				getContentPane().add(Calidad);
				Calidad.setText("Calidad");
				Calidad.setBounds(50, 179, 148, 14);
			}
			{
				txtCalidad = new JTextField();
				getContentPane().add(txtCalidad);
				txtCalidad.setBounds(219, 176, 229, 21);
				txtCalidad.setEnabled(false);
			}
			{
				btnProd = new JButton();
				getContentPane().add(btnProd);
				btnProd.setText("Buscar Proveedor");
				btnProd.setBounds(12, 270, 178, 21);
				btnProd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnProdActionPerformed(evt);
					}
				});
				btnProd.setEnabled(false);
			}
			{
				txtProv = new JTextField();
				getContentPane().add(txtProv);
				txtProv.setBounds(219, 270, 229, 21);
				txtProv.setEnabled(false);
			}
			{
				minDias = new JLabel();
				getContentPane().add(minDias);
				minDias.setText("Minimo Dias Para Vencimiento");
				minDias.setBounds(12, 219, 199, 14);
			}
			{
				txtMinVencimiento = new JTextField();
				getContentPane().add(txtMinVencimiento);
				txtMinVencimiento.setBounds(219, 216, 229, 21);
				txtMinVencimiento.setEnabled(false);
			}
			{
				faltante = new JCheckBox();
				getContentPane().add(faltante);
				faltante.setText("Por Faltante");
				faltante.setBounds(139, 77, 253, 18);
			}
			{
				btnAgregar = new JButton();
				getContentPane().add(btnAgregar);
				btnAgregar.setText("Agregar Item a la Orden");
				btnAgregar.setBounds(24, 317, 215, 21);
				btnAgregar.setEnabled(false);
				btnAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnAgregarActionPerformed(evt);
					}
				});
			}
			{
				btnConfirmar = new JButton();
				getContentPane().add(btnConfirmar);
				btnConfirmar.setText("Confirmar OC");
				btnConfirmar.setBounds(155, 377, 136, 62);
				btnConfirmar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnConfirmarActionPerformed(evt);
					}
				});
			}
			{
				estado = new JLabel();
				getContentPane().add(estado);
				estado.setBounds(12, 450, 455, 32);
			}
			{
				btnCancelar = new JButton();
				getContentPane().add(btnCancelar);
				btnCancelar.setText("Cancelar");
				btnCancelar.setBounds(278, 317, 135, 21);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnCancelarActionPerformed(evt);
					}
				});
			}
			pack();
			this.setSize(495, 521);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void btnAgregarActionPerformed(ActionEvent evt) {
		System.out.println("btnAgregar.actionPerformed, event="+evt);
		//TODO add your code for btnAgregar.actionPerformed
		if(items == null){
			items = new ArrayList<ItemCompraDTO>();
		}
		
		item.setCalidad(txtCalidad.getText());
		item.setCantidad(Integer.parseInt(txtCantidad.getText()));
		item.setMinimoDiasFechaVencimiento(Integer.parseInt(txtMinVencimiento.getText()));
		
		items.add(item);
		txtCantidad.setEnabled(false);
		txtMinVencimiento.setEnabled(false);
		txtProducto.setEnabled(true);
		txtProv.setEnabled(false);
		btnAgregar.setEnabled(false);
		btnProd.setEnabled(false);
		buscarProducto.setEnabled(true);
		txtCalidad.setEnabled(false);
		estado.setText("");
		item = null;
		
		estado.setText("se ha agregado el item");
	}
	
	private void btnConfirmarActionPerformed(ActionEvent evt) {
		System.out.println("btnConfirmar.actionPerformed, event="+evt);
		//TODO add your code for btnConfirmar.actionPerformed
		try {
			estado.setText("se han creado las siguientes ordenes: " + Cliente.getInstancia().GenerarOrdenesDeCompra(items, faltante.isSelected(), "C:/"));
			items = null;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void buscarProductoActionPerformed(ActionEvent evt) {
		System.out.println("buscarProducto.actionPerformed, event="+evt);
		//TODO add your code for buscarProducto.actionPerformed
		try {
			ProductoDTO producto = Cliente.getInstancia().GetProductoByNombreParaProveedor(txtProducto.getText());
			if(producto != null){
				txtCantidad.setEnabled(true);
				txtMinVencimiento.setEnabled(true);
				txtProducto.setEnabled(false);
				txtProv.setEnabled(true);
				txtCalidad.setEnabled(true);
				btnAgregar.setEnabled(true);
				btnProd.setEnabled(true);
				buscarProducto.setEnabled(false);
				estado.setText("Se ha encontrado el producto");
				item = new ItemCompraDTO();
				item.setProducto(producto);
			}
			else{
				estado.setText("No se ha encontrado el producto");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void btnCancelarActionPerformed(ActionEvent evt) {
		System.out.println("btnCancelar.actionPerformed, event="+evt);
		//TODO add your code for btnCancelar.actionPerformed
		txtCantidad.setEnabled(false);
		txtMinVencimiento.setEnabled(false);
		txtProducto.setEnabled(true);
		txtProv.setEnabled(false);
		btnAgregar.setEnabled(false);
		btnProd.setEnabled(false);
		buscarProducto.setEnabled(true);
		txtCalidad.setEnabled(false);
		estado.setText("");
		item = null;
	}
	
	private void btnProdActionPerformed(ActionEvent evt) {
		System.out.println("btnProd.actionPerformed, event="+evt);
		//TODO add your code for btnProd.actionPerformed
		try {
			ProveedorDTO p = Cliente.getInstancia().GetProveedorByNombre(txtProv.getText());
			if(p != null){
				Iterator<ProductoDTO> itp = p.getProductos().iterator();
				while(itp.hasNext()){
					if(itp.next().getId() == item.getProducto().getId()){
						item.setProvedor(p);
						estado.setText("se ha agregado el provedor");
						return;
						
					}
				}
				estado.setText("El proveedor no tiene ese producto");
			} else {
				estado.setText("No existe el proveedor");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
