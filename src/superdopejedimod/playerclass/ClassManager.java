package superdopesquad.superdopejedimod.playerclass;


import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.CapabilityManager;
import superdopesquad.superdopejedimod.SuperDopeJediMod;


public class ClassManager {

	
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

	public static final Integer FACTION_REPUBLIC = 0;
	public static final Integer FACTION_EMPIRE = 1;
	public static final String FACTION_REPUBLIC_NAME = "The Republic";
	public static final String FACTION_EMPIRE_NAME = "The Empire";
	
	private HashMap _factionMap = new HashMap();
	private HashMap _classMap = new HashMap();
	
	
	public ClassManager() {
		
		// Define our factions.
		FactionInfo factionInfoRepublic = new FactionInfo(FACTION_REPUBLIC, FACTION_REPUBLIC_NAME);
		FactionInfo factionInfoEmpire = new FactionInfo(FACTION_EMPIRE, FACTION_EMPIRE_NAME);
		this._factionMap.put(FACTION_REPUBLIC, factionInfoRepublic);
		this._factionMap.put(FACTION_EMPIRE, factionInfoEmpire);
				
		// Define our classes.
		this._classMap.put(UNAFFILIATED, new ClassInfo(UNAFFILIATED, UNAFFILIATED_NAME, Color.blue));
		this._classMap.put(JEDI, new ClassInfo(JEDI, JEDI_NAME, Color.blue, true, factionInfoRepublic));
		this._classMap.put(SITH, new ClassInfo(SITH, SITH_NAME, Color.red, true, factionInfoEmpire));
		this._classMap.put(SMUGGLER, new ClassInfo(SMUGGLER, SMUGGLER_NAME, Color.green, true, factionInfoRepublic));
		this._classMap.put(BOUNTYHUNTER, new ClassInfo(BOUNTYHUNTER, BOUNTYHUNTER_NAME, Color.black, true, factionInfoEmpire, BOUNTYHUNTER_SHORTNAME));
		this._classMap.put(TEAMJUDE, new ClassInfo(TEAMJUDE, TEAMJUDE_NAME, Color.pink, true, null, TEAMJUDE_SHORTNAME));
	}
	
	
	public void preInit() {
		
		// Register the 'class' capability.
		CapabilityManager.INSTANCE.register(ClassCapabilityInterface.class, new ClassCapabilityStorage(), ClassCapability.class);	
	}
	
	
	public ArrayList<ClassInfo> getClasses() {
		
		ArrayList<ClassInfo> classes = new ArrayList<ClassInfo>();
		int index = 0;
		
		for (Object o : this._classMap.values()) {
			classes.add((ClassInfo) o);
		}
		
		return classes;
	}
	
	
	public ArrayList<FactionInfo> getFactions() {
		
		ArrayList<FactionInfo> factions = new ArrayList<FactionInfo>();
		
		for (Object o : this._factionMap.values()) {
			factions.add((FactionInfo) o);
		}
		
		return factions;
	}
	
	
	public FactionInfo getFactionInfo(Integer id) {
		
		Object factionInfo = this._factionMap.get(id);
		
		if (factionInfo == null) {
			return null;
		}
		
		return (FactionInfo) factionInfo;
	}
	
	
	public ClassInfo getClassInfo(Integer id) {
		
		Object classInfo = this._classMap.get(id);
		
		if (classInfo == null) {
			return null;
		}
		
		return (ClassInfo) classInfo;
	}
	
	
	public boolean isPlayerInFaction(EntityPlayer player, FactionInfo factionInfoInput) {
		
		ClassInfo classInfo = this.getPlayerClass(player);
		FactionInfo factionInfo = classInfo.getFaction();
		
		return (factionInfoInput.getId() == factionInfo.getId());
	}
	
	
	public ClassInfo getPlayerClass(EntityPlayer player) {
		
		// We should have the Faction capability set on every player.
		// DEBUG CODE ONLY.  Let's verify that fact.
		boolean hasCapability = player.hasCapability(ClassCapabilityProvider.ClassCapability, null);
		assert(hasCapability);
				
		// Let's get the Faction capability that is set on every player.
		ClassCapabilityInterface classCapability = player.getCapability(ClassCapabilityProvider.ClassCapability, null);
		assert(classCapability != null);
		if (classCapability == null) {
			System.out.println("Uh oh! Failed to find class capability.");
			return (ClassInfo) this._classMap.get(UNAFFILIATED);
		}
		
		// Extract the name out of factionInfo, now that we have the proper id.
		Integer classId = classCapability.get();
		ClassInfo classInfo = (ClassInfo) this._classMap.get(classId);

		//System.out.println("getPlayerFaction: " + player.toString() + ", factionid:" + factionId.toString());
		
		return classInfo;
	}
	

