package soict.hedspi.itss2.gyatto.moneysavior.dto.transaction;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SepayWebhookRequest {
    //    "id": 92704,                              // ID giao dịch trên SePay
    //    "gateway":"Vietcombank",                  // Brand name của ngân hàng
    //    "transactionDate":"2023-03-25 14:02:37",  // Thời gian xảy ra giao dịch phía ngân hàng
    //    "accountNumber":"0123499999",              // Số tài khoản ngân hàng
    //    "code":null,                               // Mã code thanh toán (sepay tự nhận diện dựa vào cấu hình tại Công ty -> Cấu hình chung)
    //    "content":"chuyen tien mua iphone",        // Nội dung chuyển khoản
    //    "transferType":"in",                       // Loại giao dịch. in là tiền vào, out là tiền ra
    //    "transferAmount":2277000,                  // Số tiền giao dịch
    //    "accumulated":19077000,                    // Số dư tài khoản (lũy kế)
    //    "subAccount":null,                         // Tài khoản ngân hàng phụ (tài khoản định danh),
    //    "referenceCode":"MBVCB.3278907687",         // Mã tham chiếu của tin nhắn sms
    //    "description":""                           // Toàn bộ nội dung tin nhắn sms

    private Long id;
    private String gateway;
    private String transactionDate;
    private String accountNumber;
    private String code;
    private String content;
    private String transferType;
    private BigDecimal transferAmount;
    private BigDecimal accumulated;
    private String subAccount;
    private String referenceCode;
    private String description;
}
