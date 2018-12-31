package marksman.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.random.Random;

import basemod.abstracts.CustomCard;
import marksman.MarksmanMod;
import marksman.patches.AbstractCardEnum;

public class FlareGun extends CustomCard{
	public static final String ID = "FlareGun";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UP_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = -2;
	private static final int DRAW = 1;
	private static final int UP = 2;
	
	Random rand = new Random();
	

	public FlareGun() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ALL);
		this.baseMagicNumber = this.magicNumber = DRAW;
	}

	@Override
	public AbstractCard makeCopy() {
		return new FlareGun();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = UP_DESCRIPTION;
			this.initializeDescription();
			this.upgradeMagicNumber(UP);
		} 
	}

	@Override 
	public void triggerWhenDrawn() {
		
		boolean first = true;
		
		for (final AbstractCard c : AbstractDungeon.player.hand.group) {
			if(c.cardID == ID)
				first = false;
		}
		for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if(!mo.hasPower("Vulnerable") && first)
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, new VulnerablePower(mo, 1, false), 1));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, new WeakPower(mo, 1, false), 1));			
		}
		if(!AbstractDungeon.player.hasPower("Vulnerable") && first) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VulnerablePower(AbstractDungeon.player, 1, false), 1));
		}
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
	}
	
	@Override
    public boolean hasEnoughEnergy() {        
        return false;
    }
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {						
	}
}
