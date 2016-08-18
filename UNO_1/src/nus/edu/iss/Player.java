package nus.edu.iss;

import java.util.*;

public class Player {
    
    private int valuePoint;
    private String id;
    private String name;
    private List<Card> cardInHandList= new ArrayList<Card>();
    
    
    public Player(String id,String n)
    {
        this.id=id;
        this.name=n;
    }
    
    public void addcard(Card c)
    {
        setCardInHandList(c);
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCardInHandList() {
        return cardInHandList;
    }

    public Card removeSelectedCard(int i) {
        return cardInHandList.remove(i);
    } 
    
    public void setCardInHandList(Card c) {
        this.cardInHandList.add(c);
    }
    
        @Override
    public String toString() {
        return "\n Player : " + "Player Name=" + name + "\n Card In Hand : \n" + cardInHandList.toString();
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    public int claculateHandValue() {
     
        int totalValue = 0;
        
         for(Card card : cardInHandList) {
             totalValue += card.getValue();
         }

        return totalValue;
    }

    /**
     * @param cardInHandList the cardInHandList to set
     */
    public void setCardInHandList(List<Card> cardInHandList) {
        this.cardInHandList = cardInHandList;
    }
        
}
