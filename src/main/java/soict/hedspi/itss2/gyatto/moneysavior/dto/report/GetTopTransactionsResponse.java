package soict.hedspi.itss2.gyatto.moneysavior.dto.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import soict.hedspi.itss2.gyatto.moneysavior.dto.transaction.TransactionResponse;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetTopTransactionsResponse {
    private List<TransactionResponse> transactions;
}
