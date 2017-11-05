package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controlador.ControladorAC;
import dto.DTO_Local;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class DetalleLocal extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager
					.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JLabel jLabel5;
	private JScrollPane jScrollPane2;
	private JButton salir;
	private JTable disponibilidad;
	private List<DTO_Local> locales;
	private Object[][] objetosDis;
	private TableModel disponibilidadModel;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DetalleLocal inst = new DetalleLocal();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public DetalleLocal() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			getContentPane().setBackground(new java.awt.Color(128,128,255));
			this.setSize(534, 301);

		{
			jLabel5 = new JLabel();
			getContentPane().add(jLabel5);
			jLabel5.setText("Disponibilidad en los Locales");
			jLabel5.setBounds(25, 11, 483, 38);
			jLabel5.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			jLabel5.setFont(new java.awt.Font("Tahoma",1,18));
		}
		{
			jScrollPane2 = new JScrollPane();
			getContentPane().add(jScrollPane2);
			jScrollPane2.setBounds(25, 66, 483, 141);
			jScrollPane2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			{
				disponibilidadModel = new DefaultTableModel(
						null, new String[] { "Local", "Hs Disponibles", "Ocupacion" });
				disponibilidad = new JTable();
				jScrollPane2.setViewportView(disponibilidad);
				disponibilidad.setModel(disponibilidadModel);
				disponibilidad.setBounds(299, 40, 483, 100);
				
				//Este es el list que contiene todas las sucursales
				locales = ControladorAC.getInstancia().listarSucursales();
				
				//Se calculan las horas disponibles para produccion y el nivel de ocupacion
				locales = ControladorAC.getInstancia().calcularOcupacionSucursales(locales);
				//Agrego las tareas en estado "Activa" a la tabla
				objetosDis = new Object[locales.size()][disponibilidad.getColumnCount()];
				
				int j = 0;
				int i = 0;
				
				for(DTO_Local s: locales){
	
					objetosDis[j][i] = s.getNombre();
					objetosDis[j][i+1] = s.getHsDisponiblesProduccion();
					objetosDis[j][i+2] = (int)(((s.getHsProduccion() - s.getHsDisponiblesProduccion())/s.getHsProduccion()) * 100) + "%";
							
					
					j++;
				}
				disponibilidadModel = new DefaultTableModel(
						objetosDis, new String[] {"Local", "Hs Disponibles", "Ocupacion"}){
					
				};
				disponibilidad.setModel(disponibilidadModel);
			}
		}
		{
			salir = new JButton();
			getContentPane().add(salir);
			salir.setText("Salir");
			salir.setBounds(422, 218, 86, 27);
			salir.setFont(new java.awt.Font("Tahoma",1,12));
			salir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					dispose();
				}
			});
		}
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

}
