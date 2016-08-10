package mydomain.ghostactivity;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class SimpleDictionary implements GhostDictionary {
    private ArrayList<String> words;

    public SimpleDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>(60000);
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
                words.add(line.trim());
        }
    }

    @Override
    public boolean isWord(String word) {

        if(word.isEmpty())
            return false;
        return words.contains(word);
    }

    @Override
    public String getAnyWordStartingWith(String prefix) {
        for(int i=0;i<words.size();i++){
            String s=words.get(i);
            if(s.startsWith(prefix))return s;
        }
        return new String("");
    }
    public ArrayList getPossibleChar(String pre){
        ArrayList<Character> c=new ArrayList<>();
        for(int i=0;i<words.size();i++){
            String s=words.get(i);
            if(s.startsWith(pre)){
                char ch=s.charAt(pre.length());
                if(!c.contains(ch))
                    c.add(ch);
            }
        }
        return c;
    }
    @Override
    public String getGoodWordStartingWith(String prefix) {
        return null;
    }
}
