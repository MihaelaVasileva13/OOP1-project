package bg.tu_varna.sit.a1.f21621531;

import bg.tu_varna.sit.a1.f21621531.options.GeneralMenu;
import bg.tu_varna.sit.a1.f21621531.options.Menu;
import java.util.Scanner;
public class XMLParser {
    private static XMLParser instance;

    public static XMLParser getInstance()
    {
        if (instance==null){
            instance=new XMLParser();
        }
        return instance;
    }
    public void launch(){
        Menu menu=new GeneralMenu();
        Scanner scanner=new Scanner(System.in);
        String input;
        String[] options;
        while (true)
        {
            System.out.print("> ");
            input=scanner.nextLine();
            if (input.equals("exit"))
            {
                System.out.println("Exiting the program... ");
                System.exit(1);
            }
            options=input.split(" ");
            try {
                menu.execute(options);
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
