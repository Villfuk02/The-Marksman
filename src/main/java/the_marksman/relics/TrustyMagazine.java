package the_marksman.relics;


import java.util.Iterator;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.abstracts.CustomRelic;
import the_marksman.cards.TrustyPistol;

public class TrustyMagazine extends CustomRelic {
	public static final String ID = "TrustyMagazine";
	
	public int count = 0;
	
	public TrustyMagazine() {
		super(ID, new Texture("img/relics/"+ID+".png"), 
				RelicTier.BOSS, LandingSound.SOLID); 
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	@Override
    public void obtain()
    {
        if (AbstractDungeon.player.hasRelic(RustyMagazine.ID)) {
            for (int i=0; i<AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(RustyMagazine.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }
	
	@Override
    public void onEquip() {		
		final Iterator<AbstractCard> i = AbstractDungeon.player.masterDeck.group.iterator();
        while (i.hasNext()) {
            final AbstractCard e = i.next();
            if (e.cardID.equals("RustyPistol")) {
                i.remove();
                this.count++;
            }
        }
        final CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (int j = 0; j < this.count; ++j) {
            final AbstractCard card = new TrustyPistol();
            UnlockTracker.markCardAsSeen(card.cardID);
            card.isSeen = true;
            group.addToBottom(card);
        }
        AbstractDungeon.gridSelectScreen.openConfirmationGrid(group, "Repaired.");
	}
    
	
	@Override
	public AbstractRelic makeCopy() {
		return new TrustyMagazine();
	}
	
}