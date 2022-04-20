package com.rumos.soccer;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

import com.rumos.utility.PlayerDatabase;
import com.rumos.utility.PlayerDatabaseException;

public class League {

	public static void main(String[] args) {
       
		League league1 = new League();
		try {
		Team[] teams = league1.createTeams("The Robins,The Crows,The Swallows,The Owls", 11);
		Game[] games = league1.createGames(teams);
		System.out.println(league1.getLeagueAnnouncement(games));

//		 currGame.playGame(3);

		for (Game game : games) {
			game.playGame();
			System.out.println(game.getDescription());
		}

		league1.showBestTeam(teams);
		
		league1.showBestPlayer(teams);


		}catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}


	public Team[] createTeams(String teamNames, int teamSize) throws PlayerDatabaseException {
		PlayerDatabase playerDB = new PlayerDatabase();

		StringTokenizer teamNameTokens = new StringTokenizer(teamNames, ",");

		Team[] theTeam = new Team[teamNameTokens.countTokens()];
		for (int i = 0; i < theTeam.length; i++) {
			theTeam[i] = new Team(teamNameTokens.nextToken(), playerDB.getTeam(teamSize));
		}

		return theTeam;
	}

	public Game[] createGames(Team[] theTeams) {
		int daysBetweenGames = 0;
		ArrayList<Game> theGames = new ArrayList<>();

		for (Team hometeam : theTeams) {
			for (Team awayTeam : theTeams) {
				if (hometeam != awayTeam)
					theGames.add(new Game(hometeam, awayTeam, LocalDateTime.now().plusDays(daysBetweenGames)));
				daysBetweenGames += 7;
			}
		}

		return (Game[]) theGames.toArray(new Game[1]);

	}

	public void showBestTeam(Team[] theTeams) {
		Arrays.sort(theTeams);
		Team bestTeam = theTeams[0];
		System.out.println("Team points:");
		for (Team currTeam : theTeams) {
			System.out.println(
					currTeam.getTeamName() + ": " + currTeam.getPointsTotal() + ": " + currTeam.getGoalsTotal());
		}
		System.out.println("Winner of the League is " + bestTeam.getTeamName());

	}
	
	public void showBestPlayer(Team [] theTeam) {
		ArrayList <Player> players= new ArrayList<>();
		for(Team team :theTeam) {
			players.addAll(Arrays.asList(team.getPlayerArray()));
		}
		
		Collections.sort(players, (p1, p2)-> Double.valueOf(p2.getGoalsScored()).compareTo(Double.valueOf(p1.getGoalsScored())));
		
		for(Player player: players) {
			System.out.println(player.getPlayerName()+ " : "+ player.getGoalsScored());
		}
		System.out.println("\n\nBest Players");
	}

	public String getLeagueAnnouncement(Game[] theGames) {
		LocalDateTime firstDate = theGames[0].getDateTime();
		LocalDateTime lastDate = theGames[theGames.length - 1].getDateTime();
		Period period = Period.between(firstDate.toLocalDate(), lastDate.toLocalDate());
		return "The League is scheduled to run for " + period.getMonths() + " month(s), and " + period.getDays()
				+ " days.";
	}

}
