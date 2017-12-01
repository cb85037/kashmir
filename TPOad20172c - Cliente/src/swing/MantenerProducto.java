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

import repositorio.Cauducidad;
import repositorio.OrigenDestino;
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
public class MantenerProducto extends javax.swing.JFrame {
	private JTextField txtNombre;
	private JButton btnCancelar;
	private JLabel estado;
	private JButton btnModificarAgregar;
	private JLabel jLabel1;
	private JComboBox restaurante;
	private JLabel jLabel3;
	private JComboBox jComboBox1;
	private JButton btnBuscar;
	private ProductoDTO prod = null;
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MantenerProducto inst = new MantenerProducto();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public MantenerProducto() {
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
							prod = Cliente.getInstancia().GetProductoByNombre(txtNombre.getText());
							if(prod != null){
								txtNombre.setEnabled(false);
								restaurante.setSelectedItem(prod.getCauducidad());
								jComboBox1.setSelectedItem(prod.getOrigenDestino());
								btnBuscar.setEnabled(false);
							} else{
								prod = new ProductoDTO();
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
								new Cauducidad[] { Cauducidad.ALTA, Cauducidad.MEDIA, Cauducidad.BAJA });
				restaurante = new JComboBox();
				getContentPane().add(restaurante);
				restaurante.setModel(restauranteModel);
				restaurante.setBounds(256, 64, 165, 21);
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Cauducidad");
				jLabel1.setBounds(51, 67, 118, 14);
			}
			{
				btnModificarAgregar = new JButton();
				getContentPane().add(btnModificarAgregar);
				btnModificarAgregar.setText("Modificar/Agregar");
				btnModificarAgregar.setBounds(76, 277, 261, 21);
				btnModificarAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							if(prod != null){
								prod.setNombre(txtNombre.getText());
								prod.setCauducidad((Cauducidad)restaurante.getSelectedItem());
								prod.setOrigenDestino((OrigenDestino)jComboBox1.getSelectedItem());
								Cliente.getInstancia().GuardarProducto(prod);
								estado.setText("Se guardo el Producto");
							}
							else{
								estado.setText("No hay productos");
							}
							
						} catch (Exception e) {
							// TODO: handle exception
							estado.setText("Se produjo un error");
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
						txtNombre.setEnabled(true);
						restaurante.setSelectedIndex(0);
						jComboBox1.setSelectedIndex(0);
						prod = null;
					}
				});
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("OrigenDestino");
				jLabel3.setBounds(51, 103, 118, 14);
			}
			{
				ComboBoxModel jComboBox1Model = 
						new DefaultComboBoxModel(
								new OrigenDestino[] { OrigenDestino.COMPRAYNOVENTA, OrigenDestino.COMPRAYVENTA, OrigenDestino.ELABORACIONALANOVENTA, OrigenDestino.ELABORACIONALAVENTA });
				jComboBox1 = new JComboBox();
				getContentPane().add(jComboBox1);
				jComboBox1.setModel(jComboBox1Model);
				jComboBox1.setBounds(256, 100, 165, 21);
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
