package statistics.matcher;

import java.lang.reflect.Method;
import statistics.Player;

public class HasFewerThan implements Matcher {

    int value;
    String fieldName;
    
    public HasFewerThan(int value, String category) {
        this.value = value;
        fieldName = "get"+Character.toUpperCase(category.charAt(0))+category.substring(1, category.length());
    }
    
    @Override
    public boolean matches(Player p) {
        try {                                    
            Method method = p.getClass().getMethod(fieldName); //Player getGoals tms
            int playersValue = (Integer)method.invoke(p); //p.getGoals
            return playersValue<value; //onko pienempi kuin parametrina annettu kriteeri
            
        } catch (Exception ex) {
            System.out.println(ex);
            throw new IllegalStateException("Player does not have field "+fieldName.substring(3, fieldName.length()).toLowerCase());
        }
    }
    
}
