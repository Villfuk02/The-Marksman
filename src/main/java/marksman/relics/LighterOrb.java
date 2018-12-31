package marksman.relics;


import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import marksman.MarksmanMod;
import marksman.powers.BurningPower;

public class LighterOrb extends CustomRelic {
	public static final String ID = "LighterOrb";
	
	public LighterOrb() {
		super(ID, new Texture(MarksmanMod.RELIC_IMG_PATH + ID + ".png"),
				RelicTier.RARE, LandingSound.MAGICAL); 
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	@Override
	public void onEvokeOrb(AbstractOrb orb){
		flash();
		for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, new BurningPower(mo, 1), 1));
	    }
	}
    
	
	@Override
	public AbstractRelic makeCopy() {
		return new LighterOrb();
	}
	
}