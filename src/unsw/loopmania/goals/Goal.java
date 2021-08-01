package unsw.loopmania.goals;

public class Goal implements GoalNode {
    private boolean bool;

    public Goal(boolean bool){
        this.bool = bool;
    }
    public boolean compute() {
        return bool;
    }
    @Override
    public String print() {
        return String.valueOf(bool);
        
    }

}
