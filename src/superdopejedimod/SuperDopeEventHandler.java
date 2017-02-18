package superdopesquad.superdopejedimod;


import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import superdopesquad.superdopejedimod.faction.FactionCapabilityProvider;
import superdopesquad.superdopejedimod.faction.FactionCapability;
import superdopesquad.superdopejedimod.faction.FactionCapabilityInterface;


public class SuperDopeEventHandler {


	@SubscribeEvent
	public void onPlayerLogsIn(PlayerLoggedInEvent event) {

		EntityPlayer player = event.player;
		String factionName = SuperDopeJediMod.factionManager.getPlayerFactionName(player);
	 
		String message = "Welcome back.  You are a member of the " + factionName + " faction.";
		player.addChatMessage(new TextComponentString(message));
	}
	

	// Any custom properties that we add to player through the capabilities system does not survive
	// upon player's death.  So, they need to be manually copied over.
	@SubscribeEvent
	public void clonePlayer(PlayerEvent.Clone event) {
		
		if (event.isWasDeath()) {
			
			EntityPlayer originalPlayer = event.getOriginal();
			EntityPlayer newPlayer = event.getEntityPlayer();
			
			// Copy faction information to the new player object being clone from the original.
			String originalFaction = SuperDopeJediMod.factionManager.getPlayerFactionName(originalPlayer);
			SuperDopeJediMod.factionManager.setPlayerFactionByName(newPlayer, originalFaction);
		}
	}
	
	
	// This event is required to add any capabilities to players as they are created.
	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent<Entity> event) {

		if (event.getObject() instanceof EntityPlayer) {
			 
			// Attaching the faction capability to EntityPlayer.
			//System.out.println("DEBUG: Attaching the Faction capability to EntityPlayer.");
			ResourceLocation factionCapabilityId = new ResourceLocation(SuperDopeJediMod.MODID, "factionCapability");
			event.addCapability(factionCapabilityId, new FactionCapabilityProvider());
		}
	}
}