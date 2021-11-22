package services;

import dataaccess.UserDB;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

public class AccountService {

    public void resetPassword(String email, String path, String url) throws Exception {
        //Create a UUID (universally unique ID) and store in database for the user.
        String uuid = UUID.randomUUID().toString();
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        user.setResetPasswordUuid(uuid);
        userDB.update(user);

        String to = user.getEmail();
        String subject = "Reset Password for Notes App";
        String resetTemplate = path + "/emailtemplates/resetpassword.html";

        String link = url + "?uuid=" + uuid;

        HashMap<String, String> tags = new HashMap<>();
        tags.put("firstname", user.getFirstName());
        tags.put("lastname", user.getLastName());
        tags.put("link", link);

        GmailService.sendMail(to, subject, resetTemplate, tags);
    }

    public boolean changePassword(String uuid, String password) {
        UserDB userDB = new UserDB();
        try {
            User user = userDB.getByUUID(uuid);
            user.setPassword(password);
            user.setResetPasswordUuid(null);
            userDB.update(user);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public User login(String email, String password, String path) {
        UserDB userDB = new UserDB();

        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful login by {0}", email);

                //simple plain text email
                //GmailService.sendMail(email, "New login to Notes App", "User has logged in", false);
                String to = user.getEmail();
                String subject = "Notes App Login";
                String template = path + "/emailtemplates/login.html";

                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("date", (new java.util.Date()).toString());

                GmailService.sendMail(to, subject, template, tags);

                return user;
            }
        } catch (Exception e) {
        }

        return null;
    }
}
