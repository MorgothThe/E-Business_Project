package controllers;


import models.*;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.AccountRepository;
import repositories.ContactRepository;
import repositories.CourseRepository;
import repositories.UserRepository;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


public class ProfileController extends Controller{

    @Inject
    private UserRepository userRepository;

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private FormFactory formFactory;

    @Inject
    private ContactRepository contactRepository;

    public Result index() {

        String usernameID = session().get("accountID");
        if(usernameID == null){
            return redirect("/");
        }
        else{
            Integer participantID = Integer.parseInt(session().get("accountID"));

            User user = userRepository.getUserWithContact(participantID);
            Account account = accountRepository.findByID(participantID);
            Role role = accountRepository.getRole(account.username);

            Contact contact = null;
            if(!user.contactList.isEmpty()) {
                contact = user.contactList.get(0);
            }

            return ok(views.html.profile_page.render(user, account, role, contact));

        }

    }

    public Result editCredidentials(){

        DynamicForm dynamicForm = formFactory.form().bindFromRequest();

        Integer id = Integer.parseInt(session().get("accountID"));

        User user = userRepository.getUserWithContact(id);
        Account account = accountRepository.findByID(id);

        String date = dynamicForm.get("birthday");

        if(!date.matches("\\d{4}-\\d{2}-\\d{2}")){
            flash("date-format", "Incorect date. The date format is YYYY-MM-DD");
            return redirect("/profile");
        }

        Timestamp timestamp = Timestamp.valueOf(date + " 00:00:00");
        if(!user.contactList.isEmpty()){
            Contact contact = user.contactList.get(0);
            Contact contactFromRepository = contactRepository.findByID(contact.id);
            if(contact.contactType.name.equals("Telephone")){
                contactFromRepository.contact = dynamicForm.get("telephone");
                contactFromRepository.update();
            }
        }
        user.firstName = dynamicForm.get("name");
        user.lastName = dynamicForm.get("surname");
        user.birthDate = timestamp;
        account.email = dynamicForm.get("email");

        account.update();
        user.update();

        return redirect("/profile");
    }
}
