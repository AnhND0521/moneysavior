package soict.hedspi.itss2.gyatto.moneysavior.service;

import soict.hedspi.itss2.gyatto.moneysavior.dto.report.*;

import java.util.List;

public interface ReportService {
    GetOverviewResponse getOverview(GetOverviewRequest request);
    GetCategorySummaryResponse getCategorySummary(GetCategorySummaryRequest request);
    GetTransactionSummaryByPeriodResponse getTransactionSummaryByPeriod(GetTransactionSummaryByPeriodRequest request);
}
