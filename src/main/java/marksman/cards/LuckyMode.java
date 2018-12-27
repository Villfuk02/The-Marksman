package marksman.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import basemod.abstracts.CustomCard;
import marksman.MarksmanMod;
import marksman.patches.AbstractCardEnum;
import marksman.powers.PrecisionPower;

public class LuckyMode extends CustomCard{
	public static final String ID = "LuckyMode";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UP_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 0;
	private static final int MAGIC = 35;
	private static final int MAGIC_UP = 15;

	public LuckyMode() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
		this.baseMagicNumber = this.magicNumber = MAGIC;
	}

	@Override
	public AbstractCard makeCopy() {
		return new LuckyMode();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			this.upgradeMagicNumber(MAGIC_UP);
			
		}
	}
	
	@Override
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		if(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() > 0) {
			return false;
		}
		return super.canUse(p, m);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {	
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WeakPower(p, 1, false), 1));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PrecisionPower(p, this.magicNumber), this.magicNumber));
	}
}
