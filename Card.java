package test;

public interface Card extends Comparable<Card>{
    enum Rank {
        DEUCE("deuce",2),
        THREE("three",3),FOUR("four",4),FIVE("five",5),SIX("six",6),
        SEVEN("seven",7),EIGHT("eight",8),NINE("nine",9),TEN("ten",10),
        JACK("jack",11),QUEEN("queen",12),KING("king",13),ACE("ace",14);
       private String text;
       private int value;
       Rank(String text,int value)
       {
           this.text = text;
           this.value = value;
       }
       public String text(){ return text;}
       public int value() { return value; }
    }
    enum Suit {
        DIAMONDS("diamonds",1),CLUBS("clubs",2),
        HEARTS("hearts",3),SPADES("spades",4);
        private String text;
        private int value;

        Suit(String text,int value)
        {
            this.text = text;
            this.value = value;
        }
        public String getText(){ return text;}
        public int getValue(){ return value; }
    }
    Card.Rank getRank();
    Card.Suit getSuit();
}
