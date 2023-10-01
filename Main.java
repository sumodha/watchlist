import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		// initializing a file, a scanner for the file, a scanner for user input, and an
		// array list

		File f = new File("watchlist.txt");
		Scanner scan = new Scanner(f);

		Scanner user = new Scanner(System.in);
		ArrayList<String[]> watchlist = new ArrayList<String[]>();

		// updating array list with past watch list
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			String[] split = line.split("_");
			watchlist.add(split);
		}

		welcome();
		options();
		int option = valid(user, 1, 8);

		while (option != 8) {
			// OPTION 1
			if (option == 1) {
				System.out.println("1 -> sort by title");
				System.out.println("2 -> sort by genre");
				System.out.println("3 -> sort by status");
				System.out.println("4 -> sort by rating");
				System.out.println("5 -> sort by tags");
				int sortChoice = valid(user, 1, 5);

				if (sortChoice == 1) {
					System.out.println("1 -> A to Z");
					System.out.println("2 -> Z to A");
					int sortChoice1 = valid(user, 1, 2);

					if (sortChoice1 == 1) {
						sort(watchlist, 0, true);

					} else if (sortChoice1 == 2) {
						sort(watchlist, 0, false);
					}

				} else if (sortChoice == 2) {
					System.out.println("1 -> A to Z");
					System.out.println("2 -> Z to A");
					int sortChoice2 = valid(user, 1, 2);

					if (sortChoice2 == 1) {
						sort(watchlist, 1, true);

					} else if (sortChoice2 == 2) {
						sort(watchlist, 1, false);
					}

				} else if (sortChoice == 3) {
					System.out.println("1 -> A to Z");
					System.out.println("2 -> Z to A");
					int sortChoice3 = valid(user, 1, 2);

					if (sortChoice3 == 1) {
						sort(watchlist, 2, true);

					} else if (sortChoice3 == 2) {
						sort(watchlist, 2, false);
					}

				} else if (sortChoice == 4) {
					System.out.println("1 -> 1 to 10");
					System.out.println("2 -> 10 to 1");
					int sortChoice4 = valid(user, 1, 2);

					if (sortChoice4 == 1) {
						ratingSort(watchlist, true);

					} else if (sortChoice4 == 2) {
						ratingSort(watchlist, false);

					}

				} else if (sortChoice == 5) {
					System.out.println("1 -> A to Z");
					System.out.println("2 -> Z to A");
					int sortChoice5 = valid(user, 1, 2);

					if (sortChoice5 == 1) {
						sort(watchlist, 4, true);

					} else if (sortChoice5 == 2) {
						sort(watchlist, 4, false);
					}

				}

				print(watchlist);

			}

			// OPTION 2
			else if (option == 2) {
				System.out.println("how many would you like to add?");
				int count = user.nextInt();
				user.nextLine();
				for (int i = 0; i < count; i++) {
					String[] show = new String[5];

					System.out.println("show " + (i + 1) + ":");
					System.out.println("title: ");
					show[0] = user.nextLine();
					System.out.println("genre: ");
					show[1] = user.nextLine();
					System.out.println("status (c for completed, w for watching, and p for planning to watch): ");
					String status = user.nextLine();
					if (status.equals("c") || status.equals("C")) {
						show[2] = "completed";
						System.out.println("rating (out of 10): ");
						show[3] = user.nextLine();
					} else if (status.equals("w") || status.equals("W")) {
						show[2] = "watching";
						show[3] = "0";
					} else if (status.equals("p") || status.equals("P")) {
						show[2] = "planning to watch";
						show[3] = "0";
					}
					System.out.println("tags: ");
					show[4] = user.nextLine();

					watchlist.add(show);
				}
				System.out.println(count + " shows added to watchlist.");
				save(f, watchlist);
			}

			// OPTION 3
			else if (option == 3) {
				for (int i = 0; i < watchlist.size(); i++) {

					System.out.println("show " + (i + 1) + ":");
					System.out.println("title: " + watchlist.get(i)[0]);
					System.out.println();
					Thread.sleep(300);
				}

				System.out.println("which show would you like to delete? (enter the corresponding number)");
				int show = valid(user, 1, watchlist.size());
				watchlist.remove(show - 1);

				System.out.println();

			}
			// OPTION 4
			else if (option == 4) {
				System.out.println("1 -> search by genre");
				System.out.println("2 -> search by status");
				System.out.println("3 -> search by tags");
				System.out.println("4 -> search by rating");

				int searchChoice = valid(user, 1, 4);

				if (searchChoice == 1) {
					System.out.println("what genre are you looking for?");
					String genre = user.next();
					search(watchlist, 1, genre, false);
				} else if (searchChoice == 2) {
					System.out.println("1 -> show \"completed\"");
					System.out.println("2 -> show \"watching\"");
					System.out.println("3 -> show \"planning to watch\"");
					int statusChoice = valid(user, 1, 3);

					if (statusChoice == 1) {
						search(watchlist, 2, "completed", false);
					} else if (statusChoice == 2) {
						search(watchlist, 2, "watching", false);
					} else if (statusChoice == 3) {
						search(watchlist, 2, "planning to watch", false);
					}
				} else if (searchChoice == 3) {
					System.out.println("what tag are you looking for?");
					String tag = user.next();
					search(watchlist, 4, tag, false);

				} else if (searchChoice == 4) {
					System.out.println("what rating are you looking for?");
					String rating = user.next();
					search(watchlist, 3, rating, true);
				}

			}

			// OPTION 5
			else if (option == 5) {

				for (int i = 0; i < watchlist.size(); i++) {

					System.out.println("show " + (i + 1) + ":");
					System.out.println("title: " + watchlist.get(i)[0]);
					System.out.println("status: " + watchlist.get(i)[2]);
					System.out.println();
					Thread.sleep(300);

				}

				System.out.println("how many shows would you like to update?");
				int updateCount = user.nextInt();

				for (int i = 0; i < updateCount; i++) {
					System.out.println("which show would you like to update? (enter the corresponding number)");
					int show = valid(user, 1, watchlist.size());

					if (watchlist.get(show - 1)[2].equals("watching")) {
						watchlist.get(show - 1)[2] = "completed";
						System.out.println("you have completed the show!");
						System.out.println("ପ૮{˶• ༝ •˶}აଓ");
						System.out.println("what do you rate the show out of 10?");
						String rating = user.next();
						watchlist.get(show - 1)[3] = rating;
					} else if (watchlist.get(show - 1)[2].equals("planning to watch")) {
						watchlist.get(show - 1)[2] = "watching";
						System.out.println("you are currently watching the show!");
						System.out.println("૮₍˶ᵔ ᵕ ᵔ˶₎ა");
					}

				}
				save(f, watchlist);
			}

			// OPTION 6
			else if (option == 6) {

			}

			// OPTION 7
			else if (option == 7) {
				System.out.println("are you sure you want to delete your current watchlist? y or n");
				String delete = user.next();
				if (delete.equals("y")) {
					watchlist.clear();
				}
				save(f, watchlist);
			}
			next();
			options();

			option = user.nextInt();

		}

		// closing both scanners

		scan.close();
		user.close();

		bye();

	}

	public static void save(File f, ArrayList<String[]> wl) throws IOException {

		// deleting original file, creating a new file, and traversing the array list
		// onto it
		f.delete();
		f.createNewFile();
		FileWriter writer = new FileWriter(f);
		for (String[] s : wl) {
			writer.write(s[0] + "_" + s[1] + "_" + s[2] + "_" + s[3] + "_" + s[4] + "\n");
		}
		writer.close();

	}

	public static void welcome() throws InterruptedException {
		System.out.println("°.✩-------------∘*----˃̶୨୧˂̶----*∘-------------✩.°");
		Thread.sleep(300);
		System.out.println();
		System.out.println("hello! welcome to your watchlist!");
		Thread.sleep(300);
		System.out.println("what do you want to do today?");
		System.out.println("ପ૮{˶• ༝ •˶}აଓ");
		Thread.sleep(300);
	}

	public static void options() throws InterruptedException {
		System.out.println();
		System.out.println("◆:*:◇:*:◆:*:◇:*:◆:*:◇:*:◆:*:◇:*:◆:*:◇:*:◆:*:◇:*:◆");
		Thread.sleep(300);
		System.out.println();
		System.out.println("1 -> view watchlist");
		System.out.println("2 -> add to watchlist");
		System.out.println("3 -> delete from watchlist");
		System.out.println("4 -> search");
		System.out.println("5 -> update status");
		System.out.println("6 -> recommendations");
		System.out.println("7 -> delete watchlist");
		System.out.println("8 -> finished for today");
		Thread.sleep(300);
		System.out.println();
		System.out.println("◆:*:◇:*:◆:*:◇:*:◆:*:◇:*:◆:*:◇:*:◆:*:◇:*:◆:*:◇:*:◆");

	}

	public static void search(ArrayList<String[]> wl, int ind, String target, boolean rate)
			throws InterruptedException {
		int count = 0;
		for (String[] s : wl) {
			if (rate ? s[ind].equals(target) : s[ind].contains(target)) {
				System.out.println(s[0]);
				Thread.sleep(300);
				count++;
			}
		}
		if (count == 0) {
			System.out.println();
			System.out.println("no matches found.");
			System.out.println("(╯︿╰；)");
		} else {
			System.out.println();
			System.out.println(count + " match(es) found.");
			System.out.println("( ◎ ﹏ ◎ )");
		}
	}

	public static void print(ArrayList<String[]> watchlist) throws InterruptedException {
		if (watchlist.size() == 0) {
			System.out.println("your watchlist is empty.");
			System.out.println("∠(´•０•`)〴");
			Thread.sleep(300);
		} else {
			for (int i = 0; i < watchlist.size(); i++) {
				System.out.println("show " + (i + 1) + ":");
				System.out.println("title: " + watchlist.get(i)[0]);
				System.out.println("genre: " + watchlist.get(i)[1]);
				System.out.println("status: " + watchlist.get(i)[2]);
				System.out.println("rating: " + stars(Integer.parseInt(watchlist.get(i)[3])));
				System.out.println("tags: " + watchlist.get(i)[4]);
				System.out.println();
				Thread.sleep(300);
			}

		}
	}

	public static void sort(ArrayList<String[]> wl, int ind, boolean ascend) {
		String val;
		int pos;

		for (int i = 0; i < wl.size(); i++) {
			val = wl.get(i)[ind];
			pos = i;
			for (int j = i + 1; j < wl.size(); j++) {
				if (ascend ? wl.get(j)[ind].compareTo(val) < 0 : wl.get(j)[ind].compareTo(val) > 0) {
					val = wl.get(j)[ind];
					pos = j;
				}
			}
			String[] temp = wl.get(i);
			wl.set(i, wl.get(pos));
			wl.set(pos, temp);
		}

	}

	public static void ratingSort(ArrayList<String[]> wl, boolean ascend) {
		for (int i = 1; i < wl.size(); i++) {
			int key = Integer.parseInt(wl.get(i)[3]);
			String title = wl.get(i)[0];
			String genre = wl.get(i)[1];
			String status = wl.get(i)[2];
			int pos = i;

			while (pos > 0 && (ascend ? Integer.parseInt(wl.get(pos - 1)[3]) > key
					: Integer.parseInt(wl.get(pos - 1)[3]) < key)) {
				wl.get(pos)[3] = wl.get(pos - 1)[3];
				wl.get(pos)[0] = wl.get(pos - 1)[0];
				wl.get(pos)[1] = wl.get(pos - 1)[1];
				wl.get(pos)[2] = wl.get(pos - 1)[2];
				pos--;
			}
			wl.get(pos)[3] = Integer.toString(key);
			wl.get(pos)[0] = title;
			wl.get(pos)[1] = genre;
			wl.get(pos)[2] = status;
		}

	}

	public static String stars(int n) {
		String stars = "";
		if (n == 0) {
			stars = "☆☆☆☆☆☆☆☆☆☆";
		} else if (n == 1) {
			stars = "★☆☆☆☆☆☆☆☆☆";
		} else if (n == 2) {
			stars = "★★☆☆☆☆☆☆☆☆";
		} else if (n == 3) {
			stars = "★★★☆☆☆☆☆☆☆";
		} else if (n == 4) {
			stars = "★★★★☆☆☆☆☆☆";
		} else if (n == 5) {
			stars = "★★★★★☆☆☆☆☆";
		} else if (n == 6) {
			stars = "★★★★★★☆☆☆☆";
		} else if (n == 7) {
			stars = "★★★★★★★☆☆☆";
		} else if (n == 8) {
			stars = "★★★★★★★★☆☆";
		} else if (n == 9) {
			stars = "★★★★★★★★★☆";
		} else if (n == 10) {
			stars = "★★★★★★★★★★";
		}
		return stars;
	}

	public static void next() throws InterruptedException {
		System.out.println();
		System.out.println("°.✩-------------∘*----˃̶୨୧˂̶----*∘-------------✩.°");
		Thread.sleep(300);
		System.out.println();
		System.out.println("what else do you want to do today?");
		System.out.println("___〆(´•‿‿•`)");
		Thread.sleep(300);
	}

	public static void bye() throws InterruptedException {
		System.out.println();
		System.out.println("°.✩-------------∘*----˃̶୨୧˂̶----*∘-------------✩.°");
		Thread.sleep(300);
		System.out.println();
		System.out.println("bye! see you next time!");
		System.out.println("〵(^ o ^)〴");
		Thread.sleep(300);
		System.out.println();

	}

	public static int valid(Scanner user, int min, int max) {
		while (!user.hasNextInt()) {
			System.out.println("please type a valid number.");
			System.out.println("(｡•́︿•̀｡)");
			user.next();

		}
		int option = user.nextInt();

		while (option < min || option > max) {
			System.out.println("please type a valid number.");
			System.out.println("(｡•́︿•̀｡)");
			option = user.nextInt();
		}

		return option;
	}

}
