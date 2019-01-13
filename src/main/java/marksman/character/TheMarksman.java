package marksman.character;

import java.util.ArrayList;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import marksman.MarksmanMod;
import marksman.cards.Defend;
import marksman.cards.RustyPistol;
import marksman.cards.Strike;
import marksman.cards.StunGrenade;
import marksman.patches.AbstractCardEnum;
import marksman.patches.TheMarksmanEnum;
import marksman.relics.RustyMagazine;

public class TheMarksman extends CustomPlayer{


	public static final String NAME = "The Marksman";

	public TheMarksman(String name, PlayerClass setClass) {
		super(name, setClass, null, null, null, new SpriterAnimation(MarksmanMod.MARKSMAN_ANIMATION_PATH));
		
		initializeClass(null, MarksmanMod.MARKSMAN_SHOULDER_2, MarksmanMod.MARKSMAN_SHOULDER_1, MarksmanMod.MARKSMAN_CORPSE,
				getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(4));
		
		
	}

	@Override
	public ArrayList<String> getStartingDeck() {
		ArrayList<String> retVal = new ArrayList<>();		
		retVal.add(Defend.ID);
		retVal.add(Defend.ID);
		retVal.add(Defend.ID);
		retVal.add(Defend.ID);
		retVal.add(Defend.ID);
		retVal.add(RustyPistol.ID);
		retVal.add(RustyPistol.ID);
		retVal.add(RustyPistol.ID);
		retVal.add(StunGrenade.ID);
		retVal.add(Strike.ID);
		retVal.add(Strike.ID);
		retVal.add(Strike.ID);
		return retVal;
	}
	
	@Override
	public ArrayList<String> getStartingRelics() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add(RustyMagazine.ID);
		UnlockTracker.markRelicAsSeen(RustyMagazine.ID);
		return retVal;
	}
	
	@Override
	public CharSelectInfo getLoadout() {
		MarksmanMod.talk = 0;
		return new CharSelectInfo(NAME, "After his entire family was consumed by the spire's corrupton, NL all he has left is his Rusty Handgun. He wants to avenge his family.",
				60, 60, 0, 99, 6,
			this, getStartingRelics(), getStartingDeck(), false);
	}

	@Override
	public String getTitle(PlayerClass playerClass) {
		return NAME;
	}

	@Override
	public AbstractCard.CardColor getCardColor() {
		return AbstractCardEnum.BLACK;
	}

	@Override
	public Color getCardRenderColor() {
		return Color.BLACK;
	}

	@Override
	public AbstractCard getStartCardForEvent() {
		return new StunGrenade();
	}

	@Override
	public Color getCardTrailColor() {
		return Color.BLACK;
	}

	@Override
	public int getAscensionMaxHPLoss() {
		return 3;
	}

	@Override
	public BitmapFont getEnergyNumFont() {
		return FontHelper.energyNumFontRed;
	}

	@Override
	public void doCharSelectScreenSelectEffect() {
		CardCrawlGame.sound.playA("ATTACK_HEAVY", MathUtils.random(-0.2f, 0.2f));
		CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
	}

	@Override
	public String getCustomModeCharacterButtonSoundKey() {
		return "ATTACK_HEAVY";
	}

	@Override
	public String getLocalizedCharacterName() {
		return NAME;
	}

	@Override
	public AbstractPlayer newInstance() {
		return new TheMarksman(NAME, TheMarksmanEnum.THE_MARKSMAN);
	}

	@Override
	public String getSpireHeartText() {
		return "NL You ready your Weapon...";
	}

	@Override
	public Color getSlashAttackColor() {
		return Color.MAROON;
	}

	@Override
	public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
		return new AbstractGameAction.AttackEffect[]{
			AbstractGameAction.AttackEffect.SLASH_DIAGONAL
			, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL
			, AbstractGameAction.AttackEffect.SLASH_VERTICAL
			, AbstractGameAction.AttackEffect.SLASH_HEAVY
		};
	}

	//TODO: Character Specific Dialog
	@Override
	public String getVampireText() {
		return "Navigating an unlit street, you come across several hooded figures in the midst of some dark ritual. As you approach, they turn to you in eerie unison. The tallest among them bares fanged teeth and extends a long, pale hand towards you. NL ~\"Join~ ~us,~ ~oh Mighty Warrior,~ ~and~ ~feel~ ~the~ ~warmth~ ~of~ ~the~ ~Spire.\"~";
	}
}
