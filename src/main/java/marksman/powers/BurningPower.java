package marksman.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import marksman.MarksmanMod;

public class BurningPower extends AbstractPower implements HealthBarRenderPower
{
	public static final String POWER_ID = "BurningPower";
	
    public static final String[] DESCRIPTIONS = new String[] {
    		"At the start of the turn, take #b",
    		" damage. Can be #yBlocked. Decreases by the amount #yBlocked."
	};
    
    public BurningPower(final AbstractCreature owner, final int newAmount) {
        this.name = "Burning";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = newAmount;
        this.img = new Texture(MarksmanMod.POWER_IMG_PATH + name + ".png");
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        this.updateDescription();
    }  
    
    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    
    @Override
    public void atStartOfTurn() {
    	if(amount <= 0) {
    		AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(owner, null, this));
    		return;
    	}
    		
    	this.flash();
    	
    	if(AbstractDungeon.player.hasPower(FriendlyFirePower.POWER_ID))
    		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, amount*AbstractDungeon.player.getPower(FriendlyFirePower.POWER_ID).amount));    	
    	    	
    	AbstractDungeon.actionManager.addToTop(new DamageAction(owner, new DamageInfo(AbstractDungeon.player, amount, DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
    
    }
    
    @Override
    public void atEndOfTurn(boolean isPlayer){
    	if(amount <= 0) {
    		AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(owner, null, this));
    		return;
    	}
    	if(isPlayer || owner.currentBlock < 1)
    		return;
    	flash();
		int p = owner.currentBlock;
		if(p > amount)
			p = amount;
		AbstractDungeon.actionManager.addToTop(new DamageAction(owner, new DamageInfo(AbstractDungeon.player, p, DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
		AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, null, ID, p));
    }
    
    @Override
    public void onGainedBlock(float blockAmount){
    	if(amount <= 0) {
    		AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(owner, null, this));
    		return;
    	}
    	if(blockAmount < 1)
    		return;
    	flash();
		int p = (int) blockAmount;
		if(p > amount)
			p = amount;
		AbstractDungeon.actionManager.addToTop(new DamageAction(owner, new DamageInfo(AbstractDungeon.player, p, DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
		AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, null, ID, p));
    }  
        
    @Override
    public int getHealthBarAmount() {
        return this.amount;
    }
 
    @Override
    public Color getColor() {
        return Color.ORANGE;
    }
    
}
