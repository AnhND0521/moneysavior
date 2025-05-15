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
public class GetTopTransactionsRequest {
    private String userUuid;
    private TransactionType transactionType;
    private String startDate;
    private String endDate;
    private Integer limit;
}
