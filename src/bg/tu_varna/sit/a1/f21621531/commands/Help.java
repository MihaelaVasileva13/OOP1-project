package bg.tu_varna.sit.a1.f21621531.commands;

public class Help implements Command {
    @Override
    public void execute() {
        System.out.println("""
                The following commands are supported:\s
                open <file> opens <file>\s
                close closes currently opened file\s
                save saves the currently open file\s
                saveas <file> saves the currently open file in <file>\s
                help prints this information\s
                exit exists the program\s
                print prints the file content formatted\s""");
    }
}
