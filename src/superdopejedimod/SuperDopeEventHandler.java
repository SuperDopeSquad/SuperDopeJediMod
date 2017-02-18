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
import net.minecraft.entity.player.EntityPlayerMP;
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
import superdopesquad.superdopejedimod.entity.LayerClassIndicator;
import superdopesquad.superdopejedimod.playerclass.ClassCapability;
import superdopesquad.superdopejedimod.playerclass.ClassCapabilityInterface;
import superdopesquad.superdopejedimod.playerclass.ClassCapabilityProvider;
import superdopesquad.superdopejedimod.playerclass.ClassInfo;
import superdopesquad.superdopejedimod.playerclass.PacketPlayerSetClass;


public class SuperDopeEventHandler {


	@SubscribeEvent
	public void onPlayerLogsIn(PlayerLoggedInEvent event) {

		EntityPlayer player = event.player;
		ClassInfo classInfo = SuperDopeJediMod.classManager.getPlayerClass(player);
		//String className = classInfo.getName();
		
		//String message = "Welcome back.  You are a member of the " + className + " class.";
		String message = "Welcome back. " + SuperDopeJediMod.classManager.getPlayerClassLongDescription(player);
		player.addChatMessage(new TextComponentString(message));
		
		// Let's tell the client side of every current player what my class is.
		PacketPlayerSetClass packet = new PacketPlayerSetClass(player, classInfo.getId());
		SuperDopeJediMod.packetManager.INSTANCE.sendToAll(packet);
	
		// Let's tell the client side of this new player what the class of every other current player is.
		//Entity entity = event.getEntity();
		
		
		// If this is a player, tell them the class of all current players.
		//if (entity instanceof EntityPlayer ) {
			
			//EntityPlayer newPlayer = (EntityPlayer) entity;
		//Minecraft.getMinecraft()
			
//			for (EntityPlayer existingPlayer : event.g.playerEntities) {
//				
//				ClassInfo classInfo = SuperDopeJediMod.classManager.getPlayerClass(existingPlayer);
//				//String className = classInfo.getName();
//			 
//				//String message = "Welcome back.  You are a member of the " + className + " class.";
//				//player.addChatMessage(new TextComponentString(message));
//				
//				// Let's tell clients.
//				PacketPlayerSetClass packet = new PacketPlayerSetClass(existingPlayer, classInfo.getId());
//				SuperDopeJediMod.packetManager.INSTANCE.sendTo(packet, (EntityPlayerMP) newPlayer);
//			}
		//}
	}
	

	// Any custom properties that we add to player through the capabilities system does not survive
	// upon player's death.  So, they need to be manually copied over.
	@SubscribeEvent
	public void clonePlayer(PlayerEvent.Clone event) {
		
		if (event.isWasDeath()) {
			
			EntityPlayer originalPlayer = event.getOriginal();
			EntityPlayer newPlayer = event.getEntityPlayer();
			
			// Copy class information to the new player object being clone from the original.
			String originalClass = SuperDopeJediMod.classManager.getPlayerClassName(originalPlayer);
			SuperDopeJediMod.classManager.setPlayerClassByName(newPlayer, originalClass);
		}
	}
	
	
	// This event is required to add any capabilities to players as they are created.
	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent<Entity> event) {

		if (event.getObject() instanceof EntityPlayer) {
			 
			// Attaching the class capability to EntityPlayer.
			//System.out.println("DEBUG: Attaching the Class capability to EntityPlayer.");
			ResourceLocation classCapabilityId = new ResourceLocation(SuperDopeJediMod.MODID, "classCapability");
			event.addCapability(classCapabilityId, new ClassCapabilityProvider());
		}
	}

	
	@SubscribeEvent
	public void onEntityJoined(EntityJoinWorldEvent event)
	{
//		Entity entity = event.getEntity();
//		
//		// If this is a player, tell them the class of all current players.
//		if (entity instanceof EntityPlayer ) {
//			
//			EntityPlayer newPlayer = (EntityPlayer) entity;
//			
//			for (EntityPlayer existingPlayer : event.getWorld().playerEntities) {
//				
//				ClassInfo classInfo = SuperDopeJediMod.classManager.getPlayerClass(existingPlayer);
//				//String className = classInfo.getName();
//			 
//				//String message = "Welcome back.  You are a member of the " + className + " class.";
//				//player.addChatMessage(new TextComponentString(message));
//				
//				// Let's tell clients.
//				PacketPlayerSetClass packet = new PacketPlayerSetClass(existingPlayer, classInfo.getId());
//				SuperDopeJediMod.packetManager.INSTANCE.sendTo(packet, (EntityPlayerMP) newPlayer);
//			}
//		}
		
		//event.getWorld().playerEntities
		
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