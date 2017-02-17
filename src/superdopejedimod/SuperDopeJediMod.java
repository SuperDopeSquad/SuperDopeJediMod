package superdopesquad.superdopejedimod;

import java.util.ArrayList;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import superdopesquad.superdopejedimod.entity.EntityManager;
import superdopesquad.superdopejedimod.faction.FactionManager;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;


@Mod(modid=SuperDopeJediMod.MODID, name=SuperDopeJediMod.MODNAME, version=SuperDopeJediMod.MODVER) //Tell forge "Oh hey, there's a new mod here to load."
public class SuperDopeJediMod //Start the class Declaration
{
    // Set the metadata of the mod.
    public static final String MODID = "superdopejedimod";
    public static final String MODNAME = "SuperDopeJediMod";
    public static final String MODVER = "0.0.1";

    // Establish proxy classes, so we can do the right stuff client-side only, if necessary.
    @SidedProxy(clientSide="superdopesquad.superdopejedimod.SuperDopeClientProxy", serverSide="superdopesquad.superdopejedimod.SuperDopeServerProxy")
    public static SuperDopeCommonProxy superDopeCommonProxy;
    
    // This is our collection of custom objects we will maintain.
    public static ArrayList<SuperDopeObject> customObjects = new ArrayList<SuperDopeObject>();
    
    // this is the world generator that adds our custom objects to newly spawned world chunks.
    public static SuperDopeWorldGenerator superDopeWorldGenerator = new SuperDopeWorldGenerator();
    
    // Custom ToolMaterial's.  For a good tutorial on how to define a ToolMaterial, look here:
    // The order of those #'s at the end: harvestLevel, durability, miningSpeed, damageVsEntities, enchantability
    // http://bedrockminer.jimdo.com/modding-tutorials/basic-modding-1-7/custom-tools-swords/
	public static ToolMaterial gaffiStickMaterial = EnumHelper.addToolMaterial("GaffiStickMaterial", 3, 1000, 15.0F, 4.0F, 30);
	public static ToolMaterial powerCrystalMaterial = EnumHelper.addToolMaterial("LightSaberMaterial", 3, 2000, 0.0F, 9.0F, 30);
	public static ToolMaterial doublePowerCrystalMaterial = EnumHelper.addToolMaterial("DoubleLightSaberMaterial", 3, 2500, 0.0F, 12.0F, 30);
	public static ToolMaterial brynsAwesomeSwordMaterial = EnumHelper.addToolMaterial("BrynsAwesomeSwordMaterial", 3, 2000, 0.0F, 8.0F, 30);
	public static ToolMaterial mandalorianIronToolMaterial = EnumHelper.addToolMaterial("MandalorianIronToolMaterial", 3, 1000, 15.0F, 4.0F, 30);
	public static ToolMaterial quadaniumSteelToolMaterial = EnumHelper.addToolMaterial("QuadaniumSteelToolMaterial", 3, 1000, 15.0F, 4.0F, 30);
	public static ToolMaterial blasterMaterial = EnumHelper.addToolMaterial("BlasterMaterial", 3, 1000, 0.0F, 7.0F, 30);
	
	// Custom ArmorMaterial's.  
	// EnumHelper.addArmorMaterial("NAME", textureName, durability, reductionAmounts, enchantability, soundOnEquip, toughness)
	//		Durability: 5 - leather; 7 - gold; 15 - chain and iron; 33 - diamond
	//		Reduction Amounts: 1,3,2,1 - leather; 2,5,3,1 - gold; 2,5,4,1 - chain; 2,6,5,2 - iron; 3,8,6,3 - diamond
	//		Enchantability: 15 - leather; 12 - chain; 9 - iron; 25 - gold; 10 - diamond
	public static ArmorMaterial sithCapeMaterial = EnumHelper.addArmorMaterial("SithCapeMaterial", "", 100, new int[]{}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, (float) 0.0);
	public static ArmorMaterial mandalorianIronArmorMaterial = EnumHelper.addArmorMaterial("MandalorianIronArmorMaterial", "superdopejedimod:mandalorianironarmormaterial", 15, new int[]{2,6,5,2}, 9, null, (float) 0.0);
	public static ArmorMaterial quadaniumSteelArmorMaterial = EnumHelper.addArmorMaterial("QuadaniumSteelArmorMaterial", "superdopejedimod:quadaniumsteelarmormaterial", 15, new int[]{2,6,5,2}, 9, null, (float) 0.0);
	public static ArmorMaterial sithLordArmorMaterial = EnumHelper.addArmorMaterial("SithLordArmorMaterial", "superdopejedimod:sithlordarmormaterial", 30, new int[]{3,8,6,3}, 10, null, (float) 0.0);
	public static ArmorMaterial jediArmorMaterial = EnumHelper.addArmorMaterial("JediArmorMaterial", "superdopejedimod:jediarmormaterial", 30, new int[]{3,8,6,3}, 10, null, (float) 0.0);
	
    // instance variable.
    @Instance(value = SuperDopeJediMod.MODID) //Tell Forge what instance to use.
    public static SuperDopeJediMod instance;
    
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
    public static JediMark jediMark = new JediMark("jediMark");
    public static Faction faction = new Faction("faction");
    public static OHUMBlock ohumBlock = new OHUMBlock("OHUMBlock");
    public static StarBlock starBlock = new StarBlock("StarBlock");
        
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
     
