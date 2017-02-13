package superdopesquad.superdopejedimod.faction;


import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.CapabilityManager;


public class FactionManager {

	public static final Integer UNAFFILIATED = 0;
	public static final Integer JEDI = 1;
	public static final Integer SITH = 2;
	public static final Integer SMUGGLER = 3;
	public static final Integer BOUNTYHUNTER = 4;
	public static final String UNAFFILIATED_NAME = "Unaffiliated";
	public static final String JEDI_NAME = "Jedi";
	public static final String SITH_NAME = "Sith";
	public static final String SMUGGLER_NAME = "Smuggler";
	public static final String BOUNTYHUNTER_NAME = "Bounty Hunter";

	private HashMap _factionsMap = new HashMap();
	
	
	public FactionManager() {
				
		this._factionsMap.put(UNAFFILIATED, new FactionInfo(UNAFFILIATED, UNAFFILIATED_NAME, Color.blue));
		this._factionsMap.put(JEDI, new FactionInfo(JEDI, JEDI_NAME, Color.blue));
		this._factionsMap.put(SITH, new FactionInfo(SITH, SITH_NAME, Color.red));
		this._factionsMap.put(SMUGGLER, new FactionInfo(SMUGGLER, SMUGGLER_NAME, Color.green));
		this._factionsMap.put(BOUNTYHUNTER, new FactionInfo(BOUNTYHUNTER, BOUNTYHUNTER_NAME, Color.black));
	}
	
	
	public void preInit() {
		
		// Register the 'faction' capability.
		CapabilityManager.INSTANCE.register(FactionCapabilityInterface.class, new FactionCapabilityStorage(), FactionCapability.class);	
	}
	
	
	public FactionInfo getFactionInfo(Integer id) {
		
		Object factionInfo = this._factionsMap.get(id);
		
		if (factionInfo == null) {
			return null;
		}
		
		return (FactionInfo) factionInfo;
	}
	
	
	public FactionInfo getPlayerFaction(EntityPlayer player) {
		
		// We should have the Faction capability set on every player.
		// DEBUG CODE ONLY.  Let's verify that fact.
		boolean hasCapability = player.hasCapability(FactionCapabilityProvider.FactionCapability, null);
		assert(hasCapability);
				
		// Let's get the Faction capability that is set on every player.
		FactionCapabilityInterface factionCapability = player.getCapability(FactionCapabilityProvider.FactionCapability, null);
		assert(factionCapability != null);
		if (factionCapability == null) {
			System.out.println("Uh oh! Failed to find faction capability.");
			return (FactionInfo) this._factionsMap.get(UNAFFILIATED);
		}
		
		// Extract the name out of factionInfo, now that we have the proper id.
		Integer factionId = factionCapability.get();
		FactionInfo factionInfo = (FactionInfo) this._factionsMap.get(factionId);

		return factionInfo;
	}
	

	public String getPlayerFactionName(EntityPlayer player) {
		
//		// We should have the Faction capability set on every player.
//		// DEBUG CODE ONLY.  Let's verify that fact.
//		boolean hasCapability = player.hasCapability(FactionCapabilityProvider.FactionCapability, null);
//		assert(hasCapability);
//		//System.out.println("Inside getPlayerFaction: hasCapability == " + hasCapability);
//				
//		// Let's get the Faction capability that is set on every player.
//		FactionCapabilityInterface factionCapability = player.getCapability(FactionCapabilityProvider.FactionCapability, null);
//		assert(factionCapability != null);
//		if (factionCapability == null) {
//			System.out.println("Uh oh! Failed to find faction capability.");
//			return UNAFFILIATED_NAME;
//		}
		
		// Extract the name out of factionInfo, now that we have the proper id.
		//Integer factionId = factionCapability.get();
		FactionInfo factionInfo = this.getPlayerFaction(player);
		String factionName = factionInfo.getName();
		
//		try {
//			factionInfo = (FactionInfo) this._factionsMap.get(factionId);
//			factionName = factionInfo.getName();
//		}
//		catch (Exception e) {
//			System.out.println("Uh oh! Exception failure retriving faction information.");
//			factionName = UNAFFILIATED_NAME;
//		}
//		
//		if (factionName == null) {
//			System.out.println("Uh oh! Unknown failure retriving faction information.");
//			factionName = UNAFFILIATED_NAME;
//		}
		
		return factionName;
	}
	
	
public boolean setPlayerFactionById(EntityPlayer player, Integer inputFactionId) {
		
		// Let's get the Faction capability that is set on every player.
		FactionCapabilityInterface factionCapability = player.getCapability(FactionCapabilityProvider.FactionCapability, null);
		assert(factionCapability != null);
		if (factionCapability == null) {
			return false;
		}
		
		//int factionId = -1;
		
		return factionCapability.set(inputFactionId);
		
//		// Now, let's enumerate our dictionary map and find the faction that the user wants to join.
//		for (Object key : this._factionsMap.keySet()) {
//				
//			FactionInfo factionInfo = (FactionInfo) this._factionsMap.get(key);
//			String factionName = factionInfo.getName();
//			
//			//System.out.println(key.toString() + ":" + value);
//			if (factionName.equalsIgnoreCase(inputFactionName)) {
//				factionId = (Integer) key;
//				break;
//			}
//		}
//		
//		if (factionId != -1) {
//			return factionCapability.set(factionId);
//		}
//		
//		return false;
	}
	
	public boolean setPlayerFactionByName(EntityPlayer player, String inputFactionName) {
		
		// Let's get the Faction capability that is set on every player.
		FactionCapabilityInterface factionCapability = player.getCapability(FactionCapabilityProvider.FactionCapability, null);
		assert(factionCapability != null);
		if (factionCapability == null) {
			return false;
		}
		
		int factionId = -1;
		
		// Now, let's enumerate our dictionary map and find the faction that the user wants to join.
		for (Object key : this._factionsMap.keySet()) {
				
			FactionInfo factionInfo = (FactionInfo) this._factionsMap.get(key);
			String factionName = factionInfo.getName();
			
			//System.out.println(key.toString() + ":" + value);
			if (factionName.equalsIgnoreCase(inputFactionName)) {
				factionId = (Integer) key;
				break;
			}
		}
		
		if (factionId != -1) {
			return factionCapability.set(factionId);
		}
		
		return false;
	}
	
}
