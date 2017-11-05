package dto;

import java.util.List;

public class DTO_MesaCompuesta extends DTO_Mesa {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<DTO_MesaSimple> componentes;
	
	
	public DTO_MesaCompuesta() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<DTO_MesaSimple> getComponentes() {
		return componentes;
	}
	public void setComponentes(List<DTO_MesaSimple> componentes) {
		this.componentes = componentes;
	}

}
