package unsw.loopmania;

import java.util.ArrayList;

public class GoalComponent implements Goal {
    private String operater;
    private ArrayList<Goal> children = new ArrayList<Goal>();

    
    public GoalComponent(String operater) {
		super();
		this.operater = operater;
	}

    @Override
    public Boolean isGoalCompleted(Integer gold, Integer experience, Integer round, boolean defeatedAllBosses) {
        if (operater.equals("AND")) {
            Boolean result = children.get(0).isGoalCompleted(gold, experience, round, defeatedAllBosses) && children.get(1).isGoalCompleted(gold, experience, round, defeatedAllBosses);
            return result;
        } else { // OR
            Boolean result = children.get(0).isGoalCompleted(gold, experience, round, defeatedAllBosses) || children.get(1).isGoalCompleted(gold, experience, round, defeatedAllBosses);
            return result;
        }
        
    }
    
	@Override
	public Boolean addComponent(Goal child) {
        children.add(child);
        return true;
    }
}
