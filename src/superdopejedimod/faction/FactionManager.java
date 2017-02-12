package superdopesquad.superdopejedimod.faction;


import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.CapabilityManager;


public class FactionManager {

	public static Integer UNAFFILIATED_ID = 0;
	public static Integer JEDI_ID = 1;
	public static Integer SITH_ID = 2;
	public static Integer SMUGGLER_ID = 3;
	public static Integer BOUNTYHUNTER_ID = 4;
	public static String UNAFFILIATED_NAME = "Unaffiliated";
	public static String JEDI_NAME = "Jedi";
	public static String SITH_NAME = "Sith";
	public static String SMUGGLER_NAME = "Smuggler";
	public static String BOUNTYHUNTER_NAME = "Bounty Hunter";

	private HashMap _factionsMap = new HashMap();
	
	
	public FactionManager() {
		
		//this._factionsMap = new HashMap();
		//this._factionsMap.put(0, UNAFFILIATED);
		//this._factionsMap.put(1, JEDI);
		//this._factionsMap.put(2, SITH);
		//this._factionsMap.put(3, SMUGGLER);
		//this._factionsMap.put(4, BOUNTYHUNTER);
		
		//this._factionsMap = new HashMap();
		this._factionsMap.put(UNAFFILIATED_ID, new FactionInfo(UNAFFILIATED_ID, UNAFFILIATED_NAME, Color.blue));
		this._factionsMap.put(JEDI_ID, new FactionInfo(JEDI_ID, JEDI_NAME, Color.blue));
		this._factionsMap.put(SITH_ID, new FactionInfo(SITH_ID, SITH_NAME, Color.red));
		this._factionsMap.put(SMUGGLER_ID, new FactionInfo(SMUGGLER_ID, SMUGGLER_NAME, Color.green));
		this._factionsMap.put(BOUNTYHUNTER_ID, new FactionInfo(BOUNTYHUNTER_ID, BOUNTYHUNTER_NAME, Color.black));
	}
	
	
	public void preInit() {
		
		// Register the 'faction' capability.
		CapabilityManager.INSTANCE.register(FactionCapabilityInterface.class, new FactionCapabilityStorage(), FactionCapability.class);	
	}
	
	
	public String getPlayerFactionName(EntityPlayer player) {
		
		// We should have the Faction capability set on every player.
		// DEBUG CODE ONLY.  Let's verify that fact.
		boolean hasCapability = player.hasCapability(FactionCapabilityProvider.FactionCapability, null);
		assert(hasCapability);
		//System.out.println("Inside getPlayerFaction: hasCapability == " + hasCapability);
				
		// Let's get the Faction capability that is set on every player.
		FactionCapabilityInterface factionCapability = player.getCapability(FactionCapabilityProvider.FactionCapability, null);
		assert(factionCapability != null);
		if (factionCapability == null) {
			System.out.println("Uh oh! Failed to find faction capability.");
			return UNAFFILIATED_NAME;
		}
		
		// Extract the name out of factionInfo, now that we have the proper id.
		Integer factionId = factionCapability.get();
		FactionInfo factionInfo;
		String factionName;
		
		try {
			factionInfo = (FactionInfo) this._factionsMap.get(factionId);
			factionName = factionInfo.getName();
		}
		catch (Exception e) {
			System.out.println("Uh oh! Exception failure retriving faction information.");
			factionName = UNAFFILIATED_NAME;
		}
		
		if (factionName == null) {
			System.out.println("Uh oh! Unknown failure retriving faction information.");
			factionName = UNAFFILIATED_NAME;
		}
		
		return factionName;
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
