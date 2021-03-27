package CLI;

import java.util.Comparator;

public class GoalDifComparator implements Comparator<FootBallClub> {
	@Override
	public int compare(FootBallClub o1, FootBallClub o2) {
		return o1.getFCGoalDifference()-o2.getFCGoalDifference();
	}
}
