package soict.hedspi.itss2.gyatto.moneysavior.service;

import soict.hedspi.itss2.gyatto.moneysavior.dto.useraccount.FakeLoginRequest;
import soict.hedspi.itss2.gyatto.moneysavior.dto.useraccount.FakeSignUpRequest;
import soict.hedspi.itss2.gyatto.moneysavior.dto.useraccount.UserAccountResponse;

import java.util.List;

public interface UserAccountService {
    UserAccountResponse fakeLogin(FakeLoginRequest request);
    UserAccountResponse fakeSignUp(FakeSignUpRequest request);
    List<String> getSampleAccounts();
}
