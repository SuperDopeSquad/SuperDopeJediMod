package superdopesquad.superdopejedimod;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCarpet;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;


/*
 * 		This block summons in a dome made of obsidian with a iron door ad some buttons. a work in progress. PEACE OUT HOMIES!
*/
public class OHUMBlock extends BaseBlock 
{
	/**
	 *  "Blocks" is the database of all blocks that are in the default Minecraft block collection. Here we pick out the one we want to use for
	 * our column of blocks. 
	 */
	protected static IBlockState DOME_BLOCK = Blocks.OBSIDIAN.getDefaultState();
	protected static IBlockState MATERIAL_AIR = Blocks.AIR.getDefaultState();
	protected static IBlockState MATERIAL_DOOR = Blocks.IRON_DOOR.getDefaultState();
	protected static IBlockState MATERIAL_BUTTON = Blocks.STONE_BUTTON.getDefaultState(); 
	
	/*
	 * This is the special constructor function that is called on game startup when the first "OHUMBlock" object is created.
	*/
	public OHUMBlock(String localName) {
		super(Material.IRON, localName);
		this.setLightLevel(1.0F);
	}
	
	
	/*
	 *
	 */
	@Override
	public void registerRecipe() {
		ItemStack dirtStack = new ItemStack(Blocks.DIRT);
		GameRegistry.addShapedRecipe(this.getRegistryName(), null, new ItemStack(this), "   ", " xx"," xx",
				'x', dirtStack);
	}
	

	/**
	 * 
	 */
	protected void buildDomeFort(World worldIn, BlockPos pos, EnumFacing side) {
		/* Build central dome & floor. */
		GeometryUtil.buildDomeDestructive(worldIn, pos, 7, DOME_BLOCK);
		GeometryUtil.buildCircleDestructive(worldIn, pos.down(), 7, DOME_BLOCK, true);
		
		/* Delete the original block that triggered building the hut, man. */
		worldIn.setBlockState(pos, MATERIAL_AIR);
		
		/* Clear the doorway. */
		BlockPos doorPos = pos.offset(side, 7);
		worldIn.setBlockState(doorPos, MATERIAL_AIR);
		worldIn.setBlockState(doorPos.up(), MATERIAL_AIR);
		
		/* Now the door itself. */
		worldIn.setBlockState(doorPos, MATERIAL_DOOR
				.withProperty(BlockDoor.FACING, side)
				.withProperty(BlockDoor.OPEN, Boolean.valueOf(false))
				.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER));
		worldIn.setBlockState(doorPos.up(), MATERIAL_DOOR
				.withProperty(BlockDoor.FACING, side)
				.withProperty(BlockDoor.OPEN, Boolean.valueOf(false))
				.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER));
		
		/* Add the buttons on either side of the door. */
		BlockPos buttonPos = doorPos.up().offset(side, 1).offset(side.rotateYCCW(), 1);
		worldIn.setBlockState(buttonPos, MATERIAL_BUTTON.withProperty(BlockDirectional.FACING, side));
		buttonPos = doorPos.up().offset(side.getOpposite(), 1).offset(side.rotateY(), 1);
		worldIn.setBlockState(buttonPos, MATERIAL_BUTTON.withProperty(BlockDirectional.FACING, side.getOpposite()));
		
		// TODO: rebuild the basement later.
		/* build the tunnel to the basement. 
		BlockPos tunnelPos = pos.down(1).north(5).west(4);
		worldIn.setBlockState(tunnelPos, MATERIAL_AIR);
		for (int i=0 ; i < 5 ; ++i) {
			tunnelPos = tunnelPos.down(1);
			worldIn.setBlockState(tunnelPos, MATERIAL_AIR);
		}
		
		////bUilD BaSEmENt!!!!!
		BlockPos basementPos = tunnelPos;
		GeometryUtil.clearPrism(worldIn, basementPos, 10, 4, 3, EnumFacing.SOUTH, EnumFacing.WEST); */
	}
	
	
	/**
	 * This function is automatically called by Minecraft whenever anybody right-mouse-clicks on a OHUM block.
     */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, 
									EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		/* Don't do anything if we are the client. 
		 * SuperDopeSquad: ignore this for now. Know that if you do any block-creation, then you need this at the beginning of your function. */
        if (worldIn.isRemote)
            return true;
        
        System.out.println("OHUMBlock: activated, remote=" + worldIn.isRemote + ", pos=" + pos);
        
        /* If we hit any of the sides (not top or bottom), then build the fort! */
        if ((side == EnumFacing.NORTH) ||
        	(side == EnumFacing.SOUTH) ||
        	(side == EnumFacing.WEST) ||
        	(side == EnumFacing.EAST)){
            buildDomeFort(worldIn, pos, side);
            return true;
        }
        
       /* Add other stuff here: like hitting a side, etc. */
        
        /* We are now done. We leave this function by calling "return", this time with "true", to tell minecraft that we actually did something. */
        return true;
    }
}