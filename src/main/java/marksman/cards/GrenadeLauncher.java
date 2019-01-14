package marksman.cards;

import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import marksman.MarksmanMod;
import marksman.patches.AbstractCardEnum;
import marksman.actions.PlayCardFromHandAction;

public class GrenadeLauncher extends CustomCard{
	public static final String ID = "GrenadeLauncher";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 3;
	private static final int MAGIC = 2;
	private static final int MAGIC_UP = 1;

	public GrenadeLauncher() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ALL_ENEMY);
		this.baseMagicNumber = this.magicNumber = MAGIC;
	}

	@Override
	public AbstractCard makeCopy() {
		return new GrenadeLauncher();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.initializeDescription();
			this.retain = true;
			this.upgradeMagicNumber(MAGIC_UP);
		} 
	}
	
	@Override
	public void atTurnStart() {
		if(upgraded)
			retain = true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {	
		
		int amt = this.magicNumber;
		
		AbstractCard g = null;		
		for(int i = 0; i < amt; i++) {
			for (final AbstractCard c : AbstractDungeon.player.drawPile.group) {
				if (c.name.contains(" Grenade")) {
		            g = c;
		            break;
		            
		        }				
		    }	
			if(g != null) {
				AbstractDungeon.player.drawPile.removeCard(g);
	            AbstractDungeon.player.drawPile.addToTop(g);
	            AbstractDungeon.actionManager.addToBottom(new PlayTopCardAction(m, false));
	            amt--;
	            if(amt <= 0) return;
			}
		}
		
		g = null;
		for(int i = 0; i < amt; i++) {
			for (final AbstractCard c : AbstractDungeon.player.discardPile.group) {
		       if (c.name.contains(" Grenade")) {
		            g = c;
		            break;
		       }
		       if(g != null) {
		            AbstractDungeon.player.discardPile.removeCard(g);
		            AbstractDungeon.player.drawPile.addToTop(g);
		            AbstractDungeon.actionManager.addToBottom(new PlayTopCardAction(m, false));
		            amt--;
		            if(amt <= 0) return;
		       }
		    }	
		}
		
		for(int i = 0; i < amt; i++) {
			for (final AbstractCard c : AbstractDungeon.player.hand.group) {
				if (c.name.contains(" Grenade")) {
		            AbstractDungeon.actionManager.addToBottom(new PlayCardFromHandAction(m, c));
		            amt--;
		            if(amt <= 0) return;
		            break;
				}
			}
		}
    }			
}

