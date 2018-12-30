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
	public static final String UP_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 0;
	

	public GrenadeLauncher() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ALL_ENEMY);
	}

	@Override
	public AbstractCard makeCopy() {
		return new GrenadeLauncher();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = UP_DESCRIPTION;
			this.initializeDescription();
			this.retain = true;
			
		} 
	}
	
	@Override
	public void atTurnStart() {
		if(upgraded)
			retain = true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {		
		
		AbstractCard g;
		for (int i = 0; i < this.magicNumber; i++) {
			for (final AbstractCard c : AbstractDungeon.player.drawPile.group) {
	            if (c.name.contains(" Grenade")) {
	            	g = c;
	            	AbstractDungeon.player.drawPile.removeCard(g);
	            	AbstractDungeon.player.drawPile.addToTop(g);
	            	AbstractDungeon.actionManager.addToBottom(new PlayTopCardAction(m, true));
	            	return;
	            }
	        }	
			for (final AbstractCard c : AbstractDungeon.player.discardPile.group) {
	            if (c.name.contains(" Grenade")) {
	            	g = c;
	            	AbstractDungeon.player.discardPile.removeCard(g);
	            	AbstractDungeon.player.drawPile.addToTop(g);
	            	AbstractDungeon.actionManager.addToBottom(new PlayTopCardAction(m, true));
	            	return;
	            }
	        }	
			for (final AbstractCard c : AbstractDungeon.player.hand.group) {
	            if (c.name.contains(" Grenade")) {
	            	AbstractDungeon.actionManager.addToBottom(new PlayCardFromHandAction(m, c));
	            	return;
	            }
	        }
		}		
    }			
}

