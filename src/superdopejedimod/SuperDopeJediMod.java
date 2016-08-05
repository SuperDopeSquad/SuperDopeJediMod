package superdopesquad.superdopejedimod;


import java.util.ArrayList;

import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.registry.EntityRegistry;
//import net.minecraftforge.fml.common.Mod.Instance;
//import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.projectile.EntityEgg;


@Mod(modid=SuperDopeJediMod.MODID, name=SuperDopeJediMod.MODNAME, version=SuperDopeJediMod.MODVER) //Tell forge "Oh hey, there's a new mod here to load."
public class SuperDopeJediMod //Start the class Declaration
{
    // Set the metadata of the mod.
    public static final String MODID = "superdopejedimod";
    public static final String MODNAME = "SuperDopeJediMod";
    public static final String MODVER = "0.0.1";

    // This is the collection of custom objects we will maintain.
    public static ArrayList<SuperDopeObject> customObjects = new ArrayList<SuperDopeObject>();
    
    // this is the world generator that adds our custom objects to newly spawned world chunks.
    public static SuperDopeWorldGenerator superDopeWorldGenerator = new SuperDopeWorldGenerator();
    
    public static RenderManager renderManager = new RenderManager(null, null);
    
    static int startEntityId = 300;
    
    // Custom ToolMaterials.  For a good tutorial on how to define a ToolMaterial, look here:
    // The order of those #'s at the end: harvestLevel, durability, miningSpeed, damageVsEntities, enchantability
    // http://bedrockminer.jimdo.com/modding-tutorials/basic-modding-1-7/custom-tools-swords/
	public static ToolMaterial gaffiStickMaterial = EnumHelper.addToolMaterial("GaffiStickMaterial", 3, 1000, 15.0F, 4.0F, 30);
	public static ToolMaterial powerCrystalMaterial = EnumHelper.addToolMaterial("LightSaberMaterial", 3, 2000, 15.0F, 5.0F, 30);
	public static ToolMaterial doublePowerCrystalMaterial = EnumHelper.addToolMaterial("DoubleLightSaberMaterial", 3, 2500, 15.0F, 8.0F, 30);
	public static ToolMaterial brynsAwesomeSwordMaterial = EnumHelper.addToolMaterial("BrynsAwesomeSwordMaterial", 3, 2000, 15.0F, 6.0F, 30);
	
    // instance variable.
    //@Instance(value = SuperDopeJediMod.MODID) //Tell Forge what instance to use.
    //public static SuperDopeJediMod instance;
    
    // Miscellaneous hand-held weapons.
    public static GaffiStick gaffiStick = new GaffiStick("gaffiStick");  
    
    // Vehicle parts.
    public static VehicleSeat vehicleSeat = new VehicleSeat("vehicleSeat");
    
    // Lightsaber stuff!
    public static LightSaber lightSaberRed = new LightSaber("lightSaberRed", "Red"); 
    public static LightSaber lightSaberBlue = new LightSaber("lightSaberBlue", "Blue");
    public static LightSaber lightSaberGreen = new LightSaber("lightSaberGreen", "Green");
    public static LightSaber lightSaberPurple = new LightSaber("lightSaberPurple", "Purple");
    public static DoubleLightSaber doubleLightSaberRed = new DoubleLightSaber("doubleLightSaberRed", "Red");
    public static DoubleLightSaber doubleLightSaberBlue = new DoubleLightSaber("doubleLightSaberBlue", "Blue");
    public static DoubleLightSaber doubleLightSaberGreen = new DoubleLightSaber("doubleLightSaberGreen", "Green");
    public static DoubleLightSaber doubleLightSaberPurple = new DoubleLightSaber("doubleLightSaberPurple", "Purple");
    public static PowerCrystal redPowerCrystal = new PowerCrystal("redPowerCrystal", "Red");
    public static PowerCrystal bluePowerCrystal = new PowerCrystal("bluePowerCrystal", "Blue");
    public static PowerCrystal greenPowerCrystal = new PowerCrystal("greenPowerCrystal", "Green");
    public static PowerCrystal purplePowerCrystal = new PowerCrystal("purplePowerCrystal", "Purple");
    public static PowerCrystalOre redPowerCrystalOre = new PowerCrystalOre("redPowerCrystalOre", "Red");
    public static PowerCrystalOre bluePowerCrystalOre = new PowerCrystalOre("bluePowerCrystalOre", "Blue");
    public static PowerCrystalOre greenPowerCrystalOre = new PowerCrystalOre("greenPowerCrystalOre", "Green");
    public static PowerCrystalOre purplePowerCrystalOre = new PowerCrystalOre("purplePowerCrystalOre", "Purple");
    
    // Miscellaneous items.
    public static NourishmentCapsule nourishmentCapsule = new NourishmentCapsule("nourishmentCapsule");
    public static Credit credit = new Credit("credit"); 
    public static SithMark sithMark = new SithMark("sithMark");
    
    
    // Ranged weapons.
    public static Blaster blaster = new Blaster("blaster");
    public static BossBlaster bossBlaster = new BossBlaster("bossBlaster");
    public static Zapper zapper = new Zapper("zapper");
    
