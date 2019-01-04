
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
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.AbstractUnlock;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.BaseMod;
import basemod.abstracts.CustomUnlockBundle;
import basemod.helpers.RelicType;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostDungeonInitializeSubscriber;
import basemod.interfaces.PostExhaustSubscriber;
import basemod.interfaces.PostPowerApplySubscriber;
import basemod.interfaces.SetUnlocksSubscriber;
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
	EditStringsSubscriber, EditRelicsSubscriber, PostPowerApplySubscriber, 
	SetUnlocksSubscriber{	
	
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
	
	public static int talk = 0;
	
	public String database_export = "";
	public int latest_id = 0;
		
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
		BaseMod.addRelicToCustomPool(new RustyHelmet(), AbstractCardEnum.BLACK);
		BaseMod.addRelic(new ScaleOfInjustice(), RelicType.SHARED);
		
		BaseMod.addRelic(new InfiniteLighter(), RelicType.SHARED);
		BaseMod.addRelic(new LighterOrb(), RelicType.BLUE);
		BaseMod.addRelic(new PoisonLighter(), RelicType.GREEN);
		BaseMod.addRelic(new HellfireLighter(), RelicType.RED);
	}
	
	@Override
	public void receiveEditCards() {
		logger.info("Adding Marksman Cards");
		
		database_export = "";
		latest_id = 0;
		
		BaseMod.addDynamicVariable(new CritVariable());
		
		// COLORLESS (1)
		AddCard(new Painkillers());
		
		// BASIC (5)
		AddCard(new RustyPistol());
		AddCard(new Defend());
		AddCard(new Strike());
		AddCard(new StunGrenade());
		
		AddCard(new TrustyPistol());
		
		// COMMON (21)
		//	Attacks (14)		
		AddCard(new Grenade());			//aoe
		AddCard(new NapalmGrenade());	//aoe, burning
		AddCard(new HandCannon()); 		//-precision
		AddCard(new BurstFire()); 		//-precision, multi-hit
		AddCard(new PumpShotgun());		//multi-hit, -precision
		AddCard(new Revolver());		//high-crit
		AddCard(new RunAndGun());		//draw
		AddCard(new Bow());				//silent
		AddCard(new Chainsaw());		//pull 0-cost
		AddCard(new Bayonet());			//crit synergy
		AddCard(new SilverBullet());	//HP stuff
		AddCard(new GoldenBullet());	//GOLD stuff
		AddCard(new UVLaser());			//silent aoe, weak
		AddCard(new RocketJump());		//burn, draw
		
		//	Skills (8)
		AddCard(new HeightAdvantage());	//block, +precision
		AddCard(new Decoy());			//block, vulnerable
		AddCard(new Molotov());			//burning
		AddCard(new BackupPlan());		//seek
		AddCard(new Calculated());		//+precision
		AddCard(new ExpectAnything());	//block, innate
		AddCard(new CombatTrousers());	//block, discard, retain
		AddCard(new LayDown());			//block, +precision
		AddCard(new Camouflage());		//block
		AddCard(new Reposition());		//draw, -str
		
		
		
		// UNCOMMON (43)
		// 	Attacks(13)
		AddCard(new FragGrenade());		//aoe, multi-hit
		AddCard(new CorrosiveGrenade());//aoe, corrosive
		AddCard(new ElectricGrenade());	//+e, aoe, electric
		AddCard(new TacticalShotgun());	//multi-hit, draw
		AddCard(new HeavyShotgun());	//multi-hit, discard
		AddCard(new Railgun());			//X
		AddCard(new Crossbow());		//silent, frail
		AddCard(new GammaRay());		//criiit
		AddCard(new GrenadeLauncher());	//exh grenade
		AddCard(new Flamethrower());	//X, burn
		AddCard(new FryingPan());		//block/atk
		AddCard(new SniperRifle());		//crit
		AddCard(new Assasinate());		//one big hit, -str
		AddCard(new SawBlade());		//pull 0-cost
		
		//	Skills (22)
		AddCard(new ExtendedMagazines());//draw
		AddCard(new LockOn());			//concentrate +str
		AddCard(new SmokeBomb());		//weaken, -precision
		AddCard(new Concentrate());		//concentrated, no draw
		AddCard(new Defibrilator());	//self-damage, regen
		AddCard(new CriticalDecision());//draw, crit synergy
		AddCard(new AcidBottle());		//corrosion
		AddCard(new TheLaw());			//remove str/vuln/weak	
		AddCard(new SkinSkill());		//block, upgrade
		AddCard(new Foresight());		//block, headbutt
		AddCard(new SaveUp());			//->NextTurn
		AddCard(new FlareGun());		//vuln, unplayable
		AddCard(new ElectricBlood());	//+e, self-damage
		AddCard(new Kevlar());			//block, armor
		AddCard(new Hideout());			//block, crit synergy
		AddCard(new Ignite());			//burn for hit
		AddCard(new NuclearPower());	//X, energy maipulation
		AddCard(new AntiFlame());		//block, burn
		AddCard(new Insulation());		//block, burn -> burning
		AddCard(new MetallicBlood());	//self-damage, metallicize
		AddCard(new FirstAidKit());		//heal lost hp
		AddCard(new HandClaws());		//hp -> shivs
		
		// 	Powers (8)
		AddCard(new Tracers());			//+precision
		AddCard(new Guilt());			//+e, -str
		AddCard(new ExplosivesExpert());//draw for grenade
		AddCard(new FragileBlood());	//spiky!
		AddCard(new Aggression());		//vuln -> +str
		AddCard(new SilverBlood());		//regen for hit
		AddCard(new FlammableFumes());	//burn++
		AddCard(new CounterStrike());	//dmg for -
		AddCard(new Firepower());		//burning -> burning
		
		
		// RARE (18)
		//	Attacks (4)
		AddCard(new VoidGrenade());		//aoe, grenade succ
		AddCard(new ClusterGrenade());	//X, aoe
		AddCard(new Minigun());			//multi-hit, infinite
		AddCard(new RocketLauncher());	//one big hit, burning
		
		//	Skills (7)
		AddCard(new AmmoBox());			//draw, infinite
		AddCard(new BatteryAcid());		//+3e
		AddCard(new StealthMode());		//intangible, -str
		AddCard(new LuckyMode());		//weak, +precision
		AddCard(new AutomaticMode());	//+2e, -str
		AddCard(new Gasoline());		//multiply burning
		AddCard(new Firewall());		//burn, block next turn
		
		//	Powers (7)
		AddCard(new EyeForAnEye());		//self-damage
		AddCard(new BlastShields());	//+block for grenade
		AddCard(new BlackPowder());		//grenade for exh
		AddCard(new FriendlyFire());	//block for burning
		AddCard(new Veteran());			//-str, +str
		AddCard(new GhostForm());		//all cards ethereal
		AddCard(new Perfection());		//double crit
		
		PasteCards();
		
	}
	
	public void AddCard(AbstractCard c) {
		BaseMod.addCard(c);
		latest_id++;
		database_export += 
		"("+
		latest_id+
		",'"+
		c.cardID+
		"',"+ 
		(c.color == CardColor.COLORLESS?"0,1":"1,0")+
		",0,'"+
		c.rarity.toString()+
		"','"+
		c.type.toString()+
		"','"+
		c.cost+
		"','"+
		"DESCRIPTION"+		
		"',NULL,'0000-00-00 00:00:00',0,0,0,0,'0000-00-00 00:00:00',0,0,0,0,0,0,'0000-00-00 00:00:00',0,0,0,0,0,0,0,0,0,'0000-00-00 00:00:00',0,0,0,0,0,0,'','',''),";
	}
	
	public void PasteCards() {
		logger.info(database_export.substring(0, database_export.length()-1) + ";");
	}

	@Override
	public void receiveEditKeywords() {		
		BaseMod.addKeyword(new String[]{"crit", "critical", "crits"}, "Chance of single hit dealing #yTRIPLE Damage.");
		BaseMod.addKeyword(new String[]{"burning"}, "#yBurning creatures take damage at the end of their turn. Each turn, #yBurning is decreased by the amount #yBlocked. Also lowers the #rTransient's damage.");
		BaseMod.addKeyword(new String[]{"corrosion"}, "At the end of turn, removes all Block and decreases Corrosion by #b1.");
		BaseMod.addKeyword(new String[]{"silent damage", "silent"}, "This damage is NOT considered as #yAttack damage. That means it is not affected by #yStrength, #yWeakness or #yVulnerable. NL It also doesn't trigger #yThorns, #yCurl #yUp, etc.");
		BaseMod.addKeyword(new String[]{"concentrated"}, "Doubles #yCrit chance for one turn.");
		BaseMod.addKeyword(new String[]{"precision"}, "Increases #yCrit chance of all attacks with #yCrit. Removed at the end of turn.");
		BaseMod.addKeyword(new String[]{"expose"}, "If target isn't #yVulnerable, apply #b1 #yVulnerable.");
		BaseMod.addKeyword(new String[]{"grenade"}, "Grenades are cards, which attack ALL enemies and have #yGrenade in their name.");
		BaseMod.addKeyword(new String[]{"painkillers"}, "Painkillers are #b0 cost Skills which heal a portion of your #yHP you lost in your turn and #yExhaust.");
	}
	
	@Override
    public void receivePostPowerApplySubscriber(AbstractPower p, AbstractCreature target, AbstractCreature source) {
        for (AbstractRelic r : AbstractDungeon.player.relics) {
        	if (r instanceof  InfiniteLighter) {
                ((InfiniteLighter)r).onApplyPower(p, target, source);
            }
        	if (r instanceof  HellfireLighter) {
                ((HellfireLighter)r).onApplyPower(p, target, source);
            }
        	if (r instanceof  PoisonLighter) {
                ((PoisonLighter)r).onApplyPower(p, target, source);
            }
        	if (r instanceof  RustyHelmet) {
                ((RustyHelmet)r).onApplyPower(p, target, source);
            }
        }
    }
	
	@Override
    public void receiveSetUnlocks() {
        BaseMod.addUnlockBundle(new CustomUnlockBundle(
             RocketJump.ID, CounterStrike.ID, RocketLauncher.ID
             ), TheMarksmanEnum.THE_MARKSMAN, 1);

        BaseMod.addUnlockBundle(new CustomUnlockBundle(
             BackupPlan.ID, Insulation.ID, VoidGrenade.ID
             ), TheMarksmanEnum.THE_MARKSMAN, 2);

        BaseMod.addUnlockBundle(new CustomUnlockBundle(AbstractUnlock.UnlockType.RELIC,
             ScaleOfInjustice.ID, RumBottle.ID, InfiniteLighter.ID
             ), TheMarksmanEnum.THE_MARKSMAN, 3);

        BaseMod.addUnlockBundle(new CustomUnlockBundle(
             GoldenBullet.ID, NuclearPower.ID, Minigun.ID
             ), TheMarksmanEnum.THE_MARKSMAN, 4);

        BaseMod.addUnlockBundle(new CustomUnlockBundle(AbstractUnlock.UnlockType.RELIC,
        	 HellfireLighter.ID, PoisonLighter.ID, LighterOrb.ID
             ), TheMarksmanEnum.THE_MARKSMAN, 5);
        
        UnlockTracker.addCard(RocketJump.ID);
        UnlockTracker.addCard(CounterStrike.ID);
        UnlockTracker.addCard(RocketLauncher.ID);
        
        UnlockTracker.addCard(BackupPlan.ID);
        UnlockTracker.addCard(Insulation.ID);
        UnlockTracker.addCard(VoidGrenade.ID);
        
        UnlockTracker.addRelic(ScaleOfInjustice.ID);
        UnlockTracker.addRelic(RumBottle.ID);
        UnlockTracker.addRelic(InfiniteLighter.ID);
        
        UnlockTracker.addCard(GoldenBullet.ID);
        UnlockTracker.addCard(NuclearPower.ID);
        UnlockTracker.addCard(Minigun.ID);
        
        UnlockTracker.addRelic(HellfireLighter.ID);
        UnlockTracker.addRelic(PoisonLighter.ID);
        UnlockTracker.addRelic(LighterOrb.ID);
                
    }
	
}