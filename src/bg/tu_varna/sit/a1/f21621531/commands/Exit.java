package bg.tu_varna.sit.a1.f21621531.commands;

public class Exit implements Command {
    @Override
    public void execute(){
        System.out.println("Exiting the program... ");
        System.exit(1);
    }
}
