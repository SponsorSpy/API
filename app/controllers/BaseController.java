package controllers;

import global.RequestContextConstants;
import play.mvc.Controller;

/**
 * Base Controller class -
 * Abstracts helpful methods for all controllers
 * @lilyfrancus (4/2/18)
 */
public class BaseController extends Controller {
    public UserAccount getAccessorAccount() {
        return (UserAccount)ctx().args.get(RequestContextConstants.USER_ACCOUNT);
    }

    public <T> T getRequest() {
        return (T)ctx().args.get(RequestContextConstants.UNWRAPPED_REQUEST);
    }

    public boolean hasSuperUserContext() {
        return true;
    }
}
