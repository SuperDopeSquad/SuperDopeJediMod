package superdopesquad.superdopejedimod.teleporter;


import net.minecraft.util.math.BlockPos;


public class TeleporterCapability implements TeleporterCapabilityInterface {


	private BlockPos _blockPos = TeleporterEntity.BLOCKPOS_NULL; // don't let this be null, pain and suffering will follow.


	@Override
	public boolean setTeleporterDestination(BlockPos blockPos) {

		this._blockPos = blockPos;
		
		return true;
	}


	@Override
	public BlockPos getTeleporterDestination() {
		
		return this._blockPos;
	}
}