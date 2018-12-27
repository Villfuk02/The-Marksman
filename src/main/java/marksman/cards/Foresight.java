package marksman.cards;


import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import marksman.MarksmanMod;
import marksman.patches.AbstractCardEnum;

public class Foresight extends CustomCard{
	public static final String ID = "Foresight";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int BLOCK = 11;
	private static final int BLOCK_UP = 4;

	public Foresight() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
		this.baseBlock = BLOCK;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Foresight();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			this.upgradeBlock(BLOCK_UP);
			
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {		
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		AbstractDungeon.actionManager.addToBottom(new DiscardPileToTopOfDeckAction(p));
	}
}
