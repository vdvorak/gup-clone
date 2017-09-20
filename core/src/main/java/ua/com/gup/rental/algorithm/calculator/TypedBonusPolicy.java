package ua.com.gup.rental.algorithm.calculator;

public class TypedBonusPolicy implements BonusPolicy {
    private int points;

    TypedBonusPolicy(int points) {
        this.points = points;
    }

    @Override
    public int getBonus(int currentBonus) {
        return currentBonus + points;
    }
}
