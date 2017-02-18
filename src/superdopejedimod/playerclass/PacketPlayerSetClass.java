package superdopesquad.superdopejedimod.playerclass;


import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;


public class PacketPlayerSetClass implements IMessage {


	private int _playerId;
	private int _classId;
		  

	// A default constructor is always required
	public PacketPlayerSetClass() {}

	  
	public PacketPlayerSetClass(EntityPlayer player, int classId) {
	    
			this._playerId = player.getEntityId();
			this._classId = classId;
	}

	
	public int getPlayerId() {
		return this._playerId;
	}
	
	
	public int getClassId() {
		return this._classId;
	}
	
	
	 @Override
	 public void fromBytes(ByteBuf buffer) {
	 
		 this._playerId = buffer.readInt();
		 this._classId = buffer.readInt(); 
	 }

	 
	 @Override
	 public void toBytes(ByteBuf buffer) {
	
		 buffer.writeInt(this._playerId);
		 buffer.writeInt(this._classId);
	 }
}