package bg.tu_varna.sit.a1.f21621531.commands;

import bg.tu_varna.sit.a1.f21621531.menu.Menu;

public class Exit implements Menu {
    @Override
    public void execute(String[] command){
        System.out.println("Exiting the program... ");
        System.exit(1);
    }
}
