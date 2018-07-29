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

public class AmmoBox extends CustomCard{
	public static final String ID = "AmmoBox";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int DRAW = 3;
	private static final int UPGRADE = 1;
	
	private boolean played = false;

	public AmmoBox() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
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
	}
	
	@Override
    public boolean hasEnoughEnergy() {
        if (played)
        	return false;
        return super.hasEnoughEnergy();
    }	
	
	@Override
	public void atTurnStart() {
		played = false;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {		
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));  
		played = true;
	}
}
