package superdopesquad.superdopejedimod.teleporter;


import java.util.Random;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Biomes;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.entity.BaseEntityAnimal;
import superdopesquad.superdopejedimod.entity.EntityRenderFactory;
import superdopesquad.superdopejedimod.entity.droid.RepublicSentryDroidModel;
import superdopesquad.superdopejedimod.entity.droid.RepublicSentryDroidRender;
import superdopesquad.superdopejedimod.faction.ClassCapabilityInterface;
import superdopesquad.superdopejedimod.faction.ClassCapabilityProvider;
import superdopesquad.superdopejedimod.faction.ClassInfo;
import superdopesquad.superdopejedimod.faction.FactionInfo;
import superdopesquad.superdopejedimod.faction.PacketClientAskingServerAboutClass;
import superdopesquad.superdopejedimod.faction.PacketPlayerSetClass;
import superdopesquad.superdopejedimod.weapon.PlasmaShotEntityBase;


public class TeleporterEntity extends BaseEntityAnimal {
		
	
	public static BlockPos BLOCKPOS_NULL = new BlockPos(0,0,0);
	private static boolean hasCreativeModeEgg = false;
	
	
	public TeleporterEntity(World worldIn) {
				
		super(worldIn, "teleporter", "Teleporter Entity", hasCreativeModeEgg);
				
		// This sets the bounding box size, not the actual model that you see rendered.
		this.setSize(0.50F, 3.0F);
		
		// Customize these properties in daughter classes to get different behaviors.
		//this.movementSpeed = 0.0; // This renders this droid unmoveable.
		this.setEntityInvulnerable(true);
	}
	
	
	@Override
	public void registerEntityRender() {
			
		Class renderBaseClass = TeleporterRender.class;
		Class modelBaseClass = TeleporterModel.class;
		EntityRenderFactory factory = new EntityRenderFactory(renderBaseClass, modelBaseClass, this.shadowSize);
		RenderingRegistry.registerEntityRenderingHandler(this.getClass(), factory);
	}
	
	
	 /**
     * Called by a player entity when they collide with an entity
     */
    @Override
	public void onCollideWithPlayer(EntityPlayer entityPlayer) {
    
    	if (!entityPlayer.world.isRemote) {
    		
    		BlockPos blockPos = this.getTeleporterDestination();
    		//System.out.println("collided with " + entityPlayer.toString() + ", current teleportData: " + blockPos.toString());
    		
    		// if there is a real BlockPos there, teleport!
    		if (this.isCorrectlyConfigured()) {
    	     	
    			//System.out.println("about to tell client to teleport from: " + entityPlayer.getPosition().toString() + " to: " + blockPos.toString());	
    	     	
    	    	// Tell the client what is going on.
      			PacketTeleporterSetDestination message = new PacketTeleporterSetDestination(entityPlayer, blockPos);
    			SuperDopeJediMod.packetManager.INSTANCE.sendTo(message, (EntityPlayerMP) entityPlayer);
    			
    	     	
    		}
    		else {
    			entityPlayer.sendMessage(new TextComponentString("This teleporter's destination is not set up."));
    		}
    	}
    }
    
    
    public boolean isCorrectlyConfigured() {
    	
    	BlockPos blockPos = this.getTeleporterDestination();
    	boolean configured = (!(blockPos.equals(TeleporterEntity.BLOCKPOS_NULL)));
    	//System.out.println(TeleporterEntity.BLOCKPOS_NULL.toString() + ", " + blockPos.toString() + ", " + (configured));
    	
    	return configured;
    }
	
//    
//    @Override
//    public void moveEntity(MoverType x, double p_70091_2_, double p_70091_4_, double p_70091_6_)
//    {
//    	// do nothing.
//    }
    
    
    @Override
    public void move(MoverType x, double p_70091_2_, double p_70091_4_, double p_70091_6_)
    {
    	// do nothing.
    }
	
    
	public BlockPos getTeleporterDestination() {
		
		// We should have the Teleporter capability set on every TeleporterEntity.
		// DEBUG CODE ONLY.  Let's verify that fact.
		boolean hasCapability = this.hasCapability(TeleporterCapabilityProvider.TeleporterCapability, null);
		assert(hasCapability);
				
		// Let's get the Teleporter capability that is set on every TeleporterEntity.
		TeleporterCapabilityInterface teleporterCapability = this.getCapability(TeleporterCapabilityProvider.TeleporterCapability, null);
		assert(teleporterCapability != null);
		if (teleporterCapability == null) {
			System.out.println("Uh oh! Failed to find teleporter capability.");
			return TeleporterEntity.BLOCKPOS_NULL;
		}
		
		// Extract the name out of factionInfo, now that we have the proper id.
		return teleporterCapability.getTeleporterDestination();
	}
	
	
	public boolean setTeleporterDestination(BlockPos blockPos) {
		
		// Let's get the Faction capability that is set on every player.
		TeleporterCapabilityInterface teleporterCapability = this.getCapability(TeleporterCapabilityProvider.TeleporterCapability, null);
		assert(teleporterCapability != null);
		if (teleporterCapability == null) {
			return false;
		}
		
		// This sets it on the server.
		boolean setSuccess = teleporterCapability.setTeleporterDestination(blockPos);
		if (!setSuccess) {
			System.out.println("failed setting teleporter destination.");
			return false;
		}
			
		return true;
	}
	
	
	public void syncWithServer() {
				
		// Ask about teleporter data.
		int entityId = this.getEntityId();
		
		//System.out.println("About to create PacketClientAskingServerAboutTeleporterDestination for entityId " + entityId);
		
		PacketClientAskingServerAboutTeleporterDestination packet = new PacketClientAskingServerAboutTeleporterDestination();
		packet.setTeleporterEntityId(entityId);

		//System.out.println("About to send PacketClientAskingServerAboutTeleporterDestination for entityId " + entityId);
		
		SuperDopeJediMod.packetManager.INSTANCE.sendToServer(packet);
	}


	@Override
	public void registerModel() {}


	@Override
	public void registerRecipe() {}
}
