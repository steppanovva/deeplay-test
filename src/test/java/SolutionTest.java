import deeplay.Solution;
import org.junit.Test;

public class SolutionTest {
    @Test(expected = Exception.class)
    public void invalidRace_throwException() throws Exception {
        Solution.getResult("STWSWTPPTPTTPWPP", "non existing race");
    }

    @Test
    public void validData_correctResult() throws Exception {
        assert Solution.getResult("STWSWTPPTPTTPWPP", "Human") == 10;
        assert Solution.getResult("STWSWTPPTPTTPWPP", "Swamper") == 15;
        assert Solution.getResult("STWSWTPPTPTTPWPP", "Woodman") == 12;
    }

    @Test(expected = Exception.class)
    public void incorrectDataLength_throwException() throws Exception {
        Solution.getResult("TWSSS", "Human");
    }

    @Test(expected = Exception.class)
    public void incorrectData_throwException() throws Exception {
        Solution.getResult("STWQHR", "Human");
    }
}
