package soict.hedspi.itss2.gyatto.moneysavior.service;

import soict.hedspi.itss2.gyatto.moneysavior.dto.useraccount.FakeLoginRequest;
import soict.hedspi.itss2.gyatto.moneysavior.dto.useraccount.FakeLoginResponse;

import java.util.List;

public interface UserAccountService {
    FakeLoginResponse fakeLogin(FakeLoginRequest request);
    List<String> getSampleAccounts();
}
