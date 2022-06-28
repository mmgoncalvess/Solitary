package com.example.model;

import com.example.enums.Rank;

import java.util.ArrayList;

public class InitialStack extends Stack {

    @Override
    public boolean setCard(Card card) {
        if (card == null) return false;
        if (arrayList.size() == 0) {
            if (card.getRank() == Rank.KING) {
                arrayList.add(card);
                return true;
            }
        } else {
            if (getCard(getSize()-1).isRed() != card.isRed()) {
                if (getCard(getSize()-1).getRank().compareTo(card.getRank()) == 1) {
                    arrayList.add(card);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void removeCard(Card card) {
        arrayList.remove(card);
        if (arrayList.size() > 0) arrayList.get(arrayList.size()-1).setCovered(false);
    }
}
