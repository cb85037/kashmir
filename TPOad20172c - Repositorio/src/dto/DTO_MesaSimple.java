package dto;

import java.util.List;

import dto.DTO_Mesa;


public class DTO_MesaSimple extends DTO_Mesa{
	
	private static final long serialVersionUID = 1L;
		
	private List<DTO_MesaSimple> vecinos;
	
	
	public DTO_MesaSimple() {
		super();
		
	}

	public List<DTO_MesaSimple> getVecinos() {
		return vecinos;
	}

	public void setVecinos(List<DTO_MesaSimple> vecinos) {
		this.vecinos = vecinos;
	}

	
	
}
