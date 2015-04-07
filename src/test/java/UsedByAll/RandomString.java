package UsedByAll;

import java.util.Random;

/**
 * Created by User on 3/31/2015.
 */
public class RandomString {
    static final String letters = "abcdefghijklmnopqrstuvwxyz";
    static Random random = new Random();

    public String generateString(int length){
        StringBuilder sb = new StringBuilder(length);
        for(int i=0; i<length; i++)
            sb.append(letters.charAt(random.nextInt(letters.length())));
        return sb.toString();

    }
}
