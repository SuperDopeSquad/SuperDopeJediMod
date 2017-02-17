package superdopesquad.superdopejedimod.faction;


import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.CapabilityManager;
import superdopesquad.superdopejedimod.SuperDopeJediMod;


public class FactionManager {

	public static final Integer UNAFFILIATED = 0;
	public static final Integer JEDI = 1;
	public static final Integer SITH = 2;
	public static final Integer SMUGGLER = 3;
	public static final Integer BOUNTYHUNTER = 4;
	public static final Integer TEAMJUDE = 5;
	public static final String UNAFFILIATED_NAME = "Unaffiliated";
	public static final String JEDI_NAME = "Jedi";
	public static final String SITH_NAME = "Sith";
	public static final String SMUGGLER_NAME = "Smuggler";
	public static final String BOUNTYHUNTER_NAME = "Bounty Hunter";
	public static final String TEAMJUDE_NAME = "Team Jude";
	public static final String BOUNTYHUNTER_SHORTNAME = "bountyhunter";
	public static final String TEAMJUDE_SHORTNAME = "Jude";

	
	private HashMap _factionsMap = new HashMap();
	
	
	public FactionManager() {
				
		this._factionsMap.put(UNAFFILIATED, new FactionInfo(UNAFFILIATED, UNAFFILIATED_NAME, Color.blue, false));
		this._factionsMap.put(JEDI, new FactionInfo(JEDI, JEDI_NAME, Color.blue, true));
		this._factionsMap.put(SITH, new FactionInfo(SITH, SITH_NAME, Color.red, true));
		this._factionsMap.put(SMUGGLER, new FactionInfo(SMUGGLER, SMUGGLER_NAME, Color.green, true));
		this._factionsMap.put(BOUNTYHUNTER, new FactionInfo(BOUNTYHUNTER, BOUNTYHUNTER_NAME, Color.black, true, BOUNTYHUNTER_SHORTNAME));
		this._factionsMap.put(TEAMJUDE, new FactionInfo(TEAMJUDE, TEAMJUDE_NAME, Color.pink, true, TEAMJUDE_SHORTNAME));
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

		//System.out.println("getPlayerFaction: " + player.toString() + ", factionid:" + factionId.toString());
		
		return factionInfo;
	}
	

	public String getPlayerFactionName(EntityPlayer player) {
	
		FactionInfo factionInfo = this.getPlayerFaction(player);
		String factionName = factionInfo.getName();
		
		return factionName;
	}
	
	
	public boolean setPlayerFactionById(EntityPlayer player, Integer inputFactionId) {
		
		// Let's get the Faction capability that is set on every player.
		FactionCapabilityInterface factionCapability = player.getCapability(FactionCapabilityProvider.FactionCapability, null);
		assert(factionCapability != null);
		if (factionCapability == null) {
			return false;
		}
		
		// This sets it on the server.
		boolean setSuccess = factionCapability.set(inputFactionId);
		if (!setSuccess) {
			return false;
		}
		
		// Tell the client what is going on.
		PacketPlayerSetFaction message = new PacketPlayerSetFaction(player, inputFactionId);
		SuperDopeJediMod.packetHandler.INSTANCE.sendToAll(message);
		
		return true;
	}
	
	
	// MC-TO: Instead of having a separate function setPlayerFactionByclientId, i think i can just have a "isWorldRemote"
	// check in the setPlayerFactionById call, just to prevent recursive calling of the client message.
	public boolean setPlayerFactionByClientId(EntityPlayer player, Integer inputFactionId) {
		
		// Let's get the Faction capability that is set on every player.
		FactionCapabilityInterface factionCapability = player.getCapability(FactionCapabilityProvider.FactionCapability, null);
		assert(factionCapability != null);
		if (factionCapability == null) {
			return false;
		}
		
		// This sets it on the server.
		boolean setSuccess = factionCapability.set(inputFactionId);
		if (!setSuccess) {
			return false;
		}
		
		return true;
	}
	

	public boolean setPlayerFactionByName(EntityPlayer player, String inputFactionName) {
		
		int factionId = -1;
		
		// Now, let's enumerate our dictionary map and find the faction that the user wants to join.
		for (Object key : this._factionsMap.keySet()) {
				
			FactionInfo factionInfo = (FactionInfo) this._factionsMap.get(key);
			String factionName = factionInfo.getShortName();
			
			//System.out.println(key.toString() + ":" + value);
			if (factionName.equalsIgnoreCase(inputFactionName)) {
				factionId = (Integer) key;
				break;
			}
		}
		
		if (factionId == -1) {
			return false;
		}
		
		return this.setPlayerFactionById(player, factionId);
	}
}
