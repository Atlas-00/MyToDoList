package org.atlas.Service;

import java.util.ArrayList;


public class OrderCommand {
	private final ArrayList<NameOrder> listOrder;
	private final int id;
	private String descriptions;

	public OrderCommand(int id) {
		this.id = id;
		this.listOrder = new ArrayList<>();
	}

	public void writeDescriptions() {
		switch (this.id) {
			case 1 -> {
				listOrder.add(NameOrder.ADD_TASK);
				this.descriptions = "Ajout d'une tâche !";
			}

			case 2 -> {
				listOrder.add(NameOrder.SEE_ALL_TASK);
				this.descriptions = "Voir toute les taches !";
			}

			case 3 -> {
				listOrder.add(NameOrder.MARK_A_TASK_FINISHED);
				this.descriptions = "Une tache marqué comme terminée !";
			}
		}
	}

	@Override
	public String toString() {
		return "{\n \t\"id\": \"" + id + '"'
				+ ",\n\t\"descriptions\": \"" + descriptions + "\" \n  },  ";
	}
}
