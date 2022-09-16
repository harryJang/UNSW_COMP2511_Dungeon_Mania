package unsw.loopmania;

public interface Goal {
	public Boolean isGoalCompleted(Integer gold, Integer experience, Integer round, boolean defeatedAllBosses);
	
	public Boolean addComponent(Goal child);
}
