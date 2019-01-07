package marksman.relics;


import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import marksman.MarksmanMod;
import marksman.actions.ApplyBurningAction;
import marksman.powers.CritsThisTurn;

public class RustyLighter extends CustomRelic {
	public static final String ID = "RustyLighter";
	
	public RustyLighter() {
		super(ID, new Texture(MarksmanMod.RELIC_IMG_PATH + ID + ".png"),
				RelicTier.RARE, LandingSound.CLINK); 
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new RustyLighter();
	}
	
	public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
		if(power.ID == CritsThisTurn.POWER_ID && target == AbstractDungeon.player) {
	    	flash();	    	
	    	for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
	    		AbstractDungeon.actionManager.addToBottom(new ApplyBurningAction(mo, AbstractDungeon.player, 1));
		    }
		}
	}
	
}