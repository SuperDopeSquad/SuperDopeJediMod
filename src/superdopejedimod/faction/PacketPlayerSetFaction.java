package superdopesquad.superdopejedimod.faction;


import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;


public class PacketPlayerSetFaction implements IMessage {


	private int _playerId;
	private int _factionId;
		  

	// A default constructor is always required
	public PacketPlayerSetFaction() {}

	  
	public PacketPlayerSetFaction(EntityPlayer player, int factionId) {
	    
			this._playerId = player.getEntityId();
			this._factionId = factionId;
	}

	
	public int getPlayerId() {
		return this._playerId;
	}
	
	
	public int getFactionId() {
		return this._factionId;
	}
	
	
	 @Override
	 public void fromBytes(ByteBuf buffer) {
	 
		 this._playerId = buffer.readInt();
		 this._factionId = buffer.readInt(); 
	 }

	 
	 @Override
	 public void toBytes(ByteBuf buffer) {
	
		 buffer.writeInt(this._playerId);
		 buffer.writeInt(this._factionId);
	 }
}