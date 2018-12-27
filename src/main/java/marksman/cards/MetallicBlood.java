package marksman.cards;

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
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.random.Random;

import basemod.abstracts.CustomCard;
import marksman.MarksmanMod;
import marksman.patches.AbstractCardEnum;

public class MetallicBlood extends CustomCard{
	public static final String ID = "MetallicBlood";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int MAGIC = 3;
	private static final int MAGIC_UP = 2;
	private static final int DMG = 6;
	
	Random rand = new Random();
	

	public MetallicBlood() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
		this.baseMagicNumber = this.magicNumber = MAGIC;
	}

	@Override
	public AbstractCard makeCopy() {
		return new MetallicBlood();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeMagicNumber(MAGIC_UP);			
			this.retain = true;
		} 
	}
	
	@Override
	public void atTurnStart() {
		if(upgraded)
			retain = true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {	
		AbstractDungeon.actionManager.addToBottom(new DamageAction(p,new DamageInfo(p, DMG, DamageType.HP_LOSS)));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new MetallicizePower(p, this.magicNumber), this.magicNumber));
	}
}
