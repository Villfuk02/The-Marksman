package marksman.relics;


import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import marksman.MarksmanMod;
import marksman.patches.TheMarksmanEnum;
import marksman.powers.BurningPower;

public class InfiniteLighter extends CustomRelic {
	public static final String ID = "InfiniteLighter";
	
	public InfiniteLighter() {
		super(ID, new Texture(MarksmanMod.RELIC_IMG_PATH + ID + ".png"),
				RelicTier.UNCOMMON, LandingSound.CLINK); 
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new InfiniteLighter();
	}
	
	@Override
    public boolean canSpawn() {
		if(AbstractDungeon.player.hasRelic(PoisonLighter.ID))
			return true;
		if(AbstractDungeon.player.hasRelic(HellfireLighter.ID))
			return true;
		if(AbstractDungeon.player.hasRelic(LighterOrb.ID))
			return true;
		if(AbstractDungeon.player.chosenClass == TheMarksmanEnum.THE_MARKSMAN)
			return true;
		return false;
	}
	
	public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
		if(target != null && power.ID == BurningPower.POWER_ID && source == AbstractDungeon.player) {
	    	flash();	    	
	    	AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, null, new BurningPower(target, 1), 1));
		}
	}
	
}