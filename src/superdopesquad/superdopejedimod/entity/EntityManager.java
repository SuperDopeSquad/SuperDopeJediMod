package superdopesquad.superdopejedimod.entity;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import superdopesquad.superdopejedimod.MandalorianIron;
import superdopesquad.superdopejedimod.MandalorianIronIngot;
import superdopesquad.superdopejedimod.SuperDopeObject;
import superdopesquad.superdopejedimod.entity.droid.DroidKit;
import superdopesquad.superdopejedimod.entity.droid.DroidParts;
import superdopesquad.superdopejedimod.entity.droid.ImperialProbeDroidEntity;
import superdopesquad.superdopejedimod.entity.droid.RepublicHunterDroidEntity;
import superdopesquad.superdopejedimod.entity.droid.RepublicHunterDroidHead;
import superdopesquad.superdopejedimod.entity.droid.RepublicPatrolDroidEntity;
import superdopesquad.superdopejedimod.entity.droid.RepublicPatrolDroidHead;
import superdopesquad.superdopejedimod.entity.droid.RepublicSentryDroidEntity;
import superdopesquad.superdopejedimod.entity.droid.RepublicSentryDroidHead;
import superdopesquad.superdopejedimod.entity.ship.ImperialShuttleEntity;
import superdopesquad.superdopejedimod.entity.ship.ShuttleEntity;
import superdopesquad.superdopejedimod.entity.ship.TieFighterEntity;
import superdopesquad.superdopejedimod.entity.ship.XWingFighterEntity;
import superdopesquad.superdopejedimod.teleporter.TeleporterEntity;
import superdopesquad.superdopejedimod.weapon.PlasmaShotEntityBase;
import superdopesquad.superdopejedimod.weapon.PlasmaShotItem;


public class EntityManager {

	
	 // Entities.
    // MC-TODO: the first parameter should be a World instance (Minecraft.getMinecraft.theWorld), but i'm concerned
    // that this will crash on the server side.  Putting in null doesn't seem to have a harmful effect.  I need to
    // figure out later the downside of not having it, and if i need it, figure out the best way to get a handle
    // for it that is server-safe.
    private static int _startEntityId = 300;
    
    // MC: Snakes were our first example entity we used to learn how to make entities work.  They are not within the spirit
    // of the Star Wars world here, so i should probably comment them out.  Future coders should think this through.
    
    // Entities.
    public static SnakeEntity snake = new SnakeEntity(null);    
    public static TuskanRaiderEntity tuskanRaider = new TuskanRaiderEntity(null);
    public static JawaEntity jawa = new JawaEntity(null);
    public static WookieEntity wookie = new WookieEntity(null);
    public static ImperialProbeDroidEntity imperialProbeDroid = new ImperialProbeDroidEntity(null);
    public static StormTrooperEntity stormTrooper = new StormTrooperEntity(null);
    
    // Ships!
    public static TieFighterEntity tieFighter = new TieFighterEntity(null);
    public static XWingFighterEntity xWingFighter = new XWingFighterEntity(null);
    public static ShuttleEntity shuttle = new ShuttleEntity(null);
    public static ImperialShuttleEntity imperialShuttle = new ImperialShuttleEntity(null);
       
    // Republic Utility Droid objects.
    public static DroidParts droidParts = new DroidParts("droidParts");
    public static DroidKit droidKit = new DroidKit("droidKit");
    public static RepublicHunterDroidHead republicHunterDroidHead = new RepublicHunterDroidHead("republicHunterDroidHead");
    public static RepublicPatrolDroidHead republicPatrolDroidHead = new RepublicPatrolDroidHead("republicPatrolDroidHead");
    public static RepublicSentryDroidHead republicSentryDroidHead = new RepublicSentryDroidHead("republicSentryDroidHead");
    public static RepublicHunterDroidEntity republicHunterDroid = new RepublicHunterDroidEntity(null);
    public static RepublicPatrolDroidEntity republicPatrolDroid = new RepublicPatrolDroidEntity(null);
    public static RepublicSentryDroidEntity republicSentryDroid = new RepublicSentryDroidEntity(null);
                  
