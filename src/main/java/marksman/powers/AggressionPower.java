package marksman.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marksman.MarksmanMod;

public class AggressionPower extends AbstractPower
{
	public static final String POWER_ID = "AggressionPower";
	
    public static final String[] DESCRIPTIONS = new String[] {
    		"Every time you become #yVulnerable, remove the effect and ALL creatures gain #b",
    		" #yStrength for this turn."
	};
    
    public AggressionPower(final AbstractCreature owner, final int newAmount) {
        this.name = "Aggression";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = newAmount;
        this.updateDescription();
        this.img = new Texture(MarksmanMod.POWER_IMG_PATH + name + ".png");
    }  
    
    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }  
    
    @Override
    public void onApplyPower(final AbstractPower power, final AbstractCreature target, final AbstractCreature source) {
    	if(target == AbstractDungeon.player && power.ID == "Vulnerable") {
	    	this.flash();    	
	    	AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(target, target, "Vulnerable"));
	    	
	    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner,new StrengthPower(owner, this.amount), this.amount));
	    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner,new LoseStrengthPower(owner, this.amount), this.amount));
	    	
	    	for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
	    		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, owner,new StrengthPower(mo, this.amount), this.amount));
		    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, owner,new LoseStrengthPower(mo, this.amount), this.amount));
	    	}
		}
    }   
}
