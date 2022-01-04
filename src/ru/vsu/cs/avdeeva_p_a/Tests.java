package ru.vsu.cs.avdeeva_p_a;


import org.junit.Assert;
import org.junit.Test;
import utils.ListUtils;
import java.io.FileNotFoundException;
import java.util.List;

public class Tests {

   FindingIncomingStudents findingIncomingStudents = new FindingIncomingStudents();

    @Test
    public void testingListOfEntrantsForTwoPlaces() throws FileNotFoundException {
        List<List<String>> input = ListUtils.loadStringList2FromFile("TestCases/listOfEntrantsForTwoPlaces.txt");
        int budgetPlaces= 2;
        List<List<String>> output = ListUtils.loadStringList2FromFile("TestAnswers/twoIncomingEntrants.txt");

        List<List<String>> testResult = findingIncomingStudents.findingIncomingStudents(input, budgetPlaces);

        Assert.assertEquals(output, testResult);
    }

    @Test
    public void testingListOfEntrantsForFivePlaces() throws FileNotFoundException {
        List<List<String>> input = ListUtils.loadStringList2FromFile("TestCases/listOfEntrantsForFivePlaces.txt");
        int budgetPlaces = 5;
        List<List<String>> expectedResult = ListUtils.loadStringList2FromFile("TestAnswers/fiveIncomingEntrants.txt");

        List<List<String>> testResult = findingIncomingStudents.findingIncomingStudents(input, budgetPlaces);

        Assert.assertEquals(expectedResult, testResult);
    }

    @Test
    public void testingListOfEntrantsForThreePlaces() throws FileNotFoundException {
        List<List<String>> input = ListUtils.loadStringList2FromFile("TestCases/listOfEntrantsForThreePlaces.txt");
        int budgetPlaces = 3;
        List<List<String>> expectedResult = ListUtils.loadStringList2FromFile("TestAnswers/threeIncomingEntrants.txt");

        List<List<String>> testResult = findingIncomingStudents.findingIncomingStudents(input, budgetPlaces);

        Assert.assertEquals(expectedResult, testResult);
    }

    @Test
    public void testingListOfEntrantsForEightPlaces() throws FileNotFoundException {
        List<List<String>> input = ListUtils.loadStringList2FromFile("TestCases/listOfEntrantsForEightPlaces.txt");
        int budgetPlaces = 8;
        List<List<String>> expectedResult = ListUtils.loadStringList2FromFile("TestAnswers/eightIncomingEntrants.txt");

        List<List<String>> testResult = findingIncomingStudents.findingIncomingStudents(input, budgetPlaces);

        Assert.assertEquals(expectedResult, testResult);
    }

}