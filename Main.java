import java.io.File;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by guillaume on 6/3/17.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("This will create a file for question 1");
        File myFile1 = IOUtils.createMyFile();
        IOUtils.writeMyFile(myFile1);

        String fileContent = IOUtils.readMyFile(myFile1);

        System.out.println(fileContent);
        Map<String, String> map = new HashMap<>();
        map.put("The", "a");
        map.put("first", "beautiful");
        System.out.println(IOUtils.replacer(map, fileContent));

        System.out.println("This will create a file for question 2:");
        File myFile2 = IOUtils.createMyFile();
        IOUtils.writeMyFile(myFile2);
        IOUtils.fileContentReplacer(map, fileContent, myFile2);

        System.out.println("This will create a file for question 3:");
        File myFile3 = IOUtils.createMyFile();
        IOUtils.writeMyFile(myFile3);
        IOUtils.fileContentMerger(map, fileContent, myFile3);

        System.out.println("Question 4:");
        System.out.println("the word line is repeated " + IOUtils.checkWord(myFile3, "line") +
        " times in the file " + myFile3.getName());

    }

}
