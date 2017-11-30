/*
 * Copyright (c) 2017 Ryan Vogt <vogtr@cs.ubc.ca>
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Strategy class represents a strategy for playing a tournament of the
 * Prisoner's Dilemma. Each tournament comprises one or more rounds, and each
 * round comprises one or more matches, played round-robin between pairs of
 * players. Each match comprises one or more turns, in which each player
 * chooses to cooperate or to defect.
 */
public abstract class Strategy
{
    /**
     * An instance of every available strategy.
     */
    private static final List<Strategy> allStrategies = Strategy.buildAllStrategies();

    /**
     * The name of the strategy.
     */
    private String name;

    /**
     * Creates a new Strategy object.
     *
     * @param name the name of this strategy.
     */
    public Strategy(String name)
    {
        this.name = name;
    }

    /**
     * Called when a new match begins for this player.
     *
     * @param payoffs the payoff grid, containing the number of points awarded
     *                for every possible pair of actions.
     * @param numTurns the number of turns in this match
     * @param prng if this strategy uses any manner of randomization, it must
     *             use this pseudorandom number generator during this match.
     */
    public void beginMatch(Payoffs payoffs, int numTurns, Random prng)
    {}

    /**
     * Called when a new turn begins for this player.
     *
     * @return whether this player will cooperate or defect on this turn.
     */
    public abstract Action beginTurn();

    /**
     * Called at the end of a turn for this player.
     *
     * @param opponentAction whether the opposing player cooperated or defected
     *                       this turn.
     */
    public void endTurn(Action opponentAction)
    {}

    /**
     * Returns the name of this strategy.
     *
     * @return the name of this strategy.
     */
    public final String getName()
    {
        return this.name;
    }

    /**
     * Returns a new Strategy object that utilizes the same strategy as the
     * given strategy.
     *
     * @param strategy the type of new Strategy to return.
     * @return a new Strategy, employing the same strategy as this object.
     * @throws IllegalArgumentException if the given Strategy cannot be
     *         duplicated.
     */
    public static Strategy getStrategy(Strategy strategy)
    {
        try {
            return strategy.getClass().newInstance();
        }
        catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    /**
     * Returns the Strategy known by the given name.
     *
     * @param name the name of the Strategy for which to get an instance.
     * @return an instance of the named Strategy.
     */
    public static Strategy getStrategy(String name)
    {
        for (Strategy s : Strategy.allStrategies) {
            if (s.getName().equals(name))
                return Strategy.getStrategy(s);
        }
        throw new IllegalArgumentException("Unknown strategy: " + name);
    }

    /**
     * Returns the names of all known strategies.
     *
     * @return a list of all known strategies.
     */
    public static List<String> getAllStrategies()
    {
        List<String> list = new ArrayList<String>();
        for (Strategy s : Strategy.allStrategies)
            list.add(s.getName());
        return list;
    }

    /**
     * Constructs a list containing an instance of every known Strategy.
     *
     * @return a list of every known Strategy.
     */
    private static List<Strategy> buildAllStrategies()
    {
        /*
         * NOTE TO CPSC 210 STUDENTS:
         *
         * You will need to add your ECON 221 group's Strategy to the List of
         * strategies constructed by this method, in order to make it appear
         * in the user interface.
         */

        List<Strategy> list = new ArrayList<Strategy>();
        list.add(new TwoFace());
        list.add(new Copycat());

        return list;
    }
}