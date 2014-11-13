package UsedByAll;

public class RegionMatch {
    public static boolean IsStringRegionMatch(String searchMe, String findMe) {
        int searchMeLength = searchMe.length();
        int findMeLength = findMe.length();
        boolean foundIt = false;
        for (int i = 0; i <= (searchMeLength - findMeLength); i++)
        {
            if (searchMe.regionMatches(i, findMe, 0, findMeLength))
                foundIt = true;
        }
        return foundIt;
    }
}