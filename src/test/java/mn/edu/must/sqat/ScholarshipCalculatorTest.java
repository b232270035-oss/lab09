package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScholarshipCalculatorTest {
    private final ScholarshipCalculator calculator = new ScholarshipCalculator();

    @Test
    void letterGradeA() {
        assertEquals("A", calculator.letterGrade(95));
    }

    @Test
    void letterGradeB() {
        assertEquals("B", calculator.letterGrade(85));
    }

    @Test
    void letterGradeC() {
        assertEquals("C", calculator.letterGrade(75));
    }

    @Test
    void gpaAverage() {
        assertEquals(3.5, calculator.gpa(new double[]{95, 85}));
    }

    @Test
    void scholarshipFullAward() {
        assertEquals(100, calculator.scholarship(3.9, 16, false));
    }

    @Test
    void letterGradeDPath() {
        assertEquals("D", calculator.letterGrade(65));
    }

    @Test
    void letterGradeFPath() {
        assertEquals("F", calculator.letterGrade(50));
    }

    @Test
    void letterGradeRejectsNegativeScore() {
        assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(-1));
    }

    @Test
    void letterGradeRejectsScoreAboveMaximum() {
        assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(101));
    }

    @Test
    void gpaRejectsNullScores() {
        assertThrows(IllegalArgumentException.class, () -> calculator.gpa(null));
    }

    @Test
    void gpaRejectsEmptyScores() {
        assertThrows(IllegalArgumentException.class, () -> calculator.gpa(new double[]{}));
    }

    @Test
    void gpaCoversCDEAndDefaultSwitchCases() {
        assertEquals(1.0, calculator.gpa(new double[]{75, 65, 50}));
    }

    @Test
    void loopBoundaryZeroScoresRejectsEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> calculator.gpa(new double[]{}));
    }

    @Test
    void loopBoundaryZeroScoresRejectsNullArray() {
        assertThrows(IllegalArgumentException.class, () -> calculator.gpa(null));
    }

    @Test
    void loopBoundaryOneScore() {
        assertEquals(4.0, calculator.gpa(new double[]{95}));
    }

    @Test
    void loopBoundaryMultipleScores() {
        assertEquals(2.5, calculator.gpa(new double[]{95, 85, 75, 65}));
    }

    @Test
    void boundaryScoreSixtyIsD() {
        assertEquals("D", calculator.letterGrade(60));
    }

    @Test
    void boundaryScoreBelowSixtyIsF() {
        assertEquals("F", calculator.letterGrade(59.99));
    }

    @Test
    void boundaryScoreSixtyNinePointNineNineIsD() {
        assertEquals("D", calculator.letterGrade(69.99));
    }

    @Test
    void boundaryScoreZeroIsF() {
        assertEquals("F", calculator.letterGrade(0));
    }

    @Test
    void boundaryScoreOneHundredIsA() {
        assertEquals("A", calculator.letterGrade(100));
    }

    @Test
    void boundaryScoreEightyNinePointNineNineIsB() {
        assertEquals("B", calculator.letterGrade(89.99));
    }

    @Test
    void boundaryScoreNinetyIsA() {
        assertEquals("A", calculator.letterGrade(90));
    }

    @Test
    void scholarshipRejectsNegativeGpa() {
        assertThrows(IllegalArgumentException.class, () -> calculator.scholarship(-0.1, 15, false));
    }

    @Test
    void scholarshipRejectsGpaAboveMaximum() {
        assertThrows(IllegalArgumentException.class, () -> calculator.scholarship(4.1, 15, false));
    }

    @Test
    void scholarshipRejectsNegativeCredits() {
        assertThrows(IllegalArgumentException.class, () -> calculator.scholarship(3.9, -1, false));
    }

    @Test
    void scholarshipViolationGetsNoAward() {
        assertEquals(0, calculator.scholarship(4.0, 20, true));
    }

    @Test
    void scholarshipFullGpaWithInsufficientCreditsGetsNoFullAward() {
        assertEquals(70, calculator.scholarship(3.9, 14, false));
    }

    @Test
    void scholarshipSeventyPercentAward() {
        assertEquals(70, calculator.scholarship(3.5, 12, false));
    }

    @Test
    void scholarshipGpaQualifiesButCreditsDoNot() {
        assertEquals(50, calculator.scholarship(3.6, 11, false));
    }

    @Test
    void scholarshipFiftyPercentAward() {
        assertEquals(50, calculator.scholarship(3.1, 10, false));
    }

    @Test
    void scholarshipBelowMinimumGetsNoAward() {
        assertEquals(0, calculator.scholarship(2.9, 15, false));
    }
}