    // Special eggs that can be used in both creative and survival mode, different from spawn eggs.  More like chicken eggs.
    public static GenericEgg snakeEgg = new GenericEgg("snakeEgg", SnakeEntity.class);
    public static GenericEgg tuskanRaiderEgg = new GenericEgg("tuskanRaiderEgg", TuskanRaiderEntity.class);
    public static GenericEgg jawaEgg = new GenericEgg("jawaEgg", JawaEntity.class);
    public static GenericEgg wookieEgg = new GenericEgg("wookieEgg", WookieEntity.class);
    public static GenericEgg imperialProbeDroidEgg = new GenericEgg("imperialProbeDroidEgg", ImperialProbeDroidEntity.class);
    public static GenericEgg stormTrooperEgg = new GenericEgg("stormTrooperEgg", StormTrooperEntity.class);
    
    public EntityManager() {}
    
    
    public int getUniqueEntityId() {
    	return this._startEntityId++;
    }
    
    
    //public static EntityLivingBase createEntity(Class classToMake, World world, BlockPos blockPos) {
    public static Entity createEntity(Class classToMake, World world, BlockPos blockPos) {
    		      	
    	// Now, on to the Render object;
    	// Create an array that defines the type for each parameter.  That is how we
    	// query for the constructor (we get back the one that matches the parameters we request). 
    	Class parameterTypes[] = new Class[1];
    	parameterTypes[0] = World.class;

    	// OK, let's query for that render constructor.
    	Constructor constructor = EntityManager.getConstructor(classToMake, parameterTypes);
    	if (constructor == null) {
    		return null;
    	}
    	
    	// OK, now we create an array that stores the actual parameter values for the render constructor.
    	Object parameterValues[] = new Object[1];
    	parameterValues[0] = world;
    	
    	// Last step, let's call the constructor with the array of parameter values.
    	Object instance = EntityManager.newInstance(constructor, parameterValues);
    	if (instance == null) {
    		System.out.println("ERROR! createEntity() failed to create an object.");
    		return null;
    	}
    	//if (!(instance instanceof EntityLivingBase)) {
    	if (!(instance instanceof Entity)) {
    	   	    		System.out.println("ERROR! createEntity() created unexpected object.");
    		return null;
    	}
		
    	//EntityLivingBase entity = (EntityLivingBase) instance; 
    	Entity entity = (Entity) instance; 
    	
    	// Set the proper location of the entity.
    	if (blockPos != null) {
    		double x = blockPos.getX() + 0.5;
    		double y = blockPos.getY();
    		double z = blockPos.getZ() + 0.5;
    		entity.setLocationAndAngles(x, y, z, 0.0F, 0.0F);
    	}
    	else {
    		System.out.println("ERROR: handed null blockPos in EntityManager:createEntity(..)");
    	}
          
		// Actually spawn the entity into the world.
		world.spawnEntity(entity);
      
		// OK, let's return what we got.
    	return entity;
    }
 
 
    public static Constructor getConstructor(Class classToMake, Class parameterTypes[]) {
    	
    	// Let's query for that constructor.
    	Constructor constructor = null;
		try {
			constructor = classToMake.getConstructor(parameterTypes);
		} catch (NoSuchMethodException e) {
			System.out.println("EntityManager: sent in a bogus class: " + classToMake.getName());
			return null;
		} catch (SecurityException e) {
			System.out.println("EntityManager: weird security issue.  Bailing.");
			return null;
		}
		
		return constructor;
    }
    
    
    public static Object newInstance(Constructor constructor, Object parameterValues[]) {
    
    	// Let's call the constructor with the array of parameter values.
    	Object newObject = null;
    	
		try {
			newObject = constructor.newInstance(parameterValues);
		}
		catch (InstantiationException e1) {
				System.out.println("EntityManager: weird instantiation issue.  Bailing.");
				return null;
		} catch (IllegalAccessException e1) {
				System.out.println("EntityManager: weird access issue.  Bailing.");
				return null;
		} catch (IllegalArgumentException e1) {
				System.out.println("EntityManager: weird argument issue.  Bailing.");
				return null;
		} catch (InvocationTargetException e1) {
				System.out.println("EntityManager: weird target issue.  Bailing.");
				return null;
		}
    	
		return newObject;
    }
}
