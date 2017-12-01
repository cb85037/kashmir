package ClienteSwing.vistas;
import java.util.List;
import java.util.Vector;
import javax.swing.JScrollPane;

import javax.swing.JTable;

import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.SwingUtilities;

import repositorio.ItemPlanDeProduccionDTO;

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
public class VerEstadoTareas extends javax.swing.JFrame {
	private JTable jTable1;
	private JScrollPane jScrollPane1;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VerEstadoTareas inst = new VerEstadoTareas();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public VerEstadoTareas() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1);
				jScrollPane1.setBounds(0, 0, 470, 359);
				{
					
					Vector vectrColumnas = new Vector();
					vectrColumnas.add("ID");
					vectrColumnas.add("Nombre Semi");
					vectrColumnas.add("Cantidad");
					vectrColumnas.add("Tiempo Por Unidad");
					vectrColumnas.add("Estado");
					vectrColumnas.add("Restaurante");
					List<ItemPlanDeProduccionDTO> items = Cliente.getInstancia().GetPlanDeProduccion();
					Vector vectorOfVector = new Vector(items.size());
					for (ItemPlanDeProduccionDTO item : items) {
						Vector v = new Vector<Object>();
						v.add(item.getId());
						v.add(item.getSemielaborado().getNombre());
						v.add(item.getCantidad());
						v.add(item.getTiempoPorUnidadEnHoras());
						if(item.isLibre()){
							v.add("LIBRE");
						}else{
							v.add("PENDIENTE");
						}
						switch (item.getRestaurante()) {
						case 1:
							v.add("Belgrano");
							break;
						case 2:
							v.add("Caballito");
							break;
						default:
							v.add("Puerto Madero");
							break;
						}
						vectorOfVector.add(v);
					}
					
					//				TableModel detalleModel = 
							//				detalle = new JTable();
					//				jScrollPane1.setViewportView(detalle);
					//				detalle.setModel(detalleModel);
					//				detalle.setPreferredSize(new java.awt.Dimension(484, 138));
					TableModel jTable1Model = 
							new DefaultTableModel(vectorOfVector, vectrColumnas);
					jTable1 = new JTable();
					jScrollPane1.setViewportView(jTable1);
					jTable1.setModel(jTable1Model);
					jTable1.setModel(jTable1Model);
					
					jTable1.setBounds(0, 119, 366, 240);
					jTable1.setPreferredSize(new java.awt.Dimension(467, 355));
				}
			}
			pack();
			this.setSize(490, 398);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
