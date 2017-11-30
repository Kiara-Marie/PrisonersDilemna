import java.util.Random;




    /**
     * The Copycat strategy cooperates on the first move of a match. In subsequent
     * turns, it does whatever the opponent did in the previous turn.
     */
    public class Copycat extends Strategy
    {
        /**
         * The move that the copycat will make next turn.
         */
        private Action nextMove;

        /**
         * Creates a new Copycat strategy.
         */
        public Copycat()
        {
            // Also called "Tit-For-Tat"
            super("Copycat");
        }

        /**
         * Sets the next action to be cooperation.
         *
         * @param payoffs ignored.
         * @param numTurns ignored.
         * @param prng ignored.
         */
        @Override
        public void beginMatch(Payoffs payoffs, int numTurns, Random prng)
        {
            this.nextMove = Action.COOPERATE;
        }

        /**
         * Returns cooperation for the first move, and whatever the opponent did
         * in the previous turn for subsequent moves.
         *
         * @return Copycat's next move.
         */
        @Override
        public Action beginTurn()
        {
            return this.nextMove;
        }

        /**
         * Updates the next action to be whatever was seen this turn.
         *
         * @param opponentAction whether the opposing player cooperated or
         *                       defected.
         */
        @Override
        public void endTurn(Action opponentAction)
        {
            this.nextMove = opponentAction;
        }
    }

}
