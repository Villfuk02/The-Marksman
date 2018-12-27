
package marksman;

import java.nio.charset.StandardCharsets;

import marksman.character.TheMarksman;
import marksman.patches.AbstractCardEnum;
import marksman.patches.CritVariable;
import marksman.patches.TheMarksmanEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostDungeonInitializeSubscriber;
import basemod.interfaces.PostExhaustSubscriber;
import marksman.cards.*;
import marksman.relics.*;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;

@SpireInitializer
public class MarksmanMod implements PostExhaustSubscriber,
	PostBattleSubscriber, PostDungeonInitializeSubscriber,
	EditCardsSubscriber, EditCharactersSubscriber, EditKeywordsSubscriber,
	EditStringsSubscriber, EditRelicsSubscriber{	
	
	public static final Logger logger = LogManager.getLogger(MarksmanMod.class.getName());
    
    private static final Color BLACK_COLOR = CardHelper.getColor(50.0f, 50.0f, 50.0f);

	public static final String RESOURCE_PATH = "marksman/";
	public static final String IMG_PATH = RESOURCE_PATH + "img/";
	public static final String LOCALIZATION_PATH = RESOURCE_PATH + "localization/";
	public static final String CARD_STRINGS = LOCALIZATION_PATH + "MarksmanMod-CardStrings.json";
	public static final String RELIC_STRINGS = LOCALIZATION_PATH + "MarksmanMod-RelicStrings.json";

	public static final String CARD_IMG_PATH = IMG_PATH + "cards/";
	public static final String POWER_IMG_PATH = IMG_PATH + "powers/";
	public static final String RELIC_IMG_PATH = IMG_PATH + "relics/";

	// card backgrounds
	private static final String ATTACK_BLACK = IMG_PATH + "512/bg_attack_black.png";
	private static final String SKILL_BLACK = IMG_PATH + "512/bg_skill_black.png";
	private static final String POWER_BLACK = IMG_PATH + "512/bg_power_black.png";
	private static final String ENERGY_ORB_BLACK = IMG_PATH + "512/card_black_orb.png";

	private static final String ATTACK_BLACK_PORTRAIT = IMG_PATH + "1024/bg_attack_black.png";
	private static final String SKILL_BLACK_PORTRAIT = IMG_PATH + "1024/bg_skill_black.png";
	private static final String POWER_BLACK_PORTRAIT = IMG_PATH + "1024/bg_power_black.png";
	private static final String ENERGY_ORB_BLACK_PORTRAIT = IMG_PATH + "1024/card_black_orb.png";

	private static final String ENERGY_ORB_IN_DESCRIPTION = IMG_PATH + "energy/energyOrbInDescription.png";

	private static final String MARKSMAN_BUTTON = IMG_PATH + "charSelect/classButton.png";
	private static final String MARKSMAN_PORTRAIT = IMG_PATH + "charSelect/classPortrait.jpg";

	public static final String MARKSMAN_CHARACTER_PATH = IMG_PATH + "char/marksman/";
	public static final String MARKSMAN_SHOULDER_1 = MARKSMAN_CHARACTER_PATH + "shoulder.png";
	public static final String MARKSMAN_SHOULDER_2 = MARKSMAN_CHARACTER_PATH + "shoulder2.png";
	public static final String MARKSMAN_CORPSE = MARKSMAN_CHARACTER_PATH + "corpse.png";
	public static final String MARKSMAN_SKELETON_ATLAS = MARKSMAN_CHARACTER_PATH + "skeleton.atlas";
	public static final String MARKSMAN_SKELETON_JSON = MARKSMAN_CHARACTER_PATH + "skeleton.json";
	public static final String MARKSMAN_ANIMATION_PATH = MARKSMAN_CHARACTER_PATH + "anim/Construct.scml";
		
	public MarksmanMod() {
		BaseMod.subscribe(this);
		BaseMod.addColor(
			AbstractCardEnum.BLACK
			, BLACK_COLOR
			, ATTACK_BLACK
			, SKILL_BLACK
			, POWER_BLACK
			, ENERGY_ORB_BLACK
			, ATTACK_BLACK_PORTRAIT
			, SKILL_BLACK_PORTRAIT
			, POWER_BLACK_PORTRAIT
			, ENERGY_ORB_BLACK_PORTRAIT
			, ENERGY_ORB_IN_DESCRIPTION
		);
	}
	
	public static void initialize() {
		new MarksmanMod();
	}
	
	@Override
	public void receivePostExhaust(AbstractCard c) {
		
	}
	
	@Override
	public void receivePostBattle(AbstractRoom r) {
		
	}
	
	@Override
	public void receivePostDungeonInitialize() {
		
	}	

	@Override
	public void receiveEditCharacters() {
		BaseMod.addCharacter(
			new TheMarksman(
				TheMarksman.NAME
				, TheMarksmanEnum.THE_MARKSMAN
			)
			,MARKSMAN_BUTTON
			, MARKSMAN_PORTRAIT,
			TheMarksmanEnum.THE_MARKSMAN
		);
	}
	
	@Override
	public void receiveEditStrings() {
		
		// RelicStrings
        String relicStrings = Gdx.files.internal(RELIC_STRINGS).readString(
        		String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
		// CardStrings
        String cardStrings = Gdx.files.internal(CARD_STRINGS).readString(
        		String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        
        
        
	}
	
	@Override
	public void receiveEditRelics() {
		logger.info("Adding Marksman Relics");
		BaseMod.addRelicToCustomPool(new RustyMagazine(), AbstractCardEnum.BLACK);
		BaseMod.addRelicToCustomPool(new RumBottle(), AbstractCardEnum.BLACK);
		BaseMod.addRelicToCustomPool(new TrustyMagazine(), AbstractCardEnum.BLACK);
		BaseMod.addRelic(new ScaleOfInjustice(), RelicType.SHARED);
	}
	
	@Override
	public void receiveEditCards() {
		logger.info("Adding Marksman Cards");
		
		BaseMod.addDynamicVariable(new CritVariable());
		
		// COLORLESS (1)
		BaseMod.addCard(new Painkillers());
		
		// BASIC (5)
		BaseMod.addCard(new RustyPistol());
		BaseMod.addCard(new Defend());
		BaseMod.addCard(new Strike());
		BaseMod.addCard(new StunGrenade());
		
		BaseMod.addCard(new TrustyPistol());
		
		// COMMON (21)
		//	Attacks (14)		
		BaseMod.addCard(new Grenade());			//aoe
		BaseMod.addCard(new NapalmGrenade());	//aoe, burning
		BaseMod.addCard(new HandCannon()); 		//-precision
		BaseMod.addCard(new BurstFire()); 		//-precision, multi-hit
		BaseMod.addCard(new PumpShotgun());		//multi-hit, -precision
		BaseMod.addCard(new Revolver());		//high-crit
		BaseMod.addCard(new RunAndGun());		//draw
		BaseMod.addCard(new Bow());				//silent
		BaseMod.addCard(new Chainsaw());		//pull 0-cost
		BaseMod.addCard(new Bayonet());			//crit synergy
		BaseMod.addCard(new SilverBullet());	//HP stuff
		BaseMod.addCard(new GoldenBullet());	//GOLD stuff
		BaseMod.addCard(new UVLaser());			//silent aoe, weak
		BaseMod.addCard(new RocketJump());		//burn, draw
		
		//	Skills (8)
		BaseMod.addCard(new HeightAdvantage());	//block, +precision
		BaseMod.addCard(new Decoy());			//block, vulnerable
		BaseMod.addCard(new Molotov());			//burning
		BaseMod.addCard(new BackupPlan());		//seek
		BaseMod.addCard(new Calculated());		//+precision
		BaseMod.addCard(new ExpectAnything());	//block, innate
		BaseMod.addCard(new CombatTrousers());	//block, discard, retain
		BaseMod.addCard(new LayDown());			//block, +precision
		BaseMod.addCard(new Camouflage());		//block
		
		
		
		
		// UNCOMMON (43)
		// 	Attacks(13)
		BaseMod.addCard(new FragGrenade());		//aoe, multi-hit
		BaseMod.addCard(new CorrosiveGrenade());//aoe, corrosive
		BaseMod.addCard(new ElectricGrenade());	//+e, aoe, electric
		BaseMod.addCard(new TacticalShotgun());	//multi-hit, draw
		BaseMod.addCard(new HeavyShotgun());	//multi-hit, discard
		BaseMod.addCard(new Railgun());			//X
		BaseMod.addCard(new Crossbow());		//silent, frail
		BaseMod.addCard(new GammaRay());		//criiit
		BaseMod.addCard(new GrenadeLauncher());	//exh grenade
		BaseMod.addCard(new Flamethrower());	//X, burn
		BaseMod.addCard(new FryingPan());		//block/atk
		BaseMod.addCard(new SniperRifle());		//crit
		BaseMod.addCard(new Assasinate());		//one big hit, -str
		BaseMod.addCard(new SawBlade());		//pull 0-cost
		
		//	Skills (22)
		BaseMod.addCard(new ExtendedMagazines());//draw
		BaseMod.addCard(new LockOn());			//concentrate +str
		BaseMod.addCard(new SmokeBomb());		//weaken, -precision
		BaseMod.addCard(new Concentrate());		//concentrated, no draw
		BaseMod.addCard(new Defibrilator());	//self-damage, regen
		BaseMod.addCard(new CriticalDecision());//draw, crit synergy
		BaseMod.addCard(new AcidBottle());		//corrosion
		BaseMod.addCard(new TheLaw());			//remove str/vuln/weak	
		BaseMod.addCard(new SkinSkill());		//block, upgrade
		BaseMod.addCard(new Foresight());		//block, headbutt
		BaseMod.addCard(new SaveUp());			//->NextTurn
		BaseMod.addCard(new FlareGun());		//vuln, unplayable
		BaseMod.addCard(new ElectricBlood());	//+e, self-damage
		BaseMod.addCard(new Kevlar());			//block, armor
		BaseMod.addCard(new Hideout());			//block, crit synergy
		BaseMod.addCard(new Ignite());			//burn for hit
		BaseMod.addCard(new NuclearPower());	//X, energy maipulation
		BaseMod.addCard(new AntiFlame());		//block, burn
		BaseMod.addCard(new Insulation());		//block, burn -> burning
		BaseMod.addCard(new MetallicBlood());	//self-damage, metallicize
		BaseMod.addCard(new FirstAidKit());		//heal lost hp
		BaseMod.addCard(new HandClaws());		//hp -> shivs
		
		// 	Powers (8)
		BaseMod.addCard(new Tracers());			//+precision
		BaseMod.addCard(new Guilt());			//+e, -str
		BaseMod.addCard(new ExplosivesExpert());//draw for grenade
		BaseMod.addCard(new FragileBlood());	//spiky!
		BaseMod.addCard(new Aggression());		//vuln -> +str
		BaseMod.addCard(new SilverBlood());		//regen for hit
		BaseMod.addCard(new FlammableFumes());	//burn++
		BaseMod.addCard(new CounterStrike());	//dmg for -
		BaseMod.addCard(new Firepower());		//burning -> burning
		
		
		// RARE (18)
		//	Attacks (4)
		BaseMod.addCard(new VoidGrenade());		//aoe, grenade succ
		BaseMod.addCard(new ClusterGrenade());	//X, aoe
		BaseMod.addCard(new Minigun());			//multi-hit, infinite
		BaseMod.addCard(new RocketLauncher());	//one big hit, burning
		
		//	Skills (7)
		BaseMod.addCard(new AmmoBox());			//draw, infinite
		BaseMod.addCard(new BatteryAcid());		//+3e
		BaseMod.addCard(new StealthMode());		//intangible, -str
		BaseMod.addCard(new LuckyMode());		//weak, +precision
		BaseMod.addCard(new AutomaticMode());	//+2e, -str
		BaseMod.addCard(new Gasoline());		//multiply burning
		BaseMod.addCard(new Firewall());		//burn, block next turn
		
		//	Powers (7)
		BaseMod.addCard(new EyeForAnEye());		//self-damage
		BaseMod.addCard(new BlastShields());	//+block for grenade
		BaseMod.addCard(new BlackPowder());		//grenade for exh
		BaseMod.addCard(new FriendlyFire());	//block for burning
		BaseMod.addCard(new Veteran());			//-str, +str
		BaseMod.addCard(new GhostForm());		//all cards ethereal
		BaseMod.addCard(new Perfection());		//double crit
		
	}

	@Override
	public void receiveEditKeywords() {		
		BaseMod.addKeyword(new String[]{"crit", "critical"}, "Chance of single hit dealing #yTRIPLE Damage.");
		BaseMod.addKeyword(new String[]{"burning"}, "Burning creatures take damage at the end of their turn. Each turn, Burning is decreased by the amount Blocked. Also lowers Transient's damage.");
		BaseMod.addKeyword(new String[]{"corrosion"}, "At the end of turn, removes all Block and decreases Corrosion by #b1.");
		BaseMod.addKeyword(new String[]{"silent damage", "silent"}, "This damage is NOT considered as #yAttack damage. That means it is not affected by #yStrength, #yWeakness or #yVulnerable. NL It also doesn't trigger #yThorns, #yCurl #yUp, etc.");
		BaseMod.addKeyword(new String[]{"concentrated"}, "Doubles #yCrit for one turn.");
		BaseMod.addKeyword(new String[]{"precision"}, "Increases #yCrit of all attacks with #yCrit. Removed at the end of turn.");
		BaseMod.addKeyword(new String[]{"expose"}, "If target isn't #yVulnerable, apply #b1 #yVulnerable.");
		BaseMod.addKeyword(new String[]{"grenade"}, "Grenades are cards, which attack ALL enemies and have #yGrenade in their name.");
		BaseMod.addKeyword(new String[]{"painkillers"}, "Painkillers are #b0 cost Skills which heal a portion of your HP and Exhaust.");
	}
	
}