package unsw.loopmania.goals;

import unsw.loopmania.Character;

import org.json.JSONArray;
import org.json.JSONObject;

public class ExpressionFactory {
    private Character character;
    private JSONObject jObject;
    public ExpressionFactory(Character character, JSONObject jObject){
        this.character = character;
        this.jObject = jObject;
    }

    public GoalNode getExpression(){
        return expressionMaker(jObject);
    }

    private GoalNode expressionMaker(JSONObject jObject){
        String goalType = jObject.getString("goal");
        if(goalType.equals("AND")){
            JSONArray subgoals = jObject.getJSONArray("subgoals");
            return new AndGoalNode(expressionMaker(subgoals.getJSONObject(0)), 
                                        expressionMaker(subgoals.getJSONObject(1)));
        }
        if(goalType.equals("OR")){
            JSONArray subgoals = jObject.getJSONArray("subgoals");
            return new OrGoalNode(expressionMaker(subgoals.getJSONObject(0)), 
                                        expressionMaker(subgoals.getJSONObject(1)));

        }
        if(goalType.equals("experience")){
            int quantity = jObject.getInt("quantity");
            if(character.getXp() >= quantity){
                return new Goal(true);
            }
            return new Goal(false);
        }
        if(goalType.equals("cycles")){
            int quantity = jObject.getInt("quantity");
            if(character.getCycleCount() >= quantity){
                return new Goal(true);
            }
            return new Goal(false);
        }
        if(goalType.equals("gold")){
            int quantity = jObject.getInt("quantity");
            if(character.getGold() >= quantity){
                return new Goal(true);
            }
            return new Goal(false);
        }
        if(goalType.equals("bosses")){
            if(character.getBossKilled()>=2){
                return new Goal(true);
            }
            return new Goal(false);
            }
            return null;
        }
    }

