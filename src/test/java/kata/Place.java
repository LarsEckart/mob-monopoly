package kata;

public class Place {
    public static final Place GO = new Place("GO", 0, Integer.MAX_VALUE, Rent.getNull());
    public static Place MediterraneanAvenue = new Place("Mediterranean Avenue", 1, 60, Rent.getNull());
    public static Place CommunityChest = new Place("Community Chest", 2, Integer.MAX_VALUE, Rent.getNull());
    public static Place BalticAvenue = new Place("Baltic Avenue", 3, 60, new Rent(4, 20, 60, 180, 320, 450));
    public static Place IncomeTax = new Place("Income Tax", 4, Integer.MAX_VALUE, (p, __, ___) -> p.money -= 200);
    public static Place ReadingRailroad = new Place("Reading Railroad", 5, 200, Rent.getNull());
    public static Place OrientalAvenue = new Place("Oriental Avenue", 6, 100, Rent.getNull());
    public static Place Chance = new Place("Chance", 7, Integer.MAX_VALUE, ChancePile.get());
    public static Place VermontAvenue = new Place("Vermont Avenue", 8, 100, Rent.getNull());
    public static Place ConnecticutAvenue = new Place("Connecticut Avenue", 9, 120, Rent.getNull());
    public static Place Jail = new Place("Jail", 10, Integer.MAX_VALUE, Rent.getNull());
    public static Place StCharlesPlace = new Place("St. Charles Place", 11, 140, Rent.getNull());
    public static Place ElectricCompany = new Place("Electric Company", 12, 150, Rent.getNull());
    public static Place StatesAvenue = new Place("States Avenue", 13, 140, Rent.getNull());
    public static Place VirginiaAvenue = new Place("Virginia Avenue", 14, 160, Rent.getNull());
    public static Place PennsylvaniaRailroad = new Place("Pennsylvania Railroad", 15, 200, Rent.getNull());
    public static Place StJamesPlace = new Place("St. James Place", 16, 180, Rent.getNull());
    public static Place CommunityChest2 = new Place("Community Chest", 17, Integer.MAX_VALUE, Rent.getNull());
    public static Place TennesseeAvenue = new Place("Tennessee Avenue", 18, 180, Rent.getNull());
    public static Place NewYorkAvenue = new Place("New York Avenue", 19, 200, Rent.getNull());
    public static Place FreeParking = new Place("Free Parking", 20, Integer.MAX_VALUE, Rent.getNull());
    public static Place KentuckyAvenue = new Place("Kentucky Avenue", 21, 220, Rent.getNull());
    public static Place Chance2 = new Place("Chance", 22, Integer.MAX_VALUE, ChancePile.get());
    public static Place IndianaAvenue = new Place("Indiana Avenue", 23, 220, Rent.getNull());
    public static Place IllinoisAvenue = new Place("Illinois Avenue", 24, 240, Rent.getNull());
    public static Place BORailroad = new Place("B. & O. Railroad", 25, 200, Rent.getNull());
    public static Place AtlanticAvenue = new Place("Atlantic Avenue", 26, 260, Rent.getNull());
    public static Place VentnorAvenue = new Place("Ventnor Avenue", 27, 260, Rent.getNull());
    public static Place WaterWorks = new Place("Water Works", 28, 150, Rent.getNull());
    public static Place MarvinGardens = new Place("Marvin Gardens", 29, 280, Rent.getNull());
    public static Place GoToJail = new Place("Go To Jail", 30, Integer.MAX_VALUE, Rent.getNull());
    public static Place PacificAvenue = new Place("Pacific Avenue", 31, 300, Rent.getNull());
    public static Place NorthCarolinaAvenue = new Place("North Carolina Avenue", 32, 300, Rent.getNull());
    public static Place CommunityChest3 = new Place("Community Chest", 33, Integer.MAX_VALUE, Rent.getNull());
    public static Place PennsylvaniaAvenue = new Place("Pennsylvania Avenue", 34, 320, Rent.getNull());
    public static Place ShortLineRailroad = new Place("Short Line Railroad", 35, 200, Rent.getNull());
    public static Place Chance3 = new Place("Chance", 36, Integer.MAX_VALUE, ChancePile.get());
    public static Place ParkPlace = new Place("Park Place", 37, 350, Rent.getNull());
    public static Place LuxuryTax = new Place("Luxury Tax", 38, Integer.MAX_VALUE, Rent.getNull());
    public static Place Boardwalk = new Place("Boardwalk", 39, 400, Rent.getNull());

    private final String name;
    private final int location;
    private final int purchaseCost;
    private AutomaticAction automaticAction;

    public Place(String name, int location, int purchaseCost, AutomaticAction automaticAction) {
        this.automaticAction = automaticAction;
        this.name = name;
        this.location = location;
        this.purchaseCost = purchaseCost;
    }

    public String name() {
        return name;
    }

    public int location() {
        return location;
    }

    public int getPurchaseCost() {
        return purchaseCost;
    }

    public AutomaticAction getAutomaticAction() {
        return automaticAction;
    }

    public boolean isPurchasable() {
        return automaticAction instanceof Rent;
    }
}
