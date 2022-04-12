package parser.alexbgrv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.PriorityQueue;

import static parser.alexbgrv.Constants.NUM_OF_FILES;

public class Splitter {

    private String pathToLogFile;
    private String pathToSplitFiles;

    public Splitter(String pathToLogFile, String pathToSplitFiles) {
        this.pathToLogFile = pathToLogFile;
        this.pathToSplitFiles = pathToSplitFiles;
    }

    public void splitFiles() throws IOException {

        Reader old_file = new Reader(pathToLogFile);

        ArrayList<String> aryStrings = old_file.openAndRead(); // read main.log

        String[] path = pathToSplitFiles.split("\\\\");

        String finalPath = "";
        for (int i = 0; i < path.length - 1; i++) {
            finalPath += path[i] + "\\";
        }

        Path getFinalPath = Files.createDirectories(Path.of(finalPath)); // get path

        int writtenLines = 0;
        int numFile = 1;
        int numLine = 0;

        PriorityQueue<String> myPriorityQueue = new PriorityQueue<>(); // queue

        for (int i = 0; i < NUM_OF_FILES; i++) {
            Writer line = new Writer(getFinalPath + generateFileName(path[path.length - 1], numFile), true);

            while(writtenLines < (aryStrings.size() / NUM_OF_FILES)) { // adding each file to the queue one by one
                myPriorityQueue.offer(aryStrings.get(numLine));
                numLine++;
                writtenLines++;
            }
            line.fileWrite(myPriorityQueue); // write file one by one
            writtenLines = 0;
            numFile++;
        }
    }

    public String generateFileName(String fileName ,int numFile) {
        return "\\" + fileName + numFile + ".txt";
    }
}
