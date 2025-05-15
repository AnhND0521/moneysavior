package soict.hedspi.itss2.gyatto.moneysavior.dto.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;
import soict.hedspi.itss2.gyatto.moneysavior.common.enums.TransactionType;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetTopTransactionsRequest {
    private String userUuid;
    private TransactionType transactionType;
    private LocalDate startDate;
    private LocalDate endDate;
    @Builder.Default
    private Sort.Direction sortDirection = Sort.Direction.DESC;
    @Builder.Default
    private Integer limit = 5;
}
