package bg.tu_varna.sit.a1.f21621531.xmlParser;

import bg.tu_varna.sit.a1.f21621531.menu.GeneralMenu;
import bg.tu_varna.sit.a1.f21621531.menu.Menu;

import java.util.Scanner;

public class XmlParser {
    private static XmlParser instance;

    public static XmlParser getInstance() {
        if (instance == null) {
            instance = new XmlParser();
        }
        return instance;
    }

    public void launch() {
        Menu menu = new GeneralMenu();
        Scanner scanner = new Scanner(System.in);
        String input;
        String[] command;
        while (true) {
            System.out.print("> ");
            input = scanner.nextLine();
            if (input.equals("exit")) {
                System.out.println("Exiting the program... ");
                System.exit(0);
            }
            command = input.split(" ");
            try {
                menu.execute(command);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Exiting the program... ");
                System.exit(0);
            }
        }
    }
}
