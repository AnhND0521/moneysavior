package soict.hedspi.itss2.gyatto.moneysavior.service;

import soict.hedspi.itss2.gyatto.moneysavior.dto.chatbot.CategorizeTransactionPrompt;
import soict.hedspi.itss2.gyatto.moneysavior.dto.chatbot.CategorizeTransactionResult;
import soict.hedspi.itss2.gyatto.moneysavior.dto.chatbot.ChatHistoryResponse;
import soict.hedspi.itss2.gyatto.moneysavior.dto.chatbot.Prompt;

import java.util.List;

public interface ChatbotService {
    CategorizeTransactionResult categorizeTransaction(CategorizeTransactionPrompt prompt);

    String getResponse(Prompt prompt);

    List<ChatHistoryResponse> getChatHistory(String userUuid);
}
