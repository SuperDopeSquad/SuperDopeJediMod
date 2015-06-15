package superDopeJediMod;


import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
//import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;


@Mod(modid=SuperDopeJediMod.MODID, name=SuperDopeJediMod.MODNAME, version=SuperDopeJediMod.MODVER) //Tell forge "Oh hey, there's a new mod here to load."
public class SuperDopeJediMod //Start the class Declaration
{
    //Set the metadata of the mod.
    public static final String MODID = "superDopeJediMod";
    public static final String MODNAME = "SuperDopeJediMod";
    public static final String MODVER = "0.0.1";

    // instance variable.
    @Instance(value = SuperDopeJediMod.MODID) //Tell Forge what instance to use.
    public static SuperDopeJediMod instance;
    
    // Custom items.
    public static Item gaffiStick = new GaffiStick("gaffiStick");
    public static Block brownSteel = new BrownSteel("brownSteel");
    public static Item brownSteelIngot = new BrownSteelIngot("brownSteelIngot");    
    public static Block brownSteelOre = new BrownSteelOre("brownSteelOre");
    public static Block vehicleSeat = new VehicleSeat("vehicleSeat");
    public static Item nourishmentCapsule = new NourishmentCapsule();
    
    
    //@SidedProxy(clientSide="tutorial.generic.client.ClientProxy",
    //        serverSide="tutorial.generic.CommonProxy")
    
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	// A note on GameRegistry.registerItem ...
        // The second parameter is an unique registry identifier (not the displayed name)
        // Please don't use item1.getUnlocalizedName(), or you will make Lex sad
     
        //GameRegistry.registerItem(this.gaffiStick, "gaffiStick");
        //GameRegistry.registerItem(this.gaffiStick, this.gaffiStick.getUnlocalizedName());
        this.registerItem(this.gaffiStick, "Gaffi Stick");
        this.registerBlock(this.brownSteel);
        this.registerItem(this.brownSteelIngot, "Brown Steel Ingot");
        this.registerBlock(this.brownSteelOre);
        this.registerBlock(this.vehicleSeat);
        this.registerItem(this.nourishmentCapsule, "Nourishment Capsule");
    
    }
     
    
    @EventHandler
    public void load(FMLInitializationEvent event)
    {
    }
      
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    }
    
 
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }
    
    
    private void registerItem(Item i, String s) {
    	
    	String foo = i.getUnlocalizedName();
        GameRegistry.registerItem(i, s);
    }
    
    
    private void registerBlock(Block b) {
    	
        GameRegistry.registerBlock(b, b.getUnlocalizedName());
    }
}

