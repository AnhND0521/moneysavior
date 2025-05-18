package soict.hedspi.itss2.gyatto.moneysavior.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import soict.hedspi.itss2.gyatto.moneysavior.dto.useraccount.FakeLoginRequest;
import soict.hedspi.itss2.gyatto.moneysavior.dto.useraccount.FakeSignUpRequest;
import soict.hedspi.itss2.gyatto.moneysavior.dto.useraccount.UserAccountResponse;
import soict.hedspi.itss2.gyatto.moneysavior.entity.UserAccount;
import soict.hedspi.itss2.gyatto.moneysavior.repository.UserAccountRepository;
import soict.hedspi.itss2.gyatto.moneysavior.service.UserAccountService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository;

    @Override
    public UserAccountResponse fakeLogin(FakeLoginRequest request) {
        var user = userAccountRepository.findFirstByEmail(request.getEmail());
        if (user == null) {
            return UserAccountResponse.builder()
                    .userUuid(null)
                    .fullName(null)
                    .build();
        }
        return UserAccountResponse.builder()
                .userUuid(user.getUuid())
                .fullName(user.getFullName())
                .build();
    }

    @Override
    public UserAccountResponse fakeSignUp(FakeSignUpRequest request) {
        var user = userAccountRepository.findFirstByEmail(request.getEmail());
        if (user != null) {
            user.setFullName(request.getFullName());
        } else {
            user = UserAccount.builder()
                    .email(request.getEmail())
                    .fullName(request.getFullName())
                    .build();
        }
        userAccountRepository.save(user);
        return UserAccountResponse.builder()
                .userUuid(user.getUuid())
                .fullName(user.getFullName())
                .build();
    }

    @Override
    public List<String> getSampleAccounts() {
        return userAccountRepository.findAllThatExistTransactions()
                .stream()
                .map(UserAccount::getEmail)
                .toList();
    }
}
