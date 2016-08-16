package com.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Json {
    private String reportPath = ".\\reports\\";
    private String reportFilename = "Automation_Report.json";

    private JSONObject obj;
    private JSONArray jarr;

    FileWriter file;

    public Json (String path, String filename) {
        obj = new JSONObject();
        jarr = new JSONArray();

        if (filename != null) {
            reportFilename = filename;
        }
        if (path != null) {
            reportPath = path;
        }
    }

    public void addValue(String key, String value) {
        obj.put(key, value);
    }

    public void addValue(String key, Integer value) {
        obj.put(key, value);
    }

    public void addValue(String key, List<String> value) {
        JSONArray list = new JSONArray();
        for (String item : value) {
            list.add(item);
        }
        obj.put(key, value);
    }

    public void addArray() {
        jarr.add(obj);
        obj = new JSONObject();
    }

    public void write() throws IOException {
        try {
            write(reportPath + reportFilename);
        } catch (IOException e) {
            throw e;
        }
    }

    public void write(String outputFile) throws IOException {
        try {
            file = new FileWriter(outputFile, true);
            jarr.writeJSONString(file);
        } catch (IOException e) {
            throw e;
        }
    }

    public void flush() throws IOException {
        try {
            file.flush();
        } catch (IOException e) {
            throw e;
        }
    }

    public void close() throws IOException {
        try {
            file.close();
        } catch (IOException e) {
            throw e;
        }
    }


}
