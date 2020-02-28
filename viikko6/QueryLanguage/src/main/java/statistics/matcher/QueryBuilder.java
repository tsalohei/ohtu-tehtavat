package statistics.matcher;

import java.util.LinkedList;
import java.util.List;

public class QueryBuilder {

    Matcher matcher = new All();
    
    public QueryBuilder() {        
    }
    
    public Matcher build() {
        Matcher matcher2 = matcher;
        matcher = new All();
        return matcher2;   
    }
    
    
    public QueryBuilder oneOf(Matcher...matcherit) {
        this.matcher = new Or(matcherit);
        
        return this;
    }
    
    
    public QueryBuilder playsIn(String team) {
        this.matcher = new And(matcher, new PlaysIn(team) );
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        this.matcher = new And(matcher, new HasAtLeast(value, category));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String category) {
        this.matcher = new And(matcher, new HasFewerThan(value, category));
        return this;
    } 
}
