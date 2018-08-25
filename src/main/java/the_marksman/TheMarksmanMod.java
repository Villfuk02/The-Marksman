
package the_marksman;

import java.nio.charset.StandardCharsets;

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
import the_marksman.cards.Defend;
import the_marksman.cards.Defibrilator;
import the_marksman.cards.ElectricBlood;
import the_marksman.cards.ElectricGrenade;
import the_marksman.cards.ExpectAnything;
import the_marksman.cards.ExplosivesExpert;
import the_marksman.cards.ExtendedMagazines;
import the_marksman.cards.EyeForAnEye;
import the_marksman.cards.Firepower;
import the_marksman.cards.Firewall;
import the_marksman.cards.FirstAidKit;
import the_marksman.cards.Flamethrower;
import the_marksman.cards.FlammableFumes;
import the_marksman.cards.FlareGun;
import the_marksman.cards.Foresight;
import the_marksman.cards.FragGrenade;
import the_marksman.cards.FragileBlood;
import the_marksman.cards.FriendlyFire;
import the_marksman.cards.FryingPan;
import the_marksman.cards.GammaRay;
import the_marksman.cards.Gasoline;
import the_marksman.cards.GhostForm;
import the_marksman.cards.GoldenBullet;
import the_marksman.cards.Grenade;
import the_marksman.cards.GrenadeLauncher;
import the_marksman.cards.Guilt;
import the_marksman.cards.HandCannon;
import the_marksman.cards.HandClaws;
import the_marksman.cards.HeavyShotgun;
import the_marksman.cards.HeightAdvantage;
import the_marksman.cards.Hideout;
import the_marksman.cards.Ignite;
import the_marksman.cards.Insulation;
import the_marksman.cards.Kevlar;
import the_marksman.cards.LayDown;
import the_marksman.cards.LockOn;
import the_marksman.cards.LuckyMode;
import the_marksman.cards.MetallicBlood;
import the_marksman.cards.Minigun;
import the_marksman.cards.Molotov;
import the_marksman.cards.NapalmGrenade;
import the_marksman.cards.NuclearPower;
import the_marksman.cards.Painkillers;
import the_marksman.cards.Perfection;
import the_marksman.cards.AcidBottle;
import the_marksman.cards.Aggression;
import the_marksman.cards.AmmoBox;
import the_marksman.cards.AntiFlame;
import the_marksman.cards.Assasinate;
import the_marksman.cards.AutomaticMode;
import the_marksman.cards.BackupPlan;
import the_marksman.cards.BatteryAcid;
import the_marksman.cards.Bayonet;
import the_marksman.cards.BlackPowder;
import the_marksman.cards.BlastShields;
import the_marksman.cards.Bow;
import the_marksman.cards.BurstFire;
import the_marksman.cards.Calculated;
import the_marksman.cards.Camouflage;
import the_marksman.cards.Decoy;
import the_marksman.cards.Chainsaw;
import the_marksman.cards.ClusterGrenade;
import the_marksman.cards.CombatTrousers;
import the_marksman.cards.Concentrate;
import the_marksman.cards.CorrosiveGrenade;
import the_marksman.cards.CounterStrike;
import the_marksman.cards.CriticalDecision;
import the_marksman.cards.Crossbow;
import the_marksman.cards.RustyPistol;
import the_marksman.cards.SaveUp;
import the_marksman.cards.SawBlade;
import the_marksman.cards.SilverBlood;
import the_marksman.cards.SilverBullet;
import the_marksman.cards.SkinSkill;
import the_marksman.cards.RunAndGun;
import the_marksman.cards.SmokeBomb;
import the_marksman.cards.SniperRifle;
import the_marksman.cards.StealthMode;
import the_marksman.cards.Strike;
import the_marksman.cards.StunGrenade;
import the_marksman.cards.TacticalShotgun;
import the_marksman.cards.TheLaw;
import the_marksman.cards.Tracers;
import the_marksman.cards.TrustyPistol;
import the_marksman.cards.UVLaser;
import the_marksman.cards.Veteran;
import the_marksman.cards.VoidGrenade;
import the_marksman.patches.CritVariable;
import the_marksman.cards.PumpShotgun;
import the_marksman.cards.Railgun;
import the_marksman.cards.Revolver;
import the_marksman.cards.RocketJump;
import the_marksman.cards.RocketLauncher;
import the_marksman.relics.RumBottle;
import the_marksman.relics.RustyMagazine;
import the_marksman.relics.ScaleOfInjustice;
import the_marksman.relics.TrustyMagazine;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;

@SpireInitializer
public class TheMarksmanMod implements PostExhaustSubscriber,
	PostBattleSubscriber, PostDungeonInitializeSubscriber,
	EditCardsSubscriber, EditCharactersSubscriber, EditKeywordsSubscriber,
	EditStringsSubscriber, EditRelicsSubscriber{	
	
	public static final Logger logger = LogManager.getLogger(TheMarksmanMod.class.getName());
    
    private static final Color BLACK_COLOR = CardHelper.getColor(50.0f, 50.0f, 50.0f);
	
		
	public TheMarksmanMod() {
		BaseMod.subscribe(this);
		BaseMod.addColor(AbstractCardEnum.BLACK.toString(), BLACK_COLOR, BLACK_COLOR, BLACK_COLOR, BLACK_COLOR, BLACK_COLOR, BLACK_COLOR, BLACK_COLOR,
				"img/512/bg_attack_black.png", "img/512/bg_skill_black.png",
        		"img/512/bg_power_black.png", "img/512/card_black_orb.png",
				"img/1024/bg_attack_black.png", "img/1024/bg_skill_black.png",
        		"img/1024/bg_power_black.png", "img/1024/card_black_orb.png");
	}
	
	public static void initialize() {
		new TheMarksmanMod();
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
		BaseMod.addCharacter(TheMarksman.class, "The Marksman", "Marksman class string",
				AbstractCardEnum.BLACK.toString(), "The Marksman",
				"img/charSelect/classButton.png", "img/charSelect/classPortrait.jpg",
				TheMarksmanEnum.THE_MARKSMAN.toString());
	}
	
	@Override
	public void receiveEditStrings() {
		
		// RelicStrings
        String relicStrings = Gdx.files.internal("localization/TheMarksmanMod-RelicStrings.json").readString(
        		String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
		// CardStrings
        String cardStrings = Gdx.files.internal("localization/TheMarksmanMod-CardStrings.json").readString(
        		String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        
        
        
	}
	
	@Override
	public void receiveEditRelics() {
		logger.info("Adding Marksman Relics");
		BaseMod.addRelicToCustomPool(new RustyMagazine(), AbstractCardEnum.BLACK.toString());
		BaseMod.addRelicToCustomPool(new RumBottle(), AbstractCardEnum.BLACK.toString());
		BaseMod.addRelicToCustomPool(new TrustyMagazine(), AbstractCardEnum.BLACK.toString());
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