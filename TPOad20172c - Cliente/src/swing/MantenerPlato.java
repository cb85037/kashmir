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
import javax.xml.bind.ParseConversionEvent;

import ClienteSwing.Cliente;
import Negocio.Plato;

import repositorio.ItemIngredienteDTO;
import repositorio.PlatoDTO;
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
public class MantenerPlato extends javax.swing.JFrame {
	private JTextField txtNombre;
	private JButton btnCancelar;
	private JLabel lblcantidad;
	private JTextField txtCantidad;
	private JLabel estado;
	private JButton btnModificarAgregar;
	private JLabel estadoProducto;
	private JButton txtAgregarProducto;
	private JTextField jTextField1;
	private JLabel jLabel2;
	private JComboBox cArea;
	private JLabel area;
	private JCheckBox semiE;
	private JButton btnIngrediente;
	private JTextField txtComision;
	private JLabel jLabel1;
	private JButton btnBuscar;
	private PlatoDTO plato = null;
	private ItemIngredienteDTO item = null;
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MantenerPlato inst = new MantenerPlato();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public MantenerPlato() {
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
							plato = Cliente.getInstancia().GetPlatoByNombre(txtNombre.getText());
							if(plato != null){
								txtNombre.setEnabled(false);
								btnBuscar.setEnabled(false);
								txtComision.setText(Float.toString(plato.getComision()));
								semiE.setSelected(plato.isSemiElaborado());
							} else{
								plato = new PlatoDTO();
							}
							
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							estado.setText("Error");
							
						}
					}
				});
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Comision");
				jLabel1.setBounds(51, 67, 118, 14);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Agregar Ingrediente");
				jLabel2.setBounds(142, 150, 238, 14);
			}
			{
				jTextField1 = new JTextField();
				getContentPane().add(jTextField1);
				jTextField1.setBounds(234, 182, 139, 21);
			}
			{
				txtAgregarProducto = new JButton();
				getContentPane().add(txtAgregarProducto);
				txtAgregarProducto.setText("Buscar Producto");
				txtAgregarProducto.setBounds(7, 182, 212, 21);
				txtAgregarProducto.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							ProductoDTO prod = Cliente.getInstancia().GetProductoByNombreParaPlato(jTextField1.getText());
							if(plato.getIngredientes() == null){
								plato.setIngredientes(new ArrayList<ItemIngredienteDTO>());
							}
							if(prod != null){
								item = new ItemIngredienteDTO();
								item.setProducto(prod);
								txtCantidad.setEnabled(true);
							}
							else{
								estadoProducto.setText("No existe el item ");
							}
							
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
				estadoProducto.setBounds(31, 252, 354, 14);
			}
			{
				btnModificarAgregar = new JButton();
				getContentPane().add(btnModificarAgregar);
				btnModificarAgregar.setText("Modificar/Agregar");
				btnModificarAgregar.setBounds(70, 342, 261, 21);
				btnModificarAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							if(plato != null){
								plato.setNombre(txtNombre.getText());
								plato.setComision(Integer.parseInt(txtComision.getText()));
								plato.setSemiElaborado(semiE.isSelected());
								plato.setNombreSector(cArea.getSelectedItem().toString());
								Cliente.getInstancia().GuardarPlato(plato);
								estado.setText("Se guardo el Plato");
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
				estado.setBounds(19, 421, 391, 10);
			}
			{
				btnCancelar = new JButton();
				getContentPane().add(btnCancelar);
				btnCancelar.setText("Cancelar");
				btnCancelar.setBounds(88, 374, 227, 21);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnBuscar.setEnabled(true);
						txtNombre.setText("");
						txtNombre.setEnabled(true);
						txtCantidad.setText("");
						txtComision.setText("");
						txtCantidad.setEnabled(false);
						plato = null;
					}
				});
			}
			{
				txtCantidad = new JTextField();
				getContentPane().add(txtCantidad);
				txtCantidad.setBounds(237, 225, 49, 21);
				txtCantidad.setEnabled(false);
			}
			{
				lblcantidad = new JLabel();
				getContentPane().add(lblcantidad);
				lblcantidad.setText("Cantidad");
				lblcantidad.setBounds(88, 228, 81, 14);
			}
			{
				txtComision = new JTextField();
				getContentPane().add(txtComision);
				txtComision.setBounds(219, 64, 161, 21);
			}
			{
				btnIngrediente = new JButton();
				getContentPane().add(btnIngrediente);
				btnIngrediente.setText("Agregar Ingrediente");
				btnIngrediente.setBounds(184, 272, 189, 23);
				btnIngrediente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnIngredienteActionPerformed(evt);
					}
				});
			}
			{
				semiE = new JCheckBox();
				getContentPane().add(semiE);
				semiE.setText("SemiElaborado");
				semiE.setBounds(219, 106, 185, 20);
			}
			{
				area = new JLabel();
				getContentPane().add(area);
				area.setText("area : ");
				area.setBounds(7, 106, 49, 16);
			}
			{
				ComboBoxModel cAreaModel = 
					new DefaultComboBoxModel(
							new String[] { "Barra", "Cocina", "Cafeteria" });
				cArea = new JComboBox();
				getContentPane().add(cArea);
				cArea.setModel(cAreaModel);
				cArea.setBounds(74, 103, 126, 23);
			}
			pack();
			this.setSize(459, 492);
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
	
	private void btnIngredienteActionPerformed(ActionEvent evt) {
		System.out.println("btnIngrediente.actionPerformed, event="+evt);
		//TODO add your code for btnIngrediente.actionPerformed
		item.setCantidad(Integer.parseInt(txtCantidad.getText()));
		plato.AddIngrediente(item);
		txtCantidad.setEnabled(false);
	}

}
