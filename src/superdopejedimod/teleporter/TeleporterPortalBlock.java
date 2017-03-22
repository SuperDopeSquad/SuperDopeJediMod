package superdopesquad.superdopejedimod.teleporter;


import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.BaseBlock;


public class TeleporterPortalBlock extends BaseBlock { // BasePortalBlock { //BaseBlock {
	
	
	private TeleporterData _teleporterData = null;
	
	
	public TeleporterPortalBlock(String name) {
		
		super(Material.IRON, name);
		//super("teleporterPortalBlock");
	}
	
	
	public void setTeleporterData(TeleporterData teleporterData) {
		
//		this._teleporterData = new TeleporterData();
//		this._teleporterData.setHere(teleporterData.getBlockPosHere(), teleporterData.getFacingHere());
//		this._teleporterData.setThere(teleporterData.getBlockPosThere(), teleporterData.getFacingThere());
//		this._teleporterData.setSide(teleporterData.getSide());
		this._teleporterData = teleporterData;
	}
	
	
//	public void setDestination(BlockPos blockPos, EnumFacing facing) {
//		
//		TeleporterData teleporterData = new TeleporterData();
//		
//		if (this._teleporterData != null) {
//			////System.out.println("ERROR! bad value in TeleporterPortalBlock:setDestination()");
//			//return;
//			//this._teleporterData = new TeleporterData();
//			teleporterData.setHere(teleporterData.getBlockPosHere(), teleporterData.getFacingHere());
//		}
//		
//		this._teleporterData = teleporterData;
//		this._teleporterData.setThere(blockPos, facing);
//	}
	
	
	public TeleporterData getTeleporterData() {
		
		return this._teleporterData;
	}
	
	
	//public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
    //    
	//	return null;
    //}
	
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
	AxisAlignedBB BUSH_AABB = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 0.6000000238418579D, 0.699999988079071D);

        return BUSH_AABB;
    }

	
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

//    /**
//     * Used to determine ambient occlusion and culling when rebuilding chunks for render
//     */
//    public boolean isOpaqueCube(IBlockState state)
//    {
//        return false;
//    }
//
//    public boolean isFullCube(IBlockState state)
//    {
//        return false;
//    }
	
	
	 /**
     * Called When an Entity Collided with the Block
     */
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
    	
    	// Bail on anyone not a player.
	   	if (!(entityIn instanceof EntityPlayerSP)) {
	   		return;
	   	}
	   	EntityPlayerSP entityPlayer = (EntityPlayerSP) entityIn;
	   	
	   	// Is this teleporter broken?
    	if (this._teleporterData == null) {
    		
    		entityPlayer.addChatMessage(new TextComponentString("This teleporter is broken.")); 
    	   	return;
    	}
    	
    	// Is this teleporter's destination not set up?
    	BlockPos there = this._teleporterData.getTeleportDestination();
    	if (there == null) {
       		
    		entityPlayer.addChatMessage(new TextComponentString("This teleporter's destination is not set up."));
    	   	return;    		
    	}
    	
    	// Debug info!
     	System.out.println("onEntityCollideWithBlock: moving from here: " + pos.toString() + " to there: " + there.toString());	
   	   	//System.out.println("onEntityCollideWithBlock: there: " + there.toString());
	   	//System.out.println("onEntityCollideWithBlock: entityIn: " + entityIn.toString());
	    	   	   	
   	   	// do the move.
   	   	entityIn.moveToBlockPosAndAngles(there, entityIn.rotationYaw, entityIn.rotationPitch);
    }
}
