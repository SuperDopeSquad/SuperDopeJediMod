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
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
//import superdopesquad.superdopejedimod.entity.EntityExample;
//import superdopesquad.superdopejedimod.entity.EntityTuskanRaider;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
//import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityList;
//import net.minecraft.entity.EntitySpawnPlacementRegistry;
//import net.minecraft.entity.EnumCreatureType;
//import net.minecraft.entity.passive.EntityChicken;
//import net.minecraft.entity.passive.EntityVillager;
//import net.minecraft.entity.projectile.EntityEgg;
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
    
    // This is the collection of custom objects we will maintain.
    public static ArrayList<SuperDopeObject> customObjects = new ArrayList<SuperDopeObject>();
    
    // this is the world generator that adds our custom objects to newly spawned world chunks.
    public static SuperDopeWorldGenerator superDopeWorldGenerator = new SuperDopeWorldGenerator();
    
    // Custom ToolMaterial's.  For a good tutorial on how to define a ToolMaterial, look here:
    // The order of those #'s at the end: harvestLevel, durability, miningSpeed, damageVsEntities, enchantability
    // http://bedrockminer.jimdo.com/modding-tutorials/basic-modding-1-7/custom-tools-swords/
	public static ToolMaterial gaffiStickMaterial = EnumHelper.addToolMaterial("GaffiStickMaterial", 3, 1000, 15.0F, 4.0F, 30);
	public static ToolMaterial powerCrystalMaterial = EnumHelper.addToolMaterial("LightSaberMaterial", 3, 2000, 15.0F, 9.0F, 30);
	public static ToolMaterial doublePowerCrystalMaterial = EnumHelper.addToolMaterial("DoubleLightSaberMaterial", 3, 2500, 15.0F, 12.0F, 30);
	public static ToolMaterial brynsAwesomeSwordMaterial = EnumHelper.addToolMaterial("BrynsAwesomeSwordMaterial", 3, 2000, 15.0F, 8.0F, 30);
	public static ToolMaterial mandalorianIronToolMaterial = EnumHelper.addToolMaterial("MandalorianIronToolMaterial", 3, 1000, 15.0F, 4.0F, 30);
	public static ToolMaterial quadaniumSteelToolMaterial = EnumHelper.addToolMaterial("QuadaniumSteelToolMaterial", 3, 1000, 15.0F, 4.0F, 30);

	// Custom ArmorMaterial's.  
	// EnumHelper.addArmorMaterial("NAME", textureName, durability, reductionAmounts, enchantability, soundOnEquip, toughness)
	//		Durability: 5 - leather; 7 - gold; 15 - chain and iron; 33 - diamond
	//		Reduction Amounts: 1,3,2,1 - leather; 2,5,3,1 - gold; 2,5,4,1 - chain; 2,6,5,2 - iron; 3,8,6,3 - diamond
	//		Enchantability: 15 - leather; 12 - chain; 9 - iron; 25 - gold; 10 - diamond
	public static ArmorMaterial sithCapeMaterial = EnumHelper.addArmorMaterial("SithCapeMaterial", "", 100, new int[]{}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, (float) 0.0);
	public static ArmorMaterial mandalorianIronArmorMaterial = EnumHelper.addArmorMaterial("MandalorianIronArmorMaterial", "superdopejedimod:mandalorianironarmormaterial", 15, new int[]{2,6,5,2}, 9, null, (float) 0.0);
	public static ArmorMaterial quadaniumSteelArmorMaterial = EnumHelper.addArmorMaterial("QuadaniumSteelArmorMaterial", "superdopejedimod:quadaniumsteelarmormaterial", 15, new int[]{2,6,5,2}, 9, null, (float) 0.0);
	
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
    public static MandalorianIronOre mandalorianIronOre = new MandalorianIronOre("mandalorianIronOre");
    public static MandalorianIronIngot mandalorianIronIngot = new MandalorianIronIngot("mandalorianIronIngot");

   // public static MandalorianIronArmor mandalorianIronArmorHelmet = new MandalorianIronArmor("")
    public static Item MandalorianIronHelmet;
    public static Item MandalorianIronChestplate;
    public static Item MandalorianIronLegs;
    public static Item MandalorianIronBoots;
    

    
    //Capes
    
    public static Item SithCape;
    
   // [...]

   // GameRegistry.registerItem(tutorialHelmet = new ItemModArmor("tutorial_helmet", ARMOR, "tutorial", 0), "tutorial_helmet"); //0 for helmet
   // GameRegistry.registerItem(tutorialChestplate = new ItemModArmor("tutorial_chestplate", ARMOR, "tutorial", 1), "tutorial_chestplate"); // 1 for chestplate
   // GameRegistry.registerItem(tutorialLeggings = new ItemModArmor("tutorial_leggings", ARMOR, "tutorial", 2), "tutorial_leggings"); // 2 for leggings
   // GameRegistry.registerItem(tutorialBoots = new ItemModArmor("tutorial_boots", ARMOR, "tutorial", 3), "tutorial_boots"); // 3 for boots
    
    public static MandalorianIronArmor mandalorianIronHelmet = new MandalorianIronArmor(EntityEquipmentSlot.HEAD, "mandalorianIronHelmet");
    public static MandalorianIronArmor mandalorianIronChestplate = new MandalorianIronArmor(EntityEquipmentSlot.CHEST, "mandalorianIronChestplate");
    public static MandalorianIronArmor mandalorianIronLeggings = new MandalorianIronArmor(EntityEquipmentSlot.LEGS, "mandalorianIronLeggings");
    public static MandalorianIronArmor mandalorianIronBoots = new MandalorianIronArmor(EntityEquipmentSlot.FEET, "mandalorianIronBoots");
    public static MandalorianIronSword mandalorianIronSword = new MandalorianIronSword("mandalorianIronSword");
    //public static MandalorianIronArmor mandalorianIronShield = new MandalorianIronArmor(EntityEquipmentSlot.OFFHAND, "mandalorianIronShield");
          
    // Quadanium Steel, used to create vehicles.
    public static QuadaniumSteel quadaniumSteel = new QuadaniumSteel("quadaniumSteel");
    public static QuadaniumSteelIngot quadaniumSteelIngot = new QuadaniumSteelIngot("quadaniumSteelIngot");    
    public static QuadaniumSteelOre quadaniumSteelOre = new QuadaniumSteelOre("quadaniumSteelOre");
    public static QuadaniumSteelArmor quadaniumSteelHelmet = new QuadaniumSteelArmor(EntityEquipmentSlot.HEAD, "quadaniumSteelHelmet");
    public static QuadaniumSteelArmor quadaniumSteelChestplate = new QuadaniumSteelArmor(EntityEquipmentSlot.CHEST, "quadaniumSteelChestplate");
    public static QuadaniumSteelArmor quadaniumSteelLeggings = new QuadaniumSteelArmor(EntityEquipmentSlot.LEGS, "quadaniumSteelLeggings");
    public static QuadaniumSteelArmor quadaniumSteelBoots = new QuadaniumSteelArmor(EntityEquipmentSlot.FEET, "quadaniumSteelBoots");
    public static QuadaniumSteelSword quadaniumSteelSword = new QuadaniumSteelSword("quadaniumSteelSword");
    //public static QuadaniumSteelArmor quadaniumSteelShield = new QuadaniumSteelArmor(EntityEquipmentSlot.OFFHAND, "quadaniumSteelShield");
    
    // Peoples Custom Items
    public static BrynsAwesomeSword brynsAwesomeSword = new BrynsAwesomeSword("brynsAwesomeSword");
    
    //Items for Custom Items
    public static Ruby ruby = new Ruby("ruby");
    public static RubyOre rubyOre = new RubyOre("rubyOre");
    
    // Entities.
    // MC-TODO: the first parameter should be a World instance (Minecraft.getMinecraft.theWorld), but i'm concerned
    // that this will crash on the server side.  Putting in null doesn't seem to have a harmful effect.  I need to
    // figure out later the downside of not having it, and if i need it, figure out the best way to get a handle
    // for it that is server-safe.
    static int startEntityId = 300;
    //public static EntityTuskanRaider entityTuskanRaider = new EntityTuskanRaider(null);
    //public static EntityExample entityExample; 
 
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
    	// Call our proxy for any side-specific work.
    	superDopeCommonProxy.preInit(event);
    	
    	// Iterate through all our custom blocks and items, and register them all.
    	for (SuperDopeObject superDopeObject : this.customObjects) {
    		superDopeObject.registerObject();
    	}  
    	
