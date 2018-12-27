package marksman.cards;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

import basemod.abstracts.CustomCard;
import marksman.MarksmanMod;
import marksman.patches.AbstractCardEnum;

public class Kevlar extends CustomCard{
	public static final String ID = "Kevlar";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int BLOCK = 6;
	private static final int BLOCK_UP = 1;
	private static final int MAGIC = 3;
	private static final int MAGIC_UP = 1;

	public Kevlar() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
		this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = MAGIC;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Kevlar();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			this.upgradeBlock(BLOCK_UP);
			this.upgradeMagicNumber(MAGIC_UP);
			
				
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {			
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));  
		if(!p.hasPower("Plated Armor") || p.getPower("Plated Armor").amount < this.block)
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PlatedArmorPower(p, this.magicNumber),  this.magicNumber));
	}
}
