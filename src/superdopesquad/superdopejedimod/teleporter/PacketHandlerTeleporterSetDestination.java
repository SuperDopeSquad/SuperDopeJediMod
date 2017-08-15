package superdopesquad.superdopejedimod.teleporter;

import java.util.UUID;

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
			BlockPos blockPos = message.getBlockPos();					
			/*UUID playerId = message.getPlayerId();

			//System.out.println("DEBUG: RECEIVED PacketPlayerSetClass: " + playerId + ", " + classId);
			//System.out.println("MINECRAFT:  " + (Minecraft.getMinecraft() != null));
			//System.out.println("WORLD: " + (Minecraft.getMinecraft().theWorld != null));
			
			Entity entity = Minecraft.getMinecraft().world.getPlayerEntityByUUID(playerId);
			//System.out.println("ENTITY:" + (entity != null));
			EntityPlayer entityPlayer = (EntityPlayer) entity;	
			World world = entityPlayer.getEntityWorld();
			//EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().thePlayer;	
			//EntityPlayer entityPlayer = (EntityPlayer) entityPlayerSP;
			//World world = Minecraft.getMinecraft().theWorld;
		     	
			boolean success = TeleporterManager.teleportSomeoneSomewhere(world, blockPos, entityPlayer);
			*/
			return null; 
			
//			// Check to make sure this blockPos is clear.
//			if (blockPos == null) {
//				System.out.println("ERROR: Sent a null blockpos in a PacketTeleporterSetDestination.");
//				return null;		
//			}
//			
//			// We are cool with these type of blocks.
//			boolean isAirBlock = world.isAirBlock(blockPos);
//			boolean isPassableBlock = world.getBlockState(blockPos).getBlock().isPassable(world, blockPos);		
//			if (!(isAirBlock || isPassableBlock)) {
//				entityPlayer.addChatMessage(new TextComponentString("Your destination is blocked!  Teleportation suspended.")); 
//			    return null;
//			}
//			
//			// Move the current player.
//			double x = blockPos.getX() + 0.5;
//    		double y = blockPos.getY();
//    		double z = blockPos.getZ() + 0.5;
//     		entityPlayer.setLocationAndAngles(x, y, z, 0.0F, 0.0F);
		}
		
		catch (Exception exception) {
			System.out.println("ERROR: unpacking PacketPlayerSetClass: " + exception.getMessage());
		}
		
		return null;
	}
}