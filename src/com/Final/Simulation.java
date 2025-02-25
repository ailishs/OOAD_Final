package com.Final;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulation {
    Board board;
    Receiver receiver;
    receiveUserInteraction input;
    ArrayList<Player> players;
    ArrayList<Player> retiredPlayers;
    Player activePlayer;
    private int playerIndex;
    int round;

    Simulation(Scanner scan){
        input = new receiveUserInteraction(scan, this);
        receiver = new Receiver(this, scan, input);
        board = new Board(this);
        playerIndex = -1;
        players = new ArrayList<>();
        retiredPlayers = new ArrayList<>();
        activePlayer = null;
        round = 1;

        initializeTestPlayers(players);
    }

    void initializeTestPlayers(ArrayList<Player> list){
        list.add(new Player("Player 1", board.boardHead));
        list.add(new Player("Player 2", board.boardHead));

        list.get(0).money = 1000;
        list.get(0).hasDegree = true;
    }

    void startSim(){
        while(!players.isEmpty()){
            newPlayerTurn();
        }
    }

    void startTestSim(){
        for(int i = 0; i < (2*board.boardSpaceCards.size()); i++){
            newPlayerTurn();
        }
    }

    void newPlayerTurn(){
        newActivePlayer();
        System.out.println("");
        System.out.println("It is now " + activePlayer.name + " turn");
        activePlayer.currentSpace.card.executeCommand();
        activePlayer.currentSpace=activePlayer.currentSpace.forward;
    }

    void newActivePlayer(){
        playerIndex++;
        if(playerIndex >= players.size()){
            round++;
            playerIndex = 0;
        }
        activePlayer = players.get(playerIndex);
    }

    void retirePlayer(){
        players.remove(activePlayer);
        retiredPlayers.add(activePlayer);
    }
}
