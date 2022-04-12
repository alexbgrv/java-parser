package parser.alexbgrv;

import java.io.IOException;

public class Main {

    public static void main(String... args) throws IOException {
        try{
            if (args.length == 2) {
                new Splitter(args[0],args[1]).splitFiles();
            } else if (args.length == 3) {
                if (args[0].length() > 1) {
                    new FileMatcherSplit(args[0]).match(args[1], args[2]);
                } else {
                    new FileMatcherSplit(args[0]).matchWithSeparator(args[1], args[2]);
                }
            } else {
                if (args[0].contains("--help")) {
                    new Help(args[0]).information();
                }
                else {
                    System.out.println("Неверно введен аргумент");
                }

            }
        } catch(Exception e) {
            System.out.println("Неверное количество аргументов");
        }
    }



}
