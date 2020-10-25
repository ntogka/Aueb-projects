package com.example.georgiosmoschovis.aceindemo.services;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Vector;

import com.example.georgiosmoschovis.aceindemo.dao.Vec2;
import com.example.georgiosmoschovis.aceindemo.ui.DAO_UI_Adapter;

/**
 * This class implements statistics computations.
 */
public class ECustomComputation extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        int i = 1;
        String filename = "Statistics_";
        String fileContents = "";

        do {
            try {
                Thread.sleep(600000); //10 minutes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Vector<Vec2<Date, Date>> data;

            synchronized (DAO_UI_Adapter.getRFID().records) {
               data = DAO_UI_Adapter.getRFID().getAllData();
            }

            for(Vec2<Date, Date> entry: data) {
                fileContents += entry.toString() + "\n";
            }

            // Get the directory for the user's public pictures directory.
            final File path =
                    Environment.getExternalStoragePublicDirectory
                            (
                                    //Environment.DIRECTORY_PICTURES
                                    Environment.DIRECTORY_DCIM + "/Statistics/"
                            );

            // Make sure the path directory exists.
            if(!path.exists())
            {
                // Make it, if it doesn't exit
                path.mkdirs();
            }

            final File file = new File(path, "statistics_"+(i++)+".txt");

            // Save your stream, don't forget to flush() it before closing it.

            try
            {
                file.createNewFile();
                FileOutputStream fOut = new FileOutputStream(file);
                OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                myOutWriter.append(fileContents);
                Log.e("Writing to file...", fileContents);
                myOutWriter.close();
                fOut.flush();
                fOut.close();
            }
            catch (IOException e)
            {
                Log.e("Exception", "File write failed: " + e.toString());
            }

        } while(true);
    }
}
