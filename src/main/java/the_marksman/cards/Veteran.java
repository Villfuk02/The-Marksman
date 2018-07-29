package the_marksman.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.random.Random;

import basemod.abstracts.CustomCard;
import the_marksman.AbstractCardEnum;
import the_marksman.powers.VeteranPower;

public class Veteran extends CustomCard{
	public static final String ID = "Veteran";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int MAGIC = 2;
	private static final int MAGIC_UP = -1;
	
	Random rand = new Random();
	

	public Veteran() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.POWER, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
		this.baseMagicNumber = this.magicNumber = MAGIC;
		this.isInnate = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Veteran();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.name += "+";
			this.upgradeMagicNumber(MAGIC_UP);
			this.magicNumber = MAGIC + MAGIC_UP;
			this.upgraded = true;
		} 
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {		
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, -this.magicNumber), -this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new VeteranPower(p, 0), 0));
	}
}
