package superdopesquad.superdopejedimod.faction;


import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;


public class ClassCapabilityStorage implements IStorage<ClassCapabilityInterface> {


	@Override
	public NBTBase writeNBT(Capability<ClassCapabilityInterface> capability, ClassCapabilityInterface instance, EnumFacing side) {
	
		return new NBTTagInt(instance.get());
	}


	@Override
	public void readNBT(Capability<ClassCapabilityInterface> capability, ClassCapabilityInterface instance, EnumFacing side, NBTBase nbt) {
	
		// Did we accumulate unexpected crud in there?  Deal with it!  Error handling is your friend.
		if (nbt.getClass() != NBTTagInt.class) {
			System.out.println("Bad value found via ClassCapabilityStorage:readNBT");
			instance.set(0);
			return;
		}
	
		instance.set(((NBTTagInt) nbt).getInt());
	}
}