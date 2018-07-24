package com.example.wanglu.stationerystore.Model.JSON;

import java.io.PrintWriter;
import java.io.StringWriter;
//Author: Luo Chao
public class StackTrace {
    public static String trace(Exception ex) {
        StringWriter outStream = new StringWriter();
        ex.printStackTrace(new PrintWriter(outStream));
        return outStream.toString();
    }
}