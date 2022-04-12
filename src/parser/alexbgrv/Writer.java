package parser.alexbgrv;

import java.io.*;
import java.nio.charset.Charset;
import java.util.PriorityQueue;


public class Writer {

    private String path;
    private boolean append;

    public Writer (String path, boolean append) {
        this.path = path;
        this.append = append;
    }

    public void fileWrite (PriorityQueue<String> queue) throws IOException {
        BufferedWriter out = new BufferedWriter
                (new OutputStreamWriter(new FileOutputStream(path, append), Charset.forName("windows-1251")));

        String temp;
        while ((temp = queue.poll()) != null) { // dequeue and write to file
            out.append(temp + "\n");
        }

        out.close();

    }
}
