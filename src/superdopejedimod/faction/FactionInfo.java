package superdopesquad.superdopejedimod.faction;

import java.awt.Color;

public class FactionInfo {

	
	private Integer _id;
	private String _name;
	private Color _color;
	private String _capeResource;
	private String _shortName;
	
	
	public FactionInfo(Integer id, String name, Color color, String capeResource) {
		
		this(id, name, color, capeResource, null);
	}

	
	public FactionInfo(Integer id, String name, Color color, String capeResource, String shortName) {
		
		this._id = id;
		this._name = name;
		this._color = color;
		this._capeResource = capeResource;
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
		
		if (this._capeResource == null) {
			return null;
		}
		
		return "textures/models/" + this._capeResource;
	}
}
