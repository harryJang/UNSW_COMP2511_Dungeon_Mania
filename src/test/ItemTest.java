package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import org.javatuples.Pair;
import org.junit.Test;

import jdk.nashorn.api.tree.Tree;
import unsw.loopmania.Anduril;
import unsw.loopmania.Armour;
import unsw.loopmania.Helmet;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Shield;
import unsw.loopmania.Staff;
import unsw.loopmania.Stake;
import unsw.loopmania.Sword;
import unsw.loopmania.TreeStump;

public class ItemTest {
    @Test
    public void testLoadItem(){
        LoopManiaWorld d = new LoopManiaWorld(10, 10, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        assertTrue(d.loadItemByName("SWORD") instanceof Sword);
        assertTrue(d.loadItemByName("STAKE") instanceof Stake);
        assertTrue(d.loadItemByName("STAFF") instanceof Staff);
        assertTrue(d.loadItemByName("SHIELD") instanceof Shield);
        assertTrue(d.loadItemByName("ARMOUR") instanceof Armour);
        assertTrue(d.loadItemByName("HELMET") instanceof Helmet);
        assertTrue(d.loadItemByName("ANDURIL") instanceof Anduril);
        assertTrue(d.loadItemByName("TREESTUMP") instanceof TreeStump);
        assertNull(d.loadItemByName("nooo"));
    }

    @Test
    public void testRareItem(){
        LoopManiaWorld d = new LoopManiaWorld(10, 10, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        d.setRareItemByName("THEONERING");
        d.setRareItemByName(Anduril.ANDURIL);
        d.setRareItemByName(TreeStump.TREESTUMP);
    }

}