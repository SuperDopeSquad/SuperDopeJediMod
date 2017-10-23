package superdopesquad.superdopejedimod.hangar;


import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.CapabilityManager;
import superdopesquad.superdopejedimod.GeometryUtil;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.entity.EntityManager;
import superdopesquad.superdopejedimod.entity.droid.DroidKit;
import superdopesquad.superdopejedimod.entity.droid.RepublicBaseDroidEntity;
import superdopesquad.superdopejedimod.entity.ship.ShuttleEntity;
import superdopesquad.superdopejedimod.entity.ship.TieFighterEntity;
import superdopesquad.superdopejedimod.entity.ship.XWingFighterEntity;
import superdopesquad.superdopejedimod.faction.ClassCapability;
import superdopesquad.superdopejedimod.faction.ClassCapabilityInterface;
import superdopesquad.superdopejedimod.faction.ClassCapabilityProvider;
import superdopesquad.superdopejedimod.faction.ClassCapabilityStorage;
import superdopesquad.superdopejedimod.faction.PacketPlayerSetClass;


public class HangarManager {
	
	public static int HANGARPAD_DEFAULT_WIDTH = 15;

	public static HangarParts hangarParts = new HangarParts("hangarParts");
	public static HangarWrench hangarWrench = new HangarWrench("hangarWrench");

	public static HangarPadBlock hangarPadBlock = new HangarPadBlock("hangarPadBlock");
	public static HangarPadBlock imperialHangarPadBlock = new HangarPadBlock("imperialHangarPadBlock");
	public static HangarPadBlock republicHangarPadBlock = new HangarPadBlock("republicHangarPadBlock");

	public static HangarPadKit hangarPadKit = new HangarPadKit("hangarPadKit", HangarManager.hangarPadBlock, HangarManager.hangarParts);
	public static HangarPadKit imperialHangarPadKit = new HangarPadKit("imperialHangarPadKit", HangarManager.imperialHangarPadBlock, Items.BONE);
	public static HangarPadKit republicHangarPadKit = new HangarPadKit("republicHangarPadKit", HangarManager.republicHangarPadBlock, Items.FEATHER);
	
	public static ShipKit testShipKit = new ShipKit("testShipKit", EntityChicken.class, null);
	public static ShipKit shuttleKit = new ShipKit("shuttleKit", ShuttleEntity.class, Items.COAL);
	public static ShipKit tieFighterKit = new ShipKit("tieFighterKit", TieFighterEntity.class, Items.FLINT);
	public static ShipKit xWingFighterKit = new ShipKit("xWingFighterKit", XWingFighterEntity.class, Items.WHEAT);
	  
	
	public HangarManager() {}
	
	
	public static boolean createShip(EntityPlayer player, World world, BlockPos blockPos, EnumFacing facing) { //, ShipKit shipKit) {
				
		boolean isWorldServer = (!world.isRemote);
		IBlockState blockStateClicked = world.getBlockState(blockPos);
    	Block blockClicked = blockStateClicked.getBlock();
    	boolean isShipKit = (blockClicked instanceof ShipKit);
     
    	BlockPos blockPosShouldBeHangarPad = blockPos.offset(EnumFacing.DOWN);
    	IBlockState blockStateShouldBeHangarPad = world.getBlockState(blockPosShouldBeHangarPad);
    	Block blockClickedShouldBeHangarPad = blockStateShouldBeHangarPad.getBlock();
    	boolean onValidHangarPad = (blockClickedShouldBeHangarPad instanceof HangarPadBlock);
    	   	
    	// If we are on the server, and we are being held in the main hand, and this is actually a droidkit , ...
    	if ((isWorldServer) && (isShipKit)) {
    		
    		// Bail if we are not on a valid hangar pad.
    		if (!(onValidHangarPad)) {
    			
    			player.sendMessage(new TextComponentString("Put a ship kit on top of a valid hangar pad to create the desired ship.")); 	
    			return false;
    		}
        	
    		// Destroy the existing block.
    		world.setBlockToAir(blockPos);
    		
    		// Create the new entity.
    		Class classToMake = ((ShipKit) blockClicked).getEntityToMake();
    		EntityLiving entity = (EntityLiving) EntityManager.createEntity(classToMake, world, blockPos);

    		return true;
    	}
		
		return false;
	}
	 
	
	public static boolean createHangerPad(EntityPlayer player, World world, BlockPos blockPos, EnumFacing facing, HangarPadKit hangarPadKit) {
				
		// What is the perfect sized rectangle we want want to create?
		int diameter = hangarPadKit.getHangarDiameter();
		int radius = (hangarPadKit.getHangarDiameter() - 1) / 2;
		HangarPadBlock hangarPadBlock = hangarPadKit.getHangarPadBlock();
		
		BlockPos northWestCorner = blockPos.north(-radius).west(-radius);
		IBlockState blockStateHangarPadBlock = hangarPadBlock.getDefaultState();
		
		// Can we legally put one here?
		if (GeometryUtil.checkIfPlaneWouldBeDestructive(world, northWestCorner, diameter, 
					diameter, EnumFacing.WEST, EnumFacing.NORTH, blockStateHangarPadBlock, blockPos)) {

			player.sendMessage(new TextComponentString("Not enough space to build a Hanger Pad here.")); 	
			return false;
		}
			
		// Let's do this.
		GeometryUtil.buildPlaneDestructive(world, northWestCorner, diameter, 
				diameter, EnumFacing.WEST, EnumFacing.NORTH, blockStateHangarPadBlock);
	
		// MC-TODO: using a variant blockstate, make the edges look different.
//		IBlockState blockStateHangarPadBlockEdge = SuperDopeJediMod.hangarManager.hangarPadBlock.		
//		world.setBlockState(blockPos, state)
//		IBlockState blockStateHangarPadBlockEdge = SuperDopeJediMod.hangarManager.hangarPadBlock.
//	
		return true;
	}
}
