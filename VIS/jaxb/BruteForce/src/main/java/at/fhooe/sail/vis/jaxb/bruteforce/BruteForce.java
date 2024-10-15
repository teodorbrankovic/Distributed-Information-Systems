package at.fhooe.sail.vis.jaxb.bruteforce;

public class BruteForce {

    public static class Coordinates {
        public String latitude;
        public String longitude;

        public Coordinates(String latitude, String longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        @Override
        public String toString() {
            return "Coordinates: " +
                    "latitude = " + latitude  +
                    ", longitude = " + longitude;
        }
    }

    public static Coordinates extractCoordinates(String xmlFragment) {
        String latitude = xmlFragment.substring(xmlFragment.indexOf("<latitude>") + "<latitude>".length(),
                xmlFragment.indexOf("</latitude>"));
        String longitude = xmlFragment.substring(xmlFragment.indexOf("<longitude>") + "<longitude>".length(),
                xmlFragment.indexOf("</longitude>"));
        return new Coordinates(latitude, longitude);
    }

    public static void main(String[] args) {
        String xmlFragment = "<wgs84>\n" +
                "    <latitude>48.31</latitude>\n" +
                "    <longitude>14.29</longitude>\n" +
                "</wgs84>";
        Coordinates coordinates = extractCoordinates(xmlFragment);

        System.out.println();
        System.out.println("Task 3.1.a");
        System.out.println();
        System.out.println("----BruteForce----");
        System.out.println(coordinates);

    }
}