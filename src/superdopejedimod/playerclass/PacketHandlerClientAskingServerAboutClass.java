
package superdopesquad.superdopejedimod.playerclass;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import superdopesquad.superdopejedimod.SuperDopeJediMod;

//The params of the IMessageHandler are <REQ, REPLY>
//This means that the first param is the packet you are receiving, and the second is the packet you are returning.
//The returned packet can be used as a "response" from a sent packet.


public class PacketHandlerClientAskingServerAboutClass implements IMessageHandler<PacketClientAskingServerAboutClass, IMessage> {
// Do note that the default constructor is required, but implicitly defined in this case

	
	@Override 
	public IMessage onMessage(PacketClientAskingServerAboutClass message, MessageContext ctx) {
 		
		// This message is received and processed on the server.  A client sends it when it wants to reset
		// it's own class/faction state for it's currently logged in player, but we also use this time to
		// update all other connected clients.  
		// Secondarily, we also have to send this new client the state information of all current players.
		
		try {
			
			//System.out.println("DEBUG: RECEIVED PacketClientAskingServerAboutClass");

			EntityPlayerMP entityPlayerMP = ctx.getServerHandler().playerEntity;
			//System.out.println("Serverside: " + entityPlayerMP.getUniqueID());
			
			// Let's get a handle to the EntityPlayer's ClassInfo which is being requested.
			//int playerId = message.getPlayerId();
			WorldServer server = (WorldServer) ctx.getServerHandler().playerEntity.getServerWorld();
			//System.out.println((server != null));
			//System.out.println((playerId));
			//Entity entity = server.getEntityByID(playerId);
			//Entity entity = server.getEntityFromUuid(playerId);
			//System.out.println((entity != null));
			
			//EntityPlayer player = (EntityPlayer) entity;
			//System.out.println((player != null));
			
			ClassInfo classInfo = SuperDopeJediMod.classManager.getPlayerClass(entityPlayerMP);
			//System.out.println((classInfo != null));
			
			
			// OK, let's respond to *all* clients and respond with the info that was requested.
			PacketPlayerSetClass packet = new PacketPlayerSetClass(entityPlayerMP, classInfo.getId());
			SuperDopeJediMod.packetManager.INSTANCE.sendToAll(packet);
			
			// Next, let's go through all current players and send the new player their info.
			for (EntityPlayer otherPlayer : server.playerEntities) {
				
				EntityPlayerMP otherPlayerMP = (EntityPlayerMP) otherPlayer;
				ClassInfo classInfoOtherPlayer = SuperDopeJediMod.classManager.getPlayerClass(otherPlayer);
				PacketPlayerSetClass packetAboutOtherPlayer = new PacketPlayerSetClass(otherPlayer, classInfoOtherPlayer.getId());
				SuperDopeJediMod.packetManager.INSTANCE.sendTo(packetAboutOtherPlayer, otherPlayerMP);
			}
			
			// Note: Minor inefficiency: through the two messages sent above, the current use will get a notification twice
			// about himself.  Not worthy of running down at the moment.
		}
		catch (Exception exception) {
			System.out.println("ERROR: unpacking PacketClientAskingServerAboutClass: " + exception.getMessage());
		}
		
		return null;
	}
}