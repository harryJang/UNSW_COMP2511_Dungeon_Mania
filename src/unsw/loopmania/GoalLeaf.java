package unsw.loopmania;

public class GoalLeaf implements Goal{
    private Integer value;
    private String type;

    public GoalLeaf(String type, Integer value) {
		super();
        this.type = type;
		this.value = value;
	}

    @Override
    public Boolean isGoalCompleted(Integer gold, Integer experience, Integer round, boolean defeatedAllBosses) {
        if (type.equals("cycles")) {
           if (round >= value) {
                return true;
           }
        } else if (type.equals("gold")) { 
            if (gold >= value) {
                return true;
           }
        } else if (type.equals("experience")) {
            if (experience >= value) {
                return true;
           }
        } else if (type.equals("bosses")) {
            return defeatedAllBosses;
        }
        return false;
    }

	@Override
	public Boolean addComponent(Goal child) {
        return false;
    }
}
