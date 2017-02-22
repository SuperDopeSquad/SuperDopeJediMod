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


public class SuperDopeCommonProxy {
	 
	
	public void preInit(FMLPreInitializationEvent e) {}

	
    public void init(FMLInitializationEvent e) {}

    
    public void postInit(FMLPostInitializationEvent e) {}
    
    
	public void displayCreditGui(int stackSize) {}
	
	
	public void displayClassGui(EntityPlayer player) {}
}


