package marksman.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;



import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marksman.MarksmanMod;

public class FlammableFumesPower extends AbstractPower
{
	public static final String POWER_ID = "FlammableFumesPower";
	
    public static final String[] DESCRIPTIONS = new String[] {
    		"At the end of every turn, increase ALL #yBurning by #b",
    		"."
	};
    
    public FlammableFumesPower(final AbstractCreature owner, final int newAmount) {
        this.name = "Flammable Fumes";
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
    public void atStartOfTurnPostDraw() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            for (final AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if (!m.isDead && !m.isDying && m.hasPower(BurningPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, this.owner, new BurningPower(m, this.amount), this.amount));
                }
            }
        }
        if (owner.hasPower(BurningPower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, this.owner, new BurningPower(owner, this.amount), this.amount));
        }
    }
}