    // Blocks and Items Used for a Spaceship
    public static Engine engine = new Engine("engine");
    public static ChromateOre chromateOre = new ChromateOre("chromateOre");
    public static ChromateIngot chromateIngot = new ChromateIngot("chromateIngot");
    public static CompressedMetalPlate compressedMetalPlate = new CompressedMetalPlate("compressedMetalPlate");
    public static CompressedMetalBits compressedMetalBits = new CompressedMetalBits("compressedMetalBits");
    public static Generator generator = new Generator("generator");
    public static ChromateShard chromateShard = new ChromateShard("chromateShard");
    public static ElectricTransmitter electricTransmitter = new ElectricTransmitter("electricTransmitter");
    public static ElectricFluxIngot electricFluxIngot = new ElectricFluxIngot("electricFluxIngot");
    public static ElectricFluxOre electricFluxOre = new ElectricFluxOre("electricFluxOre");
    public static ControlPanel controlPanel = new ControlPanel("controlPanel");
    public static CompressedMetalMesh compressedMetalMesh = new CompressedMetalMesh("compressedMetalMesh");
    public static BitsOfCompressedMetalMesh bitsOfCompressedMetalMesh = new BitsOfCompressedMetalMesh("bitsOfCompressedMetalMesh");
    
    // Mandalorian Iron, used to create weapons.
    public static MandalorianIron mandalorianIron = new MandalorianIron("mandalorianIron");
    public static MandalorianIronOre mandalorianIronOre = new MandalorianIronOre("mandalorianIronOre");
    public static MandalorianIronIngot mandalorianIronIngot = new MandalorianIronIngot("mandalorianIronIngot");
    
    // Quadanium Steel, used to create vehicles.
    public static QuadaniumSteel quadaniumSteel = new QuadaniumSteel("quadaniumSteel");
    public static QuadaniumSteelIngot quadaniumSteelIngot = new QuadaniumSteelIngot("quadaniumSteelIngot");    
    public static QuadaniumSteelOre quadaniumSteelOre = new QuadaniumSteelOre("quadaniumSteelOre");
    
    // Peoples Custom Items
    public static BrynsAwesomeSword brynsAwesomeSword = new BrynsAwesomeSword("brynsAwesomeSword");
    
    //Items for Custom Items
    public static Ruby ruby = new Ruby("ruby");
    public static RubyOre rubyOre = new RubyOre("rubyOre");
    
    // Mobs
    public static EntityTuskanRaider entityTuskanRaider = new EntityTuskanRaider(null);
    //public static BaseMob baseMob = new BaseMob()

    
    //@SidedProxy(clientSide="tutorial.generic.client.ClientProxy",
    //        serverSide="tutorial.generic.CommonProxy")
    
 
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
    	// Iterate through all our custom blocks and items, and register them all.
    	for (SuperDopeObject superDopeObject : this.customObjects) {
    		superDopeObject.registerObject();
    	}
    	
    	// temporary hack to get my mob working!
        //this.entityRenderMap.put(EntityChicken.class, new RenderChicken(this, new ModelChicken(), 0.3F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTuskanRaider.class, 
        	      new RenderChicken(renderManager, new ModelChicken(), 0.3F));

    }
     
     
    
    @EventHandler
    public void load(FMLInitializationEvent event) {
    	
    	EntityRegistry.registerModEntity(EntityTuskanRaider.class, "foo", 1, this, 80, 3, true);
    	EntityRegistry.addSpawn(EntityTuskanRaider.class, 10, 2, 4, EnumCreatureType.MONSTER, BiomeGenBase.desert,
    			BiomeGenBase.desertHills, BiomeGenBase.forest);
    	
    	this.registerEntityEgg(EntityTuskanRaider.class, 0xfffffff, 0x000000);
    }
      
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    		
    	// Iterate through all our custom blocks and items, 
    	// and see if we have any recipes to register.
    	for (SuperDopeObject superDopeObject : this.customObjects) {
    		superDopeObject.registerRecipe();
    	}
    	
    	// All model and texture rendering has to be client-side only.
    	if(event.getSide() == Side.CLIENT) {
    	     
    		// Iterate through all our custom objects, and
        	// see if we have any models to render.
        	for (SuperDopeObject superDopeObject : this.customObjects) {
        		superDopeObject.registerModel();
        	}
    	}
    	
    	// Register our custom world generator, so our ore gets generated.
    	GameRegistry.registerWorldGenerator(SuperDopeJediMod.superDopeWorldGenerator, 0);
    }
    
 
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
    
    
    public static int getUniqueEntityId() {
    	do {
    		startEntityId++;
    	}
    	while (EntityList.getStringFromID(startEntityId) != null);
    	
    	return startEntityId;
    }
    
    
    public void registerModEntityWithEgg(Class parEntityClass, String parEntityName, 
    	      int parEggColor, int parEggSpotsColor) {
    	    EntityRegistry.registerModEntity(parEntityClass, parEntityName, getUniqueEntityId(), 
    	          this, 80, 3, false);
    	    registerEntityEgg(parEntityClass, parEggColor, parEggSpotsColor);
    }

    
    public static void registerEntityEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor) {
    	
    	int id = getUniqueEntityId();
    	EntityList.addMapping(entity, "foo", id, 113213, 3523523);
    	//EntityList.idToClassMapping.put(id, entity);
    	//EntityList.entityEggs.put(id,  new EntityEgg(id, primaryColor, secondaryColor));
    	
    	
    	
    }
}

