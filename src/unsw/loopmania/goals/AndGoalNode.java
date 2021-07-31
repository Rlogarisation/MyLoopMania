package unsw.loopmania.goals;

public class AndGoalNode implements GoalNode{
    private GoalNode b1;
    private GoalNode b2;

    public AndGoalNode(GoalNode b1, GoalNode b2) {
        this.b1 = b1;
        this.b2= b2;
    }

    public boolean compute() {
        return b1.compute() && b2.compute();
    }

    @Override
    public String print() {
        return "(AND " +b1.print() + " " + b2.print()+ ")";
        
    }

}
