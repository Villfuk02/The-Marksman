package marksman.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import marksman.MarksmanMod;

public class GhostFormPower extends AbstractPower
{
	public static final String POWER_ID = "GhostFormPower";
	
    public static final String[] DESCRIPTIONS = new String[] {
    		"All cards are #yEthereal."
	};
    
    public GhostFormPower(final AbstractCreature owner, final int newAmount) {
        this.name = "Ghost Form";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = newAmount;
        this.updateDescription();
        this.img = new Texture(MarksmanMod.POWER_IMG_PATH + name + ".png");
    }  
    
    @Override
    public void updateDescription() {
    	this.description = DESCRIPTIONS[0];
    }    
    
    @Override
    public void atEndOfTurn(final boolean isPlayer) {
    	this.flash();
    	for (final AbstractCard c : AbstractDungeon.player.hand.group) {
            c.isEthereal = true;
            c.flash();
        }
    }
   
}
