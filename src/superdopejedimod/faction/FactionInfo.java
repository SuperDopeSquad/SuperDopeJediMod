package superdopesquad.superdopejedimod.faction;

import java.awt.Color;

public class FactionInfo {

	
	private Integer _id;
	private String _name;
	private Color _color;
	private String _shortName;
	
	
	public FactionInfo(Integer id, String name, Color color) {
		
		this(id, name, color, null);
	}

	
	public FactionInfo(Integer id, String name, Color color, String shortName) {
		
		this._id = id;
		this._name = name;
		this._color = color;
		this._shortName = shortName;
	}
	
	
	public String getName() {
		return this._name;
	}
	
	
	public String getShortName() {
		
		if (this._shortName == null) {
			return this._name;
		}
		
		return this._shortName;
	}
}
