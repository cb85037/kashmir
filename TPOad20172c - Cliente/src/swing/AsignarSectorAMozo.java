package ClienteSwing.vistas;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import ClienteSwing.Cliente;


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
public class AsignarSectorAMozo extends javax.swing.JFrame {
	private JLabel title;
	private JComboBox restaurante;
	private JTextField sector;
	private JLabel estado;
	private JButton btnA;
	private JLabel moz;
	private JTextField mozo;
	private JLabel lblsec;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AsignarSectorAMozo inst = new AsignarSectorAMozo();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public AsignarSectorAMozo() {
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
				title.setText("Asignar Sector a Mozo");
				title.setBounds(62, 12, 287, 29);
			}
			{
				ComboBoxModel restauranteModel = 
						new DefaultComboBoxModel(
								new String[] { "Belgrano", "Caballito", "Puerto Madero" });
				restaurante = new JComboBox();
				getContentPane().add(restaurante);
				restaurante.setModel(restauranteModel);
				restaurante.setBounds(74, 47, 236, 21);
			}
			{
				sector = new JTextField();
				getContentPane().add(sector);
				sector.setBounds(163, 86, 175, 21);
			}
			{
				lblsec = new JLabel();
				getContentPane().add(lblsec);
				lblsec.setText("Sector");
				lblsec.setBounds(45, 89, 76, 18);
			}
			{
				mozo = new JTextField();
				getContentPane().add(mozo);
				mozo.setBounds(163, 137, 175, 21);
			}
			{
				moz = new JLabel();
				getContentPane().add(moz);
				moz.setText("Mozo");
				moz.setBounds(45, 138, 76, 18);
			}
			{
				btnA = new JButton();
				getContentPane().add(btnA);
				btnA.setText("Asignar");
				btnA.setBounds(143, 170, 139, 21);
				btnA.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnAActionPerformed(evt);
					}
				});
			}
			{
				estado = new JLabel();
				getContentPane().add(estado);
				estado.setBounds(12, 215, 360, 35);
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void btnAActionPerformed(ActionEvent evt) {
		System.out.println("btnA.actionPerformed, event="+evt);
		//TODO add your code for btnA.actionPerformed
		String nombre = this.mozo.getText();
		String sector = this.sector.getText();
		int restaurante = this.restaurante.getSelectedIndex() + 1;
		
		try {
			this.estado.setText(Cliente.getInstancia().AsignarMozo(nombre, sector, restaurante));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			estado.setText(e.getMessage());
			
		}
	}

}
