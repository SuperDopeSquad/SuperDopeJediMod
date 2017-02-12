package superdopesquad.superdopejedimod.entity;


import java.util.Arrays;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.player.EntityPlayer;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.faction.FactionInfo;


public class EntityAIAttackMeleeFactionAware extends EntityAIAttackMelee {

	
	private FactionInfo[] _factionsToAttack;
	
	
	public EntityAIAttackMeleeFactionAware(EntityCreature creature, double speedIn, boolean useLongMemory, FactionInfo[] factionsToAttack) {
		
		super(creature, speedIn, useLongMemory);	
		
		this._factionsToAttack = factionsToAttack;
	}
	
	
	@Override
	public boolean shouldExecute() {
	
		EntityLivingBase entity = this.attacker.getAttackTarget();
		if (entity == null) {
			return false;
		}
				
		if (entity instanceof EntityPlayer) {
			
			FactionInfo factionInfo = SuperDopeJediMod.factionManager.getPlayerFaction((EntityPlayer)entity);
			
			// Debug info.
			String attackTargetName = entity.getName();
			String factionName = factionInfo.getName();
			//System.out.println("DEBUG: Contemplating attacking " + attackTargetName + ", of faction " + factionName );
			
			// Error handling: if factionsToAttack was not initialized properly.
			if (this._factionsToAttack == null) {
				//System.out.println("DEBUG: There is noone i like to attack");
				return false;
			}
		
			//for (FactionInfo fi : this._factionsToAttack) {
			//	System.out.println("DEBUG: People I like to attack: " + fi.getName() );
			//}
			
			// Test to see if target's factionInfo is in my list of things to attack.
			boolean shouldAttack = Arrays.asList(this._factionsToAttack).contains(factionInfo);
			//System.out.println("DEBUG: Attack? " + shouldAttack);
			return shouldAttack;
		}
			
		return super.shouldExecute();
	}
}
