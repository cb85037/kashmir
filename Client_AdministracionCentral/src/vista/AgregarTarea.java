package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import controlador.ControladorAC;
import dto.DTO_Empleado;
import dto.DTO_Local;
import dto.DTO_Producto;



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
@SuppressWarnings("serial")
public class AgregarTarea extends javax.swing.JFrame {
        private JLabel jLabel1;
        private JLabel jLabel3;
        private JLabel jLabel4;
        private JComboBox local;
        private JTextField estado;
        private JButton cancelar;
        private JButton aceptar;
        private JLabel jLabel5;
        private JLabel jLabel7;
        private JTextField cantidad;
        private JLabel jLabel6;
        private JTextField horas;
        private JComboBox productos;
        private List<DTO_Local> localL;
        private List<DTO_Producto> productosL;

        /**
         * Auto-generated main method to display this JFrame
         */
        public static void main(String[] args) {
                SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                                AgregarTarea inst = new AgregarTarea(null);
                                inst.setLocationRelativeTo(null);
                                inst.setVisible(true);
                        }
                });
        }

        public AgregarTarea(DTO_Empleado e2) {
                super();
                initGUI(e2);
        }

        private void initGUI(final DTO_Empleado e2) {
                try {
                        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        this.setLocationRelativeTo(null);
                        getContentPane().setLayout(null);
                        getContentPane().setBackground(new java.awt.Color(128,128,255));
                        {
                                jLabel1 = new JLabel();
                                getContentPane().add(jLabel1);
                                jLabel1.setText("Alta Tarea");
                                jLabel1.setBounds(203, 12, 82, 16);
                                jLabel1.setFont(new java.awt.Font("Tahoma",1,12));
                        }
                        {
                                jLabel3 = new JLabel();
                                getContentPane().add(jLabel3);
                                jLabel3.setText("Producto");
                                jLabel3.setBounds(59, 106, 66, 16);
                                jLabel3.setFont(new java.awt.Font("Segoe UI",1,12));
                        }
                        {
                                
                                productos = new JComboBox<String>();
                                getContentPane().add(productos);
                                productosL = ControladorAC.getInstancia().listarProductosElabNoVenta();
                                productos.setBounds(177, 103, 207, 23);
                                for(DTO_Producto p: productosL){
                                        productos.addItem(p.getNombre());
                                }
                        }
                        {
                                jLabel4 = new JLabel();
                                getContentPane().add(jLabel4);
                                jLabel4.setText("Local");
                                jLabel4.setBounds(59, 71, 56, 16);
                                jLabel4.setFont(new java.awt.Font("Segoe UI",1,12));
                        }
                        {
                        
                                local = new JComboBox<String>();
                                getContentPane().add(local);
                                localL = ControladorAC.getInstancia().listarSucursales();
                                local.setBounds(177, 68, 207, 23);
                                for(DTO_Local s: localL){
                                        local.addItem(s.getNombre());
                                }
                        }
                        {
                                jLabel5 = new JLabel();
                                getContentPane().add(jLabel5);
                                jLabel5.setText("Horas necesarias");
                                jLabel5.setBounds(59, 184, 113, 16);
                                jLabel5.setFont(new java.awt.Font("Segoe UI",1,12));
                        }
                        {
                                horas = new JTextField();
                                getContentPane().add(horas);
                                horas.setBounds(177, 181, 207, 23);
                        }
                        {
                                jLabel6 = new JLabel();
                                getContentPane().add(jLabel6);
                                jLabel6.setText("Cantidad");
                                jLabel6.setBounds(59, 141, 60, 16);
                                jLabel6.setFont(new java.awt.Font("Segoe UI",1,12));
                        }
                        {
                                cantidad = new JTextField();
                                getContentPane().add(cantidad);
                                cantidad.setBounds(177, 138, 207, 23);
                        }
                        {
                                jLabel7 = new JLabel();
                                getContentPane().add(jLabel7);
                                jLabel7.setText("Estado");
                                jLabel7.setBounds(59, 228, 56, 16);
                                jLabel7.setFont(new java.awt.Font("Segoe UI",1,12));
                        }
                        {
                                aceptar = new JButton();
                                getContentPane().add(aceptar);
                                aceptar.setText("Aceptar");
                                aceptar.setBounds(275, 304, 97, 23);
                                aceptar.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent evt) {
                                                DTO_Local suc = null;
                                                DTO_Producto prod = null;
                                                
                                                for(DTO_Local s : localL){
                                                        if(s.getNombre().equalsIgnoreCase((String)local.getSelectedItem())){
                                                                suc = s;
                                                        }
                                                }
                                                for(DTO_Producto p : productosL){
                                                        if(p.getNombre().equalsIgnoreCase((String)productos.getSelectedItem())){
                                                                prod = p;
                                                        }
                                                }
                                                
                                                boolean resp = ControladorAC.getInstancia().altaTarea(e2,suc,prod,Integer.parseInt(cantidad.getText()),estado.getText(),Integer.parseInt(horas.getText()));
                                                
                                                if(resp == true){
                                                        JOptionPane.showMessageDialog(null, "Se dio de alta una tarea para la sucursal " + suc.getNombre());
                                                }
                                                
                                                dispose();
                                        }
                                });
                        }
                        {
                                cancelar = new JButton();
                                getContentPane().add(cancelar);
                                cancelar.setText("Cancelar");
                                cancelar.setBounds(388, 304, 91, 23);
                                cancelar.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent evt) {
                                                dispose();
                                        }
                                });
                        }
                        {
                                estado = new JTextField();
                                getContentPane().add(estado);
                                estado.setText("Activa");
                                estado.setEditable(false);
                                estado.setBounds(177, 225, 207, 23);
                        }
                        pack();
                        this.setSize(515, 386);
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

}