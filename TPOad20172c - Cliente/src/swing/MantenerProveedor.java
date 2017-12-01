package ClienteSwing.vistas;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import ClienteSwing.Cliente;

import repositorio.ProductoDTO;
import repositorio.ProveedorDTO;
import repositorio.Restaurantes;


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
public class MantenerProveedor extends javax.swing.JFrame {
	private JTextField txtNombre;
	private JButton btnCancelar;
	private JLabel estado;
	private JButton btnModificarAgregar;
	private JLabel estadoProducto;
	private JButton txtAgregarProducto;
	private JTextField jTextField1;
	private JLabel jLabel2;
	private JCheckBox checkDisp;
	private JLabel jLabel1;
	private JComboBox restaurante;
	private JButton btnBuscar;
	private ProveedorDTO prov = null;
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MantenerProveedor inst = new MantenerProveedor();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public MantenerProveedor() {
		super();
		
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				txtNombre = new JTextField();
				getContentPane().add(txtNombre);
				txtNombre.setBounds(219, 22, 154, 21);
			}
			{
				btnBuscar = new JButton();
				getContentPane().add(btnBuscar);
				btnBuscar.setText("Buscar por Nombre");
				btnBuscar.setBounds(34, 22, 166, 21);
				btnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							prov = Cliente.getInstancia().GetProveedorByNombre(txtNombre.getText());
							if(prov != null){
								txtNombre.setEnabled(false);
								checkDisp.setSelected(prov.isDisponible());
								restaurante.setSelectedIndex(prov.getRestaurante() - 1);
								btnBuscar.setEnabled(false);
							} else{
								prov = new ProveedorDTO();
							}
							
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							estado.setText("Error");
							
						}
					}
				});
			}
			{
				ComboBoxModel restauranteModel = 
						new DefaultComboBoxModel(
								new String[] { "Belgrano", "Caballito", "Puerto Madero" });
				restaurante = new JComboBox();
				getContentPane().add(restaurante);
				restaurante.setModel(restauranteModel);
				restaurante.setBounds(256, 64, 165, 21);
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Restaurante");
				jLabel1.setBounds(51, 67, 118, 14);
			}
			{
				checkDisp = new JCheckBox();
				getContentPane().add(checkDisp);
				checkDisp.setText("disponible");
				checkDisp.setBounds(256, 109, 65, 18);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("AgregarProducto");
				jLabel2.setBounds(90, 142, 238, 14);
			}
			{
				jTextField1 = new JTextField();
				getContentPane().add(jTextField1);
				jTextField1.setBounds(176, 182, 139, 21);
			}
			{
				txtAgregarProducto = new JButton();
				getContentPane().add(txtAgregarProducto);
				txtAgregarProducto.setText("Agregar producto");
				txtAgregarProducto.setBounds(7, 182, 157, 21);
				txtAgregarProducto.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							ProductoDTO prod = Cliente.getInstancia().GetProductoByNombreParaProveedor(jTextField1.getText());
							if(prov.getProductos() == null){
								prov.setProductos(new ArrayList<ProductoDTO>());
							}
							
							prov.AddProducto(prod);
							estadoProducto.setText("");
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							estado.setText("Error");
							
						}
					}
				});
			}
			{
				estadoProducto = new JLabel();
				getContentPane().add(estadoProducto);
				estadoProducto.setBounds(39, 225, 354, 14);
			}
			{
				btnModificarAgregar = new JButton();
				getContentPane().add(btnModificarAgregar);
				btnModificarAgregar.setText("Modificar/Agregar");
				btnModificarAgregar.setBounds(76, 277, 261, 21);
				btnModificarAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							if(prov != null){
								prov.setDisponible(checkDisp.isSelected());
								prov.setNombre(txtNombre.getText());
								int index = restaurante.getSelectedIndex();
								prov.setRestaurante(index + 1);
								Cliente.getInstancia().GuardarProvedor(prov);
								estado.setText("Se guardo el Provedor");
							}
							
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				});
			}
			{
				estado = new JLabel();
				getContentPane().add(estado);
				estado.setBounds(19, 358, 391, 10);
			}
			{
				btnCancelar = new JButton();
				getContentPane().add(btnCancelar);
				btnCancelar.setText("Cancelar");
				btnCancelar.setBounds(88, 317, 227, 21);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnBuscar.setEnabled(true);
						txtNombre.setText("");
						checkDisp.setEnabled(true);
						txtNombre.setEnabled(true);
						restaurante.setSelectedIndex(0);
						prov = null;
					}
				});
			}
			pack();
			this.setSize(459, 433);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void btnBuscarActionPerformed(ActionEvent evt) {
		System.out.println("btnBuscar.actionPerformed, event="+evt);
		//TODO add your code for btnBuscar.actionPerformed
	}
	
	private void txtAgregarProductoActionPerformed(ActionEvent evt) {
		System.out.println("txtAgregarProducto.actionPerformed, event="+evt);
		//TODO add your code for txtAgregarProducto.actionPerformed
	}
	
	private void btnModificarAgregarActionPerformed(ActionEvent evt) {
		System.out.println("btnModificarAgregar.actionPerformed, event="+evt);
		//TODO add your code for btnModificarAgregar.actionPerformed
	}
	
	private void btnCancelarActionPerformed(ActionEvent evt) {
		System.out.println("btnCancelar.actionPerformed, event="+evt);
		//TODO add your code for btnCancelar.actionPerformed
	}

}
