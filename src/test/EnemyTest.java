package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.javatuples.Pair;

import unsw.loopmania.PathPosition;
import unsw.loopmania.Slug;
import unsw.loopmania.Vampire;
import unsw.loopmania.Zombie;
import unsw.loopmania.Character;


public class EnemyTest {  
    @Test
    public void testNewSlug(){
        PathPosition newposition = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        assertTrue(new Slug(newposition) instanceof Slug);
    }

    @Test
    public void testNewVampire(){
        PathPosition newposition = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        assertTrue(new Vampire(newposition) instanceof Vampire);
    }

    @Test
    public void testNewZombie(){
        PathPosition newposition = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        assertTrue(new Zombie(newposition) instanceof Zombie);
    }

    @Test
    public void testEnemyLoseHealth(){
        PathPosition newposition1 = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 1)));
        PathPosition newposition2 = new PathPosition(0, Arrays.asList(new Pair<>(0, 2), new Pair<>(0, 2)));
        PathPosition newposition3 = new PathPosition(0, Arrays.asList(new Pair<>(0, 3), new Pair<>(0, 3)));
        Slug slug = new Slug(newposition1);
        Vampire vampire = new Vampire(newposition2) ;
        Zombie zombie = new Zombie(newposition3);
        int slugOriginHealth = slug.getHealth();
        int vampireOriginHealth = vampire.getHealth();
        int zombieOriginHealth = zombie.getHealth();
        slug.loseHealth(10);
        vampire.loseHealth(20);
        zombie.loseHealth(30);
        assertEquals(10, slugOriginHealth - slug.getHealth());
        assertEquals(20, vampireOriginHealth -vampire.getHealth());
        assertEquals(30, zombieOriginHealth - zombie.getHealth());
    }

    @Test
    public void testSlugAttackCharacter(){
        PathPosition newposition1 = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 1)));
        Slug slug = new Slug(newposition1);
        PathPosition pp = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        Character c = new Character(pp);
        int health1 = c.getHealth();
        slug.attack(c);
        int health2 = c.getHealth();
        int attackDamage = health1 - health2;
        assertTrue(attackDamage<=15);
        assertTrue(attackDamage>=10);
    }

    @Test
    public void testVampireAttackCharacter(){
        PathPosition newposition1 = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 1)));
        Vampire vampire = new Vampire(newposition1);
        PathPosition pp = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        Character c = new Character(pp);
        int health1 = c.getHealth();
        vampire.attack(c);
        int health2 = c.getHealth();
        int attackDamage = health1 - health2;
        assertTrue(attackDamage<=65);
        assertTrue(attackDamage>=20);
        
    }

    public void testZombieAttackCharacter(){

        PathPosition newposition1 = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 1)));
        Zombie zombie = new Zombie(newposition1);
        PathPosition pp = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2)));
        Character c = new Character(pp);
        int health1 = c.getHealth();
        zombie.attack(c);
        int health2 = c.getHealth();
        int attackDamage = health1 - health2;
        assertTrue(attackDamage ==20);
    }

    @Test
    public void testEnemyBattleRadius(){
        PathPosition newposition1 = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 1)));
        Slug slug = new Slug(newposition1);
        int radius = slug.getBattleRadius();
        assertEquals(2, radius);
    }

    @Test
    public void testEnemySupportRadius(){
        PathPosition newposition1 = new PathPosition(0, Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 1)));
        Slug slug = new Slug(newposition1);
        int radius = slug.getSupportRadius();
        assertEquals(3, radius);
    }

    


}






