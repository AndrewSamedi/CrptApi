package ru.crpt.ismp.Impl;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

public class Main {
    private static String json = null;
    public static void main(String[] args) throws InterruptedException {
        CrptApi crptApi = new CrptApi(TimeUnit.SECONDS,1,"https://ismp.crpt.ru/api/v3/lk/documents/create");
        try{
            byte[] readBytes = Files.readAllBytes(Paths.get("/src/main/resources/doc.json"));
            json = new String(readBytes, StandardCharsets.UTF_8);
        }catch (IOException e){
            e.getMessage();
        }
        Gson gson = new Gson();
        CrptApi.Document document = gson.fromJson(json,CrptApi.Document.class);
        crptApi.createDoc(document,new String("signature"));
        crptApi.shutDown();
    }
}
