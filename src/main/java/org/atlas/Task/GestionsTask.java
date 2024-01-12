package org.atlas.Task;

import org.atlas.Service.OrderCommand;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionsTask {
	private final ArrayList<Tasks> tasks;
	private final ArrayList<OrderCommand> commandHistory;

	public GestionsTask() {
		this.tasks = new ArrayList<>();
		this.commandHistory = new ArrayList<>();
	}

	public void addTask() {
		try {
			Scanner scanner = new Scanner(System.in);

			System.out.print("\nEntrez le nom de la tâche : ");
			String nameTask = scanner.nextLine();

			System.out.print("Entrez la description de la tâche : ");
			String descTask = scanner.nextLine();

			System.out.print("Entrez la date de début de la tâche (format YYYY-MM-DD) : ");
			String startDateInput = scanner.nextLine();
			LocalDate startDate = LocalDate.parse(startDateInput);

			System.out.print("Entrez la date de fin de la tâche (format YYYY-MM-DD) : ");
			String endDateInput = scanner.nextLine();
			LocalDate endDate = LocalDate.parse(endDateInput);

			Tasks task = new Tasks(nameTask, descTask, startDate, endDate, true);

			tasks.add(task);
			OrderCommand orderCommand = new OrderCommand(1);
			orderCommand.writeDescriptions();
			commandHistory.add(orderCommand);

			System.out.println("La tâche '" + nameTask + "' a été créée !");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void seeAllTask() {
		try {
			if (tasks.isEmpty()) {
				System.out.println("La liste des tâches est vide !");
				return;
			}

			for (Tasks task : tasks) {
				System.out.println(task.toString());
			}

			OrderCommand orderCommand = new OrderCommand(2);
			orderCommand.writeDescriptions();
			commandHistory.add(orderCommand);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void markATaskFinished() {
		try {
			Scanner scanner = new Scanner(System.in);

			if (tasks.isEmpty()) {
				System.out.println("La liste des tâches est vide !");
				return;
			}

			System.out.print("\nEntrez le nom de la tâche terminée : ");
			String nameOfTaskFinished = scanner.nextLine();
			boolean searchTask = false;

			for (Tasks task : tasks) {
				if (task.getName().equalsIgnoreCase(nameOfTaskFinished)) {
					task.isState(false);
					searchTask = true;
					System.out.println("La tâche '" + task.getName() + "' a été marquée comme terminée !");
				}
			}

			if (! searchTask) {
				System.out.println("La tâche que vous recherchez n'existe pas !");
			}

			OrderCommand orderCommand = new OrderCommand(3);
			orderCommand.writeDescriptions();
			commandHistory.add(orderCommand);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void writeTask() throws IOException {
		int count = 1;
		try (BufferedWriter bufferedWriter =
				     new BufferedWriter(new FileWriter("src/main/java/org/atlas/Data/Tasks.json"))) {
			bufferedWriter.write("{\n");

			for (Tasks task : tasks) {
				write(bufferedWriter, task, count);
				count++;
			}

			bufferedWriter.write("}\n");
			System.out.println("Les tâches ont été écrites dans le fichier Tasks.json.");
		} catch (IOException e) {
			throw new IOException("Erreur lors de l'écriture des tâches dans le fichier Tasks.json.", e);
		}

		writeHistory();
	}

	private void write(BufferedWriter bufferedWriter, Tasks task, int countTask) throws IOException {
		bufferedWriter.write("  \"Task n° " + countTask + "\": " + task.toString());
		bufferedWriter.newLine();
	}

	private void writeHistory() throws IOException {
		int countCommande = 1;
		try (BufferedWriter bufferedWriter =
				     new BufferedWriter(new FileWriter("src/main/java/org/atlas/Data/OrderHistory.json"))) {
			bufferedWriter.write("{\n");

			for (OrderCommand orderCommand : commandHistory) {
				bufferedWriter.write("  \" Commande n° " + countCommande + "\": " + orderCommand.toString());
				bufferedWriter.newLine();
				countCommande++;
			}

			bufferedWriter.write("}\n");
			System.out.println("l'Historique des commande à été écrite dans OrderHistory.json");
		} catch (IOException e) {
			throw new IOException("Erreur lors de l'écriture de l'historique des commandes dans le fichier OrderHistory" +
					".json.", e);
		}
	}
}
