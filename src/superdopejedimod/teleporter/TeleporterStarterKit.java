package superdopesquad.superdopejedimod.teleporter;


import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.BaseBlock;
import superdopesquad.superdopejedimod.SuperDopeJediMod;


public class TeleporterStarterKit extends BaseBlock {

	
	public TeleporterStarterKit(String unlocalizedName) {

		super(Material.IRON, unlocalizedName);
	}
	
	
	public Item getItemDropped(int metadata, Random random, int fortune) {
        
		return Item.getItemFromBlock(SuperDopeJediMod.teleporterManager.teleporterStarterKit);
	}
	
	
	@Override
	public void registerRecipe() {
		
		ItemStack itemStackTeleporterParts = new ItemStack(SuperDopeJediMod.teleporterManager.teleporterParts);
		ItemStack itemStackTeleporterPartsMany = new ItemStack(SuperDopeJediMod.teleporterManager.teleporterParts, 8);
		ItemStack itemStackThis = new ItemStack(this);
		
		GameRegistry.addRecipe(itemStackThis, "xxx", "x x", "xxx", 'x', itemStackTeleporterParts);
    	GameRegistry.addRecipe(itemStackTeleporterPartsMany, "x", 'x', itemStackThis);	
	}
}
