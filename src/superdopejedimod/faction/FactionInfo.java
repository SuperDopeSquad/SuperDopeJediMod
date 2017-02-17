package superdopesquad.superdopejedimod.faction;

import java.awt.Color;

public class FactionInfo {

	
	private Integer _id;
	private String _name;
	private Color _color;
	private String _shortName;
	private boolean _hasCape;
	
	
	public FactionInfo(Integer id, String name, Color color, boolean hasCape) {
		
		this(id, name, color, hasCape, null);
	}

	
	public FactionInfo(Integer id, String name, Color color, boolean hasCape, String shortName) {
		
		this._id = id;
		this._name = name;
		this._color = color;
		this._hasCape = hasCape;
		this._shortName = shortName;
	}

	
	public Integer getId() {
		return this._id;
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
	
	
	public String getCapeResource() {
		
		if (!(this._hasCape)) {
			return null;
		}
		
		return "textures/models/cape/" + this.getShortName().toLowerCase() + ".png";
	}
}
