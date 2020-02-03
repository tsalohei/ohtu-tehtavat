
package ohtu;

public class Player implements Comparable<Player>{
    private String name;
    private int goals;
    private int assists;
    private String team;
    
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setGoals(int goals) {
        this.goals = goals;
    }
    
    public int getGoals() {
        return this.goals;
    }
    
    public void setAssists(int assists) {
        this.assists = assists;
    }
    
    public int getAssists() {
        return this.assists;
    }
    
    public void setTeam(String team) {
        this.team = team;
    }
    
    public String getTeam() {
        return this.team;
    }

    public int goalsAndAssists() {
        return this.goals + this.assists;
    }
    
    @Override
    public String toString() {
        //return name + "team " + team + " goals " + goals + " assists " + assists;
        return name + " " + team + " " + goals + " + " + assists + " = " + (goals + assists);
    }

    @Override
    public int compareTo(Player o) {
        return (o.goals + o.assists) - (this.goals + this.assists);
    }
      
}
