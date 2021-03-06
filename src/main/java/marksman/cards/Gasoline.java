package marksman.cards;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import marksman.MarksmanMod;
import marksman.actions.ApplyBurningAction;
import marksman.patches.AbstractCardEnum;

public class Gasoline extends CustomCard{
	public static final String ID = "Gasoline";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UP_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 2;
	private static final int MAGIC = 2;
	private static final int MAGIC_UP = 1;

	public Gasoline() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ALL_ENEMY);
		this.magicNumber = this.baseMagicNumber = MAGIC;
		this.exhaust = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Gasoline();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			this.rawDescription = UP_DESCRIPTION;
			this.initializeDescription();
			this.upgradeMagicNumber(MAGIC_UP);
			
				
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {		
		for (int i = 0; i < this.magicNumber; i++) {
			if(p.hasPower("BurningPower")) {
				int pamt = p.getPower("BurningPower").amount;
				AbstractDungeon.actionManager.addToBottom(new ApplyBurningAction(p, p, pamt));
			}
			for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {	
				if(!mo.hasPower("BurningPower"))
					continue;
				int amt = mo.getPower("BurningPower").amount;
				AbstractDungeon.actionManager.addToBottom(new ApplyBurningAction(mo, p, amt));
			}
		}
	}
}
