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
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.gui.GuiHandler;
import superdopesquad.superdopejedimod.tinkertable.TinkerTableTileEntity;


public class SuperDopeCommonProxy {
	 
	
	public void preInit(FMLPreInitializationEvent e) {
		
	       GameRegistry.registerTileEntity(TinkerTableTileEntity.class, "tinkertabletileentity");
	}

	
    public void init(FMLInitializationEvent e) {
    	
    	//System.out.println("I'm in the common proxy! init event.");
    	
    	NetworkRegistry.INSTANCE.registerGuiHandler(SuperDopeJediMod.instance, new GuiHandler());  
    }

    public void registerTileEntities() {
    	
    	//GameRegistry.registerTileEntity(GeneratorTileEntity.class, SuperDopeJediMod.MODID + ":generator");
    	
    }
    
    public void postInit(FMLPostInitializationEvent e) {}
    
    
	public void displayCreditGui(int stackSize) {}
	
	
	public void displayClassGui(EntityPlayer player) {}
}


