package vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controlador.ControladorAC;
import dto.DTO_Empleado;


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
public class Login extends javax.swing.JDialog {

	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel bienvenido;
	private JSeparator jSeparator2;
	private JButton aceptarButton;
	private JButton cancelButton;
	private JFormattedTextField passwordText;
	private JTextField usuarioText;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				
				Login inst = new Login();
				inst.setVisible(true);
			}
		});
	}
	
	public Login() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				{
					jLabel2 = new JLabel();
					getContentPane().add(jLabel2);
					jLabel2.setText("Usuario:");
					jLabel2.setBounds(19, 34, 82, 14);
					jLabel2.setFont(new java.awt.Font("Segoe UI",1,12));
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(jLabel3);
					jLabel3.setText("Contraseña:");
					jLabel3.setBounds(19, 80, 82, 14);
					jLabel3.setFont(new java.awt.Font("Segoe UI",1,12));
				}
				{
					usuarioText = new JTextField();
					getContentPane().add(usuarioText);
					usuarioText.setBounds(107, 31, 158, 21);
				}
				{
					passwordText = new JFormattedTextField();
					getContentPane().add(passwordText);
					passwordText.setBounds(107, 77, 158, 21);
				}
				{
					cancelButton = new JButton();
					getContentPane().add(cancelButton);
					cancelButton.setText("Cancelar");
					cancelButton.setBounds(147, 131, 118, 30);
					cancelButton.setFont(new java.awt.Font("Tahoma",1,11));
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.exit(0);
						}
					});
				}
				{
					aceptarButton = new JButton();
					getContentPane().add(aceptarButton);
					aceptarButton.setText("Aceptar");
					aceptarButton.setBounds(12, 130, 123, 32);
					aceptarButton.setFont(new java.awt.Font("Tahoma",1,11));
					aceptarButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							
							if (usuarioText.getText().equalsIgnoreCase("")){
								JOptionPane.showMessageDialog(null, "Debe Ingresar un usuario", "Login", JOptionPane.INFORMATION_MESSAGE);
							}else
								if (passwordText.getText().equalsIgnoreCase("")){
									JOptionPane.showMessageDialog(null, "Debe Ingresar una contraseña", "Login", JOptionPane.INFORMATION_MESSAGE);
								}else{
									
									DTO_Empleado e = ControladorAC.getInstancia().validarUsuarioAdmin(usuarioText.getText(),passwordText.getText());
									
									
									if (e != null){
										JOptionPane.showMessageDialog(null, "Bienvenido " + e.getNombre(), "Login", JOptionPane.INFORMATION_MESSAGE);
					
										MenuAdministracionCentral m = new MenuAdministracionCentral(e);
										m.setVisible(true);
										dispose();
									}else{
										JOptionPane.showMessageDialog(null, "Usuario y Contraseña Invalidos", "Login", JOptionPane.ERROR_MESSAGE);
									}
											
								}
						}
					});
				}
				{
					jSeparator2 = new JSeparator();
					getContentPane().add(jSeparator2);
					jSeparator2.setBounds(-4, 114, 529, 10);
				}
				{
					bienvenido = new JLabel();
					getContentPane().add(bienvenido);
					bienvenido.setText("Bienvenido al Restaurante!");
					bienvenido.setBounds(76, 3, 179, 16);
					bienvenido.setFont(new java.awt.Font("Segoe UI",1,12));
				}
			}
		
			
			this.setTitle("Login");
			this.setSize(318, 237);
			this.setLocationRelativeTo(null);
			getContentPane().setBackground(new java.awt.Color(128,128,255));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
