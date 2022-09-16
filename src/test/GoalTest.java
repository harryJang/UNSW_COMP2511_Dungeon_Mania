package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import unsw.loopmania.*;
import unsw.loopmania.Character;

import java.util.Arrays;
import org.javatuples.Pair;

public class GoalTest {
    @Test
    public void testAND() {
        Goal goalOperater1 = new GoalComponent("AND");
        goalOperater1.addComponent(new GoalLeaf("cycles", 2));
        goalOperater1.addComponent(new GoalLeaf("gold", 10));
        assertFalse(goalOperater1.isGoalCompleted(0, 0, 0, false));
        assertTrue(goalOperater1.isGoalCompleted(10, 0, 2, false));
        //goalOperater.isGoalCompleted(gold, experience, round)
        Goal goalOperater2 = new GoalComponent("AND");
        goalOperater2.addComponent(new GoalLeaf("experience", 2));
        goalOperater2.addComponent(new GoalLeaf("gold", 10));
        assertFalse(goalOperater2.isGoalCompleted(0, 0, 0, false));
        assertTrue(goalOperater2.isGoalCompleted(10, 2, 0, false));
    }

    @Test
    public void testOR() {
        Goal goalOperater1 = new GoalComponent("OR");
        goalOperater1.addComponent(new GoalLeaf("cycles", 2));
        goalOperater1.addComponent(new GoalLeaf("gold", 10));
        assertFalse(goalOperater1.isGoalCompleted(0, 0, 0, false));
        assertTrue(goalOperater1.isGoalCompleted(10, 0, 0, false));
        //goalOperater.isGoalCompleted(gold, experience, round)
        Goal goalOperater2 = new GoalComponent("OR");
        goalOperater2.addComponent(new GoalLeaf("experience", 2));
        goalOperater2.addComponent(new GoalLeaf("gold", 10));
        assertFalse(goalOperater2.isGoalCompleted(0, 0, 0, false));
        assertTrue(goalOperater2.isGoalCompleted(0, 2, 0, false));
    }

    @Test
    public void testSingleLeaf() {
        Goal goalOperater = new GoalLeaf("cycles", 2);
        assertFalse(goalOperater.isGoalCompleted(0, 0, 0, false));
        assertTrue(goalOperater.isGoalCompleted(0, 0, 2, false));

        goalOperater = new GoalLeaf("experience", 2);
        assertFalse(goalOperater.isGoalCompleted(0, 0, 0, false));
        assertTrue(goalOperater.isGoalCompleted(0, 2, 0, false));

        goalOperater = new GoalLeaf("gold", 2);
        assertFalse(goalOperater.isGoalCompleted(0, 1110, 2220, false));
        assertTrue(goalOperater.isGoalCompleted(2, 0, 0, false));
    }

    @Test
    public void testCombine() {
        Goal goalOperater1 = new GoalComponent("AND");
        goalOperater1.addComponent(new GoalLeaf("cycles", 2));
        Goal goalOperater2 = new GoalComponent("AND");
        goalOperater1.addComponent(goalOperater2);
        goalOperater2.addComponent(new GoalLeaf("gold", 10));
        goalOperater2.addComponent(new GoalLeaf("gold", 12));
        assertFalse(goalOperater1.isGoalCompleted(10, 0, 2, false));
        assertTrue(goalOperater1.isGoalCompleted(12, 0, 2, false));
        
        Goal goalOperater3 = new GoalComponent("AND");
        goalOperater3.addComponent(new GoalLeaf("cycles", 2));
        Goal goalOperater4 = new GoalComponent("OR");
        goalOperater3.addComponent(goalOperater4);
        goalOperater4.addComponent(new GoalLeaf("gold", 10));
        goalOperater4.addComponent(new GoalLeaf("experience", 12));
        assertFalse(goalOperater3.isGoalCompleted(10, 0, 1, false));
        assertTrue(goalOperater3.isGoalCompleted(10, 0, 2, false));

    }

    @Test
    public void testWorldGoal() {
        LoopManiaWorld d = new LoopManiaWorld(10, 10, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3), new Pair<>(1, 3), new Pair<>(1, 2)));
        PathPosition pp = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3), new Pair<>(1, 3), new Pair<>(1, 2)));
        Character c = new Character(pp);
        d.setCharacter(c);

        Goal goalOperater1 = new GoalComponent("AND");
        goalOperater1.addComponent(new GoalLeaf("cycles", 2));
        Goal goalOperater2 = new GoalComponent("AND");
        goalOperater1.addComponent(goalOperater2);
        goalOperater2.addComponent(new GoalLeaf("gold", 10));
        goalOperater2.addComponent(new GoalLeaf("gold", 12));
        
        assertFalse(d.isGoalCompleted());
        d.setGoals(goalOperater1);
        assertFalse(d.isGoalCompleted());

        d.updateRound();
        assertTrue(d.isGoalCompleted());
    }
}
