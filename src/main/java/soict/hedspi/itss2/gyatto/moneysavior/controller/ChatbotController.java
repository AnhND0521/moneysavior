package soict.hedspi.itss2.gyatto.moneysavior.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import soict.hedspi.itss2.gyatto.moneysavior.dto.chatbot.ChatHistoryResponse;
import soict.hedspi.itss2.gyatto.moneysavior.service.ChatbotService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chatbot")
@RequiredArgsConstructor
public class ChatbotController {
    private final ChatbotService chatbotService;

    @GetMapping("/history")
    @Operation(
            summary = "Lấy lịch sử chat với bot",
            description = "Trả về danh sách các tin nhắn đã gửi và nhận từ bot (kèm giao dịch liên quan)."
    )
    public ResponseEntity<List<ChatHistoryResponse>> getChatHistory(@RequestParam String userUuid) {
        return ResponseEntity.ok(chatbotService.getChatHistory(userUuid));
    }
}
