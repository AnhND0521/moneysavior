package soict.hedspi.itss2.gyatto.moneysavior.dto.transaction;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordTransactionAutoRequest {
    @NotEmpty
    private String userUuid;
    @NotEmpty
    private String message;
}
