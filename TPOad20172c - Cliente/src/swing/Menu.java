package ClienteSwing.vistas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
public class Menu extends javax.swing.JFrame {
	private JMenuBar jMenuBar1;
	private JMenu AsignarMozoASector;
	private JMenu AdministrarCompras;
	private JMenuItem gList;
	private JMenuItem MProv;
	private JMenuItem asignar;
	private JMenuItem MPlato;
	private JMenu Provedor;
	private JMenuItem MantenerProd;
	private JMenuItem VerEstado;
	private JMenuItem ModificarTarea;
	private JMenuItem Generar;
	private JMenu AdministrarPlanDeProduccion;
	private JMenuItem RecibirCompras;
	private JMenuItem GenerarCompras;
	private JMenu Producto;
	private JMenu Plato;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Menu inst = new Menu();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
				inst.setSize(449, 238);
			}
		});
	}
	
	public Menu() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					AsignarMozoASector = new JMenu();
					jMenuBar1.add(AsignarMozoASector);
					AsignarMozoASector.setText("AsignarMozoASector");
					{
						asignar = new JMenuItem();
						AsignarMozoASector.add(asignar);
						asignar.setText("Asignar");
						asignar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								asignarActionPerformed(evt);
							}
						});
					}
					AsignarMozoASector.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							AsignarMozoASectorActionPerformed(evt);
						}
					});
				}
				{
					Plato = new JMenu();
					jMenuBar1.add(Plato);
					Plato.setText("Plato");
					{
						MPlato = new JMenuItem();
						Plato.add(MPlato);
						MPlato.setText("Mantener Plato");
						MPlato.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								MPlatoActionPerformed(evt);
							}
						});
					}
					Plato.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							PlatoActionPerformed(evt);
						}
					});
				}
				{
					Producto = new JMenu();
					jMenuBar1.add(Producto);
					Producto.setText("Producto");
					{
						MantenerProd = new JMenuItem();
						Producto.add(MantenerProd);
						MantenerProd.setText("Mantener Producto");
						MantenerProd.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								MantenerProdActionPerformed(evt);
							}
						});
					}
					Producto.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							ProductoActionPerformed(evt);
						}
					});
				}
				{
					AdministrarCompras = new JMenu();
					jMenuBar1.add(AdministrarCompras);
					AdministrarCompras.setText("AdministrarCompras");
					{
						GenerarCompras = new JMenuItem();
						AdministrarCompras.add(GenerarCompras);
						GenerarCompras.setText("GenerarCompras");
						GenerarCompras.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								GenerarComprasActionPerformed(evt);
							}
						});
					}
					{
						RecibirCompras = new JMenuItem();
						AdministrarCompras.add(RecibirCompras);
						RecibirCompras.setText("RecibirCompras");
						RecibirCompras.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								RecibirComprasActionPerformed(evt);
							}
						});
					}
					{
						gList = new JMenuItem();
						AdministrarCompras.add(gList);
						gList.setText("Generar Lista Reposicion");
						gList.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								gListActionPerformed(evt);
							}
						});
					}
				}
				{
					AdministrarPlanDeProduccion = new JMenu();
					jMenuBar1.add(AdministrarPlanDeProduccion);
					AdministrarPlanDeProduccion.setText("AdministrarPlanDeProduccion");
					{
						Generar = new JMenuItem();
						AdministrarPlanDeProduccion.add(Generar);
						Generar.setText("GenerarNuevaTarea");
						Generar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								GenerarActionPerformed(evt);
							}
						});
					}
					{
						ModificarTarea = new JMenuItem();
						AdministrarPlanDeProduccion.add(ModificarTarea);
						ModificarTarea.setText("ModificarTarea");
						ModificarTarea.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								ModificarTareaActionPerformed(evt);
							}
						});
					}
					{
						VerEstado = new JMenuItem();
						AdministrarPlanDeProduccion.add(VerEstado);
						VerEstado.setText("VerEstado");
						VerEstado.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								VerEstadoActionPerformed(evt);
							}
						});
					}
				}
				{
					Provedor = new JMenu();
					jMenuBar1.add(Provedor);
					Provedor.setText("Provedor");
					{
						MProv = new JMenuItem();
						Provedor.add(MProv);
						MProv.setText("Mantener Proveedor");
						MProv.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								MProvActionPerformed(evt);
							}
						});
					}
					Provedor.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							ProvedorActionPerformed(evt);
						}
					});
				}
			}
			pack();
			this.setSize(718, 264);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void AsignarMozoASectorActionPerformed(ActionEvent evt) {
		System.out.println("AsignarMozoASector.actionPerformed, event="+evt);
		//TODO add your code for AsignarMozoASector.actionPerformed
		AsignarSectorAMozo a = new AsignarSectorAMozo();
		a.setVisible(true);
	}
	
	
	private void ProvedorActionPerformed(ActionEvent evt) {
		System.out.println("Provedor.actionPerformed, event="+evt);
		//TODO add your code for Provedor.actionPerformed
		MantenerProveedor m = new MantenerProveedor();
		m.setVisible(true);
	}
	
	private void PlatoActionPerformed(ActionEvent evt) {
		System.out.println("Plato.actionPerformed, event="+evt);
		//TODO add your code for Plato.actionPerformed
		MantenerPlato m = new MantenerPlato();
		m.setVisible(true);
	}
	
	private void ProductoActionPerformed(ActionEvent evt) {
		System.out.println("Producto.actionPerformed, event="+evt);
		//TODO add your code for Producto.actionPerformed
		MantenerProducto m = new MantenerProducto();
		m.setVisible(true);
	}
	
	private void MantenerProdActionPerformed(ActionEvent evt) {
		System.out.println("MantenerProd.actionPerformed, event="+evt);
		//TODO add your code for MantenerProd.actionPerformed
		MantenerProducto m = new MantenerProducto();
		m.setVisible(true);
	}
	
	private void MPlatoActionPerformed(ActionEvent evt) {
		System.out.println("MPlato.actionPerformed, event="+evt);
		//TODO add your code for MPlato.actionPerformed
		MantenerPlato m = new MantenerPlato();
		m.setVisible(true);
	}
	
	private void asignarActionPerformed(ActionEvent evt) {
		System.out.println("asignar.actionPerformed, event="+evt);
		//TODO add your code for asignar.actionPerformed
		AsignarSectorAMozo a = new AsignarSectorAMozo();
		a.setVisible(true);
	}
	
	private void MProvActionPerformed(ActionEvent evt) {
		System.out.println("MProv.actionPerformed, event="+evt);
		//TODO add your code for MProv.actionPerformed
		MantenerProveedor m = new MantenerProveedor();
		m.setVisible(true);
	}
	
	private void GenerarActionPerformed(ActionEvent evt) {
		System.out.println("Generar.actionPerformed, event="+evt);
		//TODO add your code for Generar.actionPerformed
		GenerarItemPlan it = new GenerarItemPlan();
		it.setVisible(true);
	}
	
	private void ModificarTareaActionPerformed(ActionEvent evt) {
		System.out.println("ModificarTarea.actionPerformed, event="+evt);
		//TODO add your code for ModificarTarea.actionPerformed
		ModificarItemPlan it = new ModificarItemPlan();
		it.setVisible(true);
	}
	
	private void VerEstadoActionPerformed(ActionEvent evt) {
		System.out.println("VerEstado.actionPerformed, event="+evt);
		//TODO add your code for VerEstado.actionPerformed
		VerEstadoTareas t = new VerEstadoTareas();
		t.setVisible(true);
	}
	
	private void GenerarComprasActionPerformed(ActionEvent evt) {
		System.out.println("GenerarCompras.actionPerformed, event="+evt);
		//TODO add your code for GenerarCompras.actionPerformed
		GenerarOrdenDeCompra g = new GenerarOrdenDeCompra();
		g.setVisible(true);
	}
	
	private void gListActionPerformed(ActionEvent evt) {
		System.out.println("gList.actionPerformed, event="+evt);
		//TODO add your code for gList.actionPerformed
		GenerarListaReposicion gl = new GenerarListaReposicion();
		gl.setVisible(true);
	}
	
	private void RecibirComprasActionPerformed(ActionEvent evt) {
		System.out.println("RecibirCompras.actionPerformed, event="+evt);
		//TODO add your code for RecibirCompras.actionPerformed
		RecibirPedido r = new RecibirPedido();
		r.setVisible(true);
	}

}
