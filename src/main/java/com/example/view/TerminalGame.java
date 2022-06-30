package com.example.view;

import com.example.controller.Controller;
import com.example.enums.StackName;
import com.example.model.Card;
import com.example.model.Movement;
import java.util.ArrayList;
import java.util.Scanner;

public class TerminalGame {
    Controller controller = new Controller();
    ArrayList<ArrayList<Card>> lists;
    StackName[] stacks = StackName.values();
    Card deck;

    public static void main(String[] args) {
        TerminalGame terminalGameTwo = new TerminalGame();
        terminalGameTwo.start();
    }

    public void start() {
        init();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter line:");
            String string = scanner.nextLine().toUpperCase();
            System.out.println("Line: " + string);
            play(string);
            readMovements();
            printGame();
            if (!checkEnd()) {
                System.out.println("New game (y/n): ");
                string = scanner.nextLine().toUpperCase();
                if (string.equals("Y")) {
                    init();
                } else {
                    return;
                }
            }
        }

    }

    private void init() {
        lists = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            lists.add(new ArrayList<>());
        }
        controller.newGame();
        controller.initialDeal();
        controller.next();
        readMovements();
        printGame();
    }

    private void readMovements() {
        Movement movement;
        do {
            movement = controller.getMovement();
            System.out.println(movement);
            if (movement != null) {
                Card card = movement.getCard();
                StackName newStackName = movement.getStackName();
                StackName oldStackName = findStackName(card);
                if (!newStackName.equals(oldStackName)) {        //   && !newStackName.equals(StackName.DECK))
                    removeCard(card);
                    lists.get(newStackName.ordinal()).add(card);
                }
                if (newStackName.equals(StackName.DECK)) {
                    deck = card;
                }
            }
        } while (movement != null);
    }


    private void removeCard(Card card) {
        for (ArrayList<Card> arraylist : lists) {
            for (Card item : arraylist) {
                if (item.equals(card)) {
                    arraylist.remove(item);
                    return;
                }
            }
        }
    }

    private StackName findStackName(Card card) {
        for (ArrayList<Card> arraylist : lists) {
            for (Card item : arraylist) {
                if (item.equals(card)) {
                    int index = lists.indexOf(arraylist);
                    return stacks[index];
                }
            }
        }
        return null;
    }

    private boolean checkEnd() {
        int sum = 0;
        for (int i = 7; i < 11; i++) {
            sum = sum + lists.get(i).size();
        }
        return sum != 52;
    }

    private void printGame() {
        System.out.println("\n\n");
        String formatString = "   %7s   %7s   %7s   %7s   %7s   %7s   %7s   %7s   %7s   %7s   %7s\n";
        System.out.printf(formatString, "Z1", "Z2", "Z3", "Z4", "A1", "A2", "A3", "A4", "A5", "A6", "A7");
        printLine();
        for (int i = 0; i < 13; i++) {
            ArrayList<String> strings = new ArrayList<>();
            boolean bool = false;

            for (int j = 7; j < 11; j++) {
                if (lists.get(j).size() > i) {
                    strings.add(lists.get(j).get(i).isCovered() ? "XX" : lists.get(j).get(i).toString());
                    bool = true;
                } else {
                    strings.add("");
                }
            }

            for (int j = 0; j < 7; j++) {
                if (lists.get(j).size() > i) {
                    strings.add(lists.get(j).get(i).isCovered() ? "XX" : lists.get(j).get(i).toString());
                    bool = true;
                } else {
                    strings.add("");
                }
            }
            if (!bool) break;
            for (String item : strings) {
                System.out.printf("%10s", item);
            }
            System.out.println();
        }
        printLine();
        System.out.println("Deck: " + deck);
    }

    private void printLine() {
        for (int i = 0; i < 110; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    private void play(String string) {
        if (string.length() < 4) {
            controller.next();
            printGame();
            return;
        }

        if (string.equals("CHECK")) {
            System.out.println("Resolved: " + controller.isSolved());
            return;
        }

        if (string.equals("COMPLETE")) {
            System.out.println("Terminate: " + controller.completeGame());
            printGame();
            return;
        }

        String firstStack = string.substring(0, 2);
        String secondStack = string.substring(2, 4);
        int index;
        try {
            index = Integer.parseInt(string.substring(4));
        } catch (NumberFormatException e) {
            System.out.println("Number Format Exception");
            //e.printStackTrace();
            index = -1;
        }

        StackName
                stackOne = getStackName(firstStack);
        StackName stackTwo = getStackName(secondStack);
        Card card = getCard(stackOne, index);

        boolean result = controller.moveCard(card, stackTwo, index);
        System.out.println(result ? "The cards were moved!!!" : "Move not accepted!!");
    }

    private Card getCard(StackName stackName, int index) {
        ArrayList<Card> arrayList = lists.get(stackName.ordinal());
        if (index == -1) {
            return arrayList.get(arrayList.size() - 1);
        } else {
            return arrayList.get(index);
        }
    }

    private StackName getStackName(String string) {
        switch (string) {
            case "A1":
                return StackName.INITIAL_ONE;
            case "A2":
                return StackName.INITIAL_TWO;
            case "A3":
                return StackName.INITIAL_THREE;
            case "A4":
                return StackName.INITIAL_FOUR;
            case "A5":
                return StackName.INITIAL_FIVE;
            case "A6":
                return StackName.INITIAL_SIX;
            case "A7":
                return StackName.INITIAL_SEVEN;
            case "Z1":
                return StackName.FINAL_ONE;
            case "Z2":
                return StackName.FINAL_TWO;
            case "Z3":
                return StackName.FINAL_THREE;
            case "Z4":
                return StackName.FINAL_FOUR;
            case "QQ":
                return StackName.DECK;
        }
        return null;
    }
}