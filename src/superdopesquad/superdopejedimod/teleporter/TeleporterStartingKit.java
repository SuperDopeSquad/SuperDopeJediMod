package superdopesquad.superdopejedimod.teleporter;


import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
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


public class TeleporterStartingKit extends BaseBlock {

	
	public TeleporterStartingKit(String unlocalizedName) {

		super(Material.IRON, unlocalizedName, false);
	}
	
	
	public Item getItemDropped(int metadata, Random random, int fortune) {
        		
		return SuperDopeJediMod.teleporterManager.teleporterStartingKitItem;
	}
}
