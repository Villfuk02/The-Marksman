package marksman.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;



import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import marksman.MarksmanMod;

public class ConcentratedPower extends AbstractPower
{
	public static final String POWER_ID = "ConcentratedPower";
	
    public static final String[] DESCRIPTIONS = new String[] {
    		"Doubles #yCrit chance of all atacks with #yCrit for #b",
    		" turns.",
    		"Doubles #yCrit chance of all atacks with #yCrit for this turn."
	};
    
    public ConcentratedPower(final AbstractCreature owner, final int newAmount) {
        this.name = "Concentrated";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = newAmount;
        this.updateDescription();
        this.img = new Texture(MarksmanMod.POWER_IMG_PATH + name + ".png");
    }  
    
    @Override
    public void updateDescription() {
    	if(amount > 1) {
    		this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    	}else {
    		this.description = DESCRIPTIONS[2];
    	}
    }   
    
    @Override
    public void atEndOfTurn(final boolean isPlayer) {
    	AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, ID, 1));
    }
}
