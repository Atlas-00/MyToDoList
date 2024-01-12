package org.atlas.Task;

import java.time.LocalDate;

public class Tasks {
	private final String name;
	private final String description;
	private final LocalDate startDate;
	private final LocalDate endDate;
	private boolean state;

	public Tasks(String name, String description, LocalDate startDate, LocalDate endDate, boolean state) {
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.state = state;
	}

	public String getName() {
		return name;
	}
	
	public void isState(boolean newValue) {
		this.state = newValue;
	}

	@Override
	public String toString() {
		return "{" + "\n\t\"name\": \"" + name + '"' +
				", \n\t\"description\": \"" + description + '"' +
				", \n\t\"startDate\": \"" + startDate + '"' +
				", \n\t\"endDate\": \"" + endDate + '"' +
				", \n\t\"state\": \"" + (state ? "In Progress" : "Finished") + "\"" + "\n  }, \n";

	}

}
