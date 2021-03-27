import controllers.HomeController;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http.RequestBuilder;
import play.mvc.Result;
import play.test.WithApplication;

import static play.test.Helpers.GET;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.route;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;

public class HomeControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    //FootBall Default list
    @Test
    public void test_football_list_from_a_new_instance() {
        Result result = new HomeController().footBallList();
        assertEquals(OK, result.status());
        assertEquals("application/json", result.contentType().get());
        assertEquals(contentAsString(result), "{\"content\":\"Java Play Angular Seed\"}");
    }

    @Test
    public void test_football_list_from_route() {
        RequestBuilder request = new RequestBuilder()
                .method(GET)
                .uri("/api/footballClubs");

        Result result = route(app, request);
        assertEquals(OK, result.status());
        assertEquals("application/json", result.contentType().get());
        assertEquals(contentAsString(result), "{\"content\":\"Java Play Angular Seed\"}");
    }
    //Match Default list
    @Test
    public void test_matches_from_a_new_instance() {
        Result result = new HomeController().matchList();
        assertEquals(OK, result.status());
        assertEquals("application/json", result.contentType().get());
        assertEquals(contentAsString(result), "{\"content\":\"Java Play Angular Seed\"}");
    }

    @Test
    public void test_matches_test_from_route() {
        RequestBuilder request = new RequestBuilder()
                .method(GET)
                .uri("/api/matches");

        Result result = route(app, request);
        assertEquals(OK, result.status());
        assertEquals("application/json", result.contentType().get());
        assertEquals(contentAsString(result), "{\"content\":\"Java Play Angular Seed\"}");
    }
    //Match Sorted list
    @Test
    public void test_matches_date_from_a_new_instance() {
        Result result = new HomeController().matchDateSortList();
        assertEquals(OK, result.status());
        assertEquals("application/json", result.contentType().get());
        assertEquals(contentAsString(result), "{\"content\":\"Java Play Angular Seed\"}");
    }

    @Test
    public void test_matches_date_test_from_route() {
        RequestBuilder request = new RequestBuilder()
                .method(GET)
                .uri("/api/matchesDate ");

        Result result = route(app, request);
        assertEquals(OK, result.status());
        assertEquals("application/json", result.contentType().get());
        assertEquals(contentAsString(result), "{\"content\":\"Java Play Angular Seed\"}");
    }

    //FootBall Win Sorted list
    @Test
    public void test_club_win_sort_from_a_new_instance() {
        Result result = new HomeController().clubWinSortList();
        assertEquals(OK, result.status());
        assertEquals("application/json", result.contentType().get());
        assertEquals(contentAsString(result), "{\"content\":\"Java Play Angular Seed\"}");
    }

    @Test
    public void test_club_win_sort_test_from_route() {
        RequestBuilder request = new RequestBuilder()
                .method(GET)
                .uri("/api/clubWinSort");

        Result result = route(app, request);
        assertEquals(OK, result.status());
        assertEquals("application/json", result.contentType().get());
        assertEquals(contentAsString(result), "{\"content\":\"Java Play Angular Seed\"}");
    }

    //FootBall Goal Sorted list
    @Test
    public void test_club_goal_sort_from_a_new_instance() {
        Result result = new HomeController().clubGoalSortList();
        assertEquals(OK, result.status());
        assertEquals("application/json", result.contentType().get());
        assertEquals(contentAsString(result), "{\"content\":\"Java Play Angular Seed\"}");
    }

    @Test
    public void test_club_goal_sort_test_from_route() {
        RequestBuilder request = new RequestBuilder()
                .method(GET)
                .uri("/api/clubGoalSort");

        Result result = route(app, request);
        assertEquals(OK, result.status());
        assertEquals("application/json", result.contentType().get());
        assertEquals(contentAsString(result), "{\"content\":\"Java Play Angular Seed\"}");
    }

    //random Match
    @Test
    public void test_random_from_a_new_instance() {
        Result result = new HomeController().randomMatch();
        assertEquals(OK, result.status());
        assertEquals("application/json", result.contentType().get());
        assertEquals(contentAsString(result), "{\"content\":\"Java Play Angular Seed\"}");
    }

    @Test
    public void test_random_test_from_route() {
        RequestBuilder request = new RequestBuilder()
                .method(GET)
                .uri("/api/randomMatch");

        Result result = route(app, request);
        assertEquals(OK, result.status());
        assertEquals("application/json", result.contentType().get());
        assertEquals(contentAsString(result), "{\"content\":\"Java Play Angular Seed\"}");
    }

}
