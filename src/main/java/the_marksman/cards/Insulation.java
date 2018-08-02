package the_marksman.cards;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.random.Random;

import basemod.abstracts.CustomCard;
import the_marksman.AbstractCardEnum;
import the_marksman.powers.BurningPower;

public class Insulation extends CustomCard{
	public static final String ID = "Insulation";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UP_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 1;
	private static final int MAGIC = 4;
	private static final int MAGIC_UP = 1;	
	
	Random rand = new Random();
	

	public Insulation() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
		this.baseMagicNumber = this.magicNumber = MAGIC;
		this.retain = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Insulation();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeMagicNumber(MAGIC_UP);

			this.rawDescription = UP_DESCRIPTION;
			this.initializeDescription();			
		} 
	}
	
	@Override
	public void atTurnStart() {
		retain = true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {		
		int amt = 0;
		
		CardGroup tmp = new CardGroup(CardGroupType.UNSPECIFIED);
		
		for (final AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.cardID == "Burn") {
            	tmp.addToTop(c);
            	amt++;
            }
        }
		for (int i = 0; i < amt; i++) {
			AbstractCard c = tmp.getTopCard();
			AbstractDungeon.player.discardPile.removeCard(c);
        	AbstractDungeon.player.drawPile.addToTop(c);
        	AbstractDungeon.actionManager.addToBottom(new PlayTopCardAction(m, true));
        	tmp.removeTopCard();
			for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new BurningPower(mo, this.magicNumber), this.magicNumber, true));
			}
		}
		
		tmp.clear();
		if(!upgraded)
			return;
		
		for (final AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.cardID == "Burn") {
            	tmp.addToTop(c);
            	amt++;
            }
        }
		for (int i = 0; i < amt; i++) {
			AbstractCard c = tmp.getTopCard();
			AbstractDungeon.player.drawPile.removeCard(c);
        	AbstractDungeon.player.drawPile.addToTop(c);
        	AbstractDungeon.actionManager.addToBottom(new PlayTopCardAction(m, true));
        	tmp.removeTopCard();
			for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new BurningPower(mo, this.magicNumber), this.magicNumber, true));
			}
		}
    }			
}

