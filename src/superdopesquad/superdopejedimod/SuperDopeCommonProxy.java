package superdopesquad.superdopejedimod;


import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import java.lang.reflect.Proxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.gui.TileEntityGenerator;


public class SuperDopeCommonProxy {
	 
	
	public void preInit(FMLPreInitializationEvent e) {}

	
    public void init(FMLInitializationEvent e) {}

    public void registerTileEntities() {
    	
    	GameRegistry.registerTileEntity(TileEntityGenerator.class, SuperDopeJediMod.MODID + ":generator");
    	
    }
    
    public void postInit(FMLPostInitializationEvent e) {}
    
    
	public void displayCreditGui(int stackSize) {}
	
	
	public void displayClassGui(EntityPlayer player) {}
}


