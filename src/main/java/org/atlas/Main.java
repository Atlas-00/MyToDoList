package org.atlas;

import org.atlas.Task.GestionsTask;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void startTask() throws IOException {

		GestionsTask gestionsTask = new GestionsTask();

		try (Scanner scanner = new Scanner(System.in)) {
			boolean quick = false;
			System.out.println("\t\tBienvenue sur MyToDoList !");
			System.out.println("========================================");

			while (! quick) {
				System.out.println("\n1. Créer une tache :");
				System.out.println("2. Affichez la liste des taches :");
				System.out.println("3. Marquer une tache comme terminée :");
				System.out.println("4. Quitter ");

				System.out.print("\nEntrez votre choix : ");
				int choice = scanner.nextInt();

				switch (choice) {
					case 1 -> gestionsTask.addTask();
					case 2 -> gestionsTask.seeAllTask();
					case 3 -> gestionsTask.markATaskFinished();
					case 4 -> quick = true;
					default -> System.out.println("Vous devez entrer un nombre entre 1 et 4 !");
				}
			}
		} catch (InputMismatchException e) {
			System.err.println("Une erreur s'est produite !, Vous devez un nombre !");
		}

		// Écriture des tâches dans le fichier après la sortie de la boucle
		gestionsTask.writeTask();
	}

	public static void main(String[] args) throws IOException {
		startTask();
	}
}
