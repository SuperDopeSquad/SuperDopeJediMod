package superdopesquad.superdopejedimod;

import java.util.Random;

import net.minecraft.world.World;

public interface SuperDopeObject {

	public void registerObject();
	public void registerModel();
	public void registerRecipe();
	public void generateEnd(World world, Random random, int i, int j);
	public void generateSurface(World world, Random random, int i, int j);
	public void generateNether(World world, Random random, int i, int j);
}
