package marksman.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import marksman.MarksmanMod;

public class VeteranPower extends AbstractPower
{
	public static final String POWER_ID = "VeteranPower";
	
    public static final String[] DESCRIPTIONS = new String[] {
    		"Every turn, gain #b",
    		" #yStrength. Lose all of it whenever you have empty draw pile."
	};
    
    public int bonus = 0;
    
    public VeteranPower(final AbstractCreature owner, final int newAmount) {
        this.name = "Veteran";
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
    public void atStartOfTurn() {
    	this.flash();
    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner,new StrengthPower(owner, this.amount), this.amount));
    	bonus += this.amount;
    }
    
    @Override
    public void onDrawOrDiscard() {
    	if(AbstractDungeon.player.drawPile.size() == 0)
    		onShuffle();
    }
    
    
    public void onShuffle() {
    	this.flash();
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner,new StrengthPower(owner, -bonus), -bonus));
    	bonus = 0;
    }
   
}
