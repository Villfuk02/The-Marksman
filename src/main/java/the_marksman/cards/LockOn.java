package the_marksman.cards;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import basemod.abstracts.CustomCard;
import the_marksman.AbstractCardEnum;
import the_marksman.powers.ConcentratedPower;

public class LockOn extends CustomCard{
	public static final String ID = "LockOnBlack";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int UP_COST = 0;

	public LockOn() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
		this.exhaust = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new LockOn();
	}

	@Override
	public void upgrade() {
		if(!upgraded) {
			this.name += "+";
			this.upgraded = true;
			this.upgradeBaseCost(UP_COST);
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {		
		
		if(m.getPower("Vulnerable") == null) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, 1, false), 1));
		}
		for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if(mo != m) {
				AbstractDungeon.actionManager.addToBottom(new GainBlockAction(mo, p, 99));
			}
		}	
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new ConcentratedPower(p, 1)));		
	}
}
