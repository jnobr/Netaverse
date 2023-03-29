/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cwk4tests;

import cwk4.SpaceWars;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import cwk4.WIN;

/**
 *
 * @author aam
 */
public class T2DisplayBattlesForcesTest {
    WIN swim;
    
    public T2DisplayBattlesForcesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        swim = new SpaceWars("Bob");
    }
    
    @After
    public void tearDown() {
    }

    private boolean containsText2(String text, String s1, String s2, String s3) {
        boolean result = false;
        result = text.contains(s1) && text.contains(s2) && text.contains(s3);
        return result;
    }
    
    
    
    @Test
    public void battleNo1Displayed() {
        String result = swim.getAllBattles();
        boolean actual = containsText2(result, "1", "Fight", "Borg");
        assertTrue(actual);
    }
    
    @Test
    public void battleNo2Displayed() {
        String result = swim.getAllBattles();
        boolean actual = containsText2(result, "2", "Skirmish", "Kardassians");
        assertTrue(actual);
    }
    
    @Test
    public void battleNo3Displayed() {
        String result = swim.getAllBattles();
        boolean actual = containsText2(result, "3", "Ambush", "Ferengi");
        assertTrue(actual);
    }
    
    @Test
    public void battleNo4Displayed() {
        String result = swim.getAllBattles();
        boolean actual = containsText2(result, "4", "Fight", "Ewoks");
        assertTrue(actual);
    }
    
    @Test
    public void battleNo5Displayed() {
        String result = swim.getAllBattles();
        boolean actual = containsText2(result, "5", "Ambush", "Borg");
        assertTrue(actual);
    }
    
    @Test
    public void battleNo6Displayed() {
        String result = swim.getAllBattles();
        boolean actual = containsText2(result, "6", "Skirmish", "Groaners");
        assertTrue(actual);
    }
    
    @Test
    public void forceFleetIW1Displayed() {
        String result = swim.getForcesInDock();
        boolean actual = containsText2(result, "IW1", "Twisters", "");
        assertTrue(actual);
    }
    
    @Test
    public void forceFleetSS2Displayed() {
        String result = swim.getForcesInDock();
        boolean actual = containsText2(result, "SS2", "Enterprise", "");
        assertTrue(actual);
    }
    
    
    @Test
    public void forceFleetWB3Displayed() {
        String result = swim.getForcesInDock();
        boolean actual = containsText2(result, "WB3", "Droop", "");
        assertTrue(actual);
    }
    
    
    @Test
    public void forceFleetIW4Displayed() {
        String result = swim.getForcesInDock();
        boolean actual = containsText2(result, "IW4", "Wingers", "");
        assertTrue(actual);
    }
    
    @Test
    public void forceFleetWB5Displayed() {
        String result = swim.getForcesInDock();
        boolean actual = containsText2(result, "WB5", "Hang", "");
        assertTrue(actual);
    }
    
    @Test
    public void forceFleetSS6Displayed() {
        String result = swim.getForcesInDock();
        boolean actual = containsText2(result, "SS6", "Voyager", "");
        assertTrue(actual);
    }
    
    @Test
    public void forceFleetSS7Displayed() {
        String result = swim.getForcesInDock();
        boolean actual = containsText2(result, "SS7", "Explorer", "");
        assertTrue(actual);
    }
    
    @Test
    public void forceFleetWB9Displayed() {
        String result = swim.getForcesInDock();
        boolean actual = containsText2(result, "WB9", "Hover", "");
        assertTrue(actual);
    }
    
    @Test
    public void forceFleetIW10Displayed() {
        String result = swim.getForcesInDock();
        boolean actual = containsText2(result, "IW10", "Flyers", "");
        assertTrue(actual);
    }
}
