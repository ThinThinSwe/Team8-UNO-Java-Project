package nus.edu.iss;

public class Card {
    
    private String cardId;
    private String color;
    private String type;
    private Integer value;
    private String image;

    public Card()
    {
        //default constructor;
    }
    
    public Card(String Id,String color,String type,Integer value, String image)
     {
         this.cardId=Id;
         this.color = color;
         this.type = type;
         this.value = value;
         this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "\t Card :"+ " Card ID=" +cardId + ", Color=" + color + ", type=" + type + ", value=" + value + ", image=" + image + "\n";
    }

    /**
     * @return the cardId
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * @param cardId the cardId to set
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    
    
     

}
