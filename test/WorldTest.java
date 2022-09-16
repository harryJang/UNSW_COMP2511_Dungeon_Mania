package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.AlliedSoldier;
import unsw.loopmania.BarracksBuilding;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Basilisk;
import unsw.loopmania.Building;
import unsw.loopmania.Character;
import unsw.loopmania.Enemy;
import unsw.loopmania.GoldPileBuilding;
import unsw.loopmania.HealthPotionBuilding;
import unsw.loopmania.HeroCastleBuilding;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Slug;
import unsw.loopmania.Vampire;
import unsw.loopmania.Village;
import unsw.loopmania.Witch;
import unsw.loopmania.Zombie;

public class WorldTest {
    @Test
    public void testLoadVampireCardFromWorld(){
        LoopManiaWorld d = new LoopManiaWorld(10, 10, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        //PathPosition p = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        assertTrue(d.isPath(0, 1));
        assertFalse(d.isPath(0, 0));
        assertFalse(d.isPath(-1, 0));
        assertTrue(d.isAdjacentPath(0, 0));
        assertFalse(d.isAdjacentPath(5, 5));
        assertFalse(d.isAdjacentPath(0, 1));
    }

    @Test
    public void testIsOccupied(){
        LoopManiaWorld d = new LoopManiaWorld(10, 10, Arrays.asList(new Pair<>(0, 0), new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3), 
            new Pair<>(0, 4), new Pair<>(0, 5), new Pair<>(0, 6), new Pair<>(0, 7), new Pair<>(0, 8), new Pair<>(0, 9)));
        //PathPosition p = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        assertNull(d.isOccupied(0, 0)); // Nothing on map
        d.setHeroCastle(new HeroCastleBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        d.loadCardByName("VAMPIRECASTLECARD");
        d.convertCardToBuildingByCoordinates(0, 0, 0, 1);
        assertNotNull(d.isOccupied(0, 0)); // Castle
        assertNotNull(d.isOccupied(0, 1)); // Vampirecastle
        assertNull(d.isOccupied(0, 2)); // No building here
        assertNull(d.isOccupied(3, 3)); // Not path

        d.loadCardByName("ZOMBIEPITCARD");
        d.convertCardToBuildingByCoordinates(0, 0, 0, 2);
        assertNotNull(d.isOccupied(0, 2)); // Zombie
        d.loadCardByName("TOWERCARD");
        d.convertCardToBuildingByCoordinates(0, 0, 0, 3);
        assertNotNull(d.isOccupied(0, 3)); // Tower
        d.loadCardByName("VILLAGECARD");
        d.convertCardToBuildingByCoordinates(0, 0, 0, 4);
        assertNotNull(d.isOccupied(0, 4)); // Village
        d.loadCardByName("BARRACKSCARD");
        d.convertCardToBuildingByCoordinates(0, 0, 0, 5);
        assertNotNull(d.isOccupied(0, 5)); // Barracks

        d.loadCardByName("TRAPCARD");
        d.convertCardToBuildingByCoordinates(0, 0, 0, 6);
        assertNotNull(d.isOccupied(0, 6)); // Trap
        d.loadCardByName("CAMPFIRECARD");
        d.convertCardToBuildingByCoordinates(0, 0, 0, 7);
        assertNotNull(d.isOccupied(0, 7)); // Castle
        
        d.spawnNewHealthPotionBuilding();
        assertNotNull(d.isOccupied(0, 8)); // Health potion
        d.spawnNewGoldPile();
        assertNotNull(d.isOccupied(0, 9)); // Gold

    }

    @Test
    public void testIsHeroCatle(){
        LoopManiaWorld d = new LoopManiaWorld(10, 10, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        PathPosition pp = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        Character c = new Character(pp);
        d.setCharacter(c);
        d.setHeroCastle(new HeroCastleBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1))); // Place herocastle on character position
        assertTrue(d.isHeroCastle());
        d.runTickMoves();
        assertFalse(d.isHeroCastle());
    }

    @Test
    public void testPotionGold(){
        LoopManiaWorld d = new LoopManiaWorld(10, 10, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        PathPosition pp = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        Character c = new Character(pp);
        d.setCharacter(c);

        d.setHeroCastle(new HeroCastleBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0))); 
        d.spawnNewHealthPotionBuilding();  // Spawn on character position
        Building potion =  d.isGiveBuildingType(HealthPotionBuilding.HEALTHPOTION);
        assertTrue(potion instanceof HealthPotionBuilding);
        assertTrue(potion.shouldExist().get());
        d.collectHealthPotion(potion);
        assertFalse(potion.shouldExist().get());

