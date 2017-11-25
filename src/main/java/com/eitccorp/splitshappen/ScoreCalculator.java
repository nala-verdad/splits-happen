package com.eitccorp.splitshappen;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Given a valid sequence of rolls for one line of American Ten-Pin Bowling, produces the total score for the game.
 */
public class ScoreCalculator {
    /**
     * Given a valid sequence of roles, returns the total score for the game.
     *
     * @param game the game to score, in the format {@code X7/9-X-88/-6XXX81}
     * @return the score for the provided game
     */
    public static int getScore(String game) {
        // This matcher is used to find each individual frame within the game, including any extra rolls needed to
        // calculate the score for the frame.
        Matcher frameMatcher = Pattern.compile("(X..|./.|..)").matcher(game);
        return Stream
                .generate(() -> {
                    if (frameMatcher.find()) {
                        String frame = frameMatcher.group();
                        // Move the matcher forward 1 or 2 characters to move past the current frame.
                        frameMatcher.region(frameMatcher.start() + (frame.startsWith("X") ? 1 : 2), game.length());
                        return frame;
                    }
                    return null;
                })
                .limit(10) // Games are limited to 10 frames.
                .mapToInt(frame -> // Turn the frame into a score.
                        // Replacing all <num>/ rolls with -/ makes the math easier,
                        // just return the sum of all the individual rolls.
                        Arrays.stream(frame.replaceAll("./", "-/").split(""))
                                .mapToInt(roll -> {
                                    switch (roll) {
                                        case "X":
                                        case "/": return 10; // Ok since we turned the previous roll into a -
                                        case "-": return 0;
                                        default:  return Integer.parseInt(roll);
                                    }
                                })
                                .sum())
                .sum();
    }
}
