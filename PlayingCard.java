package test;

public class PlayingCard implements Card
{
    private Card.Rank rank;
    private Card.Suit suit;

    public PlayingCard(Card.Rank rank)
    {
        this.rank = rank;
    }

    public PlayingCard(Card.Rank rank,Card.Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
    }
    @Override
    public Rank getRank() {
        return rank;
    }

    @Override
    public Suit getSuit() {
        return suit;
    }
    @Override
    public boolean equals(Object o)
    {
        if(o instanceof Card)
        {
            Card card = (Card)o;
            if(card.getRank() == rank&&card.getSuit()==suit)
            {
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    @Override
    public int hashCode()
    {
        return ((suit.getValue()-1)*13)+rank.value();
    }
    @Override
    public int compareTo(Card card) {
        return this.hashCode()-card.hashCode();
    }
    @Override
    public String toString()
    {
        if(suit==null)
            return rank+" ";
        else
        return rank.text() +" of "+suit.getText();
    }
}
