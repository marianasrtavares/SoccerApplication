package com.rumos.soccer;

public class Team implements Comparable {
	private String teamName;
	private Player[] playerArray;
	private int pointsTotal;
	private int goalsTotal;

	public Team(String teamName) {
		this.teamName = teamName;
	}

	public Team(String teamName, Player[] playerArray) {
		this(teamName);
		this.playerArray = playerArray;
	}

	public Team() {

	}

	public Player[] getPlayerArray() {
		return playerArray;
	}

	public void setPlayerArray(Player[] playerArray) {
		this.playerArray = playerArray;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getPointsTotal() {
		return pointsTotal;
	}

	public void setPointsTotal(int pointsTotal) {
		this.pointsTotal = pointsTotal;
	}

	public void incPointsTotal(int pointsTotal) {
		this.pointsTotal += pointsTotal;
	}

	public int getGoalsTotal() {
		return goalsTotal;
	}

	public void setGoalsTotal(int goalsTotal) {
		this.goalsTotal = goalsTotal;
	}

	public void incGoals(int goals) {
		this.goalsTotal += goals;
	}

	@Override
	public int compareTo(Object theTeam) {
		int returnValue = -1;
		if(this.pointsTotal<((Team)theTeam).getPointsTotal()) {
			returnValue= 1;
		}else if(this.pointsTotal==((Team)theTeam).pointsTotal) {
			if(this.goalsTotal<((Team)theTeam).goalsTotal) {
				returnValue=1;
			}
		}
		return returnValue;
	}

}
