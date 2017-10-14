package superdopesquad.superdopejedimod;


import java.util.Random;
import net.minecraft.world.World;


public interface SuperDopeObjectGeneratable extends SuperDopeObject {

	default public void generateEnd(World world, Random random, int i, int j) {
	}
	
	default public void generateSurface(World world, Random random, int i, int j) {
	}
	
	default public void generateNether(World world, Random random, int i, int j) {
	}
}
