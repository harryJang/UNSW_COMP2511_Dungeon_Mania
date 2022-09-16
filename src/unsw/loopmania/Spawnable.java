package unsw.loopmania;

import java.util.List;

import org.javatuples.Pair;

public interface Spawnable {
    public Pair<Integer, Integer> spawnEnemy(List<Pair<Integer, Integer>> orderedPath);
}