    // Mandalorian Iron, used to create weapons and armor.
    public static MandalorianIron mandalorianIron = new MandalorianIron("mandalorianIron");
    public static MandalorianIronIngot mandalorianIronIngot = new MandalorianIronIngot("mandalorianIronIngot");
    public static MandalorianIronOre mandalorianIronOre = new MandalorianIronOre("mandalorianIronOre");
    public static MandalorianIronArmor mandalorianIronHelmet = new MandalorianIronArmor(EntityEquipmentSlot.HEAD, "mandalorianIronHelmet");
    public static MandalorianIronArmor mandalorianIronChestplate = new MandalorianIronArmor(EntityEquipmentSlot.CHEST, "mandalorianIronChestplate");
    public static MandalorianIronArmor mandalorianIronLeggings = new MandalorianIronArmor(EntityEquipmentSlot.LEGS, "mandalorianIronLeggings");
    public static MandalorianIronArmor mandalorianIronBoots = new MandalorianIronArmor(EntityEquipmentSlot.FEET, "mandalorianIronBoots");
    public static MandalorianIronSword mandalorianIronSword = new MandalorianIronSword("mandalorianIronSword");
            
    // Quadanium Steel, used to create vehicles.
    public static QuadaniumSteel quadaniumSteel = new QuadaniumSteel("quadaniumSteel");
    public static QuadaniumSteelIngot quadaniumSteelIngot = new QuadaniumSteelIngot("quadaniumSteelIngot");    
    public static QuadaniumSteelOre quadaniumSteelOre = new QuadaniumSteelOre("quadaniumSteelOre");
    public static QuadaniumSteelArmor quadaniumSteelHelmet = new QuadaniumSteelArmor(EntityEquipmentSlot.HEAD, "quadaniumSteelHelmet");
    public static QuadaniumSteelArmor quadaniumSteelChestplate = new QuadaniumSteelArmor(EntityEquipmentSlot.CHEST, "quadaniumSteelChestplate");
    public static QuadaniumSteelArmor quadaniumSteelLeggings = new QuadaniumSteelArmor(EntityEquipmentSlot.LEGS, "quadaniumSteelLeggings");
    public static QuadaniumSteelArmor quadaniumSteelBoots = new QuadaniumSteelArmor(EntityEquipmentSlot.FEET, "quadaniumSteelBoots");
    public static QuadaniumSteelSword quadaniumSteelSword = new QuadaniumSteelSword("quadaniumSteelSword");
    
    //Sith Armor
    public static SithLordArmor sithLordHelmet = new SithLordArmor(EntityEquipmentSlot.HEAD, "sithLordHelmet");
    public static SithLordArmor sithLordChestplate = new SithLordArmor(EntityEquipmentSlot.CHEST, "sithLordChestplate");
    public static SithLordArmor sithLordLeggings = new SithLordArmor(EntityEquipmentSlot.LEGS, "sithLordLeggings");
    public static SithLordArmor sithLordBoots = new SithLordArmor(EntityEquipmentSlot.FEET, "sithLordBoots");
    
    //Jedi Armor
    public static JediArmor jediHelmet = new JediArmor(EntityEquipmentSlot.HEAD, "jediHelmet");
    public static JediArmor jediChestplate = new JediArmor(EntityEquipmentSlot.CHEST, "jediChestplate");
    public static JediArmor jediLeggings = new JediArmor(EntityEquipmentSlot.LEGS, "jediLeggings");
    public static JediArmor jediBoots = new JediArmor(EntityEquipmentSlot.FEET, "jediBoots");
    
    // Peoples Custom Items
    public static BrynsAwesomeSword brynsAwesomeSword = new BrynsAwesomeSword("brynsAwesomeSword");
    
    //Items for Custom Items
    public static Ruby ruby = new Ruby("ruby");
    public static RubyOre rubyOre = new RubyOre("rubyOre");
    public static SaphireOre saphireOre = new SaphireOre("saphireOre");
    public static Saphire saphire = new Saphire("saphire");
    
    // Factions.  Must be before EntityManager.
    public static FactionManager factionManager = new FactionManager();
   
    // Entities.
    public static EntityManager entityManager = new EntityManager();
 
    // Commands.
    public static CommandManager commandManager = new CommandManager();
         
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
    	// Call our proxy for any side-specific work.
    	superDopeCommonProxy.preInit(event);
    	
    	// Iterate through all our custom blocks and items, and register them all.
    	for (SuperDopeObject superDopeObject : this.customObjects) {
    		superDopeObject.registerObject();
    	}  
    	
    	// Call the pre-init of FactionManager, which needs to do some registration work.
    	this.factionManager.preInit();
    	
    	// Let's register our eventhandler class.   
    	MinecraftForge.EVENT_BUS.register(new SuperDopeEventHandler());	   
    }
     
     
    @EventHandler
    public void load(FMLInitializationEvent event) {
    	
    }
    
    
    @EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		
    	commandManager.serverLoad(event);
	}
      
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    		
    	// Call our proxy for any side-specific work.
    	// Looking for where we register models?  Check in SuperDopeClientProxy.init(e).
    	superDopeCommonProxy.init(event);
    	
    	// Iterate through all our custom blocks and items, 
    	// and see if we have any recipes to register.
    	for (SuperDopeObject superDopeObject : this.customObjects) {
    		superDopeObject.registerRecipe();
    	}
    	
    	// Register our custom world generator, so our ore gets generated.
    	GameRegistry.registerWorldGenerator(SuperDopeJediMod.superDopeWorldGenerator, 0);
    }
    
 
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    	// Call our proxy for any side-specific work.
    	superDopeCommonProxy.postInit(event);
    }
    
}
