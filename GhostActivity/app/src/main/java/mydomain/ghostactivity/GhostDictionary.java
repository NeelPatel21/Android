package mydomain.ghostactivity;

import java.util.ArrayList;

/**
 * Created by admin on 10-08-2016.
 */
public interface GhostDictionary {
    public final static int MIN_WORD_LENGTH = 4;
    boolean isWord(String word);
    String getAnyWordStartingWith(String prefix);
    String getGoodWordStartingWith(String prefix);
    ArrayList getPossibleChar(String pre);
}
