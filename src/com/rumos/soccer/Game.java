package com.rumos.soccer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Game {
	private Team homeTeam;
	private Team awayTeam;
	private GameEvent[] gameEvents;
	private LocalDateTime dateTime;


	public Game(Team homeTeam, Team awayTeam, LocalDateTime dateTime) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.dateTime=dateTime;
	}
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public void playGame() {
		ArrayList<GameEvent> eventList= new ArrayList<>();
		
		GameEvent currEvent;
		
		for(int i=1;i<=90;i++) {
			if(Math.random()>0.90) {
				currEvent= Math.random()<0.8?new Goal(): new Possession();
				currEvent.setTheTeam(Math.random()>0.5?homeTeam:awayTeam);
				currEvent.setThePlayer(currEvent.getTheTeam().getPlayerArray()[(int)(Math.random()*currEvent.getTheTeam().getPlayerArray().length)]);
			    currEvent.setTheTime(i);
			    eventList.add(currEvent);
			}
		}
		
		this.gameEvents=new GameEvent[eventList.size()];
		eventList.toArray(gameEvents);
	}


	public String getDescription() {
		int homeTeamGoals = 0;
		int awayTeamGoals = 0;

		StringBuilder returnString = new StringBuilder();

		returnString.append(homeTeam.getTeamName() + " vs " + awayTeam.getTeamName()+"\n"+ "Date "+this.dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE)+"\n");

		for (GameEvent currEvent : getEvents()) {
			if(currEvent instanceof Goal) {
			if (currEvent.getTheTeam() == homeTeam) {
				homeTeamGoals++;
				homeTeam.incGoals(1);
				
			} else {
				awayTeamGoals++;
				awayTeam.incGoals(1);
			}
			currEvent.getThePlayer().incGoalScored();
			}
			returnString.append(currEvent+ " after "+String.format("%.2f", currEvent.getTheTime()) + " min by "
					+ currEvent.getThePlayer().getPlayerName() + " of " + currEvent.getTheTeam().getTeamName());
			returnString.append(System.lineSeparator());
		
			}

		if (homeTeamGoals == awayTeamGoals) {
			returnString.append("Its a draw!");
			homeTeam.incPointsTotal(1);
			awayTeam.incPointsTotal(1);
		} else if (homeTeamGoals > awayTeamGoals) {
			returnString.append(homeTeam.getTeamName()+ " win! ("+ homeTeamGoals+" - "+awayTeamGoals+")");
		    homeTeam.incPointsTotal(2);
		} else {
			returnString.append(awayTeam.getTeamName()+ " win! ("+ awayTeamGoals+" - "+homeTeamGoals+")");
		    awayTeam.incPointsTotal(2);

		}
		return returnString.toString();

	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public GameEvent[] getEvents() {
		return gameEvents;
	}

	public void setEvents(GameEvent[] gameEvents) {
		this.gameEvents = gameEvents;
	}

}
