package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import unsw.loopmania.BarracksBuilding;
import unsw.loopmania.BarracksCard;
import unsw.loopmania.Building;
import unsw.loopmania.CampfireBuilding;
import unsw.loopmania.CampfireCard;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.TowerBuilding;
import unsw.loopmania.TowerCard;
import unsw.loopmania.TrapBuilding;
import unsw.loopmania.TrapCard;
import unsw.loopmania.VampireCastleBuilding;
import unsw.loopmania.VampireCastleCard;

/**
 * this class is a dummy class demonstrating how to setup tests for the project
 * you should setup additional test classes in a similar fashion, aiming to achieve high coverage.
 * A clickable "Run Test" link should appear if you have installed the Java Extension Pack properly.
 */
public class CardTest {
    @Test
    public void testLoadVampireCardFromWorld(){
        LoopManiaWorld d = new LoopManiaWorld(10, 10, new ArrayList<>());
        assertTrue(d.loadCardByName("VAMPIRECASTLECARD") instanceof VampireCastleCard);
        assertTrue(d.loadCardByName("TOWERCARD") instanceof TowerCard);
        assertTrue(d.loadCardByName("BARRACKSCARD") instanceof BarracksCard);
        assertTrue(d.loadCardByName("TRAPCARD") instanceof TrapCard);
        assertTrue(d.loadCardByName("CAMPFIRECARD") instanceof CampfireCard);
        assertEquals(d.loadCardByName("INVALID"), null);
    }
    
    @Test
    public void GenerateBuildingFromCard(){
        VampireCastleCard vc = new VampireCastleCard(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        assertTrue(vc.getBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1)) instanceof VampireCastleBuilding);

        TowerCard tc = new TowerCard(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        assertTrue(tc.getBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1)) instanceof TowerBuilding);

        BarracksCard bc = new BarracksCard(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        assertTrue(bc.getBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1)) instanceof BarracksBuilding);

        TrapCard trc = new TrapCard(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        assertTrue(trc.getBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1)) instanceof TrapBuilding);
        
        CampfireCard cc = new CampfireCard(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        assertTrue(cc.getBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1)) instanceof CampfireBuilding);
        
    }

    @Test
    public void checkPath(){
        LoopManiaWorld d = new LoopManiaWorld(10, 10, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        //PathPosition p = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        assertTrue(d.isPath(0, 1));
        assertFalse(d.isPath(0, 0));
    }

    @Test
    public void convertCardToBuilding(){
        LoopManiaWorld d = new LoopManiaWorld(10, 10, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        d.loadCardByName("VAMPIRECASTLECARD");
        assertTrue(d.getBuildingListByName(VampireCastleBuilding.VAMPIRECASTLE).size() == 0);
        Building b = d.convertCardToBuildingByCoordinates(0, 0, 2, 2);
        assertEquals("VAMPIRECASTLE", b.getName());
        assertTrue(d.getBuildingListByName(VampireCastleBuilding.VAMPIRECASTLE).size() == 1);
        assertNull(d.convertCardToBuildingByCoordinates(0, 3, 2, 2));
    }
}