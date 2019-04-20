package model;

import android.content.Context;
import android.widget.Toast;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.List;

public class FileManager {

    public static void writeFile(Context context, String fileName, List<?extends Object> strings){

        // Try to put it as external
        // I have to add to manifest permisson read external storage

        File root = new File(context.getFilesDir(),fileName);

        try {

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName,Context.MODE_PRIVATE));

            for (int i = 0; i < strings.size(); i++){
                outputStreamWriter.write(strings.get(i).toString());
                outputStreamWriter.flush();
            }

            outputStreamWriter.close();

            Toast.makeText(context, "Results saved", Toast.LENGTH_SHORT).show();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
