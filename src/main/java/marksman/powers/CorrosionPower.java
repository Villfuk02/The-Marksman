package marksman.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;



import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import marksman.MarksmanMod;

public class CorrosionPower extends AbstractPower
{
	public static final String POWER_ID = "CorrosionPower";
	
    public static final String[] DESCRIPTIONS = new String[] {
    		"At the end of the turn, removes all #yBlock. Lasts for #b",
    		" turns."
	};
    
    public CorrosionPower(final AbstractCreature owner, final int newAmount) {
        this.name = "Corrosion";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = newAmount;
        this.type = AbstractPower.PowerType.DEBUFF;
        this.updateDescription();
        this.img = new Texture(MarksmanMod.POWER_IMG_PATH + name + ".png");
    }  
    
    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }         
    
    @Override
    public void atEndOfTurn(final boolean isPlayer) {
    	if(owner.currentBlock > 0) {
    		this.flash();    	
    		owner.loseBlock();
    	}
    	AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, ID, 1));    	
    }
    
    
    
}
