package controllers;

import com.google.inject.Inject;
import data.request.ForgotPasswordWrapper;
import data.request.ResetFromForgotPasswordWrapper;
import models.accounts.Account;
import models.accounts.ForgotPasswordToken;
import play.libs.Json;
import play.mvc.Result;
import services.ForgotPasswordTokenService;
import services.TransactionalMailerService;
import utils.JsonDebugger;

public class ManageAccessController extends BaseController {
    private final ForgotPasswordTokenService forgotPasswordTokenService;
    private final TransactionalMailerService mailerService;

    @Inject
    public ManageAccessController(ForgotPasswordTokenService forgotPasswordTokenService, TransactionalMailerService mailerService) {
        this.forgotPasswordTokenService = forgotPasswordTokenService;
        this.mailerService = mailerService;
    }

    public Result changePassword() {
        JsonDebugger.debug(request().body().asJson());
        ResetFromForgotPasswordWrapper wrapper = Json.fromJson(request().body().asJson(), ResetFromForgotPasswordWrapper.class);
        if(wrapper.newPassword == null || wrapper.hashedForgotPasswordToken == null) {
            return badRequest();
        }
        return forgotPasswordTokenService.findAccountForValidToken(wrapper.hashedForgotPasswordToken).map(account -> {
            account.getLoginInfo().setPassword(wrapper.newPassword);
            account.update();
            return noContent();
        }).orElse(badRequest());
    }

    public Result generateForgotPasswordToken() {
        ForgotPasswordWrapper req = play.libs.Json.fromJson(request().body().asJson(), ForgotPasswordWrapper.class);
        return Account.findByEmail(req.accountIdentifier)
                .filter(account -> !account.isArchived())
                .map(account -> {
            try {
                ForgotPasswordToken token = forgotPasswordTokenService.generateForgotPasswordToken(account);
                // clone before save since save hashes
                JsonDebugger.debug(token);
                ForgotPasswordToken clone = new ForgotPasswordToken(token);
                token.save();
                mailerService.sendResetPasswordEmail(clone);
                return ok();
            } catch(Exception e) {
                e.printStackTrace();
                return internalServerError();
            }
        }).orElse(unauthorized());
    }
}
