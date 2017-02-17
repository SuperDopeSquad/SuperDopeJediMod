package superdopesquad.superdopejedimod.faction;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import superdopesquad.superdopejedimod.SuperDopeJediMod;

//The params of the IMessageHandler are <REQ, REPLY>
//This means that the first param is the packet you are receiving, and the second is the packet you are returning.
//The returned packet can be used as a "response" from a sent packet.


public class PacketHandlerPlayerSetFaction implements IMessageHandler<PacketPlayerSetFaction, IMessage> {
// Do note that the default constructor is required, but implicitly defined in this case

	
	@Override 
	public IMessage onMessage(PacketPlayerSetFaction message, MessageContext ctx) {
 
		//System.out.println("DEBUG: Message Received: " + message.getPlayerId() + ", " + message.getFactionId());
		
		// The value that was sent
		int playerId = message.getPlayerId();
		int factionId = message.getFactionId();
		EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().theWorld.getEntityByID(playerId);
		boolean setSuccess = SuperDopeJediMod.factionManager.setPlayerFactionByClientId(player, factionId);
 
		System.out.println(setSuccess);
		
		return null;
	}
}