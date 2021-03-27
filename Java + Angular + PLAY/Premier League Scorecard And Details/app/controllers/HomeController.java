package controllers;

import CLI.FootBallClub;
import CLI.Match;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

public class HomeController extends Controller {
    //This is an auto Generated controller class to pass Data in Json Format.

    public Result footBallList() {
        List<FootBallClub> newList = GUIController.getClubList();
        return ok(Json.toJson(newList));
    }

    public Result matchList() {
        List<Match> newList2 = GUIController.getMatchList();
        return ok(Json.toJson(newList2));
    }

    public Result matchDateSortList() {
        List<Match> newList2 = GUIController.getMatchSortList();
        return ok(Json.toJson(newList2));
    }

    public Result clubGoalSortList() {
        List<FootBallClub> newList2 = GUIController.goalSortedClubList();
        return ok(Json.toJson(newList2));
    }

    public Result clubWinSortList() {
        List<FootBallClub> newList2 = GUIController.winSortedClubList();
        return ok(Json.toJson(newList2));
    }

    public Result randomMatch() {
        List<Match> newList = new ArrayList<>();
        Match randomMatch = GUIController.autoGenerateMatch();
        newList.clear();
        newList.add(randomMatch);
        return ok(Json.toJson(newList));
    }
}
