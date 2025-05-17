package soict.hedspi.itss2.gyatto.moneysavior.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import soict.hedspi.itss2.gyatto.moneysavior.common.enums.TransactionType;
import soict.hedspi.itss2.gyatto.moneysavior.dto.report.*;
import soict.hedspi.itss2.gyatto.moneysavior.service.ReportService;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/overview")
    @Operation(summary = "Tổng quan số dư, tổng thu, tổng chi")
    public ResponseEntity<GetOverviewResponse> getOverview(
            @RequestParam String userUuid,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        var request = GetOverviewRequest.builder()
                .userUuid(userUuid)
                .startDate(startDate)
                .endDate(endDate)
                .build();
        return ResponseEntity.ok(reportService.getOverview(request));
    }

    @GetMapping("/category-summary")
    @Operation(
            summary = "Thống kê chi tiêu theo từng danh mục",
            description = "startDate và endDate định dạng yyyy-MM-dd (ví dụ: 2025-05-01). Trả về list thông số mỗi danh mục gồm categoryName (tên danh mục), totalAmount (tổng chi của danh mục đó) và percentage (phần trăm của danh mục đó so với tổng chi)."
    )
    public ResponseEntity<GetCategorySummaryResponse> getCategorySummary(
            @RequestParam String userUuid,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        var request = GetCategorySummaryRequest.builder()
                .userUuid(userUuid)
                .startDate(startDate)
                .endDate(endDate)
                .build();
        return ResponseEntity.ok(reportService.getCategorySummary(request));
    }

    @GetMapping("/transaction-summary-by-period")
    @Operation(
            summary = "Thống kê giao dịch theo từng khoảng thời gian",
            description = "Lấy period làm nhãn trục hoành và amount làm giá trị trục tung. Dùng startDate và endDate để lấy top transactions ứng với cột mà người dùng chọn."
    )
    public ResponseEntity<GetTransactionSummaryByPeriodResponse> getTransactionSummaryByPeriod(
            @RequestParam String userUuid,
            @RequestParam TransactionType transactionType,
            @RequestParam GetTransactionSummaryByPeriodRequest.SummaryType summaryType
    ) {
        var request = GetTransactionSummaryByPeriodRequest.builder()
                .userUuid(userUuid)
                .transactionType(transactionType)
                .summaryType(summaryType)
                .build();
        return ResponseEntity.ok(reportService.getTransactionSummaryByPeriod(request));
    }

    @GetMapping("/top-transactions")
    @Operation(
            summary = "Lấy các giao dịch thu / chi nhiều nhất trong một khoảng thời gian"
    )
    public ResponseEntity<GetTopTransactionsResponse> getTopTransactions(
            @RequestParam String userUuid,
            @RequestParam TransactionType transactionType,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam(required = false, defaultValue = "DESC") Sort.Direction sortDirection,
            @RequestParam(required = false, defaultValue = "5") Integer limit
    ) {
        var request = GetTopTransactionsRequest.builder()
                .userUuid(userUuid)
                .transactionType(transactionType)
                .startDate(startDate)
                .endDate(endDate)
                .sortDirection(sortDirection)
                .limit(limit)
                .build();
        return ResponseEntity.ok(reportService.getTopTransactions(request));
    }
}
