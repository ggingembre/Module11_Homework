import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by guillaume on 6/3/17.
 */
public class IOUtils {


    public static File createMyFile(){
        File myFile = null;
        String fileName = "";

        while(true){

            try{
                fileName = getFileName();
                File file = new File("/home/guillaume/" + fileName + ".txt");

                if (file.createNewFile()){
                    System.out.println("File is created!\n");
                    myFile = file;
                }else{
                    System.out.println("File already exists. Please try again with a new name");
                    throw new IOException();
                }
                break;

            } catch (IOException e){
                continue;
            }
        }
        return myFile;
    }


    public static String getFileName(){

        String fileName = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            try{
                System.out.println("Please enter the name of the new file:");
                fileName = br.readLine();
                break;
            } catch (IOException e){
                System.out.println("You entered the wrong value, please try again");
                continue;
            }
        }
        return fileName;
    }

    public static void writeMyFile(File file){

        List<String> lines = Arrays.asList("The first line", "The second line", "The third line");
        String filePath = file.getPath();

        //Files.write(filePath, lines, Charset.forName("UTF-8"));

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){

            for (String line : lines){
                bw.write(line + "\n");
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public static String readMyFile(File myFile){

        StringBuilder outPut = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(myFile));
            reader.lines().forEach(s -> outPut.append(s).append("\n"));
        } catch (IOException e ) {
            e.printStackTrace();
        }

        String fileContent = outPut.toString();

        return fileContent;
    }

    public static String replacer(Map<String, String> map, String source) {
        String replaced = source;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            replaced = replaced.replaceAll(entry.getKey(), entry.getValue());
        }
        return replaced;
    }

    public static File fileContentReplacer(Map<String, String> map, String fileContent, File file) {
        String newFile = replacer(map, fileContent);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter (file))) {
            writer.write(newFile);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static File fileContentMerger(Map<String, String> map, String fileContent, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write("\n");
            writer.write(replacer(map, fileContent));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static int checkWord(File myFile, String word) {

        int countTheSameWords = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(myFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\ |\\.");
                for (String s : words) {
                    if (s.equals(word)) {
                        countTheSameWords++;
                    }
                }
            }
            } catch (IOException e) {
                e.printStackTrace();
            }

        return countTheSameWords;
    }
}