//    	// mc-temp
//    	RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
//    	World world = Minecraft.getMinecraft().theWorld;
//    	entityExample = new EntityExample(world);
//    	// mc-temp
//    	
//    	// mc-temp
//		ResourceLocation resourceLocation = new ResourceLocation("entityExample");
//	  	//EntityRegistry.registerModEntity(resourceLocation, this.getClass(), this.name, SuperDopeJediMod.getUniqueEntityId(), SuperDopeJediMod.instance, 80, 3, true, 0xfffffff, 0x000000);
//	  	EntityRegistry.registerModEntity(resourceLocation, EntityExample.class, "entityExample", SuperDopeJediMod.getUniqueEntityId(), SuperDopeJediMod.instance, 80, 3, true, 0xfffffff, 0x000000);
//		RenderingRegistry.registerEntityRenderingHandler(EntityExample.class, new RenderVillager(renderManager));
//    	// mc-temp
    	
    	// mc-temp
	  	//EntityRegistry.registerModEntity(EntityTuskanRaider.class, "entityTuskanRaider2", SuperDopeJediMod.getUniqueEntityId(), SuperDopeJediMod.instance, 80, 3, true, 0xfffffff, 0x000000);
		//RenderingRegistry.registerEntityRenderingHandler(EntityTuskanRaider.class, entityTuskanRaider2);
    	// mc-temp
		
    	// mc-temp
	  	//EntityRegistry.registerModEntity(EntityVillager.class, "entityVillager2", SuperDopeJediMod.getUniqueEntityId(), SuperDopeJediMod.instance, 80, 3, true, 0xfffffff, 0x000000);
		//RenderingRegistry.registerEntityRenderingHandler(EntityVillager.class, entityVillager2);
		//RenderingRegistry.registerEntityRenderingHandler(EntityVillager.class, new RenderVillager(renderManager));
    	// mc-temp
    	
    	// MC-TODO: 
      	//EntityRegistry.registerModEntity(EntityExample.class, "entityExample", SuperDopeJediMod.getUniqueEntityId(), SuperDopeJediMod.instance, 80, 3, true, 0xfffffff, 0x000000);
      	
      	//RenderingRegistry.registerEntityRenderingHandler(EntityExample.class, this.entityExample);
    	
    	//RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
    	//RenderChicken renderChicken = new RenderChicken(renderManager, new ModelChicken(), 0);
      	//RenderingRegistry.registerEntityRenderingHandler(EntityExample.class, renderChicken);
        
      	//Render
;
//    	RenderingRegistry.registerEntityRenderingHandler(EntityExample.class, new RenderChicken(new RenderManager(), ));
//    	
//    	  public RenderChicken(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn)
//    	    {
//    	        super(renderManagerIn, modelBaseIn, shadowSizeIn);
//    	    }

    }
     
     
    @EventHandler
    public void load(FMLInitializationEvent event) {
    	
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
    
    
    public static int getUniqueEntityId() {
    	
    	return startEntityId++;
    }
}