package marksman.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import marksman.MarksmanMod;
import marksman.patches.AbstractCardEnum;

public class RocketJump extends CustomCard{
	public static final String ID = "RocketJump";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int DMG = 11;
	private static final int DMG_UP = 1;
	private static final int MAGIC = 4;
	private static final int MAGIC_UP = 1;

	public RocketJump() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF_AND_ENEMY);
		this.baseDamage = DMG;
		this.baseMagicNumber = this.magicNumber = MAGIC;
	}

	@Override
	public AbstractCard makeCopy() {
		return new RocketJump();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			this.upgradeDamage(DMG_UP);
			this.upgradeMagicNumber(MAGIC_UP);
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {	
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, damageTypeForTurn), AttackEffect.FIRE));
		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Burn(), 3));  
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
	}
}
