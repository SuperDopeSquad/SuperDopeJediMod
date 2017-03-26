package superdopesquad.superdopejedimod.teleporter;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class TeleporterData {

	
	private BlockPos _blockPosHere = null;
	private EnumFacing _enumFacingHere = null;
	private BlockPos _blockPosThere = null;
	private EnumFacing _enumFacingThere = null;
	private TeleporterSide _teleporterSide = null;
	private TeleporterEntity _entity = null;
	
	
	public TeleporterData() {
		
	}
	
	
	public TeleporterEntity getEntity() {
		
		return this._entity;
	}
	
	
	public void setEntity(TeleporterEntity entity) {
		
		this._entity = entity;
	}
	
//	public TeleporterData(BlockPos blockPosHere, EnumFacing enumFacingHere, BlockPos blockPosThere, EnumFacing enumFacingThere) {
//		
//		//this._blockPosHere = blockPosHere;
////		this._enumFacingHere = enumFacingHere;
////		this._blockPosThere = blockPosThere;
////		this._enumFacingThere = enumFacingThere;
//		this.setHere(blockPosHere, enumFacingHere);
//		this.setThere(blockPosThere, enumFacingThere);
//	}
	
	
	public void setHere(BlockPos blockPosHere, EnumFacing enumFacingHere) {
		
		this._blockPosHere = blockPosHere;
		this._enumFacingHere = enumFacingHere;
	}
	
	
	public BlockPos getBlockPosHere() {
		
		return this._blockPosHere;
	}

	
	public BlockPos getBlockPosThere() {
		
		return this._blockPosThere;
	}

	
	public EnumFacing getFacingThere() {
		
		return this._enumFacingThere;
	}

	
	public EnumFacing getFacingHere() {
		
		return this._enumFacingHere;
	}
	
	
	public void setThere(BlockPos blockPosThere, EnumFacing enumFacingThere) {
		
		String oldString;
		String newString;
		if (this._blockPosThere == null) {
			oldString = "NULL";
		}
		else {
			oldString = this._blockPosThere.toString();
		}
		if (blockPosThere == null) {
			newString = "NULL";
		}
		else {
			newString = blockPosThere.toString();
		}
		
		System.out.println("setThere: old: " + oldString + " new:" + newString);
		
		this._blockPosThere = blockPosThere;
		this._enumFacingThere = enumFacingThere;
	}
	
	
	public void setSide(TeleporterSide teleporterSide) {
		
		this._teleporterSide = teleporterSide;
	}
	
	
	public TeleporterSide getSide() {
		
		return this._teleporterSide;
	}
	
	
	public BlockPos getTeleportDestination() {
		
		if (this._blockPosThere == null) {
			
			return null;
		}
		
		if (this._enumFacingThere == EnumFacing.NORTH) {
			return this._blockPosThere.north(1);
		}
		else if (this._enumFacingThere == EnumFacing.SOUTH) {
			return this._blockPosThere.south(1);
		}
		else if (this._enumFacingThere == EnumFacing.EAST) {
			return this._blockPosThere.east(1);
		}
		else if (this._enumFacingThere == EnumFacing.WEST) {
			return this._blockPosThere.west(1);
		}
		
		System.out.println("DEBUG: weird value detected in TeleporterData.getDestination()");
		return this._blockPosThere;
	}
}
