package superdopesquad.superdopejedimod.faction;

import java.util.List;

public interface ClassAwareInterface {

	List<ClassInfo> GetFriendlyClasses();
	List<ClassInfo> GetUnfriendlyClasses();
	
	boolean IsUseFriendlyOnly();
	boolean IsUseUnfriendlyBanned();
}
