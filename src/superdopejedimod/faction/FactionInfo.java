package superdopesquad.superdopejedimod.faction;

import java.awt.Color;

public class FactionInfo {

	
	private Integer _id;
	private String _name;
	private Color _color;
	
	
	public FactionInfo(Integer id, String name, Color color) {
		
		this._id = id;
		this._name = name;
		this._color = color;
	}
	
	
	public String getName() {
		return this._name;
	}
}
