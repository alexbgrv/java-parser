package parser.alexbgrv;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static parser.alexbgrv.Constants.*;

public class FileMatcherSplit {
    private String arg;

    public FileMatcherSplit(String arg) {
        this.arg = arg;
    }

    private Pattern pattern;
    private Matcher matcher;

    public void match(String path, String fileName) throws IOException {
        Path getFinalPath = Files.createDirectories(Path.of(path + "\\results"));

        File[] files = new File(path).listFiles();

        for (int i = 1; i <= NUM_OF_FILES; i++) {
            if(files[i].getName().endsWith(".txt")) {
                pattern = Pattern.compile(arg);

                Reader splitFile = new Reader(path + files[i].getName());
                ArrayList<String> linesOfSplitTxtFile = splitFile.openAndRead();

                if (fileName.endsWith(".txt")) {
                    Writer txtFileResult = new Writer(getFinalPath + "\\" + fileName, true);
                }
                else {
                    Writer txtFileResult = new Writer(getFinalPath + "\\" + fileName + ".txt", true);       
                }
                changeFile(linesOfSplitTxtFile, txtFileResult);
            }
        }
    }

    public void matchWithSeparator(String path, String fileName) throws IOException {
        Path getFinalPath = Files.createDirectories(Path.of(path + "\\results"));

        File[] files = new File(path + "\\results").listFiles();
        Reader txtFileResult = new Reader(String.valueOf(files[0]));

        ArrayList<String> linesOfTxtFile = txtFileResult.openAndRead();

        pattern = Pattern.compile(LOGICAL_SPLIT_REGEX);

        if (fileName.endsWith(".csv")) {
            Writer csvFileResult = new Writer(getFinalPath + "\\" + fileName, true);
        }
        else {
            Writer csvFileResult = new Writer(getFinalPath + "\\" + fileName + ".csv", true);
        }
        changeFile(linesOfTxtFile, arg, csvFileResult);

    }

    public void changeFile(ArrayList<String> lineOfFile, Writer fileWriteName) throws IOException { // check result file
        PriorityQueue<String> myPriorityQueue = new PriorityQueue<>();
        for (String item: lineOfFile) {
            matcher = pattern.matcher(item);
            if (matcher.find())  {
                myPriorityQueue.offer(item);
            }
        }
        fileWriteName.fileWrite(myPriorityQueue);
    }

    public void changeFile(ArrayList<String> lineOfFile, String separator, Writer fileWriter) throws IOException { // check result file with separator
        PriorityQueue<String> myPriorityQueue = new PriorityQueue<>();
        for (String item: lineOfFile) {
            matcher = pattern.matcher(item);
            if (matcher.find())  {
                myPriorityQueue.offer(matcher.group(1) + separator + matcher.group(2) + separator + matcher.group(3) + separator + matcher.group(4));
            }
        }
        fileWriter.fileWrite(myPriorityQueue);
    }

}
