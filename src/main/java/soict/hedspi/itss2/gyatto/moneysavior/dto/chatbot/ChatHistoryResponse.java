package soict.hedspi.itss2.gyatto.moneysavior.dto.chatbot;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import soict.hedspi.itss2.gyatto.moneysavior.dto.transaction.TransactionResponse;
import soict.hedspi.itss2.gyatto.moneysavior.entity.ChatHistory;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatHistoryResponse {
    private String uuid;
    private ChatHistory.Sender sender;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;
    private TransactionResponse transaction;
}
