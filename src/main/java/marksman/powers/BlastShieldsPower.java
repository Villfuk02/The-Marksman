package marksman.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;



import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marksman.MarksmanMod;

public class BlastShieldsPower extends AbstractPower
{
	public static final String POWER_ID = "BlastShieldsPower";
	
    public static final String[] DESCRIPTIONS = new String[] {
    		"Every time you use a #yGrenade, gain #b",
    		" Block."
	};
    
    public BlastShieldsPower(final AbstractCreature owner, final int newAmount) {
        this.name = "Blast Shields";
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
    public void onPlayCard(final AbstractCard c, final AbstractMonster m) {
    	if(c.name.contains(" Grenade")) {
    		this.flash();
    		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(owner, owner, amount));
    	}
    }
}
