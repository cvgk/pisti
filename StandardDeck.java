package test;

import java.util.*;
import java.util.stream.Collectors;

public class StandardDeck implements Deck
{
    private List<Card> entireDeck;

    public StandardDeck(List<Card> existingList)
    {
        entireDeck = existingList;
    }
    public StandardDeck()
    {
        entireDeck = new LinkedList<>();
        for(Card.Rank rank:Card.Rank.values())
        {
            for(Card.Suit suit:Card.Suit.values())
            {
                entireDeck.add(new PlayingCard(rank,suit));
            }
        }
    }
    @Override
    public List<Card> getCards() {
        return entireDeck;
    }

    @Override
    public Deck deckFactory() {
        return new StandardDeck(new ArrayList<>());
    }

    @Override
    public int size() {
        return entireDeck.size();
    }

    @Override
    public void addCard(Card card) {
      entireDeck.add(card);
    }

    @Override
    public void addCards(List<Card> cards) {
        entireDeck.addAll(cards);
    }

    @Override
    public void addDeck(Deck deck) {
      List<Card> listToAdd = deck.getCards();
      entireDeck.addAll(listToAdd);
    }

    @Override
    public void shuffle() {
      Collections.shuffle(entireDeck);
    }

    @Override
    public void sort() {
     Collections.sort(entireDeck);
    }

    @Override
    public void sort(Comparator<Card> c) {
      Collections.sort(entireDeck,c);
    }

    @Override
    public String deckToString() {
        return entireDeck.stream().map(Card::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public Map<Integer, Deck> deal(int players, int numberOfCards) throws IllegalArgumentException {
        int cardsDealt = players * numberOfCards;
        int sizeOfDeck = entireDeck.size();
        if(cardsDealt>sizeOfDeck)
        {
            throw new IllegalArgumentException();
        }

        Map<Integer,List<Card>> dealtDeck = entireDeck.stream().
                collect(Collectors.groupingBy(card->{
                   int cardIndex = entireDeck.indexOf(card);
                   if(cardIndex>=cardsDealt){
                       return players+1;
                   }else{
                       return (cardIndex % players)+1;
                   }
                }));
        Map<Integer,Deck> map = new HashMap<>();
        for(int i=1;i<=(players+1);i++)
        {
            Deck currentDeck = deckFactory();
            currentDeck.addCards(dealtDeck.get(i));
            map.put(i,currentDeck);
        }
        return map;
    }
}
