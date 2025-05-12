package soict.hedspi.itss2.gyatto.moneysavior.mapper;

import org.mapstruct.Mapper;
import soict.hedspi.itss2.gyatto.moneysavior.dto.chatbot.ChatHistoryResponse;
import soict.hedspi.itss2.gyatto.moneysavior.entity.ChatHistory;

@Mapper(componentModel = "spring", uses = {TransactionMapper.class})
public interface ChatbotMapper {
    ChatHistoryResponse toChatHistoryResponse(ChatHistory chatHistory);
}
