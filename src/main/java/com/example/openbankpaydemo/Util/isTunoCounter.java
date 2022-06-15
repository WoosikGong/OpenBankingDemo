package com.example.openbankpaydemo.Util;

import java.io.*;
import java.nio.charset.Charset;

public class isTunoCounter {
    public String counter() throws IOException {

        File file = new File("src/main/resources/tunoTmp");

        if(!file.exists()){
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bfWriter = new BufferedWriter(fw);
            bfWriter.write("0");
            bfWriter.close();
        }

        BufferedReader bfReader = new BufferedReader(
                new FileReader(file, Charset.forName("UTF-8"))
        );
        String str = bfReader.readLine();
        str = Integer.toString(Integer.parseInt(str) + 1);
        bfReader.close();

        FileWriter fw = new FileWriter(file);
        BufferedWriter bfWriter = new BufferedWriter(fw);
        bfWriter.write(str);
        bfWriter.close();

        return str;
    }
}
// 임시용