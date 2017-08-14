package superdopesquad.superdopejedimod.teleporter;


import net.minecraft.util.math.BlockPos;


public interface TeleporterCapabilityInterface {

	public boolean setTeleporterDestination(BlockPos blockPos);
	public BlockPos getTeleporterDestination();

}
