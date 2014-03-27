import java.util.ArrayList;

import org.junit.Test;


public class PromptTest {

    public ArrayList<City> makeTestList() {
        // List with unique out-of-order elements to test sorting methods
        ArrayList<City> testList = new ArrayList<City>();
        testList.add(new City("Name1", "333", "22", "Coun1", "N3", "E2", "1"));
        testList.add(new City("Name2", "111", "33", "Coun2", "N1", "E3", "2"));
        testList.add(new City("Name3", "222", "11", "Coun3", "N2", "E1", "3"));
        return testList;
    }

    
    @Test
    public void testMultiple() {
        // Make sure user input works
        String[] testFiles = Prompt.getFiles();
        for (int i = 0; i < testFiles.length; i++)
        {
            System.out.println(testFiles[i]);            
        }
        
        String testData = Prompt.getDataType();
        System.out.println(testData);

        String testSort = Prompt.getSortMethod(testData);
        System.out.println(testSort);
        
        boolean testExit = Prompt.getContinue();
        System.out.println(testExit);
        
    }
}
