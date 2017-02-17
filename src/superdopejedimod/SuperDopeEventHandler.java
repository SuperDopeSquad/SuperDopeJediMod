package superdopesquad.superdopejedimod;


import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerCape;
import net.minecraft.client.renderer.entity.layers.LayerDeadmau5Head;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import superdopesquad.superdopejedimod.faction.FactionCapabilityProvider;
import superdopesquad.superdopejedimod.faction.FactionInfo;
import superdopesquad.superdopejedimod.entity.LayerFactionIndicator;
import superdopesquad.superdopejedimod.faction.FactionCapability;
import superdopesquad.superdopejedimod.faction.FactionCapabilityInterface;


public class SuperDopeEventHandler {


	@SubscribeEvent
	public void onPlayerLogsIn(PlayerLoggedInEvent event) {

		EntityPlayer player = event.player;
		FactionInfo factionInfo = SuperDopeJediMod.factionManager.getPlayerFaction(player);
		String factionName = factionInfo.getName();
	 
		String message = "Welcome back.  You are a member of the " + factionName + " faction.";
		player.addChatMessage(new TextComponentString(message));
		
		// Let's tell clients.
		SuperDopePacketMessage packet = new SuperDopePacketMessage(player, factionInfo.getId());
		SuperDopeJediMod.packetHandler.INSTANCE.sendToAll(packet);
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

	
	@SubscribeEvent
	public void onEntityJoined(EntityJoinWorldEvent event)
	{
//		Entity entityIn = event.getEntity();
//		Class entityClass = entityIn.getClass();
//		Render<? extends Entity> render = Minecraft.getMinecraft().getRenderManager().entityRenderMap.get(entityClass);
//				
//		// Debug info.
//		//String info;
//		//if (render == null) {
//		//	info = "Render: NULL; " + entityIn.getName();
//		//}
//		//else {
//		//	info = "Render: " + render.toString() + ", Entity: " + entityIn.getName();;
//		//}
//		//System.out.println("DEBUG: onEntityJoined: " + info);
//		
//		// Try to add the LayerFactionIndicator if entityRender points to this being a creature.
//		if (false) {
//		if (render != null && render instanceof RenderLivingBase) {
//			
//			//System.out.println("DEBUG: onEntityJoined: about to add layer to " + info);
//			LayerRenderer layerRenderer = new LayerFactionIndicator((RenderLivingBase)render);
//			
//			try {
//				((RenderLivingBase)render).addLayer(layerRenderer);
//			}
//			catch (Exception e) {
//				System.out.println("DEBUG: onEntityJoined: failed to add LayerFactionIndicator on " + entityIn.getName() + ": " + e.getMessage());
//			}
//		}
//		}
	}
}