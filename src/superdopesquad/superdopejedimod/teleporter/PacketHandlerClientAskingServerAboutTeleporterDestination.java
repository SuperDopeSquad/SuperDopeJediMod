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
			
			//System.out.println("RECEIVED PacketClientAskingServerAboutTeleporterDestination");

			EntityPlayerMP player = ctx.getServerHandler().player;
			WorldServer server = (WorldServer) ctx.getServerHandler().player.getServerWorld();
			
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
			
			// Let's turn right back around and tell the client what it wants to know.
			PacketServerTellingClientAboutTeleporterInfo packet = new PacketServerTellingClientAboutTeleporterInfo((EntityPlayer) player, teleporterEntityId, blockPos);
			SuperDopeJediMod.packetManager.INSTANCE.sendTo(packet, player);
			
		}
		catch (Exception exception) {
			System.out.println("ERROR: unpacking PacketClientAskingServerAboutTeleporterDestination: " + exception.getMessage());
		}
		
		return null;
	}
}