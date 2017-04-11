package superdopesquad.superdopejedimod.teleporter;


import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;


public class TeleporterCapabilityStorage implements IStorage<TeleporterCapabilityInterface> {


	@Override
	public NBTBase writeNBT(Capability<TeleporterCapabilityInterface> capability, TeleporterCapabilityInterface instance, EnumFacing side) {

		return new NBTTagLong(instance.getTeleporterDestination().toLong());
	}


	@Override
	public void readNBT(Capability<TeleporterCapabilityInterface> capability, TeleporterCapabilityInterface instance, EnumFacing side, NBTBase nbt) {

		// Did we accumulate unexpected crud in there?  Deal with it!  Error handling is your friend.
		if (nbt.getClass() != NBTTagLong.class) {
			System.out.println("Bad value found via TeleporterCapabilityStorage:readNBT");
			instance.setTeleporterDestination((BlockPos) BlockPos.NULL_VECTOR);
			return;
		}
			
		long value = ((NBTTagLong) nbt).getLong();
		BlockPos blockPos = BlockPos.fromLong(value);
		
		instance.setTeleporterDestination(blockPos);
	}
}
