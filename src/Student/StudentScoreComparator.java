import java.util.Comparator;

public class StudentScoreComparator implements Comparator<ScoredStudent> {
    @Override
    public int compare(ScoredStudent s1, ScoredStudent s2) {
        return Double.compare(s1.percentComplete, s2.percentComplete);
    }
}