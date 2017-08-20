package superdopesquad.superdopejedimod.faction;


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

	
	@Override 
	public IMessage onMessage(PacketClientAskingServerAboutClass message, MessageContext ctx) {
 		
		// This message is received and processed on the server.  A client sends it when it wants to reset
		// it's own class/faction state for it's currently logged in player, but we also use this time to
		// update all other connected clients.  
		// Secondarily, we also have to send this new client the state information of all current players.
		
		try {
			
			//System.out.println("RECEIVED PacketClientAskingServerAboutClass");

			// Let's get a handle to who the current player is, and what class they are.
			EntityPlayerMP entityPlayerMP = ctx.getServerHandler().player;
			ClassInfo classInfo = SuperDopeJediMod.classManager.getPlayerClass(entityPlayerMP);
			//System.out.println((classInfo != null));
			
			// OK, let's respond to *all* clients and respond with the info that was requested.
			PacketPlayerSetClass packet = new PacketPlayerSetClass(entityPlayerMP, classInfo.getId());
			//System.out.println("Sending PacketPlayerSetClass about me to everyone.");
			SuperDopeJediMod.packetManager.INSTANCE.sendToAll(packet);
			
			// Next, let's go through all current players and send the new player their info.
			WorldServer server = (WorldServer) entityPlayerMP.getServerWorld();
			for (EntityPlayer otherPlayer : server.playerEntities) {
				
				EntityPlayerMP otherPlayerMP = (EntityPlayerMP) otherPlayer;
				ClassInfo classInfoOtherPlayer = SuperDopeJediMod.classManager.getPlayerClass(otherPlayer);
				PacketPlayerSetClass packetAboutOtherPlayer = new PacketPlayerSetClass(otherPlayer, classInfoOtherPlayer.getId());
				//System.out.println("Sending PacketPlayerSetClass request to everyone about them..");
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