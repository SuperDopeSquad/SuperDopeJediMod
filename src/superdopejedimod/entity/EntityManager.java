package superdopesquad.superdopejedimod.entity;


import superdopesquad.superdopejedimod.SuperDopeObject;


public class EntityManager {

	
	 // Entities.
    // MC-TODO: the first parameter should be a World instance (Minecraft.getMinecraft.theWorld), but i'm concerned
    // that this will crash on the server side.  Putting in null doesn't seem to have a harmful effect.  I need to
    // figure out later the downside of not having it, and if i need it, figure out the best way to get a handle
    // for it that is server-safe.
    private static int _startEntityId = 300;
    
    // MC: Snakes were our first example entity we used to learn how to make entities work.  They are not within the spirit
    // of the Star Wars world here, so i'm commenting them out.  Future coders can still uncomment it out and play with them if they want.
    
    // Entities.
    //public static SnakeEntity snake = new SnakeEntity(null);    
    public static TuskanRaiderEntity tuskanRaider = new TuskanRaiderEntity(null);
    public static JawaEntity jawa = new JawaEntity(null);
    public static WookieEntity wookie = new WookieEntity(null);
    public static ImperialProbeDroidEntity imperialProbeDroid = new ImperialProbeDroidEntity(null);
    
    // Special eggs that can be used in both creative and survival mode, different from spawn eggs.  More like chicken eggs.
    //public static GenericEgg snakeEgg = new GenericEgg("snakeEgg", SnakeEntity.class);
    public static GenericEgg tuskanRaiderEgg = new GenericEgg("tuskanRaiderEgg", TuskanRaiderEntity.class);
    public static GenericEgg jawaEgg = new GenericEgg("jawaEgg", JawaEntity.class);
    public static GenericEgg wookieEgg = new GenericEgg("wookieEgg", WookieEntity.class);
    public static GenericEgg imperialProbeDroidEgg = new GenericEgg("imperialProbeDroidEgg", ImperialProbeDroidEntity.class);
    
    
    public EntityManager() {}
    
    
    public int getUniqueEntityId() {
    	return this._startEntityId++;
    }
}
