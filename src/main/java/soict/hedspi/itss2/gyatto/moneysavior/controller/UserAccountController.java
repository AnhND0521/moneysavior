package soict.hedspi.itss2.gyatto.moneysavior.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soict.hedspi.itss2.gyatto.moneysavior.dto.useraccount.FakeLoginRequest;
import soict.hedspi.itss2.gyatto.moneysavior.dto.useraccount.FakeLoginResponse;
import soict.hedspi.itss2.gyatto.moneysavior.service.UserAccountService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserAccountController {
    private final UserAccountService userAccountService;

    @PostMapping("/accounts:fake-login")
    @Operation(summary = "Giả lập login ảo, nhập email là lấy được userUuid để dùng mấy api khác")
    public ResponseEntity<FakeLoginResponse> fakeLogin(@RequestBody @Valid FakeLoginRequest request) {
        return ResponseEntity.ok(userAccountService.fakeLogin(request));
    }

    @GetMapping("/accounts")
    @Operation(summary = "Lấy các email có sẵn giao dịch để đăng nhập")
    public ResponseEntity<List<String>> getFakeLogin() {
        return ResponseEntity.ok(userAccountService.getSampleAccounts());
    }
}
