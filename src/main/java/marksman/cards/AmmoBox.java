package marksman.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import marksman.MarksmanMod;
import marksman.patches.AbstractCardEnum;

public class AmmoBox extends CustomCard{
	public static final String ID = "AmmoBox";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int DRAW = 3;
	private static final int UPGRADE = 1;
	

	public AmmoBox() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
		this.baseMagicNumber = this.magicNumber = DRAW;
	}

	@Override
	public AbstractCard makeCopy() {
		return new AmmoBox();
	}

	@Override
	public void upgrade() {
		this.timesUpgraded++;
		this.name = NAME + "+" + timesUpgraded;		
		this.upgradeMagicNumber(UPGRADE);
		this.upgraded = true;
		this.initializeTitle();
	}
	
	@Override
    public boolean canUpgrade() {
        return true;
    }
	
	@Override
	public void atTurnStart() {
		this.updateCost(-this.cost);
		this.isCostModified = false;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {		
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));  
		this.modifyCostForTurn(1);
	}
}
