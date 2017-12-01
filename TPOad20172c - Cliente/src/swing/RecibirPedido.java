package ClienteSwing.vistas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextArea;
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
public class RecibirPedido extends javax.swing.JFrame {
	private JFileChooser jFileChooser1;
	private JLabel estado;
	private JTextArea jTextArea1;
	private JTextField txtOrden;
	private JLabel jLabel1;
	private JButton btnGuardar;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				RecibirPedido inst = new RecibirPedido();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public RecibirPedido() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jFileChooser1 = new JFileChooser();
				getContentPane().add(jFileChooser1);
				jFileChooser1.setBounds(34, 20, 318, 162);
				jFileChooser1.setControlButtonsAreShown(false);
			}
			{
				btnGuardar = new JButton();
				getContentPane().add(btnGuardar);
				btnGuardar.setText("Cargar Remito");
				btnGuardar.setBounds(64, 258, 288, 21);
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnGuardarActionPerformed(evt);
					}
				});
			}
			{
				estado = new JLabel();
				getContentPane().add(estado);
				estado.setBounds(41, 290, 327, 23);
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Numero de Orden");
				jLabel1.setBounds(29, 211, 143, 14);
			}
			{
				txtOrden = new JTextField();
				getContentPane().add(txtOrden);
				txtOrden.setBounds(199, 208, 159, 21);
			}
			{
				jTextArea1 = new JTextArea();
				getContentPane().add(jTextArea1);
				jTextArea1.setBounds(29, 430, 348, 57);
			}
			pack();
			this.setSize(431, 526);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void btnGuardarActionPerformed(ActionEvent evt) {
		System.out.println("btnGuardar.actionPerformed, event="+evt);
		//TODO add your code for btnGuardar.actionPerformed
		String path = jFileChooser1.getSelectedFile().getAbsolutePath();
		try {
			List<String> errors = Cliente.getInstancia().CargarRemito(path, txtOrden.getText());
			String err = new String();
			for (String string : errors) {
				err = err + string + "\n"; 
			}
			
			jTextArea1.setText(err);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
