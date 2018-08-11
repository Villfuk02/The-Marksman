package the_marksman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;

import basemod.abstracts.CustomCard;
import the_marksman.AbstractCardEnum;

public class UVLaser extends CustomCard{
	public static final String ID = "UVLaser";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;	
	private static final int DMG = 8;
	private static final int DMG_UP = 2;
	
	Random rand = new Random();
	

	public UVLaser() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ALL_ENEMY);
		this.baseDamage = DMG;
		this.damageType = DamageType.THORNS;
	}

	@Override
	public AbstractCard makeCopy() {
		return new UVLaser();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeDamage(DMG_UP);
			
		} 
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		
		for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if(mo.currentHealth > 0)
				AbstractDungeon.actionManager.addToBottom(new VFXAction(new SmallLaserEffect(p.hb.cX, p.hb.cY, mo.hb.cX, mo.hb.cY)));
		}
		
		AbstractDungeon.actionManager.addToTop(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.baseDamage, true), DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
		AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new WeakPower(p, 2, false), 2));
	}
}
