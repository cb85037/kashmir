package vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import dto.DTO_Empleado;

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
public class MenuAdministracionCentral extends javax.swing.JFrame {
	private JMenuBar jMenuBar1;
	private JMenuItem jMenuItem5;
	private JMenuItem jMenuItem4;
	private JMenuItem jMenuItem3;
	private JMenuItem jMenuItem1;
	private JMenu jMenu3;
	private JMenu jMenu2;
	private JMenu jMenu1;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MenuAdministracionCentral inst = new MenuAdministracionCentral(null);
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public MenuAdministracionCentral(DTO_Empleado e) {
		super();
		initGUI(e);
	}

	private void initGUI(final DTO_Empleado e2) {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setLocationRelativeTo(null);
			getContentPane().setLayout(null);
			getContentPane().setBackground(new java.awt.Color(128,128,255));

			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenu3 = new JMenu();
					jMenuBar1.add(jMenu3);
					jMenu3.setText("Plan Produccion Semi Elaborados");
					jMenu3.setBorder(BorderFactory.createTitledBorder(""));
					{
						jMenuItem3 = new JMenuItem();
						jMenu3.add(jMenuItem3);
						jMenuItem3.setText("Visualizar Plan");
						jMenuItem3.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								PlanDeProduccion p = new PlanDeProduccion(e2);
								p.setVisible(true);
							}
						});
					}
				}
				{
					jMenu2 = new JMenu();
					jMenuBar1.add(jMenu2);
					jMenu2.setText("Insumos");
					jMenu2.setBorder(BorderFactory.createTitledBorder(""));
					{
						jMenuItem1 = new JMenuItem();
						jMenu2.add(jMenuItem1);
						jMenuItem1.setText("Pedidos");
					}
					{
						jMenuItem4 = new JMenuItem();
						jMenu2.add(jMenuItem4);
						jMenuItem4.setText("Compra Insumos");
					}
					{
						jMenuItem5 = new JMenuItem();
						jMenu2.add(jMenuItem5);
						jMenuItem5.setText("Reposicion de Insumos");
					}
				}

			}
			pack();
			this.setSize(678, 364);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
