package nus.edu.iss;

import nus.edu.iss.Card;
import java.util.*;


public class Deck {

    private int count=0;
    private List<Card> thedeck = new ArrayList<Card>();
    private String[] color = {"Red", "Yellow", "Green", "Blue"};
    private String[] type = {"Number", "Skip", "Reverse", "Take2", "Take4", "ChangeColor"};

    public List<Card> NewDeck() {

        for (int i = 1; i < 3; i++) {      
            for (int j = 0; j < 4; j++) {
                for (int k = 1; k < 10; k++) {                    
                    Card c = new Card();
                    c.setColor(color[j]);
                    c.setValue(k);
                    c.setType(type[0]);
                    c.setImage(color[j]+type[0]+k);
                    c.setCardId(count+"");
                    setThedeck(c);
                    count++;
                }
                for (int l = 0; l <= 2; l++) {
                    Card c = new Card();
                    c.setColor(color[j]);
                    c.setValue(20);
                    c.setType(type[l + 1]);
                    c.setImage(color[j]+type[l+1]+20);
                    c.setCardId(count+"");
                    setThedeck(c);
                    count++;
                }

            }
        }
        for (int i = 0; i < 4; i++) {
            Card c = new Card();
            c.setColor("RGBY");
            c.setType(type[5]);
            c.setValue(20);
            c.setImage("RGBY"+type[5]+20);
            c.setCardId(count+"");
            setThedeck(c);
            count++;
        }
        
          for (int i = 0; i < 4; i++) {
            Card c = new Card();
            c.setColor("RGBY");
            c.setType(type[4]);
            c.setValue(20);
            c.setImage("RGBY"+type[4]+20);
            c.setCardId(count+"");
            setThedeck(c);
            count++;
        }
          
            for (int i = 0; i < 4; i++) {
            Card c = new Card();
            c.setColor(color[i]);
            c.setType(type[5]);
            c.setValue(0);
            c.setImage(color[i]+type[0]+0);
            c.setCardId(count+"");
            setThedeck(c);
            count++;
        }
         return thedeck;
    }

    public Card DrawCard() {
        
        return getThedeck().get(0);
        
    }

    
    public List<Card> getThedeck() {
        return thedeck;
    }

    public void setThedeck(Card c) {
        this.thedeck.add(c);
    }

    public Card removeThedeck(int i){
         return thedeck.remove(i);
    }
//    @Override
//    public String toString() {
//        return "Deck :" + thedeck + "\n";
//    }

}
