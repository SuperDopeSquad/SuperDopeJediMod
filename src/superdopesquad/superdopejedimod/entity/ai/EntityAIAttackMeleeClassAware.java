package superdopesquad.superdopejedimod.entity.ai;


import java.util.Arrays;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.player.EntityPlayer;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.faction.ClassInfo;


public class EntityAIAttackMeleeClassAware extends EntityAIAttackMelee {

	
	private ClassInfo[] _classesToAttack;
	
	
	public EntityAIAttackMeleeClassAware(EntityCreature creature, double speedIn, boolean useLongMemory, ClassInfo[] classsToAttack) {
		
		super(creature, speedIn, useLongMemory);	
		
		this._classesToAttack = classsToAttack;
	}
	
	
	@Override
	public boolean shouldExecute() {
	
		EntityLivingBase entity = this.attacker.getAttackTarget();
		if (entity == null) {
			return false;
		}
				
		if (entity instanceof EntityPlayer) {
			
			ClassInfo classInfo = SuperDopeJediMod.classManager.getPlayerClass((EntityPlayer)entity);
			
			// Debug info.
			String attackTargetName = entity.getName();
			String className = classInfo.getName();
			//System.out.println("DEBUG: Contemplating attacking " + attackTargetName + ", of faction " + factionName );
			
			// Error handling: if factionsToAttack was not initialized properly.
			if (this._classesToAttack == null) {
				//System.out.println("DEBUG: There is noone i like to attack");
				return false;
			}
			
			// Test to see if target's classInfo is in my list of things to attack.
			boolean shouldAttack = Arrays.asList(this._classesToAttack).contains(classInfo);
			//System.out.println("DEBUG: Attack? " + shouldAttack);
			return shouldAttack;
		}
			
		return super.shouldExecute();
	}
}