	public String getPlayerClassName(EntityPlayer player) {
	
		ClassInfo classInfo = this.getPlayerClass(player);
		String className = classInfo.getName();
		
		return className;
	}
	
	
	public String getPlayerClassLongDescription(EntityPlayer player) {
		
		// Let's refresh it.
    	ClassInfo classInfo = this.getPlayerClass(player);
    	
    	if (classInfo.getId() == this.UNAFFILIATED) {
    		return ("You are not affiliated with any class.");
    	}
    	
    	// return ("You are a member of the " + classInfo.getName() + " class.");
    	String message = "You are a member of the " + classInfo.getName() + " class.";
    	
    	FactionInfo factionInfo = classInfo.getFaction();
    	if (factionInfo != null) {
    		message += " You are affiliated with " + factionInfo.getName() + ".";
    	}
    	
    	return message;
	}
	
	
	public boolean setPlayerClassById(EntityPlayer player, Integer inputClassId) {
		
		// Let's get the Faction capability that is set on every player.
		ClassCapabilityInterface classCapability = player.getCapability(ClassCapabilityProvider.ClassCapability, null);
		assert(classCapability != null);
		if (classCapability == null) {
			return false;
		}
		
		// This sets it on the server.
		boolean setSuccess = classCapability.set(inputClassId);
		if (!setSuccess) {
			return false;
		}
		
		// Tell the client what is going on.
		PacketPlayerSetClass message = new PacketPlayerSetClass(player, inputClassId);
		SuperDopeJediMod.packetManager.INSTANCE.sendToAll(message);
		
		return true;
	}
	
	
	// MC-TO: Instead of having a separate function setPlayerFactionByclientId, i think i can just have a "isWorldRemote"
	// check in the setPlayerFactionById call, just to prevent recursive calling of the client message.
	public boolean setPlayerClassByClientId(EntityPlayer player, Integer inputClassId) {
		
		// Let's get the Faction capability that is set on every player.
		ClassCapabilityInterface classCapability = player.getCapability(ClassCapabilityProvider.ClassCapability, null);
		assert(classCapability != null);
		if (classCapability == null) {
			return false;
		}
		
		// This sets it on the client.
		boolean setSuccess = classCapability.set(inputClassId);
		if (!setSuccess) {
			return false;
		}
		
		return true;
	}
	

	public boolean setPlayerClassByName(EntityPlayer player, String inputClassName) {
		
		int classId = -1;
		
		// Now, let's enumerate our dictionary map and find the faction that the user wants to join.
		for (Object key : this._classMap.keySet()) {
				
			ClassInfo classInfo = (ClassInfo) this._classMap.get(key);
			String className = classInfo.getShortName();
			
			//System.out.println(key.toString() + ":" + value);
			if (className.equalsIgnoreCase(inputClassName)) {
				classId = (Integer) key;
				break;
			}
		}
		
		if (classId == -1) {
			return false;
		}
		
		return this.setPlayerClassById(player, classId);
	}
}
