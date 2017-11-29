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

/**
 * The Payoffs class represents the two-by-two payoff grid for a single turn in
 * a Prisoner's Dilemma tournament.
 */
public class Payoffs
{
    /**
     * The default number of points awarded to a player if they cooperate and
     * the opposing player cooperates.
     */
    public static final int
            DEFAULT_POINTS_PLAYER_COOPERATE_OPPONENT_COOPERATE = 3;

    /**
     * The default number of points awarded to a player if they cooperate and
     * the opposing player defects.
     */
    public static final int
            DEFAULT_POINTS_PLAYER_COOPERATE_OPPONENT_DEFECT = 0;

    /**
     * The default number of points awarded to a player if they defect and
     * the opposing player cooperates.
     */
    public static final int
            DEFAULT_POINTS_PLAYER_DEFECT_OPPONENT_COOPERATE = 5;

    /**
     * The default number of points awarded to a player if they defect and
     * the opposing player defects.
     */
    public static final int
            DEFAULT_POINTS_PLAYER_DEFECT_OPPONENT_DEFECT = 1;

    /**
     * The number of points awarded to a player if they cooperate and the
     * opposing player cooperates.
     */
    private int pointsCC;

    /**
     * The number of points awarded to a player if they cooperate and the
     * opposing player defects.
     */
    private int pointsCD;

    /**
     * The number of points awarded to a player if they defect and the
     * opposing player cooperates.
     */
    private int pointsDC;

    /**
     * The number of points awarded to a player if they defect and the
     * opposing player defects.
     */
    private int pointsDD;

    /**
     * The minimal number of points awarded in a turn.
     */
    private int min;

    /**
     * The maximal number of points awarded in a turn.
     */
    private int max;

    /**
     * Creates a new payoff table with the default values.
     */
    public Payoffs()
    {
        this(Payoffs.DEFAULT_POINTS_PLAYER_COOPERATE_OPPONENT_COOPERATE,
             Payoffs.DEFAULT_POINTS_PLAYER_COOPERATE_OPPONENT_DEFECT,
             Payoffs.DEFAULT_POINTS_PLAYER_DEFECT_OPPONENT_COOPERATE,
             Payoffs.DEFAULT_POINTS_PLAYER_DEFECT_OPPONENT_DEFECT);
    }

    /**
     * Creates a new payoff table with the specified values.
     *
     * @param pointsCC the number of points a player receives if they
     *                 cooperate and the opponent cooperates.
     * @param pointsCD the number of points a player receives if they
     *                 cooperate and the opponent defects.
     * @param pointsDC the number of points a player receives if they
     *                 defect and the opponent cooperates.
     * @param pointsDD the number of points a player receives if they
     *                 defect and the opponent defects.
     */
    public Payoffs(int pointsCC, int pointsCD, int pointsDC, int pointsDD)
    {
        this.pointsCC = pointsCC;
        this.pointsCD = pointsCD;
        this.pointsDC = pointsDC;
        this.pointsDD = pointsDD;

        this.min = this.pointsCC;
        this.max = this.pointsCC;

        if (this.min > this.pointsCD)
            this.min = this.pointsCD;
        if (this.max < this.pointsCD)
            this.max = this.pointsCD;

        if (this.min > this.pointsDC)
            this.min = this.pointsDC;
        if (this.max < this.pointsDC)
            this.max = this.pointsDC;

        if (this.min > this.pointsDD)
            this.min = this.pointsDD;
        if (this.max < this.pointsDD)
            this.max = this.pointsDD;
    }

    /**
     * Returns the number of points a player receives if they cooperate and
     * the opponent cooperates.
     *
     * @return the number of points awarded.
     */
    public int getPointsCC()
    {
        return this.pointsCC;
    }

    /**
     * Returns the number of points a player receives if they cooperate and
     * the opponent defects.
     *
     * @return the number of points awarded.
     */
    public int getPointsCD()
    {
        return this.pointsCD;
    }

    /**
     * Returns the number of points a player receives if they defect and
     * the opponent cooperates.
     *
     * @return the number of points awarded.
     */
    public int getPointsDC()
    {
        return this.pointsDC;
    }

    /**
     * Returns the number of points a player receives if they defect and
     * the opponent defects.
     *
     * @return the number of points awarded.
     */
    public int getPointsDD()
    {
        return this.pointsDD;
    }

    /**
     * Normalizes the number of points awarded over a number of turns of play
     * to a value between 0 (for the worst play) and 1 (for the best play).
     *
     * @param score the score given of numerous turns of play.
     * @param turns the number of turns of play.
     * @return the normalized score.
     */
    public double normalize(int score, int turns)
    {
        if (score < this.min*turns || score > this.max*turns)
            throw new IllegalArgumentException("Score out of range: " + score);
        if (turns < 0)
            throw new IllegalArgumentException("Negative turns: " + turns);

        if (turns == 0 || this.max == this.min)
            return 1.0;
        return ((double)(score - this.min * turns)) /
                ((this.max - this.min) * turns);
    }
}