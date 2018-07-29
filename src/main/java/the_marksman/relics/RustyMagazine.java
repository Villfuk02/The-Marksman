package the_marksman.relics;


import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;

public class RustyMagazine extends CustomRelic {
	public static final String ID = "RustyMagazine";
	
	public RustyMagazine() {
		super(ID, new Texture("img/relics/"+ID+".png"), 
				RelicTier.STARTER, LandingSound.SOLID); 
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	@Override
    public void atBattleStart() {
        flash();        
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    	for (final AbstractCard c : AbstractDungeon.player.drawPile.group) {
                if (c.cardID == "RustyPistol" && c.canUpgrade()) {
                	c.upgrade(); 
                }
            }
    	}
    
	
	@Override
	public AbstractRelic makeCopy() {
		return new RustyMagazine();
	}
	
}