package superdopesquad.superdopejedimod.playerclass;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
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


public class PacketHandlerPlayerSetClass implements IMessageHandler<PacketPlayerSetClass, IMessage> {
// Do note that the default constructor is required, but implicitly defined in this case

	
	@Override 
	public IMessage onMessage(PacketPlayerSetClass message, MessageContext ctx) {
 		
		// MC-TODO: for some reason, when i log back in a second time, the event that i get doesn't have
		// theWorld set correctly (it's null), so i bail on it.  But, my local state is already set correctly.
		
		
		try {
			int playerId = message.getPlayerId();
			int classId = message.getClassId();
			//System.out.println("DEBUG: Message Contents: " + playerId + ", " + classId);
			//System.out.println("MINECRAFT:  " + (Minecraft.getMinecraft() != null));
			//System.out.println("WORLD: " + (Minecraft.getMinecraft().theWorld != null));
			
			Entity entity = Minecraft.getMinecraft().theWorld.getEntityByID(playerId);
			//System.out.println("ENTITY:" + (entity != null));
			//EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().theWorld.getEntityByID(playerId);
			EntityPlayer player = (EntityPlayer) entity;
			//System.out.println("PLAYER:" + (player != null));
			boolean setSuccess = SuperDopeJediMod.classManager.setPlayerClassByClientId(player, classId);

			//System.out.println(setSuccess);
		}
		catch (Exception exception) {
			System.out.println("ERROR: unpacking PacketPlayerSetClass: " + exception.getMessage());
		}
		
		return null;
	}
}