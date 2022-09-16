package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.javatuples.Pair;
import unsw.loopmania.*;
import unsw.loopmania.Character;
/**
 * this class is a dummy class demonstrating how to setup tests for the project
 * you should setup additional test classes in a similar fashion, aiming to achieve high coverage.
 * A clickable "Run Test" link should appear if you have installed the Java Extension Pack properly.
 */
public class CharacterTest {
    @Test
    public void testAlly(){

        LoopManiaWorld d = new LoopManiaWorld(10, 10, new ArrayList<>());
        PathPosition pp = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        Character c = new Character(pp);
        d.setCharacter(c);
        AlliedSoldier nas1 = new AlliedSoldier();
        AlliedSoldier nas2= new AlliedSoldier();
        c.addAlly(nas1);
        c.addAlly(nas2);
        assertEquals(c.getSizeofAlly(),2);
        List<AlliedSoldier> new1= new ArrayList<AlliedSoldier>();
        new1.add(nas1);
        new1.add(nas2);
        c.removeListofAlly(new1);
        assertEquals(c.getSizeofAlly(),0);
    }

    @Test
    public void testEquip(){

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

        c.addAlly(new AlliedSoldier());
        c.addAlly(new AlliedSoldier());
        d.loadCardByName("CAMPFIRECARD");
        d.convertCardToBuildingByCoordinates(0, 0, 2, 3);

        d.loadCardByName("ZOMBIEPITCARD");
        d.convertCardToBuildingByCoordinates(0, 0, 2, 2);
        d.loadCardByName("ZOMBIEPITCARD");
        d.convertCardToBuildingByCoordinates(0, 0, 1, 1);
        d.loadCardByName("VAMPIRECASTLECARD");
        d.convertCardToBuildingByCoordinates(0, 0, 2, 1);
        d.loadCardByName("VAMPIRECASTLECARD");
        d.convertCardToBuildingByCoordinates(0, 0, 1, 4);
        d.possiblySpawnEnemies();
        StaticEntity sword = d.loadItemByName("SWORD");
        StaticEntity stake = d.loadItemByName("STAKE");
        StaticEntity staff = d.loadItemByName("STAFF");
        StaticEntity shield = d.loadItemByName("SHIELD");
        StaticEntity armour = d.loadItemByName("ARMOUR");
        StaticEntity helmet =  d.loadItemByName("HELMET");
        StaticEntity anduril = d.loadItemByName("ANDURIL");
        StaticEntity treeStump = d.loadItemByName("TREESTUMP");

        d.equipItem(sword);
        assertTrue(d.getEquippedWeapon() instanceof Weapon);
        d.equipItem(shield);
        assertTrue(d.getEquippedShield() instanceof Shield);
        d.equipItem(armour);
        assertTrue(d.getEquippedArmour() instanceof Armour);
        d.equipItem(helmet);
        assertTrue(d.getEquippedHelmet() instanceof Helmet);

        d.removeReplacedWeapon();
        d.removeReplacedArmour();
        d.removeReplacedHelmet();
        d.removeReplacedShield();

        assertNull(d.getEquippedArmour());
        assertNull(d.getEquippedWeapon());
        assertNull(d.getEquippedShield());
        assertNull(d.getEquippedHelmet());

        d.equipItem(staff);
        d.equipItem(armour);
        d.equipItem(helmet);
        d.equipItem(shield);
        d.runBattles();
        d.runTickMoves();
        d.possiblySpawnEnemies();
        d.runBattles();
        d.runTickMoves();
        d.removeReplacedWeapon();
        d.removeReplacedShield();
        d.equipItem(anduril);
        d.equipItem(treeStump);
        d.possiblySpawnEnemies();
        d.runBattles();
        d.runTickMoves();
        d.removeReplacedWeapon();
        d.equipItem(sword);
        d.possiblySpawnEnemies();
        d.runBattles();
        d.runTickMoves();
        d.removeReplacedWeapon();
        d.equipItem(stake);
        d.possiblySpawnEnemies();
        d.runBattles();
    }

    @Test
    public void testCheat(){

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
        d.convertCardToBuildingByCoordinates(0, 0, 2, 3);

        d.loadCardByName("ZOMBIEPITCARD");
        d.convertCardToBuildingByCoordinates(0, 0, 2, 2);
        d.loadCardByName("ZOMBIEPITCARD");
        d.convertCardToBuildingByCoordinates(0, 0, 1, 1);
        d.loadCardByName("VAMPIRECASTLECARD");
        d.convertCardToBuildingByCoordinates(0, 0, 2, 1);
        d.loadCardByName("VAMPIRECASTLECARD");
        d.convertCardToBuildingByCoordinates(0, 0, 1, 4);
        d.possiblySpawnEnemies();
        d.worldEnableCheat();

        int check = d.getHealth();
        assertNotNull(d.runBattles());
        assertTrue(d.getHealth() == check);

        d.possiblySpawnEnemies();
        d.worldDisableCheat();
        assertNotNull(d.runBattles());
        assertTrue(d.getHealth() < check);
    }
}