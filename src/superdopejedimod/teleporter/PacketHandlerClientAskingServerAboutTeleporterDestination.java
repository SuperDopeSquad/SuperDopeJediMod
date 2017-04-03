package superdopesquad.superdopejedimod.teleporter;


import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import superdopesquad.superdopejedimod.SuperDopeJediMod;


//The params of the IMessageHandler are <REQ, REPLY>
//This means that the first param is the packet you are receiving, and the second is the packet you are returning.
//The returned packet can be used as a "response" from a sent packet.


public class PacketHandlerClientAskingServerAboutTeleporterDestination implements IMessageHandler<PacketClientAskingServerAboutTeleporterDestination, IMessage> {

	
	@Override 
	public IMessage onMessage(PacketClientAskingServerAboutTeleporterDestination message, MessageContext ctx) {
 		
		// This message is received and processed on the server.  A client sends it when it wants to reset
		// it's own class/faction state for it's currently logged in player, but we also use this time to
		// update all other connected clients.  
		// Secondarily, we also have to send this new client the state information of all current players.
		
		try {
			
			System.out.println("RECEIVED PacketClientAskingServerAboutTeleporterDestination");

			EntityPlayerMP player = ctx.getServerHandler().playerEntity;
			WorldServer server = (WorldServer) ctx.getServerHandler().playerEntity.getServerWorld();
			
			// Get a handle to the teleporter entity, and it's info that we want to know about.
			int teleporterEntityId = message.getTeleporterEntityId();
			Entity entity = server.getEntityByID(teleporterEntityId);
			if (entity == null) {
				System.out.println("ERROR! UUID returns null entity in PacketClientAskingServerAboutTeleporterDestination.");
				return null;
			}
			if (!(entity instanceof TeleporterEntity)) {
				System.out.println("ERROR! Received bad UUID in PacketClientAskingServerAboutTeleporterDestination.");
				return null;
			}
			TeleporterEntity teleporterEntity = (TeleporterEntity)entity;
			BlockPos blockPos = teleporterEntity.getTeleporterDestination();
			//EnumFacing facing = teleporterEntity.getTeleportDirection();
			
			// BOOKMARK
			
			// Let's turn right back around and tell the client what it wants to know.
			PacketServerTellingClientAboutTeleporterInfo packet = new PacketServerTellingClientAboutTeleporterInfo(teleporterEntityId, blockPos);
			SuperDopeJediMod.packetManager.INSTANCE.sendTo(packet, player);
			
			
			// Let's get a handle to who the current player is.
			//EntityPlayerMP entityPlayerMP = ctx.getServerHandler().playerEntity;
			//ClassInfo classInfo = SuperDopeJediMod.classManager.getPlayerClass(entityPlayerMP);
			//System.out.println((classInfo != null));
			
//			// OK, let's respond to *all* clients and respond with the info that was requested.
//			//PacketPlayerSetClass packet = new PacketPlayerSetClass(entityPlayerMP, classInfo.getId());
//			//System.out.println("Sending PacketPlayerSetClass about me to everyone.");
//			SuperDopeJediMod.packetManager.INSTANCE.sendToAll(packet);
//			
//			// Next, let's go through all current players and send the new player their info.
//			WorldServer server = (WorldServer) ctx.getServerHandler().playerEntity.getServerWorld();
//			for (EntityPlayer otherPlayer : server.playerEntities) {
//				
//				EntityPlayerMP otherPlayerMP = (EntityPlayerMP) otherPlayer;
//				ClassInfo classInfoOtherPlayer = SuperDopeJediMod.classManager.getPlayerClass(otherPlayer);
//				PacketPlayerSetClass packetAboutOtherPlayer = new PacketPlayerSetClass(otherPlayer, classInfoOtherPlayer.getId());
//				//System.out.println("Sending PacketPlayerSetClass request to everyone about them..");
//				SuperDopeJediMod.packetManager.INSTANCE.sendTo(packetAboutOtherPlayer, otherPlayerMP);
//			}
			
		}
		catch (Exception exception) {
			System.out.println("ERROR: unpacking PacketClientAskingServerAboutTeleporterDestination: " + exception.getMessage());
		}
		
		return null;
	}
}