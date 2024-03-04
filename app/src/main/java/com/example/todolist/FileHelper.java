package com.example.todolist;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Objects;

public class FileHelper {
    //File that is saved to device memory
    public static final String FILENAME = "listinfo.dat";

    public static void writeData(ArrayList<String> item, Context context){
        //It will create file in devices memory and open it
        try {
            //Write data into file
            //It creates file and opens it
            //Private so other applications cant read it
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oas = new ObjectOutputStream(fos);
            //Object writen ito the file
            oas.writeObject(item);
            //We need to close the file
            oas.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //To read file / reading items
    public static ArrayList<String> readData(Context context){
        ArrayList<String> itemList = null;
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            //convert from object to String
            itemList = (ArrayList<String>) ois.readObject();
        } catch (FileNotFoundException e) {
//needed for very first run of application
            itemList = new ArrayList<>();

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return itemList;
    }
}
