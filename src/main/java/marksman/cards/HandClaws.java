package marksman.cards;


import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.cards.colorless.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import marksman.MarksmanMod;
import marksman.patches.AbstractCardEnum;

public class HandClaws extends CustomCard{
	public static final String ID = "HandClaws";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int AMT = 5;
	private static final int AMT_UP = 2;
	private static final int SELF = 6;

	public HandClaws() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
		this.baseDamage = AMT;
	}

	@Override
	public AbstractCard makeCopy() {
		return new HandClaws();
	}

	@Override
	public void upgrade() {
		if(!upgraded) {
			upgradeName();			
			this.upgradeDamage(AMT_UP);
		}
	}
	
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {		
		AbstractDungeon.actionManager.addToBottom(new DamageAction(p,new DamageInfo(p, SELF, DamageType.HP_LOSS)));
		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Shiv(), this.damage));
	}
}
