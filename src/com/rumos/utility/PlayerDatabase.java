package com.rumos.utility;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.rumos.soccer.Player;

public class PlayerDatabase {
	private ArrayList <Player> players;
	 
	String authorList
     = "Agatha Christie,"
     + "Alan Patton,"
     + "Alexander Solzhenitsyn,"
     + "Arthur Conan Doyle,"
     + "Anthony Trollope,"
     + "Baroness Orczy,"
     + "Brendan Behan,"
     + "Brian Moore,"
     + "Boris Pasternik,"
     + "Charles Dickens,"
     + "Charlotte Bronte,"
     + "Dorothy Parker,"
     + "Emile Zola,"
     + "Frank O'Connor,"
     + "Geoffrey Chaucer,"
     + "George Eliot,"
     + "G. K. Chesterton,"
     + "Graham Green,"
     + "Henry James,"
     + "James Joyce,"
     + "J. M. Synge,"
     + "J. R. Tolkien,"
     + "Jane Austin,"
     + "Leo Tolstoy,"
     + "Liam O'Flaherty,"
     + "Marcel Proust,"
     + "Mark Twain,"
     + "Oscar Wilde,"
     + "O. Henry,"
     + "Samuel Beckett,"
     + "Sean O'Casey,"
     + "William Shakespeare,"
     + "William Makepeace Thackeray,"
     + "W. B. Yeats,"
     + "Wilkie Collins";
	
	public PlayerDatabase() {
		StringTokenizer authorTokens= new StringTokenizer(authorList, ", ");
	    players= new ArrayList<Player>();
	    while(authorTokens.hasMoreTokens()) {
	    	players.add(new Player(authorTokens.nextToken()));
	    }
	}
	
	public Player[] getTeam(int numberOfPlayers) throws PlayerDatabaseException{
		Player [] thePlayers= new Player[numberOfPlayers];
		for(int i=0; i<numberOfPlayers;i++) {
			int playerIndex=(int) (Math.random()*players.size());
			try {
			thePlayers[i]=players.get(playerIndex);
			players.remove(playerIndex);
			}catch(IndexOutOfBoundsException ie) {
				throw new PlayerDatabaseException("Not enough players in the database for teams requested");
			}
		}
		return thePlayers;
	}
	
	

}
