package nus.edu.iss;

import java.util.*;


public class Game {
    
    private int playerScorePoint=0;
    private String gameid;
    private String gameName;
    private String status;
    private List<Player> listofplayers = new ArrayList<Player>();
    
    private Deck theDeck=new Deck();
    
    
    private Card discardCard;
     
    
    
    public Game(){
        
    }
            
    public Game(String gameid,String gameName,String status)
    {
        this.gameid =gameid;
        this.gameName=gameName;
        this.status=status;
    }
    
    public void CreateGame()
    {
        getTheDeck().NewDeck();
        Collections.shuffle(theDeck.getThedeck());        
    }
    
    public void AddPlayer(Player p)
    {
        setListofplayers(p);
    }
    
    public void DealCard()
    {
        for (Player player : getListofplayers()) {
                player.setCardInHandList(new ArrayList<Card>());
        }
        
        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < getListofplayers().size(); j++)
            {
                getListofplayers().get(j).addcard(getTheDeck().DrawCard());
                getTheDeck().getThedeck().remove(getTheDeck().DrawCard());
            }
        }
    }

    public Card DiscardCardToPile()
    {
        Card c = new Card();
        c=getTheDeck().getThedeck().get(0);
        getTheDeck().getThedeck().remove(0);
        return c;
    }
        
    private int CalculateInHandValue()
    {
        for(Player p : listofplayers){
            playerScorePoint+=p.claculateHandValue();
        }
        return playerScorePoint;
    }
    
    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Player> getListofplayers() {
        return listofplayers;
    }


    public void setListofplayers(Player p) {
        this.listofplayers.add(p);
    }
    
    @Override
    public String toString() 
    {
        return "Game :" + "Game ID =" + getGameid() + "\n " + "Discard Card :" + DiscardCardToPile()+ "\n" +
                "Cards On Deck : " + getTheDeck().getThedeck().size() + listofplayers.toString();
    }

    /**
     * @return the gameName
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * @param gameName the gameName to set
     */
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /**
     * @return the theDeck
     */
    public Deck getTheDeck() {
        return theDeck;
    }

    /**
     * @param theDeck the theDeck to set
     */
    public void setTheDeck(Deck theDeck) {
        this.theDeck = theDeck;
    }

    /**
     * @return the discardCard
     */
    public Card getDiscardCard() {
        return discardCard;
    }

    /**
     * @param discardCard the discardCard to set
     */
    public void setDiscardCard(Card discardCard) {
        this.discardCard = discardCard;
    }
    
}
