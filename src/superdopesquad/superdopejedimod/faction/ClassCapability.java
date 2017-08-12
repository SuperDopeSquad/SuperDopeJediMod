package superdopesquad.superdopejedimod.faction;


public class ClassCapability implements ClassCapabilityInterface {


	private Integer _classId = 0; // don't let this be null, pain and suffering will follow.

	
	public boolean set(Integer classId) {
		
		this._classId = classId;
		
		return true;
	}


	public Integer get() {
		
		return this._classId;
	}
}