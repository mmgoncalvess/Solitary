package com.example.model;

import java.util.ArrayList;

public abstract class Stack {
    protected final ArrayList<Card> arrayList = new ArrayList<>();

    public Card getCard(int index) {
        if (index >= 0 && index < arrayList.size()) {
            return arrayList.get(index);
        }
        return null;
    }

    public void removeCard(Card card) {
            arrayList.remove(card);
    }

    public int getSize() {
        return arrayList.size();
    }

    public boolean setCard(Card card) {
        return false;
    }

    public void addCard(int index, Card card) {
        arrayList.add(index, card);
    }

    public int getIndex(Card card) {
        return arrayList.indexOf(card);         // returns -1 if card does not exist in stack
    }

    public ArrayList<Card> getArrayList() {
        return arrayList;
    }
}
