import com.google.gson.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONParse implements parse {
    private FileWriter fileWriter;
    public void parser(String inputFilePath, String outputFilePath) {
        JsonParser parser = new JsonParser();
        try {
            JsonObject jsonObject = (JsonObject) parser.parse(new FileReader(inputFilePath));
            fileWriter=new FileWriter(outputFilePath);
            printData(jsonObject);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void printData(JsonObject jsonObject)throws IOException {
        for (String keyString : jsonObject.keySet()) {
            Object object = jsonObject.get(keyString);
            if (object instanceof JsonPrimitive){
                fileWriter.write( keyString + " : " + object+System.getProperty("line.separator"));
            }
            if (object instanceof JsonObject) {
                fileWriter.write( keyString+System.getProperty("line.separator"));
                fileWriter.write("-----------------------"+System.getProperty("line.separator"));
                printData((JsonObject) object);
            }
            if (object instanceof JsonArray){
                fileWriter.write( keyString+System.getProperty("line.separator"));
                fileWriter.write("["+System.getProperty("line.separator"));

                JsonArray jsonElements = (JsonArray) object;
                for (Object element : jsonElements){
                    if (element instanceof JsonPrimitive ) {
                        fileWriter.write( element+ System.getProperty("line.separator"));
                    }else {
                        printData((JsonObject) element);
                    }
                }
                fileWriter.write("]"+System.getProperty("line.separator"));
            }
        }
    }

}
