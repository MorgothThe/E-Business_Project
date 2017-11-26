package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class CourseController extends Controller {

    public Result index(){


        return ok(views.html.course.render());
    }
}
