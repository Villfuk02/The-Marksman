package marksman.cards;


import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.PotionBounceEffect;

import basemod.abstracts.CustomCard;
import marksman.MarksmanMod;
import marksman.patches.AbstractCardEnum;
import marksman.powers.CorrosionPower;

public class AcidBottle extends CustomCard{
	public static final String ID = "AcidBottle";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int MAGIC = 2;
	private static final int MAGIC_UP = 1;

	public AcidBottle() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
		this.baseMagicNumber = this.magicNumber = MAGIC;
		this.exhaust = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new AcidBottle();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			this.upgradeMagicNumber(MAGIC_UP);
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {		
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new PotionBounceEffect(p.hb.cX, p.dialogY, m.hb.cX, m.hb.cY), 0.25f));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new CorrosionPower(m, this.magicNumber), this.magicNumber));
	}
}
