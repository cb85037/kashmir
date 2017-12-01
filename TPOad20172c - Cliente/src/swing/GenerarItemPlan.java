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
import Negocio.ItemPlanDeProduccion;

import repositorio.ItemPlanDeProduccionDTO;
import repositorio.PlatoDTO;


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
public class GenerarItemPlan extends javax.swing.JFrame {
	private JLabel title;
	private JTextField txtSemi;
	private JCheckBox pVez;
	private JLabel estado;
	private JButton btnAgregar;
	private JTextField txtTiempoPorUnidad;
	private JLabel txtTiempo;
	private JComboBox restaurante;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JTextField txtCantidad;
	private JButton txtBuscarSemi;
	private PlatoDTO semiElaborado;
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GenerarItemPlan inst = new GenerarItemPlan();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public GenerarItemPlan() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				title = new JLabel();
				getContentPane().add(title);
				title.setText("Agregar Item al Plan de Produccion");
				title.setBounds(19, 27, 405, 30);
				title.setFont(new java.awt.Font("Aparajita",1,22));
			}
			{
				txtSemi = new JTextField();
				getContentPane().add(txtSemi);
				txtSemi.setBounds(240, 69, 160, 23);
			}
			{
				txtBuscarSemi = new JButton();
				getContentPane().add(txtBuscarSemi);
				txtBuscarSemi.setText("Buscar Semielaborado");
				txtBuscarSemi.setBounds(6, 69, 204, 23);
				txtBuscarSemi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						txtBuscarSemiActionPerformed(evt);
					}
				});
			}
			{
				txtCantidad = new JTextField();
				getContentPane().add(txtCantidad);
				txtCantidad.setBounds(240, 114, 160, 23);
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Cantidad a Producir");
				jLabel1.setBounds(38, 117, 166, 16);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Restaurante encargado");
				jLabel2.setBounds(38, 187, 154, 40);
			}
			{
				ComboBoxModel restauranteModel = 
						new DefaultComboBoxModel(
								new String[] { "Belgrano", "Caballito", "Puerto Madero" });
				restaurante = new JComboBox();
				getContentPane().add(restaurante);
				restaurante.setModel(restauranteModel);
				restaurante.setBounds(235, 197, 165, 21);
			}
			{
				txtTiempo = new JLabel();
				getContentPane().add(txtTiempo);
				txtTiempo.setText("Tiempo por unidad");
				txtTiempo.setBounds(38, 160, 154, 16);
			}
			{
				txtTiempoPorUnidad = new JTextField();
				getContentPane().add(txtTiempoPorUnidad);
				txtTiempoPorUnidad.setBounds(240, 157, 160, 23);
			}
			{
				btnAgregar = new JButton();
				getContentPane().add(btnAgregar);
				btnAgregar.setText("Agregar Nuevo Item");
				btnAgregar.setBounds(94, 251, 276, 23);
				btnAgregar.setEnabled(false);
				btnAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnAgregarActionPerformed(evt);
					}
				});
			}
			{
				estado = new JLabel();
				getContentPane().add(estado);
				estado.setBounds(18, 313, 406, 29);
			}
			{
				pVez = new JCheckBox();
				getContentPane().add(pVez);
				pVez.setText("Primera Carga");
				pVez.setBounds(38, 226, 207, 18);
			}
			pack();
			this.setSize(462, 466);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void txtBuscarSemiActionPerformed(ActionEvent evt) {
		System.out.println("txtBuscarSemi.actionPerformed, event="+evt);
		//TODO add your code for txtBuscarSemi.actionPerformed
		try {
			semiElaborado = Cliente.getInstancia().GetSemielaboradoByNombre(txtSemi.getText());
			if(semiElaborado != null){
				txtSemi.setEnabled(false);
				btnAgregar.setEnabled(true);
			}
			else{
				estado.setText("no existe el semielaborado");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void btnAgregarActionPerformed(ActionEvent evt) {
		System.out.println("btnAgregar.actionPerformed, event="+evt);
		//TODO add your code for btnAgregar.actionPerformed
		ItemPlanDeProduccionDTO item = new ItemPlanDeProduccionDTO();
		item.setCantidad(Integer.parseInt(txtCantidad.getText()));
		item.setLibre(true);
		item.setRestaurante(restaurante.getSelectedIndex() + 1);
		item.setSemielaborado(semiElaborado);
		item.setTiempoPorUnidadEnHoras(Float.parseFloat(txtTiempoPorUnidad.getText()));
		try {
			estado.setText(Cliente.getInstancia().CrearItemPlanDeProd(item, pVez.isSelected())); 
			txtCantidad.setText("");
			semiElaborado = null;
			txtSemi.setEnabled(true);
			txtTiempoPorUnidad.setText("");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			estado.setText(e.getMessage());
		}
	}

}
