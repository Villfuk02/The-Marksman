package the_marksman;

import java.util.ArrayList;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;


import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

public class TheMarksman extends CustomPlayer{
	
	
	public TheMarksman(String name, PlayerClass setClass) {
		super(name, setClass, null, null, null, new SpriterAnimation("img/char/anim/Construct.scml"));
		
		initializeClass(null, "img/char/shoulder2.png", "img/char/shoulder.png", "img/char/corpse.png",
				getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(4));
		
		
	}

	public static ArrayList<String> getStartingDeck() {
		ArrayList<String> retVal = new ArrayList<>();		
		retVal.add("DefendBlack");
		retVal.add("DefendBlack");
		retVal.add("DefendBlack");
		retVal.add("DefendBlack");
		retVal.add("RustyPistol");
		retVal.add("RustyPistol");
		retVal.add("RustyPistol");
		retVal.add("StunGrenade");
		retVal.add("StrikeBlack");
		retVal.add("StrikeBlack");
		return retVal;
	}
	
	public static ArrayList<String> getStartingRelics() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add("RustyMagazine");
		UnlockTracker.markRelicAsSeen("RustyMagazine");
		return retVal;
	}
	
	public static CharSelectInfo getLoadout() {
		return new CharSelectInfo("The Marksman", "After his entire family was killed, all he has left is his Rusty Handgun. NL He wants to avenge his family by Slaying The Spire.",
				90, 90, 0, 90, 6,
			TheMarksmanEnum.THE_MARKSMAN, getStartingRelics(), getStartingDeck(), false);
	}
	
}
