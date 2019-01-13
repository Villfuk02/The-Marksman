package marksman.cards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;

import basemod.abstracts.CustomCard;
import marksman.MarksmanMod;
import marksman.patches.AbstractCardEnum;

public class ClusterGrenade extends CustomCard{
	public static final String ID = "ClusterGrenade";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = -1;	
	private static final int DMG = 4;
	private static final int DMG_UP = 2;
	

	public ClusterGrenade() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ALL_ENEMY);
		this.baseDamage = DMG;
		this.isMultiDamage = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new ClusterGrenade();
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
		if (this.energyOnUse < EnergyPanel.totalCount) {
            this.energyOnUse = EnergyPanel.totalCount;
        }
		int effect = this.energyOnUse;
		if (!this.freeToPlayOnce) {
            p.energy.use(effect);
        }
		if (p.hasRelic("Chemical X")) {
	         effect += 2;
	         p.getRelic("Chemical X").flash();
	    }
		for (int i = 0; i < effect+1; i++) {
			AbstractDungeon.actionManager.addToBottom(new VFXAction(new ExplosionSmallEffect(MathUtils.random(p.hb.cX, Settings.WIDTH - p.hb.cX), MathUtils.random(p.hb.cY, Settings.HEIGHT - p.hb.cY))));
			AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage, true), damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
		}
		if(this.energyOnUse != 0) {
			AbstractDungeon.actionManager.addToTop(new GainEnergyAction(1));
		}
		
		
	}
}
