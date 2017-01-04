package superdopesquad.superdopejedimod.entity;


import superdopesquad.superdopejedimod.SuperDopeObject;


public class EntityManager {

	
	 // Entities.
    // MC-TODO: the first parameter should be a World instance (Minecraft.getMinecraft.theWorld), but i'm concerned
    // that this will crash on the server side.  Putting in null doesn't seem to have a harmful effect.  I need to
    // figure out later the downside of not having it, and if i need it, figure out the best way to get a handle
    // for it that is server-safe.
    private static int _startEntityId = 300;
    
    public static EntitySnake entitySnake = new EntitySnake(null);    
    public static EntityTuskanRaider entityTuskanRaider = new EntityTuskanRaider(null);
    public static EntityJawa entityJawa = new EntityJawa(null);
    public static EntityWookie entityWookie = new EntityWookie(null);
    
    
    public EntityManager() {
        
    	return;
    }
    
    
    public int getUniqueEntityId() {
    	
    	return this._startEntityId++;
    }
}
