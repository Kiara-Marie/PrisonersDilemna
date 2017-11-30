public class TwoFace extends Strategy {

    private int roundsSoFar;
    private Integer roundWhenOpponentDefected;   // stores the round number which was when the opponent first defected
    private boolean shouldDefect;

    public TwoFace() {
        super("TwoFace");
        this.roundsSoFar = 1;
        this.shouldDefect = false;
        this.roundWhenOpponentDefected = 0;   // initialized at zero for reason explained in endTurn method
    }

    /**
     * Checks if shouldDefect is true and returns DEFECT if it is, COOPERATE otherwise
     * @return the action which should be played
     */
    @Override
    public Action beginTurn() {
        return shouldDefect ? Action.DEFECT : Action.COOPERATE;
    }

    /**
     * Checks if conditions have been met to trigger defecting, and sets shouldDefect accordingly
     * Note that if shouldDefect is already true, entering the nested if statements is not necessary,
     * because once shouldDefect is true, it will remain true through the duration of the match.
     * Conditions which could trigger defecting:
     * 1. We are entering second round and the opponent defected on the opening round
     * 2. Opponent has already defected once, and defected again in the previous round
     * 3. 80 rounds have elapsed since the last time the opponent defected
     * 4. 80 rounds have elapsed since the first round, and the opponent has never defected
     *
     * @param opponentAction whether the opposing player cooperated or defected
     */

    @Override
    public void endTurn(Action opponentAction) {
        if (!(shouldDefect)) {
            if (opponentAction == Action.DEFECT) {
                if (this.roundsSoFar == 1){
                    shouldDefect = true;
                }
                if (!(0 == roundWhenOpponentDefected)) {
                    shouldDefect = true;
                } else {
                    roundWhenOpponentDefected = roundsSoFar;
                }
            } else if ((roundWhenOpponentDefected + 80) == roundsSoFar) {         // since roundWhenOpponentDefected
                                                                                // was initialized with zero, if the opponent never defects,
                                                                               // this will be true at turn 80, as is the desired implementation
                shouldDefect = true;
            }
        }
        this.roundsSoFar++;
    }

}
