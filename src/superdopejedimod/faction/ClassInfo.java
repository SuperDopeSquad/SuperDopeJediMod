package superdopesquad.superdopejedimod.faction;

import java.awt.Color;

public class ClassInfo {

	
	private Integer _id;
	private String _name;
	private Color _color;
	private boolean _hasCape;
	private FactionInfo _factionInfo;
	
	
	public ClassInfo(Integer id, String name, Color color) {
		
		this(id, name, color, false, null);
	}

	
	public ClassInfo(Integer id, String name, Color color, boolean hasCape, FactionInfo factionInfo) {
		
		this._id = id;
		this._name = name;
		this._color = color;
		this._hasCape = hasCape;
		this._factionInfo = factionInfo;
		
		// If we just set a faction, tickle that faction, since it stores a reverse list as well.
		if (factionInfo != null) {
			factionInfo.addClass(this);
		}
	}

	
	public Integer getId() {
		return this._id;
	}
	
	
	public String getName() {
		return this._name;
	}
	
	
	public String getDescription() {
		
		String message = this._name;
		
		FactionInfo factionInfo = this.getFaction();
		if (factionInfo != null) {
			message += " - affiliated with " + factionInfo.getName();
		}
		
		return message;
	}
	
	
	public String getFactionDescription() {
		
		FactionInfo factionInfo = this.getFaction();
		if (factionInfo == null) {
			return null;
		}
		
		return "affiliated with " + factionInfo.getName() + ".";	
	}
	
	
	public String getShortName() {
		
		return this.stripSpaces(this._name);
	}
	
	
	private String stripSpaces(String stringIn) {
		
		return stringIn.replace(" ", "").toLowerCase();
	}
	
	
	public String getCapeResource() {
		
		if (!(this._hasCape)) {
			return null;
		}
		
		return "textures/models/cape/" + this.getShortName().toLowerCase() + ".png";
	}
	
	
	public FactionInfo getFaction() {
		
		return this._factionInfo;
	}
}
