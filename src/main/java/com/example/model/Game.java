package com.example.model;

import com.example.enums.Rank;
import com.example.enums.StackName;
import com.example.enums.Suit;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    private final ArrayList<Stack> stacks = new ArrayList<>();
    private Movements movements;

    public Game() {
        initialize();
    }

    private void initialize() {
        movements = new Movements();
        for (int i = 0; i < 7; i++) stacks.add(i, new InitialStack());
        for (int i = 7; i < 11 ; i++) stacks.add(i, new FinalStack());
        stacks.add(11, new Deck());
    }

    public void initialDeal() {
        for (int cardIndex = 0; cardIndex < 7; cardIndex++) {
            for (int stackIndex = cardIndex; stackIndex < 7; stackIndex++) {
                Card card = stacks.get(11).getCard(stacks.get(11).getSize()-1);
                stacks.get(11).removeCard(card);
                if (stackIndex == cardIndex) card.setCovered(false);
                stacks.get(stackIndex).addCard(cardIndex, card);
                movements.addMovement(card, StackName.values()[stackIndex], cardIndex);
            }
        }
        System.out.println("Hello!!!");
    }

    public boolean moveCard(Card card, StackName stackTarget, int index) {
        return move(card, stacks.get(stackTarget.ordinal()), index);
    }

    private boolean move(Card card, Stack stackTarget, int index) {
        if (card == null) return false;
        if (card.isCovered()) {
            movements.addMovement(card, getStackName(getStack(card)), getStackName(getStack(card)).equals(StackName.DECK) ? -1 : index);
            return false;
        }
        Stack stackSource = getStack(card);

        if (stackSource.equals(stackTarget)) {
            movements.addMovement(card, getStackName(stackSource), getStackName(getStack(card)).equals(StackName.DECK) ? -1 : index);
            return false;
        }
        boolean result = stackTarget.setCard(card);
        if (result) {
            stackSource.removeCard(card);
            movements.addMovement(card, getStackName(stackTarget), -1);
            Card newCard = stackSource.getCard(index);
            //move(newCard,stackTarget,index);

            return true;
        } else {
            movements.addMovement(card, getStackName(stackSource), getStackName(getStack(card)).equals(StackName.DECK) ? -1 : index);
        }
        return false;
    }

    private StackName getStackName(Stack stack) {
        return StackName.values()[stacks.indexOf(stack)];
    }

    private Stack getStack(Card card) {
        for (Stack stack : stacks) {
            for (Card item : stack.getArrayList()) {
                if (item.equals(card)) return stack;
            }
        }
        return null;
    }

    public boolean isSolved() {
        for (int stackIndex = 0; stackIndex < 7; stackIndex++) {
            for (Card card : stacks.get(stackIndex).getArrayList()) {
                if (card.isCovered()) return false;
            }
        }
        //return stacks.get(11).getSize() <= 0;
        return true;
    }

    public boolean completeGame() {
        if (!isSolved()) return false;
        Suit[] suits = checkSuits();
        for (Rank rank : Rank.values()) {
            fillRank(rank,suits);
        }
        return true;
    }

    public Suit[] checkSuits() {
        int index = 0;
        ArrayList<Suit> allSuits = new ArrayList<> (Arrays.asList(Suit.values()));
        Suit[] suits = new Suit[4];
        for (int stackIndex = 7; stackIndex < 11; stackIndex++) {
            int cardIndex = stacks.get(stackIndex).getSize() - 1;
            Card card = stacks.get(stackIndex).getCard(cardIndex);
            if (card != null) {
                suits[index] = card.getSuit();
                allSuits.remove(card.getSuit());
            } else {
                suits[index] = null;
            }
            index++;
        }
        for (Suit suit: suits) {
            if (suit == null) suit = allSuits.remove(0);
        }
        return suits;
    }

    public Card findCard (Rank rank, Suit suit){
        for (int stackIndex = 0; stackIndex < 7; stackIndex++) {
            Card card = stacks.get(stackIndex).getCard(stacks.get(stackIndex).getSize() - 1);
            if (card != null) {
                if (card.getRank().compareTo(rank) == 0 && card.getSuit().compareTo(suit) == 0) return card;
            }
        }
        for (Card card : stacks.get(11).getArrayList()) {
            if (card.getRank().compareTo(rank) == 0 && card.getSuit().compareTo(suit) == 0) {
                card.setCovered(false);
                return card;
            }
        }
        return null;
    }

    public void fillRank(Rank rank, Suit[] suits) {
        for (int stackIndex = 7; stackIndex < 11; stackIndex++) {
            Card card = findCard(rank, suits[stackIndex - 7]);
            move(card, stacks.get(stackIndex), -1);
        }
    }

    public Movement getMovement() {
        return movements.getMovement();
    }

    public void next() {
        Deck deck = (Deck) stacks.get(11);
        deck.next();
        deck.getCard(deck.getSize()-1).setCovered(false);
        movements.addMovement(deck.getCard(deck.getSize()-1), StackName.DECK, -1);
    }
}