package the_marksman.cards;


import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import the_marksman.AbstractCardEnum;

public class FirstAidKit extends CustomCard{
	public static final String ID = "FirstAidKit";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UP_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
	private static final int COST = 1;

	public FirstAidKit() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
	}

	@Override
	public AbstractCard makeCopy() {
		return new FirstAidKit();
	}

	@Override
	public void upgrade() {
		if(!upgraded) {
			upgradeName();
			
			this.retain = true;
			this.rawDescription = UP_DESCRIPTION;
			this.initializeDescription();
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
        final int count = GameActionManager.damageReceivedThisTurn;
        this.rawDescription = getDesc() + EXTENDED_DESCRIPTION[0] + count + EXTENDED_DESCRIPTION[1];        
        this.initializeDescription();
    }
    
    @Override
    public void onMoveToDiscard() {
        this.rawDescription = getDesc();
        this.initializeDescription();
    }

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {		
		AbstractDungeon.actionManager.addToBottom(new HealAction(p,p,GameActionManager.damageReceivedThisTurn));
	}
	
	private String getDesc() {
		if(upgraded)
			return UP_DESCRIPTION;
		return DESCRIPTION;
	}
}
