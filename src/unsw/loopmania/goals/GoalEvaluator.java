package unsw.loopmania.goals;

public class GoalEvaluator {

    public static boolean evaluate(GoalNode condition){
        return condition.compute();
    }

    public static String prettyPrint(GoalNode condition) {
        return condition.print();
    }

    
   

    

}
