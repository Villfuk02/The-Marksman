package the_marksman.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import basemod.abstracts.CustomCard;
import the_marksman.AbstractCardEnum;

public class Bayonet extends CustomCard{
	public static final String ID = "Bayonet";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UP_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 0;
	private static final int DMG = 5;
	private static final int DMG_UP = 2;

	public Bayonet() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
		this.exhaust = true;
		this.baseDamage = DMG;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Bayonet();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.name += "+";
			this.upgradeDamage(DMG_UP);
			this.rawDescription = UP_DESCRIPTION;
			this.initializeDescription();
			this.retain = true;
			this.upgraded = true;
		}
	}	
	
	@Override
	public void atTurnStart() {
		if(upgraded)
			retain = true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		int pr = 0;
		if(p.getPower("CritsThisTurn") != null) {
			pr = p.getPower("CritsThisTurn").amount;
		}
		
		if(p.getPower("Vulnerable") == null) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new VulnerablePower(p, 1, false), 1));
		}
		
		for (int i = 0; i < pr; i++) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
	
		}
	}
}
