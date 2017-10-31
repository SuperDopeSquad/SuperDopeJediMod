package superdopesquad.superdopejedimod.faction;

import java.util.List;

public interface FactionAwareInterface {

	List<FactionInfo> GetFriendlyFactions();
	List<FactionInfo> GetUnfriendlyFactions();
	
	boolean IsUseFriendlyOnly();
	boolean IsUseUnfriendlyBanned();
}
