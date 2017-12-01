package ClienteSwing.vistas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import ClienteSwing.Cliente;

import repositorio.ItemPlanDeProduccionDTO;


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
public class ModificarItemPlan extends javax.swing.JFrame {
	private JButton btnBuscar;
	private JTextField txtId;
	private JTextField jTextField1;
	private JTextField txtTiempo;
	private JTextField txtNombre;
	private JLabel aa;
	private JLabel res;
	private JComboBox restaurante;
	private JButton Editar;
	private JLabel estadoBorrar;
	private JButton btnBorrar;
	private JCheckBox libre;
	private JLabel jLabel1;
	private JLabel txtCantidad;
	private ItemPlanDeProduccionDTO item;
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ModificarItemPlan inst = new ModificarItemPlan();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public ModificarItemPlan() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				btnBuscar = new JButton();
				getContentPane().add(btnBuscar);
				btnBuscar.setText("Buscar Por Id");
				btnBuscar.setBounds(21, 17, 162, 23);
				btnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnBuscarActionPerformed(evt);
					}
				});
			}
			{
				txtId = new JTextField();
				getContentPane().add(txtId);
				txtId.setBounds(204, 17, 152, 23);
			}
			{
				txtCantidad = new JLabel();
				getContentPane().add(txtCantidad);
				txtCantidad.setText("Cantidad");
				txtCantidad.setBounds(21, 63, 178, 16);
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Tiempo Por Unidad");
				jLabel1.setBounds(21, 104, 178, 16);
			}
			{
				libre = new JCheckBox();
				getContentPane().add(libre);
				libre.setText("Libre");
				libre.setBounds(21, 132, 84, 20);
			}
			{
				jTextField1 = new JTextField();
				getContentPane().add(jTextField1);
				jTextField1.setBounds(211, 63, 140, 23);
			}
			{
				txtTiempo = new JTextField();
				getContentPane().add(txtTiempo);
				txtTiempo.setBounds(211, 101, 140, 23);
			}
			{
				btnBorrar = new JButton();
				getContentPane().add(btnBorrar);
				btnBorrar.setText("Borrar");
				btnBorrar.setBounds(115, 272, 155, 23);
				btnBorrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnBorrarActionPerformed(evt);
					}
				});
			}
			{
				estadoBorrar = new JLabel();
				getContentPane().add(estadoBorrar);
				estadoBorrar.setBounds(21, 315, 343, 25);
			}
			{
				Editar = new JButton();
				getContentPane().add(Editar);
				Editar.setText("Editar");
				Editar.setBounds(91, 359, 193, 23);
				Editar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						EditarActionPerformed(evt);
					}
				});
			}
			{
				res = new JLabel();
				getContentPane().add(res);
				res.setText("Restaurante");
				res.setBounds(21, 172, 122, 16);
			}
			{
				ComboBoxModel restauranteModel = 
						new DefaultComboBoxModel(
								new String[] { "Belgrano", "Caballito", "Puerto Madero" });
				restaurante = new JComboBox();
				getContentPane().add(restaurante);
				restaurante.setModel(restauranteModel);
				restaurante.setBounds(204, 169, 137, 23);
			}
			{
				aa = new JLabel();
				getContentPane().add(aa);
				aa.setText("Nombre");
				aa.setBounds(21, 211, 103, 16);
			}
			{
				txtNombre = new JTextField();
				getContentPane().add(txtNombre);
				txtNombre.setBounds(204, 208, 152, 23);
				txtNombre.setEnabled(false);
			}
			pack();
			this.setSize(400, 469);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void btnBuscarActionPerformed(ActionEvent evt) {
		System.out.println("btnBuscar.actionPerformed, event="+evt);
		//TODO add your code for btnBuscar.actionPerformed
		try {
			item = Cliente.getInstancia().buscarItemPlan(Integer.parseInt(txtId.getText()));
			if(item != null){
				jTextField1.setText(Integer.toString(item.getCantidad()));
				txtTiempo.setText(Float.toString(item.getTiempoPorUnidadEnHoras()));
				txtNombre.setText(item.getSemielaborado().getNombre());
				restaurante.setSelectedIndex(item.getRestaurante() - 1);
				libre.setSelected(item.isLibre());
				txtTiempo.setEnabled(item.isLibre());
				jTextField1.setEnabled(item.isLibre());
				libre.setEnabled(item.isLibre());
				btnBorrar.setEnabled(item.isLibre());
				Editar.setEnabled(item.isLibre());
				restaurante.setEnabled(item.isLibre());
			} else {
				estadoBorrar.setText("no existe ese item");
			}
		} catch (NumberFormatException e) {
			estadoBorrar.setText("no existe ese item");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			estadoBorrar.setText("no existe ese item");
			e.printStackTrace();
		}
		
	}
	
	private void EditarActionPerformed(ActionEvent evt) {
		System.out.println("Editar.actionPerformed, event="+evt);
		//TODO add your code for Editar.actionPerformed
		item.setCantidad(Integer.parseInt(jTextField1.getText()));
		item.setLibre(libre.isSelected());
		item.setTiempoPorUnidadEnHoras(Float.parseFloat(txtTiempo.getText()));
		item.setRestaurante(restaurante.getSelectedIndex() + 1);
		
		try {
			if(Cliente.getInstancia().GuardarItemPlan(item)){
				estadoBorrar.setText("Se ha modificado el item");
			} else{
				estadoBorrar.setText("No se pudo editar el item");
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			estadoBorrar.setText("No se pudo editar el item");
		}
		
	}
	
	private void btnBorrarActionPerformed(ActionEvent evt) {
		System.out.println("btnBorrar.actionPerformed, event="+evt);
		//TODO add your code for btnBorrar.actionPerformed
		try {
			if(Cliente.getInstancia().BorrarItemPlanDeProduccion(item)){
				estadoBorrar.setText("se ha borrado el item");
			} else{
				estadoBorrar.setText("se se puede borrar el item");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
