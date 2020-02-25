package statistics.matcher;

import java.util.ArrayList;
import statistics.Player;

public class All implements Matcher {

    
    @Override
    public boolean matches(Player p) {
        return (p instanceof Player);
    }
    
}
