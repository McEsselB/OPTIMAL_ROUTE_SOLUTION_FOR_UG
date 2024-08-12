package app;

public class Main {
    public static Map campusNavigationGraph = new Map();

    public static void main(String[] args) {

        Destination mainGate = new Destination("Main Gate");
        Destination UGFireService = new Destination("UG Fire Service");
        Destination greatHall = new Destination("Great Hall");
        Destination nb = new Destination("N Block");
        Destination jqb = new Destination("JQB");
        Destination pentHall = new Destination("Pentagon Hall");
        Destination balmeLibrary = new Destination("Balme Library");
        Destination nnb = new Destination("NNB");
        Destination ugcs = new Destination("UGCS");
        Destination CSDepartment = new Destination("CS Department");
        Destination athleticOval = new Destination("Athletic Oval");
        Destination voltaHall = new Destination("Volta Hall");
        Destination akuafoHall = new Destination("Akuafo Hall");
        Destination legonHall = new Destination("Legon Hall");
        Destination nightMarket = new Destination("Night Market");
        Destination diasporaHalls = new Destination("Diaspora");
        Destination businessSchool = new Destination("Business School");
        Destination lawSchool = new Destination("Law School");
        Destination commonWealthHall = new Destination("Common Wealth Hall");

        campusNavigationGraph.createVertex(mainGate);
        campusNavigationGraph.createVertex(greatHall);
        campusNavigationGraph.createVertex(nb);
        campusNavigationGraph.createVertex(UGFireService);
        campusNavigationGraph.createVertex(jqb);
        campusNavigationGraph.createVertex(pentHall);
        campusNavigationGraph.createVertex(balmeLibrary);
        campusNavigationGraph.createVertex(nnb);
        campusNavigationGraph.createVertex(ugcs);
        campusNavigationGraph.createVertex(CSDepartment);
        campusNavigationGraph.createVertex(athleticOval);
        campusNavigationGraph.createVertex(voltaHall);
        campusNavigationGraph.createVertex(akuafoHall);
        campusNavigationGraph.createVertex(legonHall);
        campusNavigationGraph.createVertex(nightMarket);
        campusNavigationGraph.createVertex(diasporaHalls);
        campusNavigationGraph.createVertex(businessSchool);
        campusNavigationGraph.createVertex(lawSchool);
        campusNavigationGraph.createVertex(commonWealthHall);

        campusNavigationGraph.addRouteEdge(new RouteDuration(mainGate, jqb, 300));
        campusNavigationGraph.addRouteEdge(new RouteDuration(mainGate, UGFireService, 240));
        campusNavigationGraph.addRouteEdge(new RouteDuration(mainGate, akuafoHall, 500));

        campusNavigationGraph.addRouteEdge(new RouteDuration(UGFireService, akuafoHall, 650));

        campusNavigationGraph.addRouteEdge(new RouteDuration(greatHall, commonWealthHall, 100));

        campusNavigationGraph.addRouteEdge(new RouteDuration(nb, balmeLibrary, 600));
        campusNavigationGraph.addRouteEdge(new RouteDuration(nb, businessSchool, 500));
        campusNavigationGraph.addRouteEdge(new RouteDuration(nb, nnb, 500));
        campusNavigationGraph.addRouteEdge(new RouteDuration(nb, CSDepartment, 500));
        campusNavigationGraph.addRouteEdge(new RouteDuration(nb, ugcs, 500));

        campusNavigationGraph.addRouteEdge(new RouteDuration(voltaHall, businessSchool, 200));
        campusNavigationGraph.addRouteEdge(new RouteDuration(voltaHall, commonWealthHall, 450));
        campusNavigationGraph.addRouteEdge(new RouteDuration(voltaHall, legonHall, 190));

        campusNavigationGraph.addRouteEdge(new RouteDuration(businessSchool, ugcs, 70));

        campusNavigationGraph.addRouteEdge(new RouteDuration(legonHall, akuafoHall, 400));
        campusNavigationGraph.addRouteEdge(new RouteDuration(legonHall, athleticOval, 450));
        campusNavigationGraph.addRouteEdge(new RouteDuration(legonHall, commonWealthHall, 750));

        campusNavigationGraph.addRouteEdge(new RouteDuration(jqb, akuafoHall, 700));
        campusNavigationGraph.addRouteEdge(new RouteDuration(jqb, lawSchool, 350));

        campusNavigationGraph.addRouteEdge(new RouteDuration(lawSchool, pentHall, 900));

        campusNavigationGraph.addRouteEdge(new RouteDuration(ugcs, balmeLibrary, 200));
        campusNavigationGraph.addRouteEdge(new RouteDuration(ugcs, voltaHall, 350));

        campusNavigationGraph.addRouteEdge(new RouteDuration(CSDepartment, balmeLibrary, 650));
        campusNavigationGraph.addRouteEdge(new RouteDuration(CSDepartment, akuafoHall, 850));
        campusNavigationGraph.addRouteEdge(new RouteDuration(CSDepartment, lawSchool, 650));

        campusNavigationGraph.addRouteEdge(new RouteDuration(balmeLibrary, akuafoHall, 270));
        campusNavigationGraph.addRouteEdge(new RouteDuration(balmeLibrary, legonHall, 500));

        campusNavigationGraph.addRouteEdge(new RouteDuration(athleticOval, akuafoHall, 550));
        campusNavigationGraph.addRouteEdge(new RouteDuration(athleticOval, nightMarket, 800));

        campusNavigationGraph.addRouteEdge(new RouteDuration(akuafoHall, nightMarket, 1000));

        campusNavigationGraph.addRouteEdge(new RouteDuration(nightMarket, diasporaHalls, 850));

        new DestinationSelectorUI();

    }
}