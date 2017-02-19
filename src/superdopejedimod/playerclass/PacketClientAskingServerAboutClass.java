package superdopesquad.superdopejedimod.playerclass;


import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;


public class PacketClientAskingServerAboutClass implements IMessage {

	//private UUID _playerId;
	//private int _playerId;
	//private int _classId;
		  

	// A default constructor is always required
	public PacketClientAskingServerAboutClass() {}

	  
	public PacketClientAskingServerAboutClass(EntityPlayer player) {
	    
			//this._playerId = player.getEntityId();
			//this._playerId = player.getUniqueID();
			//System.out.println("client side: " + (this._playerId.toString()));
			//System.out.println("client side UUID:" + player.getUniqueID().toString());
			//this._classId = classId;
	}

	
//	public UUID getPlayerId() {
//		//return this._playerId;
//	}
	
//	
//	public int getClassId() {
//		return this._classId;
//	}
	
	
	 @Override
	 public void fromBytes(ByteBuf buffer) {
	 
		 //this._playerId = buffer.readBytes(sizeof(UUID));
		//this._playerId = buffer.readInt();
		 //this._classId = buffer.readInt(); 
	 }

	 
	 @Override
	 public void toBytes(ByteBuf buffer) {
	
		// buffer.writeInt(this._playerId);
		//buffer.writeInt(this._classId);
	 }
}