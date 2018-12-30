package marksman.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import marksman.MarksmanMod;

public class CounterStrikePower extends AbstractPower
{
	public static final String POWER_ID = "CounterStrikePower";
	
    public static final String[] DESCRIPTIONS = new String[] {
    		"Every time you lose #yStrength, deal #b",
    		"  damage to ALL enemies and Gain #b1 #yDexterity."
	};
    
    public CounterStrikePower(final AbstractCreature owner, final int newAmount) {
        this.name = "Counter Strike";
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
    	if(target == AbstractDungeon.player && power.ID == StrengthPower.POWER_ID && power.amount < 0) {
	    	this.flash();    	
	    	AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(this.amount, true), DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
	    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, this.owner, new DexterityPower(target, 1), 1));
		}
    }   
}
