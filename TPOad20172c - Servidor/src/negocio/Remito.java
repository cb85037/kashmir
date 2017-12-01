package Negocio;

import java.io.File;
import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dao.ProductoDAO;
import dao.ProveedorDAO;

@Entity
@Table(name="remitos")
public class Remito implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_remito")
	private int id;
	@ManyToOne
	@JoinColumn(name="id_provedor")
	private Provedor proveedor;
	private Date fecha;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_remito")
	private List<ItemRemito> items;
	
	@Transient
	private String ordenDeCompra;
	
	public String getOrdenDeCompra() {
		return ordenDeCompra;
	}

	public void setOrdenDeCompra(String ordenDeCompra) {
		this.ordenDeCompra = ordenDeCompra;
	}

	public Remito(Provedor proveedor, Date fecha, List<ItemRemito> items) {
		super();
		this.proveedor = proveedor;
		this.fecha = fecha;
		this.items = items;
	}

	public Remito() {
		// TODO Auto-generated constructor stub
	}

	public Provedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Provedor proveedor) {
		this.proveedor = proveedor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<ItemRemito> getItems() {
		return items;
	}

	public void setItems(List<ItemRemito> items) {
		this.items = items;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public void AddItemRemito(ItemRemito it){
		if(this.items == null){
			this.items = new ArrayList<ItemRemito>();
		}
		
		this.items.add(it);
	}
	
	public static Remito parseXMLOrdenPedido(File a){
		Remito r = null;
		try{
			ProductoDAO pDao = ProductoDAO.getProductoDAO();
			ProveedorDAO provDAO = ProveedorDAO.getProveedorDAO();
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(a);
			doc.getDocumentElement().normalize();
			
			List<String> errores = new ArrayList<String>();
			
			
			
			NodeList solicitudes = doc.getElementsByTagName("Remito");
			Node solicitud = solicitudes.item(0);
			if(solicitud.getNodeType() == Node.ELEMENT_NODE){
				Element elemento = (Element) solicitud;
				r = new Remito();
				String numero = elemento.getAttribute("numeroOrdenCompra");
				String fecha = getTagValue("Emision", elemento);
				int provId = Integer.parseInt(getTagValue("Id", elemento));
				Provedor provedor = provDAO.getById(provId);
				r.setOrdenDeCompra(numero);
				r.setProveedor(provedor);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			    java.util.Date parsed = format.parse(fecha);
				Date sql = new Date(parsed.getTime());
				r.setFecha(sql);
			}
			
			NodeList items = doc.getElementsByTagName("Item");
			for (int itera=0; itera < items.getLength() ; itera++){
				Node co = items.item(itera);
				if(co.getNodeType() == Node.ELEMENT_NODE){
					Element comp = (Element) co;
					String nombreProd = getTagValue("Nombre", comp);
					String calidad = getTagValue("Calidad", comp);
					String fechaV = getTagValue("FechaVencimiento", comp);
					String cant = getTagValue("Cantidad", comp);
					String nombreProdAsust = getTagValue("NombreASustitur", comp);
					boolean porFaltante = Boolean.parseBoolean(getTagValue("PorFaltante", comp));
					SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
					java.util.Date fechaDate1 = new java.util.Date();
					Date fechaSql = null;
					try{
						fechaDate1 = formatoFecha.parse(fechaV);
						if(fechaDate1 == null)
							errores.add("El formato de la fecha debe ser dd/mm/yyyy");
						else
							fechaSql = new Date(fechaDate1.getTime());
					}
					catch(Exception e){
						e.printStackTrace();
					}
					
					
					ItemRemito it = new ItemRemito();
					Producto p = pDao.GetByNombre(nombreProd);
					Producto pFaltante = pDao.GetByNombre(nombreProdAsust);
					it.setCalidad(calidad);
					it.setFechaVencimiento(fechaSql);
					it.setPorFaltante(porFaltante);
					it.setProd(p);
					it.setCantidad(Integer.parseInt(cant));
					it.setProdASustituir(pFaltante);
					r.AddItemRemito(it);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		} 
		
		return r;
	 }
	
	private static String getTagValue(String tag, Element elemento) {
		NodeList existeElemento = elemento.getElementsByTagName(tag);
		if(existeElemento!=null){
			Node tieneItem = existeElemento.item(0);
			if(tieneItem!=null){
				NodeList lista = elemento.getElementsByTagName(tag).item(0).getChildNodes();
				if(lista!=null){
					Node valor = (Node) lista.item(0);
					if(valor!=null)
						return valor.getNodeValue();
				}	
			}
		}
		return " ";
	}
	
}
