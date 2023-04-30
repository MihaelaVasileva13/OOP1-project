package bg.tu_varna.sit.a1.f21621531.commandImpl;

import bg.tu_varna.sit.a1.f21621531.commands.Help;

public class HelpImpl implements Help {
    @Override
    public void showOptions() {
        String optionInfo = """
                The following commands are supported:\s
                open <file> opens <file>\s
                close closes currently opened file\s
                save saves the currently open file\s
                saveas <file> saves the currently open file in <file>\s
                help prints this information\s
                exit exists the program\s""";
        System.out.println(optionInfo);
    }
}
