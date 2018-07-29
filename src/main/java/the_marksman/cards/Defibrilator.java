package the_marksman.cards;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;

import basemod.abstracts.CustomCard;
import the_marksman.AbstractCardEnum;

public class Defibrilator extends CustomCard{
	public static final String ID = "Defibrilator";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UP_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 1;
	private static final int MAGIC = 14;
	private static final int MAGIC_UP = -3;

	public Defibrilator() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
		this.baseMagicNumber = this.magicNumber = MAGIC;
		this.exhaust = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Defibrilator();
	}

	@Override
	public void upgrade() {
		if(!upgraded) {
			this.name += "+";
			this.upgraded = true;
			this.upgradeMagicNumber(MAGIC_UP);
			this.isInnate = true;
			this.magicNumber = MAGIC + MAGIC_UP;
			this.rawDescription = UP_DESCRIPTION;
			this.initializeDescription();
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(p,new DamageInfo(p,this.magicNumber, DamageType.HP_LOSS)));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new RegenPower(p, 6), 6));
		
	}
}
