package deneme;
import test.*;
import java.util.*;
public class Test8 {
    public static void main(String[] args)
    {

        Scanner scanner = new Scanner(System.in);
        StandardDeck deck = new StandardDeck();
        deck.shuffle();
        List<Card> standardDeck = deck.getCards();
        LinkedList<Card> cards = (LinkedList<Card>) standardDeck;

        LinkedList<Card> middle = new LinkedList<>();

        for(int i=1;i<=4;i++)
        {
            if(i==4)
                middle.push(cards.remove());
            else
                middle.add(cards.remove());
        }

        List<Card>  takenByMe = new LinkedList<>();
        List<Card>  takenByOpponent = new LinkedList<>();
        Card card = null;
        ArrayList<Integer> myP = new ArrayList<>();
        ArrayList<Integer> opP = new ArrayList<>();
        do{
            LinkedList<Card> myCards = new LinkedList<>();
            LinkedList<Card> opponentCards = new LinkedList<>();
            for(int i=1;i<=4;i++)
                myCards.add(cards.remove());

            for(int i=1;i<=4;i++)
                opponentCards.add(cards.remove());
            if(!middle.isEmpty())
            card = middle.getFirst();

            System.err.println("card: "+card);
            System.out.println("middle: "+middle);
            int count =0;
            while(count< myCards.size()) {
                System.out.println("opponent cards: "+opponentCards);
                System.out.print("enter 1,2,3,4 from list: " + myCards+" ");
                int selection = scanner.nextInt();
                Card selectedCard = myCards.remove(selection - 1);
                if(middle.size()==1&&selectedCard.getRank()==card.getRank())
                {
                    System.out.println("pisti....");
                    middle.push(selectedCard);
                    takenByMe.addAll(0,middle);
                    middle.clear();
                    myP.add(1);
                }
                else if(selectedCard.getRank()==Card.Rank.JACK&&middle.isEmpty())
                {
                    middle.push(selectedCard);
                }
                else if(selectedCard.getRank()==Card.Rank.JACK)
                {
                    middle.push(selectedCard);
                    takenByMe.addAll(0,middle);
                    middle.clear();
                }
                else if(middle.isEmpty()&&selectedCard.getRank().value() == card.getRank().value())
                {
                    middle.push(selectedCard);
                }else if (selectedCard.getRank().value() == card.getRank().value()) {
                    middle.push(selectedCard);
                    takenByMe.addAll(0,middle);
                    middle.clear();

                }else {
                    middle.push(selectedCard);
                }
                System.out.println("opponent searching...");
                card = search(middle,takenByMe,takenByOpponent,opponentCards);
                opponentCards.remove(card);
                if(middle.size()==1&&selectedCard.getRank()==card.getRank())
                {
                    System.out.println("pisti....");
                    middle.push(card);
                    takenByOpponent.addAll(0,middle);
                    middle.clear();
                    opP.add(1);
                }
                else if(card.getRank()==Card.Rank.JACK && middle.isEmpty())
                {
                    middle.push(card);
                }
                else if(card.getRank()==Card.Rank.JACK)
                {
                    middle.push(card);
                    takenByOpponent.addAll(0,middle);
                    middle.clear();
                }
                else if(middle.isEmpty()&&card.getRank()==selectedCard.getRank())
                {
                    middle.push(card);
                }
                else if(card.getRank()==selectedCard.getRank())
                {
                    middle.push(card);
                    takenByOpponent.addAll(0,middle);
                    middle.clear();

                }else{
                    middle.push(card);
                }


                System.out.println("middle: "+middle);
                System.out.println("taken by me: "+takenByMe);
                System.out.println("taken by opponent: "+takenByOpponent);
            }
            myCards.clear();
            opponentCards.clear();

        }while(!cards.isEmpty());
        if(middle.size()%2==1)
        {
            takenByMe.addAll(0,middle);
            middle.clear();
        }else{
            takenByOpponent.addAll(0,middle);
            middle.clear();
        }

        System.out.println("i got this point: "+totalPoints(takenByMe,myP));
        System.out.println("opponent got this point: "+totalPoints(takenByOpponent,opP));
        scanner.close();


    }
    private static Card search(LinkedList<Card> middle,List<Card> takenByMe,
                               List<Card> takenByOpponent,List<Card> opponentCards)
    {
        Card card = middle.peek();
        Random random = new Random();
        if(card==null)
            return opponentCards.get(random.nextInt(opponentCards.size()));

        for(Card op:opponentCards)
        {
            if(op.getRank()==card.getRank())
            {
                return op;
            }
        }
        int[] array = new int[13];
        for(Card c:middle)
            ++array[c.getRank().value()-2];
        for(Card c:takenByMe)
            ++array[c.getRank().value()-2];
        for(Card c:takenByOpponent)
            ++array[c.getRank().value()-2];

        int count =0 ;
        Card.Rank[] ranks = Card.Rank.values();
        HashMap<Integer,Card> map = new HashMap<>();
        for(Card.Rank rank:ranks)
            map.put(count++,new PlayingCard(rank));
        int max = array[0];
        for(int i=1;i<array.length;i++)
        {
            if(max<array[i])
                max = array[i];
        }
        ArrayList<Card> cards = new ArrayList<>();
        for(int i=0;i<array.length;i++)
        {
            if(array[i]==max)
            {
                cards.add(map.get(i));
            }
        }
        Collections.shuffle(cards);
        for(int i=0;i<cards.size();i++)
        {
            Card x = cards.get(i);
            for(int j=0;j< opponentCards.size();j++)
            {
                Card y = opponentCards.get(j);
                if(x.getRank()==y.getRank())
                {
                    return y;
                }
            }
        }

        return opponentCards.get(random.nextInt(opponentCards.size()));
    }
    private static int totalPoints(List<Card> cards,ArrayList<Integer> list)
    {
        int total = 0;
        for(Card card:cards)
        {
            if(card.getRank()==Card.Rank.ACE)
                total++;
            if(card.getRank()==Card.Rank.JACK)
                total++;
            if(card.getRank()==Card.Rank.DEUCE && card.getSuit()==Card.Suit.CLUBS)
            {
                total = total +2;
            }
            if(card.getRank()==Card.Rank.TEN && card.getSuit()==Card.Suit.DIAMONDS)
            {
                total = total +3;
            }
        }
        total += list.size() *10;
        if(cards.size()>26)
            total = total +3;
        return total;
    }
}