        d.spawnNewHealthPotionBuilding();
        d.runTickMoves();
        assertNull(d.isGiveBuildingType(HealthPotionBuilding.HEALTHPOTION));
        
        
        d.spawnNewGoldPile();  // Spawn on character position
        assertNotNull(d.isGiveBuildingType(GoldPileBuilding.GOLDPILE));
        Integer gold = d.getGold();
        d.collectGoldPile(d.isGiveBuildingType(GoldPileBuilding.GOLDPILE));
        assertTrue(d.getGold() > gold);
        d.runTickMoves();
        assertNull(d.isGiveBuildingType(GoldPileBuilding.GOLDPILE));
    }

    // @Test
    // public void testVillage(){
    //     LoopManiaWorld d = new LoopManiaWorld(10, 10, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
    //     PathPosition pp = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
    //     Character c = new Character(pp);
    //     d.setCharacter(c);
 
    //     d.loadCardByName("VILLAGECARD");
    //     d.convertCardToBuildingByCoordinates(0, 0, 0, 1);
    //     assertNotNull(d.isGiveBuildingType(Village.VILLAGE));

    //     Integer health = d.getHealth();
    //     c.loseHealth(40);
    //     assertEquals(d.getHealth(), health - 40);
    //     d.collectVillageHealth(d.isGiveBuildingType(Village.VILLAGE));
    //     assertEquals(d.getHealth(), health - 10);

    //     d.runTickMoves();
    //     assertNull(d.isGiveBuildingType(Village.VILLAGE));
    // }

    @Test
    public void testIsBarracks(){
        LoopManiaWorld d = new LoopManiaWorld(10, 10, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        PathPosition pp = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        Character c = new Character(pp);
        d.setCharacter(c);
 
        d.loadCardByName("BARRACKSCARD");
        d.convertCardToBuildingByCoordinates(0, 0, 0, 1);
        assertNotNull(d.isGiveBuildingType(BarracksBuilding.BARRACKS));
        d.runTickMoves();
        assertNull(d.isGiveBuildingType(BarracksBuilding.BARRACKS));

    }

    @Test
    public void testAdd(){
        LoopManiaWorld d = new LoopManiaWorld(10, 10, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        PathPosition pp = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        Character c = new Character(pp);
        d.setCharacter(c);
        int check = d.getXP();
        d.addToExp(20);
        assertEquals(check + 20, d.getXP());

        check = d.getGold();
        d.addToGold(30);
        assertEquals(check + 30, d.getGold());

        check = d.getHealth();
        d.addToHealth(40);
        assertEquals(check, d.getHealth());

        check = d.getRound();
        d.updateRound();
        assertEquals(check + 1, d.getRound());
    }

    // @Test
    // public void testRareRingAndRespawn(){
    //     LoopManiaWorld d = new LoopManiaWorld(10, 10, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
    //     PathPosition pp = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
    //     Character c = new Character(pp);
    //     d.setCharacter(c);
    //     d.setHeroCastle(new HeroCastleBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0))); 
        
    //     assertFalse(d.canRespawn());
    //     d.setRareItemByName("THEONERING");;
    //     //assertFalse(d.rareRingSpawned());  //Random function, is tested by changing the if condition to < 100
    //     Integer health = d.getHealth();
    //     c.loseHealth(10);
    //     assertEquals(d.getHealth(), health - 10);
    //     d.respawnCharacter();
    //     assertEquals(d.getHealth(), health);
    // }

    @Test
    public void testTrapDamage(){
        LoopManiaWorld d = new LoopManiaWorld(10, 10, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3), new Pair<>(0, 4)));
        PathPosition pp = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        Character c = new Character(pp);
        d.setCharacter(c);
        
        List<BasicEnemy> newSlugs = d.possiblySpawnEnemies();
        assertNotNull(newSlugs);

        d.loadCardByName("TRAPCARD");
        d.convertCardToBuildingByCoordinates(0, 0, 0, 1);

        d.loadCardByName("TRAPCARD");
        d.convertCardToBuildingByCoordinates(0, 0, 0, 2);
        
        d.trapDamage(); //Tested by adding tmp getters
    
    }

    @Test
    public void testHealthPotion(){
        LoopManiaWorld d = new LoopManiaWorld(10, 10, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        PathPosition pp = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        Character c = new Character(pp);
        d.setCharacter(c);
 
        assertEquals(d.getHealthPotionNumber(), 0);
        d.loadHealthPotion();
        assertEquals(d.getHealthPotionNumber(), 1);
        d.consumeHealthPotion();
        assertEquals(d.getHealthPotionNumber(), 0);
    }
    

    @Test
    public void testActualBattle(){
        LoopManiaWorld d = new LoopManiaWorld(10, 10, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3), new Pair<>(1, 3), new Pair<>(1, 2)));
        PathPosition pp = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        Character c = new Character(pp);
        c.addAlly(new AlliedSoldier());
        d.setCharacter(c);
        d.loadCardByName("TOWERCARD");
        d.convertCardToBuildingByCoordinates(0, 0, 0, 1);
        PathPosition newposition1 = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 1)));
        List<Enemy> bEnemies = new ArrayList<Enemy>();
        List<Enemy> sEnemies = new ArrayList<Enemy>();
        bEnemies.add(new Zombie(newposition1));
        bEnemies.add(new Slug(newposition1));
        bEnemies.add(new Vampire(newposition1));
        sEnemies.add(new Slug(newposition1));
        sEnemies.add(new Zombie(newposition1));
        sEnemies.add(new Vampire(newposition1));

        assertTrue(d.actualBattle(new ArrayList<Enemy>(), new ArrayList<Enemy>()).isEmpty());

        List<Enemy> defeatedEnemies = d.actualBattle(bEnemies, sEnemies);
        assertTrue(c.getHealth() < Character.FULLHEALTH);
        assertEquals(6, defeatedEnemies.size());

        assertTrue(c.getAlly().isEmpty());
        c.addAlly(new AlliedSoldier());
        assertNotNull(c.getAlly());
        d.actualBattle(bEnemies, sEnemies);

        bEnemies.add(new Basilisk(newposition1));
        d.runTickMoves();
        d.actualBattle(bEnemies, sEnemies);
    }

    @Test
    public void testMoveEnemy(){
        LoopManiaWorld d = new LoopManiaWorld(10, 10, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3), new Pair<>(1, 3), new Pair<>(1, 2), new Pair<>(1, 1), new Pair<>(2, 1), new Pair<>(2, 2)));
        PathPosition pp = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3), new Pair<>(1, 3), new Pair<>(1, 2), new Pair<>(1, 1), new Pair<>(2, 1), new Pair<>(2, 2)));
        Character c = new Character(pp);
        d.setCharacter(c);
        d.runTickMoves(); 
        d.runTickMoves();
        d.runTickMoves();
        d.updateRound();
        d.updateRound();
        d.updateRound();
        d.updateRound();

        d.loadCardByName("CAMPFIRECARD");
        d.convertCardToBuildingByCoordinates(0, 0, 0, 1);

        d.loadCardByName("ZOMBIEPITCARD");
        d.convertCardToBuildingByCoordinates(0, 0, 2, 2);
        d.loadCardByName("ZOMBIEPITCARD");
        d.convertCardToBuildingByCoordinates(0, 0, 1, 1);
        d.loadCardByName("VAMPIRECASTLECARD");
        d.convertCardToBuildingByCoordinates(0, 0, 2, 1);
        d.loadCardByName("VAMPIRECASTLECARD");
        d.convertCardToBuildingByCoordinates(0, 0, 1, 4);

        // Below functions are called and used for debug with breakpoints
        assertTrue(d.possiblySpawnEnemies().size()>0);

        for(int i =0; i < 39; i++) {
            d.runTickMoves();
        }
    }

    @Test
    public void tesrRunBattle(){
        LoopManiaWorld d = new LoopManiaWorld(10, 10, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3), new Pair<>(1, 3), new Pair<>(1, 2)));
        PathPosition pp = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3), new Pair<>(1, 3), new Pair<>(1, 2)));
        Character c = new Character(pp);
        d.setCharacter(c);
        d.runTickMoves(); 
        d.runTickMoves();
        d.runTickMoves();
        d.updateRound();
        d.updateRound();
        d.updateRound();
        d.updateRound();
        d.loadCardByName("ZOMBIEPITCARD");
        assertNotNull(d.convertCardToBuildingByCoordinates(0, 0, 0, 1));
        d.loadCardByName("ZOMBIEPITCARD");
        d.convertCardToBuildingByCoordinates(0, 0, 1, 2);
        d.loadCardByName("VAMPIRECASTLECARD");
        d.convertCardToBuildingByCoordinates(0, 0, 0, 1);
        d.loadCardByName("VAMPIRECASTLECARD");
        d.convertCardToBuildingByCoordinates(0, 0, 1, 4);
        d.possiblySpawnEnemies();
        List<Enemy> Enemies =  d.runBattles();
        assertEquals(3, Enemies.size());
    }

    @Test
    public void tesrVillageHealth(){
        LoopManiaWorld d = new LoopManiaWorld(10, 10, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3), new Pair<>(1, 3), new Pair<>(1, 2)));
        PathPosition pp = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3), new Pair<>(1, 3), new Pair<>(1, 2)));
        Character c = new Character(pp);
        d.setCharacter(c);

        d.loadCardByName("VILLAGECARD");
        Building v = d.convertCardToBuildingByCoordinates(0, 0, 0, 1);


        d.loadCardByName("ZOMBIEPITCARD");
        assertNotNull(d.convertCardToBuildingByCoordinates(0, 0, 0, 1));
        d.loadCardByName("ZOMBIEPITCARD");
        d.convertCardToBuildingByCoordinates(0, 0, 1, 2);
        d.loadCardByName("VAMPIRECASTLECARD");
        d.convertCardToBuildingByCoordinates(0, 0, 0, 1);
        d.loadCardByName("VAMPIRECASTLECARD");
        d.convertCardToBuildingByCoordinates(0, 0, 1, 4);
    
        assertEquals(d.getHealth(), Character.FULLHEALTH);
        assertFalse(d.possiblySpawnEnemies().isEmpty());
        d.runBattles();
        int health  = d.getHealth();
        assertTrue(health < Character.FULLHEALTH);
        d.collectVillageHealth(v);
        assertTrue(d.getHealth() > health);
    }    

    @Test
    public void tesrBarracksAlly(){
        LoopManiaWorld d = new LoopManiaWorld(10, 10, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3), new Pair<>(1, 3), new Pair<>(1, 2)));
        PathPosition pp = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3), new Pair<>(1, 3), new Pair<>(1, 2)));
        Character c = new Character(pp);
        d.setCharacter(c);

        d.loadCardByName("BARRACKSCARD");
        Building b = d.convertCardToBuildingByCoordinates(0, 0, 0, 1);
    
        int number  = d.getAlliesNumber();
        d.spawnAlliedSoldier(b);
        assertTrue(d.getAlliesNumber() > number);
    }    

    @Test
    public void tesrFirstRound(){
        LoopManiaWorld d = new LoopManiaWorld(10, 10, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3), new Pair<>(1, 3), new Pair<>(1, 2)));
        PathPosition pp = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3), new Pair<>(1, 3), new Pair<>(1, 2)));
        Character c = new Character(pp);
        d.setCharacter(c);

        assertFalse(d.isFirstRound());
        d.runTickMoves();
        assertTrue(d.isFirstRound());
    }    

    @Test
    public void tesrBasilisk(){
        LoopManiaWorld d = new LoopManiaWorld(10, 10, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3), new Pair<>(1, 3), new Pair<>(1, 2)));
        PathPosition pp = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3), new Pair<>(1, 3), new Pair<>(1, 2)));
        Character c = new Character(pp);
        d.setCharacter(c);

        for(int i =0; i < 39; i++) {
            d.updateRound();
        }
        d.addToExp(10000);
        assertEquals(d.getRound(), 40);
        assertNotNull(d.possiblySpawnBosses());

        int health  = d.getHealth();
        assertEquals(health, Character.FULLHEALTH);
        d.applyPossibleGasAttack();        
        assertTrue(d.getHealth() == health - 3);
    }    

    @Test
    public void tesrWitch(){
        LoopManiaWorld d = new LoopManiaWorld(10, 10, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3), new Pair<>(1, 3), new Pair<>(1, 2)));
        PathPosition pp = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3), new Pair<>(1, 3), new Pair<>(1, 2)));
        Character c = new Character(pp);
        d.setCharacter(c);

        for(int i =0; i < 19; i++) {
            d.updateRound();
        }
        d.loadCardByName("ZOMBIEPITCARD");
        assertNotNull(d.convertCardToBuildingByCoordinates(0, 0, 0, 1));
        d.loadCardByName("ZOMBIEPITCARD");
        d.convertCardToBuildingByCoordinates(0, 0, 1, 2);
        d.loadCardByName("VAMPIRECASTLECARD");
        d.convertCardToBuildingByCoordinates(0, 0, 0, 1);
        d.loadCardByName("VAMPIRECASTLECARD");
        d.convertCardToBuildingByCoordinates(0, 0, 1, 4);
    
        assertFalse(d.possiblySpawnEnemies().isEmpty());

        assertEquals(d.getRound(), 20);
        assertNotNull(d.possiblySpawnBosses());

        d.runTickMoves();
        d.runBattles();
    }    

    @Test
    public void tesrHealthPotion(){
        LoopManiaWorld d = new LoopManiaWorld(10, 10, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3), new Pair<>(1, 3), new Pair<>(1, 2)));
        PathPosition pp = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3), new Pair<>(1, 3), new Pair<>(1, 2)));
        Character c = new Character(pp);
        d.setCharacter(c);

        for(int i =0; i < 39; i++) {
            d.updateRound();
        }
        d.addToExp(10000);
        assertEquals(d.getRound(), 40);
        assertFalse(d.needHealthPotion());

        int health  = d.getHealth();
        assertEquals(health, Character.FULLHEALTH);
        d.possiblySpawnBosses();
        d.applyPossibleGasAttack();        
        
        for(int i =0; i < 40; i++) {
            d.applyPossibleGasAttack();    
        }

        d.updateRound();
        d.updateRound();
        for(int i =0; i < 10; i++) {
            boolean a = d.needHealthPotion();
            System.out.println(a);
        }
    }    


    
}
