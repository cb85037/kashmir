package dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.*;

public class DTO_Carta implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private int numero;
	private List<DTO_RubroCarta> rubros;
	private Date desde;
	private Date hasta;
	

	public DTO_Carta() {
		this.rubros = new ArrayList<DTO_RubroCarta>();
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public List<DTO_RubroCarta> getRubros() {
		return rubros;
	}

	public void setRubros(List<DTO_RubroCarta> rubros) {
		this.rubros = rubros;
	}

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}
	
	
	
}
