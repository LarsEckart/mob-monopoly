package kata;

public abstract class ChanceCard implements AutomaticAction {

    public static class BankCard extends ChanceCard {

        @Override
        public void execute(Player player, Monopoly monopoly, Place place) {
            player.money += 50;
        }
    }

    public static class GoToStCharlesPlace extends ChanceCard {

        @Override
        public void execute(Player player, Monopoly monopoly, Place place) {
            monopoly.moveTo(Place.StCharlesPlace);
        }
    }

    public static class GoBack3Spaces extends ChanceCard {

        @Override
        public void execute(Player player, Monopoly monopoly, Place place) {
            monopoly.move(-3);
        }
    }

    public static class SpeedingFine extends ChanceCard {

        @Override
        public void execute(Player player, Monopoly monopoly, Place place) {
            player.money -= 15;
        }
    }

    public static class ChairmanOfTheBoard extends ChanceCard {

        @Override
        public void execute(Player currentPlayer, Monopoly monopoly, Place place) {
            monopoly.players.where(p -> p != currentPlayer).forEach(p -> {
                p.money += 50;
                currentPlayer.money -= 50;
            });
        }
    }

    public static class GetOutOfJailFree extends ChanceCard {
        @Override
        public void execute(Player player, Monopoly monopoly, Place place) {
            player.outOfJailFreeCardCount++;
        }
    }

    public static class AdvanceToNearestRailroad extends ChanceCard {

        // Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay wonder twice the rental to which they are otherwise entitled
        @Override
        public void execute(Player player, Monopoly monopoly, Place place) {
            monopoly.moveTo(nextRailroad(player.location()));
        }

        private Place nextRailroad(int location) {
            if (location < Place.ReadingRailroad.location()) {
                return Place.ReadingRailroad;
            } else if (location < Place.PennsylvaniaRailroad.location()) {
                return Place.PennsylvaniaRailroad;
            } else if (location < Place.BORailroad.location()) {
                return Place.BORailroad;
            } else if (location < Place.ShortLineRailroad.location()) {
                return Place.ShortLineRailroad;
            } else {
                return Place.ReadingRailroad;
            }

        }
    }
}
