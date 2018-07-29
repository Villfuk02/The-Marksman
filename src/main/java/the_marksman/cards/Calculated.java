package the_marksman.cards;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import the_marksman.AbstractCardEnum;
import the_marksman.powers.PrecisionPower;

public class Calculated extends CustomCard{
	public static final String ID = "Calculated";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int MAGIC = 20;
	private static final int MAGIC_UP = 10;
	

	public Calculated() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
		this.baseMagicNumber = this.magicNumber = MAGIC;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Calculated();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.name += "+";
			this.upgradeMagicNumber(MAGIC_UP);
			this.magicNumber = MAGIC + MAGIC_UP;
			this.upgraded = true;
		}
	}	

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {		
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PrecisionPower(p, this.magicNumber), this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 2));
		AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, 2, false));
	}
}
