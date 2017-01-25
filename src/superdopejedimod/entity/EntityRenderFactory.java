package superdopesquad.superdopejedimod.entity;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraftforge.fml.client.registry.IRenderFactory;


public class EntityRenderFactory implements IRenderFactory<EntityLiving> {

	
	private Class _renderClass;
	private Class _modelClass;
	private float _shadowSize;
	
	
	public EntityRenderFactory(Class renderClass, Class modelClass, float shadowSize) {
		
		super();
		
		this._renderClass = renderClass;
		this._modelClass = modelClass;
		this._shadowSize = shadowSize;
	}
    
	
    @Override
    public Render<? super EntityLiving> createRenderFor(RenderManager manager) {
    	
    	// Let's create a new model object.  This is simple, since it takes no parameters.
    	// First, grab the constructor.
    	Constructor modelConstructor = this.getConstructor(this._modelClass, new Class[0]);
    	if (modelConstructor == null) {
    		return null;
    	}
		
		// Now that we have the model constructor, call it to make a model object.
		Object modelInstance = this.newInstance(modelConstructor, new Object[0]);
		if (modelInstance == null) {
			return null;
		}
    	
    	// Now, on to the Render object;
    	// Create an array that defines the type for each parameter.  That is how we
    	// query for the constructor (we get back the one that matches the parameters we request). 
    	Class parameterTypes[] = new Class[3];
    	parameterTypes[0] = RenderManager.class; // manager.getClass();
    	parameterTypes[1] = ModelBase.class;
    	parameterTypes[2] = float.class;
    	
    	// OK, let's query for that render constructor.
    	Constructor renderConstructor = this.getConstructor(this._renderClass, parameterTypes);
    	if (renderConstructor == null) {
    		return null;
    	}
    	
    	// OK, now we create an array that stores the actual parameter values for the render constructor.
    	Object parameterValues[] = new Object[3];
    	parameterValues[0] = manager;
    	parameterValues[1] = modelInstance;
    	parameterValues[2] = this._shadowSize;
    	
    	// Last step, let's call the constructor with the array of parameter values.
    	Object renderInstance = this.newInstance(renderConstructor, parameterValues);
    	if (renderInstance == null) {
    		return null;
    	}
		
		// OK, let's return what we got.
    	return (Render<? super EntityLiving>) renderInstance;
    }
    
    
    private Constructor getConstructor(Class classToMake, Class parameterTypes[]) {
    	
    	// Let's query for that constructor.
    	Constructor constructor = null;
		try {
			constructor = classToMake.getConstructor(parameterTypes);
		} catch (NoSuchMethodException e) {
			System.out.println("EntityRenderFactory: sent in a bogus renderClass: " + classToMake.getName());
			return null;
		} catch (SecurityException e) {
			System.out.println("EntityRenderFactory: weird security issue.  Bailing.");
			return null;
		}
		
		return constructor;
    }
    
    
    private Object newInstance(Constructor constructor, Object parameterValues[]) {
    
    	// Let's call the constructor with the array of parameter values.
    	Object newObject = null;
    	
		try {
			newObject = constructor.newInstance(parameterValues);
		}
		catch (InstantiationException e1) {
				System.out.println("EntityRenderFactory: weird instantiation issue.  Bailing.");
				return null;
		} catch (IllegalAccessException e1) {
				System.out.println("EntityRenderFactory: weird access issue.  Bailing.");
				return null;
		} catch (IllegalArgumentException e1) {
				System.out.println("EntityRenderFactory: weird argument issue.  Bailing.");
				return null;
		} catch (InvocationTargetException e1) {
				System.out.println("EntityRenderFactory: weird target issue.  Bailing.");
				return null;
		}
    	
		return newObject;
    }
}
