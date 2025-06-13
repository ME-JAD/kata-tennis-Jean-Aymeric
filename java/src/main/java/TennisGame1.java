public class TennisGame1 implements TennisGame {

    static final int ADVANTAGE_POINTS_LIMIT = 3;
    static final String SCORE_SEPARATOR = "-";
    static final String DEUCE_SCORE = "Deuce";
    static final String LOVE = "Love";
    static final String FIFTEEN = "Fifteen";
    static final String THIRTY = "Thirty";
    static final String FORTY = "Forty";
    private final String player1Name;
    private final String player2Name;
    private int nbPointsPlayer1 = 0;
    private int nbPointsPlayer2 = 0;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (this.player1Name.equals(playerName)) this.nbPointsPlayer1 += 1;
        if (this.player2Name.equals(playerName)) this.nbPointsPlayer2 += 1;
    }

    public String getScore() {
        if (this.nbPointsPlayer1 == this.nbPointsPlayer2) return this.generateEqualScore();

        if (this.nbPointsPlayer1 > TennisGame1.ADVANTAGE_POINTS_LIMIT
                || this.nbPointsPlayer2 > TennisGame1.ADVANTAGE_POINTS_LIMIT) {
            return this.generateMoreThanPointsAdvantageLimitScore();
        }

        return this.generateLessThanPointsAdvantageLimitScore();
    }

    private String generateEqualScore() {
        if (this.nbPointsPlayer1 >= TennisGame1.ADVANTAGE_POINTS_LIMIT) return TennisGame1.DEUCE_SCORE;
        return TennisGame1.getScoreFromPoints((this.nbPointsPlayer1)) + TennisGame1.SCORE_SEPARATOR + "All";
    }

    private String generateMoreThanPointsAdvantageLimitScore() {
        String score;
        int minusResult = this.nbPointsPlayer1 - this.nbPointsPlayer2;
        score = (Math.abs(minusResult) == 1) ? "Advantage" : "Win for";
        score += " ";
        score += (minusResult > 0) ? "player1" : "player2";
        return score;
    }

    private String generateLessThanPointsAdvantageLimitScore() {
        return TennisGame1.getScoreFromPoints(
                this.nbPointsPlayer1) + TennisGame1.SCORE_SEPARATOR + TennisGame1.getScoreFromPoints(
                this.nbPointsPlayer2);
    }

    private static String getScoreFromPoints(final int points) {
        return switch (points) {
            case 0 -> TennisGame1.LOVE;
            case 1 -> TennisGame1.FIFTEEN;
            case 2 -> TennisGame1.THIRTY;
            case 3 -> TennisGame1.FORTY;
            default -> TennisGame1.DEUCE_SCORE;
        };
    }
}
