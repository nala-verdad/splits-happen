package com.eitccorp.splitshappen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScoreCalculatorTest {
    @Test
    public void getScore() {
        assertEquals(300, ScoreCalculator.getScore("XXXXXXXXXXXX"));
        assertEquals(90,  ScoreCalculator.getScore("9-9-9-9-9-9-9-9-9-9-"));
        assertEquals(150, ScoreCalculator.getScore("5/5/5/5/5/5/5/5/5/5/5"));
        assertEquals(167, ScoreCalculator.getScore("X7/9-X-88/-6XXX81"));
    }
}
