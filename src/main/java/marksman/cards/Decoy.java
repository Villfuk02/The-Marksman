package marksman.cards;


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
import marksman.MarksmanMod;
import marksman.patches.AbstractCardEnum;

public class Decoy extends CustomCard{
	public static final String ID = "Decoy";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK = 11;
	private static final int BLOCK_UP = 4;

	public Decoy() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
		this.baseBlock = BLOCK;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Decoy();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			this.upgradeBlock(BLOCK_UP);
				
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {				
		if(p.getPower("Vulnerable") == null) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new VulnerablePower(p, 1, false), 1));
		}
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p,p,this.block));  
	}
}
