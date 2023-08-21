package kata;

import org.lambda.query.Queryable;

import java.text.MessageFormat;

public class Player {
    private final String name;
    public Queryable<Place> properties = new Queryable<>(Place.class);
    public int outOfJailFreeCardCount;
    private int location;
    public int money = 1500;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int location() {
        return location;
    }

    public String details() {
        return "%s ($%s) %s %s".formatted(this.name, money, properties.join(", ", p -> p.name()),
                printOutOfJailFreeCards());
    }

    private String printOutOfJailFreeCards() {
        return switch (outOfJailFreeCardCount) {
            case 1 -> "GetOutOfJailFree";
            case 2 -> "GetOutOfJailFree (2)";
            default -> "";
        };
    }

    public void move(int spaces) {
        location += spaces;
    }

    public int getMoney() {
        return money;
    }

    public Upgrades getPropertyGroupImprovements(Place place) {
        return Upgrades.SINGLE;
    }

    public void move(Place place) {
        location = place.location();
    }
}
