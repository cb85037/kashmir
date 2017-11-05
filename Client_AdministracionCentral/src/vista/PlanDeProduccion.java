package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXDatePicker;

import controlador.ControladorAC;
import dto.DTO_Empleado;
import dto.DTO_Local;
import dto.DTO_Tarea;

import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.SwingUtilities;

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
public class PlanDeProduccion extends javax.swing.JFrame {

	private JLabel jLabel1;
	private JLabel jLabel2;
	private JScrollPane jScrollPane1;
	private JLabel jLabel5;
	private JButton detalleLocal;
	private JScrollPane jScrollPane2;
	private JTable disponibilidad;
	private JLabel jLabel4;
	private JButton salir;
	private JButton actualizar;
	private JButton agregarTarea;
	private JTable tareas;
	private JTextField numero;
	private List<DTO_Tarea> tareasT;
	private List<DTO_Local> sucursales;
	private TableModel tareasModel;
	private Object[][] objetos;
	private Object[][] objetosDis;
	private TableModel disponibilidadModel;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				PlanDeProduccion inst = new PlanDeProduccion(null);
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public PlanDeProduccion(DTO_Empleado e2) {
		super();
		initGUI(e2);
	}

	private void initGUI(final DTO_Empleado e2) {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			getContentPane().setBackground(new java.awt.Color(128,128,255));
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Plan Produccion Semi Elaborados");
				jLabel1.setBounds(236, 12, 316, 43);
				jLabel1.setFont(new java.awt.Font("Calibri", 0, 22));
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("ID Plan");
				jLabel2.setBounds(597, 23, 72, 16);
			}
			{
				numero = new JTextField();
				getContentPane().add(numero);
				numero.setBounds(661, 20, 68, 23);
				String num = String.valueOf(ControladorAC.getInstancia().getNumeroPlanProduccion());
				numero.setText(num);
			}
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1);
				jScrollPane1.setBounds(29, 86, 700, 191);
				{
					//Es la list que tiene todas las tareas
					tareasT = ControladorAC.getInstancia().listarTareasActivas();
					
					tareas = new JTable();
					tareasModel = new DefaultTableModel(
							objetos, new String[] {
							"Local", "Tarea", "Producto","Cantidad", "Demora", "Estado"}){
					};
					tareas.setModel(tareasModel);
					jScrollPane1.setViewportView(tareas);
					tareas.setBounds(39, 180, 329, 184);
					tareas.setPreferredSize(new java.awt.Dimension(661, 171));
					tareas.setEditingColumn(0);
					tareas.setEditingRow(0);
					
					//Agrego las tareas en estado "Activa" a la tabla
					objetos= new Object[tareasT.size()][tareas.getColumnCount()];
					int j = 0;
					int i = 0;
					for(DTO_Tarea t: tareasT){
		
								objetos[j][i] = t.getSucursal().getNombre();
								objetos[j][i+1] = t.getNumero(); 
								objetos[j][i+2] = t.getProducto().getNombre();
								objetos[j][i+3] = t.getCantidad();
								objetos[j][i+4] = t.getTiempoRequerido();
								objetos[j][i+5] = t.getEstado();
		
						
						j++;
					}
					tareasModel = new DefaultTableModel(
							objetos, new String[] {
									"Local", "Tarea", "Producto","Cantidad", "Demora", "Estado"}){
						
					};
					tareas.setModel(tareasModel);
				}
			}
			{
				agregarTarea = new JButton();
				getContentPane().add(agregarTarea);
				agregarTarea.setText("Agregar Tarea");
				agregarTarea.setBounds(354, 289, 123, 35);
				agregarTarea.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						AgregarTarea a = new AgregarTarea(e2);
						a.setVisible(true);
					}
				});
			}
			{
				actualizar = new JButton();
				getContentPane().add(actualizar);
				actualizar.setBounds(482, 290, 122, 33);
				actualizar.setText("Actualizar");
				actualizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						tareasT = ControladorAC.getInstancia().listarTareasActivas();
						
						tareas = new JTable();
						tareasModel = new DefaultTableModel(
								objetos, new String[] {
										"Local", "Tarea", "Producto","Cantidad", "Demora", "Estado"}){
						};
						tareas.setModel(tareasModel);
						jScrollPane1.setViewportView(tareas);
						tareas.setBounds(39, 180, 329, 184);
						tareas.setPreferredSize(new java.awt.Dimension(661, 171));
						tareas.setEditingColumn(0);
						tareas.setEditingRow(0);
						
						//Agrego las tareas en estado "Activa" a la tabla
						objetos= new Object[tareasT.size()][tareas.getColumnCount()];
						int j = 0;
						int i = 0;
						for(DTO_Tarea t: tareasT){
			
									objetos[j][i] = t.getNumero();
									objetos[j][i+1] = t.getProducto().getNombre(); 
									objetos[j][i+2] = t.getCantidad();
									objetos[j][i+3] = t.getSucursal().getNombre();
									objetos[j][i+4] = t.getTiempoRequerido();
									objetos[j][i+5] = t.getEstado();
							
							j++;
						}
						tareasModel = new DefaultTableModel(
								objetos, new String[] {
										"Local", "Tarea", "Producto","Cantidad", "Demora", "Estado"}){
							
						};
						tareas.setModel(tareasModel);
						
						disponibilidadModel = new DefaultTableModel(
								null, new String[] { "Sucursal", "Hs Disponibles", "Ocupacion" });
						disponibilidad = new JTable();
						jScrollPane2.setViewportView(disponibilidad);
						disponibilidad.setModel(disponibilidadModel);
						disponibilidad.setBounds(299, 40, 483, 100);
						
						//Este es el list que contiene todas las sucursales
						sucursales = ControladorAC.getInstancia().listarSucursales();
						
						//Se calculan las horas disponibles para produccion y el nivel de ocupacion
						sucursales = ControladorAC.getInstancia().calcularOcupacionSucursales(sucursales);
						//Agrego las tareas en estado "Activa" a la tabla
						objetosDis = new Object[sucursales.size()][disponibilidad.getColumnCount()];
						
						int jj = 0;
						int ii= 0;
						
						for(DTO_Local s: sucursales){
			
							objetosDis[jj][ii] = s.getNombre();
							objetosDis[jj][ii+1] = s.getHsDisponiblesProduccion();
							objetosDis[jj][ii+2] = (int)(((s.getHsProduccion() - s.getHsDisponiblesProduccion())/s.getHsProduccion()) * 100) + "%";
									
							
							jj++;
						}
						disponibilidadModel = new DefaultTableModel(
								objetosDis, new String[] {"Local", "Hs Disponibles", "Ocupacion"}){
							
						};
						disponibilidad.setModel(disponibilidadModel);
					}
				});
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4);
				jLabel4.setText("Tareas");
				jLabel4.setBounds(29, 58, 105, 22);
				jLabel4.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				jLabel4.setFont(new java.awt.Font("Segoe UI",1,12));
			}
			{
				salir = new JButton();
				getContentPane().add(salir);
				salir.setText("Salir");
				salir.setBounds(609, 291, 120, 31);
				salir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						dispose();
					}
				});
			}
			{
				detalleLocal = new JButton();
				getContentPane().add(detalleLocal);
				detalleLocal.setText("Detalle Local");
				detalleLocal.setBounds(229, 291, 120, 32);
				detalleLocal.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						DetalleLocal a = new DetalleLocal();
						a.setVisible(true);
					}
				});
			}
			

			pack();
			this.setSize(757, 374);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
