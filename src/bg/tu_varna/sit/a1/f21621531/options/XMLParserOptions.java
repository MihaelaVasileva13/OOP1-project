package bg.tu_varna.sit.a1.f21621531.options;

import java.io.*;

public class XMLParserOptions implements Options{
    private String fileName;
    private String fileContent;
    private boolean fileOpened=false;
    public XMLParserOptions() {
        fileName = null;
    }
    public void start(String[] options) throws IOException {
        try {
            switch (options[0]) {
                case "open" -> open(options);
                case "close" -> close();
                case "save" -> save();
                case "saveas" -> saveAs(options);
                case "help" -> help();
                default -> throw new InvalidOptionException("Invalid option");
            }
        }catch (InvalidOptionException e){
            System.out.println(e.getMessage());
        }

    }

    private void readFile(String fileToBeRead){
        try (BufferedReader reader = new BufferedReader(new FileReader(fileToBeRead))) {
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = reader.readLine();
            }
            fileContent = sb.toString();
        } catch (IOException e) {
            System.out.println("Unable to read file.");
        }
    }
    private void open(String[] option) throws InvalidOptionException {
        if (option.length != 2 || option[1].isEmpty()) {
            throw new InvalidOptionException("Invalid arguments for option open");
        }
        fileName= option[1];
        readFile(fileName);
        fileOpened=true;
        System.out.println("Successfully opened " + fileName);
    }
    private void close() throws InvalidOptionException {
        if (!fileOpened) {
            throw new InvalidOptionException("No file is currently open.");
        }
        System.out.println("Successfully closed "+fileName);
        fileName=null;
        fileContent=null;
        fileOpened=false;
    }
    private void save() throws InvalidOptionException{
        if (!fileOpened) {
            throw new InvalidOptionException("No file is currently open.");
        }
        readFile(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(fileContent);
            System.out.println("Successfully saved " + fileName);
        } catch (IOException e) {
            System.out.println("Unable to save file.");
        }
    }
    private void saveAs(String[] option) throws InvalidOptionException, IOException {
        if (option.length != 2 || option[1].isEmpty()) {
            throw new InvalidOptionException("Invalid arguments for option saveas");
        }
        if (!fileOpened) {
            throw new InvalidOptionException("No file is currently open.");
        }
        String newFileName= option[1];
        File newFile = new File(newFileName);
        if(!newFile.createNewFile()){
            System.out.println("Unable to create file.");
        }
        readFile(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
            writer.write(fileContent);
            System.out.println("Successfully saved " + newFileName);
        } catch (IOException e) {
            System.out.println("Unable to save file.");
        }
    }
    private void help(){
        String optionInfo= """
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
