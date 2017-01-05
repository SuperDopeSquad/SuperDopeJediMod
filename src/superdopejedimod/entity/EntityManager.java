package superdopesquad.superdopejedimod.entity;


import superdopesquad.superdopejedimod.SuperDopeObject;


public class EntityManager {

	
	 // Entities.
    // MC-TODO: the first parameter should be a World instance (Minecraft.getMinecraft.theWorld), but i'm concerned
    // that this will crash on the server side.  Putting in null doesn't seem to have a harmful effect.  I need to
    // figure out later the downside of not having it, and if i need it, figure out the best way to get a handle
    // for it that is server-safe.
    private static int _startEntityId = 300;
    
    public static SnakeEntity snake = new SnakeEntity(null);    
    public static TuskanRaiderEntity tuskanRaider = new TuskanRaiderEntity(null);
    public static JawaEntity jawa = new JawaEntity(null);
    public static WookieEntity wookie = new WookieEntity(null);
    
    
    public EntityManager() {
        
    	return;
    }
    
    
    public int getUniqueEntityId() {
    	
    	return this._startEntityId++;
    }
}
