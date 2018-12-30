package marksman.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.random.Random;

import basemod.abstracts.CustomCard;
import marksman.MarksmanMod;
import marksman.patches.AbstractCardEnum;
import marksman.powers.EnergizedBlackPower;

public class NuclearPower extends CustomCard{
	public static final String ID = "NuclearPower";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = -1;
	private static final int MAGIC = 2;
	private static final int MAGIC_UP = 1;
	
	Random rand = new Random();
	

	public NuclearPower() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
		this.baseMagicNumber = this.magicNumber = MAGIC;
	}

	@Override
	public AbstractCard makeCopy() {
		return new NuclearPower();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeMagicNumber(MAGIC_UP);
			
			
		} 
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		int effect = this.energyOnUse;
		if (!this.freeToPlayOnce) {
            p.energy.use(effect);
        }
		if (p.hasRelic("Chemical X")) {
	         effect += 2;
	         p.getRelic("Chemical X").flash();
	    }		
		int amt = effect * this.magicNumber;
		if(amt != 0)
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EnergizedBlackPower(p, amt), amt));
	}
}
