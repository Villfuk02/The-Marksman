package marksman.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marksman.MarksmanMod;
import marksman.actions.ApplyBurningAction;

public class FirepowerPower extends AbstractPower
{
	public static final String POWER_ID = "FirepowerPower";
	
    public static final String[] DESCRIPTIONS = new String[] {
    		"Every time you gain #yBurning, remove the effect and apply #b",
    		" that much burning to ALL enemies.",
    		" times as much burning to ALL enemies.",
	};
    
    public FirepowerPower(final AbstractCreature owner, final int newAmount) {
        this.name = "Firepower";
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
    	if(target == AbstractDungeon.player && power.ID == BurningPower.POWER_ID) {
	    	this.flash();	    	
	    	AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(target, target, BurningPower.POWER_ID, power.amount));
	    	
	    	int amt = power.amount * this.amount;
	    	
	    	for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
	    		AbstractDungeon.actionManager.addToBottom(new ApplyBurningAction(mo, owner, amt));
	    	}
		}
    }   
}
