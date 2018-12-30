package marksman.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;



import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import marksman.MarksmanMod;

public class PrecisionPower extends AbstractPower
{
	public static final String POWER_ID = "PrecisionPower";
	
    public static final String[] DESCRIPTIONS = new String[] {
    		"This turn, increases #yCrit chance of all atacks with #yCrit by #b",
    		"This turn, decreases #yCrit chance of all atacks with #yCrit by #b",
    		"%."
	};
    
    public PrecisionPower(final AbstractCreature owner, final int newAmount) {
        this.name = "Precision";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = newAmount;
        this.canGoNegative = true;
        this.updateDescription();
        this.img = new Texture(MarksmanMod.POWER_IMG_PATH + name + ".png");
    }  
    
    @Override
    public void updateDescription() {
    	if(this.amount < 0) {
    		this.description = DESCRIPTIONS[1] + -this.amount + DESCRIPTIONS[2];
    	}else {
        	this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];    		
    	}
    }   
    
    @Override
    public void atEndOfTurn(final boolean isPlayer) {
    	AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ID));
    }
}
