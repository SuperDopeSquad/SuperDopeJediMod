package superdopesquad.superdopejedimod.teleporter;

import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
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
						
			UUID teleporterEntityUuid = message.getTeleporterEntityUuid();
			int teleporterEntityId = message.getTeleporterEntityId();
			BlockPos blockPos = message.getBlockPos();
			//System.out.println("DEBUG: RECEIVED PacketPlayerSetClass: " + playerId + ", " + classId);
			//System.out.println("MINECRAFT:  " + (Minecraft.getMinecraft() != null));
			//System.out.println("WORLD: " + (Minecraft.getMinecraft().theWorld != null));
			
			Entity entity = Minecraft.getMinecraft().theWorld.getEntityByID(teleporterEntityId);
			//Entity entity = Minecraft.getMinecraft().theWorld.getPlayerEntityByUUID(playerId);
			System.out.println("ENTITY:" + (entity != null));
			//EntityPlayer player = (EntityPlayer) entity;
			//System.out.println("PLAYER:" + (player != null));
			
			if (entity instanceof TeleporterEntity) {
				
				System.out.println("received teleporter command to move to " + blockPos.toString());
				
				
				EntityPlayer entityPlayer = Minecraft.getMinecraft().thePlayer;
				//boolean setSuccess = ((TeleporterEntity)entity).setTeleporterDestination(blockPos);
		     	
				
				//entityPlayer.moveToBlockPosAndAngles(blockPos, entityPlayer.rotationYaw, entityPlayer.rotationPitch);
				BlockPos blockPos2 = new BlockPos((blockPos.getX() + 5),blockPos.getY(),blockPos.getZ());
			    entityPlayer.setLocationAndAngles(blockPos2.getX(), blockPos2.getY(), blockPos2.getZ(), entityPlayer.rotationYaw, entityPlayer.rotationPitch);

				
				
				//System.out.println("set teleporter on client-side to " + blockPos.toString() + ": " + (setSuccess));
			}
			else {
				System.out.println("entity not instance of TeleporterEntity!");
			}
			
			
			//boolean setSuccess = SuperDopeJediMod.classManager.setPlayerClassByClientId(player, classId);
			//System.out.println(setSuccess);
		}
		catch (Exception exception) {
			System.out.println("ERROR: unpacking PacketPlayerSetClass: " + exception.getMessage());
		}
		
		return null;
	}
}