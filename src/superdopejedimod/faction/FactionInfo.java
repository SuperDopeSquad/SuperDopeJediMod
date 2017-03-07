package superdopesquad.superdopejedimod.faction;

import java.util.ArrayList;
import java.util.List;

public class FactionInfo {

	
	private Integer _id;
	private String _name;
	private ArrayList<ClassInfo> _classes;
	
	
	public FactionInfo(Integer id, String name) {
		
		this._id = id;
		this._name = name;
		this._classes = new ArrayList<ClassInfo>();
	}
	
	
	public Integer getId() {
		
		return this._id;
	}
	
	
	public String getName() {
		
		return this._name;
	}
	
	
	public void addClass(ClassInfo classInfo) {
		
		this._classes.add(classInfo);
	}
	
	
	public ArrayList<ClassInfo> getClasses() {
		
		return this._classes;
	}
}
