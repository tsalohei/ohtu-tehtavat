package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author taru
 */
public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54)); // 99
            players.add(new Player("Kurri",   "EDM", 37, 53)); // 90
            players.add(new Player("Yzerman", "DET", 42, 56)); // 98
            players.add(new Player("Gretzky", "EDM", 35, 89)); // 124
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        stats = new Statistics(readerStub);
    }  

    //pelkkä pelaaja-olioiden vertailu ei onnistu, joten toString
    @Test
    public void existingPlayerIsFoundwithSearch() {
        Player testPlayer = new Player("Semenko", "EDM", 4, 12);
        assertEquals(testPlayer, stats.search("Semenko"));
    }
    
    @Test
    public void searchingForNonExistingPlayerWorksCorrectly() {
        assertEquals(null, stats.search("foo"));
    }
    
    
    @Test
    public void gettingThePlayersForaTeamWorks() {
        ArrayList<Player> playersOfTeam = new ArrayList<Player>();
        Player testPlayer = new Player("Lemieux", "PIT", 45, 54);
        playersOfTeam.add(testPlayer);
        
        assertEquals(playersOfTeam, stats.team("PIT"));
    }
    
    @Test
    public void gettingTopScorersWorks() {
        Player player2 = new Player("Lemieux", "PIT", 45, 54);
        Player player1 = new Player("Gretzky", "EDM", 35, 89);
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        
        assertEquals(players, stats.topScorers(1));
    }
}
