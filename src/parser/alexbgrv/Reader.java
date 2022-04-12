package parser.alexbgrv;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Reader {

    private String path;

    public Reader(String path) {
        this.path = path;
    }

    public ArrayList<String> openAndRead() throws IOException {
        BufferedReader lines = new BufferedReader
                (new InputStreamReader(new FileInputStream(path), Charset.forName("windows-1251")));
        String line;
        ArrayList<String> ar = new ArrayList<>();
        while ((line = lines.readLine()) != null) {
            ar.add(line);
        }
        lines.close();
        return ar;
    }
}
