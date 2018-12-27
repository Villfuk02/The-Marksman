package marksman.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import basemod.abstracts.CustomCard;
import marksman.MarksmanMod;
import marksman.patches.AbstractCardEnum;

public class ElectricBlood extends CustomCard{
	public static final String ID = "ElectricBlood";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UP_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 0;
	private static final int DMG = 1;
	private static final int SELF = 8;

	public ElectricBlood() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ALL);
		this.baseDamage = DMG;
	}

	@Override
	public AbstractCard makeCopy() {
		return new ElectricBlood();
	}

	@Override
	public void upgrade() {
		if(!upgraded) {
			upgradeName();
			this.rawDescription = UP_DESCRIPTION;
			this.initializeDescription();
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {		

        AbstractDungeon.actionManager.addToBottom(new SFXAction("LIGHTNING"));
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new LightningEffect(p.hb.cX, p.hb.cY)));
		AbstractDungeon.actionManager.addToBottom(new DamageAction(p,new DamageInfo(p, SELF, DamageType.HP_LOSS)));
		AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(upgraded?2:1));
		for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if(mo.currentHealth > 0)
				AbstractDungeon.actionManager.addToBottom(new VFXAction(new LightningEffect(mo.hb.cX, mo.hb.cY)));
		}
		AbstractDungeon.actionManager.addToTop(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage, true), damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));		
	}
}
