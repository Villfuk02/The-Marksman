package marksman.relics;


import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import marksman.MarksmanMod;
import marksman.powers.BurningPower;

public class HellfireLighter extends CustomRelic {
	public static final String ID = "HellfireLighter";
	
	public HellfireLighter() {
		super(ID, new Texture(MarksmanMod.RELIC_IMG_PATH + ID + ".png"),
				RelicTier.RARE, LandingSound.CLINK); 
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new HellfireLighter();
	}
	
	public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
		if(target != null && power.ID == VulnerablePower.POWER_ID && source == AbstractDungeon.player) {
	    	flash();	    	
	    	for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, new BurningPower(mo, 1), 1));
		    }
		}
		if(power.ID == StrengthPower.POWER_ID && target == AbstractDungeon.player && power.amount > 0) {
	    	flash();	    	
	    	for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, new BurningPower(mo, 1), 1));
		    }
		}
	}
	
}