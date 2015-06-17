package superDopeJediMod;

import java.util.Set;
import java.util.HashSet;

import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
//import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
//import net.minecraftforge.common.MinecraftForge;


@Mod(modid=SuperDopeJediMod.MODID, name=SuperDopeJediMod.MODNAME, version=SuperDopeJediMod.MODVER) //Tell forge "Oh hey, there's a new mod here to load."
public class SuperDopeJediMod //Start the class Declaration
{
    // Set the metadata of the mod.
    public static final String MODID = "superDopeJediMod";
    public static final String MODNAME = "SuperDopeJediMod";
    public static final String MODVER = "0.0.1";

    // This is the collection of custom objects we will maintain.
    public static Set<BaseItem> customObjects = new HashSet<BaseItem>();
    
    // instance variable.
    @Instance(value = SuperDopeJediMod.MODID) //Tell Forge what instance to use.
    public static SuperDopeJediMod instance;
    
    // Custom items.
    public static BaseItem gaffiStick = new GaffiStick("gaffiStick");  
    public static Block brownSteel = new BrownSteel("brownSteel");
    public static Item brownSteelIngot = new BrownSteelIngot("brownSteelIngot");    
    public static Block brownSteelOre = new BrownSteelOre("brownSteelOre");
    public static Block vehicleSeat = new VehicleSeat("vehicleSeat");
<<<<<<< HEAD
<<<<<<< HEAD
    public static Item nourishmentCapsule = new NourishmentCapsule();
=======
=======
    public static Item nourishmentCapsule = new NourishmentCapsule();
>>>>>>> origin/master
    public static Item lightSaberRed = new LightSaber("lightSaberRed", "Red"); 
    public static Item lightSaberBlue = new LightSaber("lightSaberBlue", "Blue");
    public static Item lightSaberGreen = new LightSaber("lightSaberGreen", "Green");
    public static Item lightSaberPurple = new LightSaber("lightSaberPurple", "Purple");
    public static Item doubleLightSaberRed = new DoubleLightSaber("doubleLightSaberRed", "Red");
    public static Item doubleLightSaberBlue = new DoubleLightSaber("doubleLightSaberBlue", "Blue");
    public static Item doubleLightSaberGreen = new DoubleLightSaber("doubleLightSaberGreen", "Green");
    public static Item doubleLightSaberPurple = new DoubleLightSaber("doubleLightSaberPurple", "Purple");
    public static Item redPowerCrystal = new PowerCrystal("redPowerCrystal", "Red");
    public static Item bluePowerCrystal = new PowerCrystal("bluePowerCrystal", "Blue");
    public static Item greenPowerCrystal = new PowerCrystal("greenPowerCrystal", "Green");
    public static Item purplePowerCrystal = new PowerCrystal("purplePowerCrystal", "Purple");
    public static Block redPowerCrystalOre = new PowerCrystalOre("redPowerCrystalOre", "Red");
    public static Block bluePowerCrystalOre = new PowerCrystalOre("bluePowerCrystalOre", "Blue");
    public static Block greenPowerCrystalOre = new PowerCrystalOre("greenPowerCrystalOre", "Green");
    public static Block purplePowerCrystalOre = new PowerCrystalOre("purplePowerCrystalOre", "Purple");
<<<<<<< HEAD
>>>>>>> origin/master
    
    
=======
 
>>>>>>> origin/master
    //@SidedProxy(clientSide="tutorial.generic.client.ClientProxy",
    //        serverSide="tutorial.generic.CommonProxy")
    
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	this.customObjects.add(this.gaffiStick);
    	
    	// A note on GameRegistry.registerItem ...
        // The second parameter is an unique registry identifier (not the displayed name)
        // Please don't use item1.getUnlocalizedName(), or you will make Lex sad
     
        this.registerItem(this.gaffiStick, "Gaffi Stick");
        this.registerBlock(this.brownSteel);
        this.registerItem(this.brownSteelIngot, "Brown Steel Ingot");
        this.registerBlock(this.brownSteelOre);
        this.registerBlock(this.vehicleSeat);
<<<<<<< HEAD
<<<<<<< HEAD
        this.registerItem(this.nourishmentCapsule, "Nourishment Capsule");
    
=======
=======
        this.registerItem(this.nourishmentCapsule, "Nourishment Capsule");
>>>>>>> origin/master
        this.registerItem(this.lightSaberRed, "Red Lightsaber");
        this.registerItem(this.lightSaberBlue, "Blue Lightsaber");
        this.registerItem(this.lightSaberGreen, "Green Lightsaber");
        this.registerItem(this.lightSaberPurple, "Purple Lightsaber");
        this.registerItem(this.doubleLightSaberRed, "Red Double Lightsaber");
        this.registerItem(this.doubleLightSaberBlue, "Blue Double Lightsaber");
        this.registerItem(this.doubleLightSaberGreen, "Green Double Lightsaber");
        this.registerItem(this.doubleLightSaberPurple, "Purple Double Lightsaber");
        this.registerItem(this.redPowerCrystal, "Red Power Crystal");
        this.registerItem(this.bluePowerCrystal, "Blue Power Crystal");
        this.registerItem(this.greenPowerCrystal, "Green Power Crystal");
        this.registerItem(this.purplePowerCrystal, "Purple Power Crystal");
        this.registerBlock(this.redPowerCrystalOre);
        this.registerBlock(this.bluePowerCrystalOre);
        this.registerBlock(this.greenPowerCrystalOre);
        this.registerBlock(this.purplePowerCrystalOre);
<<<<<<< HEAD
      
>>>>>>> origin/master
=======
     
>>>>>>> origin/master
    }
     
     
    
    @EventHandler
    public void load(FMLInitializationEvent event)
    {
    }
      
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	
    	// Iterate through all our custom objects, 
    	// and see if we have any recipes to register.
    	for (BaseItem baseItem : this.customObjects) {
    		baseItem.registerRecipe();
    	}
    	
    	// MC-to-delete
    	//this.gaffiStick.registerRecipe();
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

