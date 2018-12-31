package marksman.relics;


import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import marksman.MarksmanMod;
import marksman.powers.CritsThisTurn;

public class RustyHelmet extends CustomRelic {
	public static final String ID = "RustyHelmet";
	
	public RustyHelmet() {
		super(ID, new Texture(MarksmanMod.RELIC_IMG_PATH + ID + ".png"),
				RelicTier.UNCOMMON, LandingSound.CLINK); 
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new RustyHelmet();
	}
	
	public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
		if(target == AbstractDungeon.player && power.ID == CritsThisTurn.POWER_ID) {
	    	flash();	    	
	    	AbstractDungeon.actionManager.addToTop(new GainBlockAction(target, target, 3));
		}
	}
	
}