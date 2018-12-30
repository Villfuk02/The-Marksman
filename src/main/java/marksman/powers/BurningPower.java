package marksman.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import marksman.MarksmanMod;

public class BurningPower extends AbstractPower
{
	public static final String POWER_ID = "BurningPower";
	
    public static final String[] DESCRIPTIONS = new String[] {
    		"At the end of the turn, take #b",
    		" damage. #yBurning is decreased by the amount Blocked.  Also lowers Transient's damage."
	};
    
    public BurningPower(final AbstractCreature owner, final int newAmount) {
        this.name = "Burning";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = newAmount;
        this.updateDescription();
        this.img = new Texture(MarksmanMod.POWER_IMG_PATH + name + ".png");
        this.type = PowerType.DEBUFF;
        if(owner.hasPower("Shifting")) {
    		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new StrengthPower(owner, -this.amount), -this.amount)); 
    	}
    }  
    
    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    
    @Override
    public void atEndOfTurn(final boolean isPlayer) {
    	this.flash();
    	
    	if(!isPlayer && AbstractDungeon.player.hasPower(FriendlyFirePower.POWER_ID))
    		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, amount*AbstractDungeon.player.getPower(FriendlyFirePower.POWER_ID).amount));    	
    		
    	AbstractPower cor = owner.getPower(CorrosionPower.POWER_ID);
    	if(cor != null && cor.amount > 0) {
    	}else {
    		AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, ID, owner.currentBlock));
    	}    	 
    	
    	AbstractDungeon.actionManager.addToTop(new DamageAction(owner, new DamageInfo(AbstractDungeon.player, amount, DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
    
    }
}
