package deneme;
import  test.*;
import java.util.*;


public class Test7 {
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
       do{
           LinkedList<Card> myCards = new LinkedList<>();
           LinkedList<Card> opponentCards = new LinkedList<>();
          for(int i=1;i<=4;i++)
              myCards.add(cards.remove());

          for(int i=1;i<=4;i++)
              opponentCards.add(cards.remove());
         if(!middle.isEmpty())
         Card card = middle.getFirst();
         System.err.println("card: "+card);
         System.out.println("middle: "+middle);
         int count =0;
         while(count< myCards.size()) {
             System.out.println("opponent cards: "+opponentCards);
             //System.out.print("enter 1,2,3,4 from list: " + myCards+" ");
             print(myCards);
             int selection = scanner.nextInt();
             Card selectedCard = myCards.remove(selection - 1);
             System.out.println("card: "+card);
             if(middle.isEmpty()&&selectedCard.getRank().value() == card.getRank().value())
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
             if(middle.isEmpty()&&card.getRank()==selectedCard.getRank())
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

             System.out.println("card: "+card);
             System.out.println("middle: "+middle);
             System.out.println("taken by me: "+takenByMe);
             System.out.println("taken by opponent: "+takenByOpponent);
         }
          myCards.clear();
          opponentCards.clear();

       }while(!cards.isEmpty());
       scanner.close();

    }
    private static Card search(LinkedList<Card> middle,List<Card> takenByMe,
                               List<Card> takenByOpponent,List<Card> opponentCards)
    {
        Card card = middle.peek();
        Random random = new Random();
        if(card==null)
        return opponentCards.get(random.nextInt(opponentCards.size()));
        boolean result =opponentCards.stream().anyMatch(op-> op.getRank()==card.getRank());
        if(result){
          Card x123 =opponentCards.stream().filter(op-> op.getRank()==card.getRank()).findAny().get();
          return x123;
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
    private static void print(List<Card> myCards)
    {
        for(int i=1;i<=myCards.size();i++)
        {
            if(i==1)
            {
                if(myCards.size()==1)
                    System.out.print(i+": ");
                else
                System.out.print("enter "+i+",");
            }else if(i==myCards.size())
            {
                System.out.print(i+" from list: ");
            }else{
                System.out.print(i+",");
            }

        }
        System.out.print(myCards+" ");
    }
}
