import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException, Throwable {
		Scanner sc = new Scanner(System.in);
		boolean isExitMenu3 = true;
		while (isExitMenu3) {
			System.out.println("\t\t++++++++++++++++++++++++++++++++++");
			System.out.println("\t\t+ WELCOME TO THE SYSTEM          +");
			System.out.println("\t\t++++++++++++++++++++++++++++++++++\n");
			System.out.println("Please Choose Number From Menu:       \n");
			System.out.println(" =====================================");
			System.out.println("|  [1]Articals SUB MENU               |");
			System.out.println("|  [2]Authors SUB MENU                |");
			System.out.println("|  [3]Sections SUB MENU               |");
			System.out.println("|  [4]EXIT                            |");
			System.out.println(" =====================================");
			Integer num = sc.nextInt();

			switch (num) {
			case 1:
				boolean isExitMenu = true;
				while (isExitMenu) {

					System.out.println("Please Choose Number From Menu:               \n");
					System.out.println(" ==============================================");
					System.out.println("|  [1]CREATE Articals TABLE                    |");
					System.out.println("|  [2]Insert into Articals from API            |");
					System.out.println("|  [3]EXIT                                     |");
					System.out.println(" ==============================================");
					int op = sc.nextInt();

					switch (op) {
					case 1:
						CreateTables.creatingArticalsTable();

						break;

					case 2:
						InsertToTables.insertToArticals();
						break;
					case 3:
						isExitMenu = false;

						break;
					}

				}

				isExitMenu3 = true;

				break;

			case 2:
				boolean isExitMenu4 = true;
				while (isExitMenu4) {

					System.out.println("Please Choose Number From Menu:               \n");
					System.out.println(" ==============================================");
					System.out.println("|  [1]CREATE Authors TABLE                     |");
					System.out.println("|  [2]Insert into Authors from API             |");
					System.out.println("|  [3]EXIT                                     |");
					System.out.println(" ==============================================");
					int op = sc.nextInt();

					switch (op) {
					case 1:
						CreateTables.creatingAuthorsTable();

						break;

					case 2:
						InsertToTables.insertToAuthors();
						break;
					case 3:
						isExitMenu = false;

						break;
					}

				}

				isExitMenu3 = true;

				break;

			case 3:
				boolean isExitMenu5 = true;
				while (isExitMenu5) {

					System.out.println("Please Choose Number From Menu:               \n");
					System.out.println(" ==============================================");
					System.out.println("|  [1]CREATE Sections TABLE                    |");
					System.out.println("|  [2]Insert into Sections from API            |");
					System.out.println("|  [3]EXIT                                     |");
					System.out.println(" ==============================================");
					int op = sc.nextInt();

					switch (op) {
					case 1:
						CreateTables.creatingSectionsTable();

						break;

					case 2:
						InsertToTables.insertToSections();
						break;
					case 3:
						isExitMenu = false;

						break;
					}

				}

				isExitMenu3 = true;

				break;

			case 4:
				System.out.println("THANK YOU");
				isExitMenu = false;
				break;

			}
		}
	}
}
