package the_marksman.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import the_marksman.AbstractCardEnum;

public class ElectricBlood extends CustomCard{
	public static final String ID = "ElectricBlood";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int DMG = 1;
	private static final int DMG_UP = 2;
	private static final int SELF = 8;

	public ElectricBlood() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ALL);
		this.baseDamage = DMG;
	}

	@Override
	public AbstractCard makeCopy() {
		return new ElectricBlood();
	}

	@Override
	public void upgrade() {
		if(!upgraded) {
			upgradeName();
			
			this.upgradeDamage(DMG_UP);
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {		
		AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
		AbstractDungeon.actionManager.addToBottom(new DamageAction(p,new DamageInfo(p, SELF, DamageType.HP_LOSS),AbstractGameAction.AttackEffect.SLASH_VERTICAL));
		AbstractDungeon.actionManager.addToTop(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage, true), damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_VERTICAL));		
	}
}
