package marksman.cards;


import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import marksman.MarksmanMod;

public class Painkillers extends CustomCard{
	public static final String ID = "Painkillers";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
	private static final int COST = 1;
	private static final int MAGIC = 75;
	private static final int MAGIC_UP = 25;

	public Painkillers() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS,
        		AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
		magicNumber = baseMagicNumber = MAGIC;
		this.exhaust = true;
	}
	
	public Painkillers(boolean upgraded) {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS,
        		AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
		magicNumber = baseMagicNumber = MAGIC;
		this.exhaust = true;
		if(upgraded)
			upgrade();
	}

	@Override
	public AbstractCard makeCopy() {
		return new Painkillers();
	}

	@Override
	public void upgrade() {
		if(!upgraded) {
			upgradeName();
			this.upgradeMagicNumber(MAGIC_UP);
		}
	}
	@Override
	public void atTurnStart() {
		if(upgraded)
			retain = true;
	}
	
	@Override
    public void applyPowers() {
        super.applyPowers();
        final int count = (GameActionManager.damageReceivedThisTurn * magicNumber)/100;
        this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0] + count + EXTENDED_DESCRIPTION[1];        
        this.initializeDescription();
    }
    
    @Override
    public void onMoveToDiscard() {
        this.rawDescription = DESCRIPTION;
        this.initializeDescription();
    }

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {		
		AbstractDungeon.actionManager.addToBottom(new HealAction(p,p,(GameActionManager.damageReceivedThisTurn * magicNumber)/100));
	}
}
