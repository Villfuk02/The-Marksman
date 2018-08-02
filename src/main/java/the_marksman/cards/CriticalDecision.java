package the_marksman.cards;


import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import the_marksman.AbstractCardEnum;

public class CriticalDecision extends CustomCard{
	public static final String ID = "CriticalDecision";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int MAGIC = 3;
	private static final int MAGIC_UP = 2;

	public CriticalDecision() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
		this.baseMagicNumber = this.magicNumber = MAGIC;
		this.isEthereal = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new CriticalDecision();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			this.upgradeMagicNumber(MAGIC_UP);
			
			
		}
	}	

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		int pr = 0;
		if(p.getPower("CritsThisTurn") != null) {
			pr = p.getPower("CritsThisTurn").amount;
		}
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, pr*this.magicNumber));
	}
}
