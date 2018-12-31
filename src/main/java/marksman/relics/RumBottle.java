package marksman.relics;


import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import marksman.MarksmanMod;
import marksman.powers.ConcentratedPower;
import marksman.powers.PrecisionPower;

public class RumBottle extends CustomRelic {
	public static final String ID = "RumBottle";
	
	public RumBottle() {
		super(ID, new Texture(MarksmanMod.RELIC_IMG_PATH + ID + ".png"),
				RelicTier.RARE, LandingSound.CLINK); 
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	
	@Override
    public void atTurnStart() {
        flash();        
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PrecisionPower(AbstractDungeon.player, -8), -8));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ConcentratedPower(AbstractDungeon.player, 1), 1));
    }
    
	
	@Override
	public AbstractRelic makeCopy() {
		return new RumBottle();
	}
	
}