package kata;

import com.spun.util.markdown.table.MarkdownTable;
import org.lambda.query.Queryable;

import java.util.List;

public class Monopoly {

    private Place[] board = {
            Place.GO,
            Place.MediterraneanAvenue,
            Place.CommunityChest,
            Place.BalticAvenue,
            Place.IncomeTax,
            Place.ReadingRailroad,
            Place.OrientalAvenue,
            Place.Chance,
            Place.VermontAvenue,
            Place.ConnecticutAvenue,
            Place.Jail,
            Place.StCharlesPlace,
            Place.ElectricCompany,
            Place.StatesAvenue,
            Place.VirginiaAvenue,
            Place.PennsylvaniaRailroad,
            Place.StJamesPlace,
            Place.CommunityChest2,
            Place.TennesseeAvenue,
            Place.NewYorkAvenue,
            Place.FreeParking,
            Place.KentuckyAvenue,
            Place.Chance2,
            Place.IndianaAvenue,
            Place.IllinoisAvenue,
            Place.BORailroad,
            Place.AtlanticAvenue,
            Place.VentnorAvenue,
            Place.WaterWorks,
            Place.MarvinGardens,
            Place.GoToJail,
            Place.PacificAvenue,
            Place.NorthCarolinaAvenue,
            Place.CommunityChest3,
            Place.PennsylvaniaAvenue,
            Place.ShortLineRailroad,
            Place.Chance3,
            Place.ParkPlace,
            Place.LuxuryTax,
            Place.Boardwalk
    };
    Queryable<Player> players = new Queryable<>(Player.class);
    public int currentPlayer = 0;

    public Monopoly(int playerCount) {
        for (int i = 0; i < playerCount; i++) {
            players.add(new Player("Player " + (i + 1)));
        }
    }

    Player getPlayer(int index) {
        return players.get(index);
    }

    @Override
    public String toString() {
        MarkdownTable markdownTable = new MarkdownTable();

        for (var square : board) {
            // get players on square, if there are players, add it to md column, if not
            List<Player> players = getPlayersOnSquare(square);
            markdownTable.addRow(square.name(), players);
        }
        String output = markdownTable.toMarkdown();
        for (var player : players) {
            output += player.details() + "\n";
        }
        output += "current player: " + (currentPlayer + 1);
        return output;
    }

    private List<Player> getPlayersOnSquare(Place square) {
        return players.where(p -> p.location() == square.location());
    }

    public Turn move(int spaces) {
        return move(spaces, false);
    }

    public Turn move(int spaces, boolean isDouble) {
        Player player = players.get(currentPlayer);
        player.move(spaces);
        doAutomaticActions(player);
        return new Turn(player, this, isDouble);
    }

    private void doAutomaticActions(Player player) {
        Place place = board[player.location()];
        var action = place.getAutomaticAction();
        action.execute(player, this, place);
    }

    public Player getOwner(Place place) {
        return players.first(p -> p.properties.contains(place));
    }

    public boolean isPropertyAvailable(int location) {
        Place property = getProperty(location);
        return property.isPurchasable();
    }

    public Place getProperty(int location) {
        return board[location];
    }

    public void endTurn() {
        this.currentPlayer = (this.currentPlayer + 1) % players.size();
    }

    public void moveTo(Place place) {
        int currentLoc = players.get(currentPlayer).location();
        int howManySteps = place.location() - currentLoc;
        move(howManySteps);
    }
}
