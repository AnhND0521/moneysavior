package soict.hedspi.itss2.gyatto.moneysavior.dto.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import soict.hedspi.itss2.gyatto.moneysavior.common.enums.TransactionType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetTransactionSummaryByPeriodRequest {
    private String userUuid;
    private TransactionType transactionType;
    private SummaryType summaryType;

    public enum SummaryType {
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY
    }
}
