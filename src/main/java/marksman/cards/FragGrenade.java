package marksman.cards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;

import basemod.abstracts.CustomCard;
import marksman.MarksmanMod;
import marksman.patches.AbstractCardEnum;
import marksman.actions.ShotgunAction;

public class FragGrenade extends CustomCard{
	public static final String ID = "FragGrenade";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 3;	
	private static final int DMG = 7;
	private static final int DMG_UP = 1;
	private static final int AMOUNT = 7;
	private static final int AMOUNT_UP = 3;
	
	Random rand = new Random();
	

	public FragGrenade() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ALL_ENEMY);
		this.baseDamage = DMG;
		this.baseMagicNumber = this.magicNumber = AMOUNT;
	}

	@Override
	public AbstractCard makeCopy() {
		return new FragGrenade();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeDamage(DMG_UP);
			this.upgradeMagicNumber(AMOUNT_UP);			
		} 
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new ExplosionSmallEffect(MathUtils.random(p.hb.cX, Settings.WIDTH - p.hb.cX), MathUtils.random(p.hb.cY, Settings.HEIGHT - p.hb.cY))));
		AbstractDungeon.actionManager.addToTop(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage, true), damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
		AbstractDungeon.actionManager.addToBottom(new ShotgunAction(AbstractDungeon.getMonsters().getRandomMonster(true), new DamageInfo(p, 2, DamageType.THORNS), this.magicNumber, 0, 4));				
	}
}
