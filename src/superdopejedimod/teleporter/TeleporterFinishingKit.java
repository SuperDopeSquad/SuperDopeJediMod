package superdopesquad.superdopejedimod.teleporter;


import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.BaseBlock;
import superdopesquad.superdopejedimod.SuperDopeJediMod;


public class TeleporterFinishingKit extends BaseBlock {

	private EnumFacing _facingTeleporterA;
	private BlockPos _blockPosTeleporterA;
	private TeleporterEntity _entityTeleporterA;

	
	public TeleporterFinishingKit(String unlocalizedName) {

		super(Material.IRON, unlocalizedName, false);
	}
	
	
	public Item getItemDropped(int metadata, Random random, int fortune) {
        
		return Item.getItemFromBlock(SuperDopeJediMod.teleporterManager.teleporterFinishingKit);
	}
	
	
	public EnumFacing getFacingForTeleporterA() {
		
		return this._facingTeleporterA;
	}
	
	
	public BlockPos getBlockPosForTeleporterA() {
		
		return this._blockPosTeleporterA;
	}
	
	
	public TeleporterEntity getEntityForTeleporterA() {
		
		return this._entityTeleporterA;
	}
	
	
	public void setTeleporterData(EnumFacing facing, BlockPos blockPos, TeleporterEntity entity) {
			
		this._facingTeleporterA = facing;
		this._blockPosTeleporterA = blockPos;
		this._entityTeleporterA = entity;
	}
}
