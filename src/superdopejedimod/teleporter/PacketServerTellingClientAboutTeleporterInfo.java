package superdopesquad.superdopejedimod.teleporter;


import java.util.UUID;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;


public class PacketServerTellingClientAboutTeleporterInfo implements IMessage {


	private int _teleporterEntityId;
	private BlockPos _blockPos;
	//private EnumFacing _facing;
		  

	// A default constructor is always required
	public PacketServerTellingClientAboutTeleporterInfo() {}

	  
	//public PacketServerTellingClientAboutTeleporterInfo(UUID teleporterEntityUuid, BlockPos blockPos, EnumFacing facing) {
	public PacketServerTellingClientAboutTeleporterInfo(int teleporterEntityId, BlockPos blockPos) {
		    
	    
		this._teleporterEntityId = teleporterEntityId;
		this._blockPos = blockPos;
		//this._facing = facing;
	}
	
	
	public BlockPos getBlockPos() {
		
		return this._blockPos;
	}
	
	
//	public EnumFacing getFacing() {
//		
//		return this._facing;
//	}
	
	
	public int getTeleporterEntityId() {
		
		return this._teleporterEntityId;
	}
	
	
	 @Override
	 public void fromBytes(ByteBuf buffer) {
		 
		 // Get the id.
		 //long mostsignificant  = buffer.readLong();
		 //long leastsignificant  = buffer.readLong();
		 //this._teleporterEntityUuid = new UUID(mostsignificant, leastsignificant);
		 this._teleporterEntityId = buffer.readInt();
		 
		 // Get the BlockPos.
		 int x = buffer.readInt();
		 int y = buffer.readInt();
		 int z = buffer.readInt();
		 this._blockPos = new BlockPos(x, y, z);
		 
		 // Get the EnumFacing;
		 //this._facing = EnumFacing.getFront(buffer.readInt());
	 }

	 
	 @Override
	 public void toBytes(ByteBuf buffer) {
	
		 // Set the id.
		// buffer.writeLong(this._teleporterEntityUuid.getMostSignificantBits());
		 //buffer.writeLong(this._teleporterEntityUuid.getLeastSignificantBits());
		 buffer.writeInt(this._teleporterEntityId);
		 
		 // Set the BlockPos.
		 buffer.writeInt(this._blockPos.getX());
		 buffer.writeInt(this._blockPos.getY());
		 buffer.writeInt(this._blockPos.getZ());
		 
		 // Set the EnumFacing;
		 //buffer.writeInt(this._facing.getIndex());
	 }
}
