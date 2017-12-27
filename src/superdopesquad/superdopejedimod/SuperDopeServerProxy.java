package superdopesquad.superdopejedimod;


import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


public class SuperDopeServerProxy extends SuperDopeCommonProxy {

	
	@Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

	
	@Override
	public void init(FMLInitializationEvent e){
		
		super.init(e);
		
		//System.out.println("I'm on the server!  init event.");
	}

	
    @Override
    public void postInit(FMLPostInitializationEvent e) {
        
    	super.postInit(e);
        
     	//System.out.println("I'm in the server proxy! post init event.");
    }
}
