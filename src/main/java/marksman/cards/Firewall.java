package marksman.cards;


import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;

import basemod.abstracts.CustomCard;
import marksman.MarksmanMod;
import marksman.patches.AbstractCardEnum;
import marksman.powers.BurningPower;

public class Firewall extends CustomCard{
	public static final String ID = "Firewall";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int BLOCK = 30;
	private static final int BLOCK_UP = 10;
	private static final int MAGIC = 12;
	private static final int MAGIC_UP = -2;

	public Firewall() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
		this.baseBlock = BLOCK;
		this.baseMagicNumber = this.magicNumber = MAGIC;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Firewall();
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
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new FlameBarrierEffect(p.hb.cY, p.hb.cX), 0.25f));		
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BurningPower(p, this.magicNumber), this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new NextTurnBlockPower(p, this.block), this.block));  
	}
}
