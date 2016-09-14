package mydomain.ghostactivity;

/**
 * Created by admin on 10-08-2016.
 */
import java.util.HashMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class TrieNode {
    private HashMap<String, TrieNode> children;
    private boolean isWord;

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }

    public void add(String s) {
        if(this.children.containsKey(s)){
            this.children.get(s).isWord=true;
            return;
        }
        Set key=this.children.keySet();
        {
            Iterator i = key.iterator();
            if(i.hasNext())
            for (String t = (String) i.next(); i.hasNext(); t = (String) i.next()) {
                if (s.startsWith(t)) {
                    TrieNode c = this.children.get(t);
                    c.add(s);
                    return;
                }
            }
        }
        for(String sc=s.substring(0,s.length()-1);sc.length()>0;sc=sc.substring(0,sc.length()-1)){
            Iterator i=key.iterator();
            if(i.hasNext())
                for(String t=(String) i.next();i.hasNext();t=(String)i.next()){
                    if(t.startsWith(sc)){
                        TrieNode c=this.children.get(t);
                        this.children.remove(t);
                        TrieNode n=new TrieNode();
                        n.isWord=false;
                        this.children.put(sc,n);
                        n.add(t);
                        n.add(s);
                        return;
                    }
                }
        }
        TrieNode t=new TrieNode();
        t.isWord=true;
        this.children.put(s,t);
    }

    public boolean isWord(String s) {
        if(this.children.get(s)!=null&&this.children.get(s).isWord)
            return true;
        Set key=this.children.keySet();
        Iterator i=key.iterator();
        if(i.hasNext())
            for(String t=(String) i.next();i.hasNext();t=(String)i.next()) {
                if (s.startsWith(t)) {
                    TrieNode c = this.children.get(t);
                    if (c.isWord(s))
                        return true;
                }
            }
        return false;
    }
    public String getAnyWordStartingWith(String s) {
        Set key=this.children.keySet();
        {
            Iterator i=key.iterator();
            if(i.hasNext()) {
                for (String t = (String) i.next(); i.hasNext(); t = (String) i.next()) {
                    if (s.equals(t)) {
                        TrieNode c = this.children.get(t);
                        String[] ar = new String[1];
                        String st[]= c.children.keySet().toArray(ar);
                        return (s.length() > 0 ? st[0] : null);
                    }
                }
            }
        }
        {
            Iterator i=key.iterator();
            if(i.hasNext()) {
                for(String t=(String) i.next();i.hasNext();t=(String)i.next()) {
                    if (s.startsWith(t)){
                        TrieNode c = this.children.get(t);
                        if (c.isWord(s)) {
                            return c.getAnyWordStartingWith(s);
                        }
                    }
                }
            }
        }
        return null;
    }

    public String getGoodWordStartingWith(String s) {
        return null;
    }
}
