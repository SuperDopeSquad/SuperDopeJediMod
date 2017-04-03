package superdopesquad.superdopejedimod.teleporter;


import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import superdopesquad.superdopejedimod.SuperDopeJediMod;

//The params of the IMessageHandler are <REQ, REPLY>
//This means that the first param is the packet you are receiving, and the second is the packet you are returning.
//The returned packet can be used as a "response" from a sent packet.


public class PacketHandlerTeleporterSetDestination implements IMessageHandler<PacketTeleporterSetDestination, IMessage> {

	
	@Override 
	public IMessage onMessage(PacketTeleporterSetDestination message, MessageContext ctx) {
 			
		try {
						
			//UUID teleporterEntityUuid = message.getTeleporterEntityUuid();
			//int teleporterEntityId = message.getTeleporterEntityId();
			BlockPos blockPos = message.getBlockPos();		
			//Entity entity = Minecraft.getMinecraft().theWorld.getEntityByID(teleporterEntityId);
			//Entity entity = Minecraft.getMinecraft().theWorld.getPlayerEntityByUUID(playerId);			
				
			EntityPlayer entityPlayer = Minecraft.getMinecraft().thePlayer;	
			World world = Minecraft.getMinecraft().theWorld;
		     	
			// Check to make sure this blockPos is clear.
			if (!(world.isAirBlock(blockPos))) {
				entityPlayer.addChatMessage(new TextComponentString("Your destination is blocked!  Teleportation suspended.")); 
			    return null;
			}
				
			// Move the current player.
			entityPlayer.setLocationAndAngles(blockPos.getX(), blockPos.getY(), blockPos.getZ(), entityPlayer.rotationYaw, entityPlayer.rotationPitch);
		}
		
		catch (Exception exception) {
			System.out.println("ERROR: unpacking PacketPlayerSetClass: " + exception.getMessage());
		}
		
		return null;
	}
}