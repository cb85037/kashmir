package ClienteSwing.vistas;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import javax.swing.JTable;

import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.SwingUtilities;

import repositorio.ItemAReponerDTO;
import repositorio.ItemPlanDeProduccionDTO;

import ClienteSwing.Cliente;
import Negocio.ItemAReponer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class GenerarListaReposicion extends javax.swing.JFrame {
	private JTable jTable1;
	private JScrollPane jScrollPane1;
	private JButton btnGenerarLista;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GenerarListaReposicion inst = new GenerarListaReposicion();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public GenerarListaReposicion() {
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
				jScrollPane1.setBounds(0, 55, 470, 304);
				{
					
					
				}
			}
			{
				btnGenerarLista = new JButton();
				getContentPane().add(btnGenerarLista);
				btnGenerarLista.setText("Generar Lista");
				btnGenerarLista.setBounds(120, 12, 238, 21);
				btnGenerarLista.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnGenerarListaActionPerformed(evt);
					}
				});
			}
			pack();
			this.setSize(490, 398);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void btnGenerarListaActionPerformed(ActionEvent evt) {
		System.out.println("btnGenerarLista.actionPerformed, event="+evt);
		//TODO add your code for btnGenerarLista.actionPerformed
		Vector vectrColumnas = new Vector();
		vectrColumnas.add("ID");
		vectrColumnas.add("Area");
		vectrColumnas.add("Cantidad");
		vectrColumnas.add("Producto");
		vectrColumnas.add("Restaurante");
		List<ItemAReponerDTO> items = new ArrayList<ItemAReponerDTO>();
		try {
			items = Cliente.getInstancia().GenerarListaDeReposicion();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Vector vectorOfVector = new Vector(items.size());
		for (ItemAReponerDTO item : items) {
			Vector v = new Vector<Object>();
			v.add(item.getId());
			v.add(item.getArea());
			v.add(item.getCantidad());
			v.add(item.getProducto().getNombre());
			
			switch (item.getRestaurante()) {
			case 1:
				v.add("Belgrano");
				break;
			case 2:
				v.add("Caballito");
				break;
			case 3:
				v.add("Puerto Madero");
				break;
			default:
				v.add("Deposito Central");
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
		
		//jTable1.setBounds(0, 119, 366, 240);
		//jTable1.setPreferredSize(new java.awt.Dimension(467, 355));
	}

}
