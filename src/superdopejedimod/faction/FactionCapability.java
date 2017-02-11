package superdopesquad.superdopejedimod.faction;


public class FactionCapability implements FactionCapabilityInterface {


	private Integer _factionId = 0; // don't let this be null, pain and suffering will follow.

	
	public boolean set(Integer factionId) {
		
		this._factionId = factionId;
		return true;
	}


	public Integer get() {
		
		return this._factionId;
	}
}