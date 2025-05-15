package soict.hedspi.itss2.gyatto.moneysavior.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import soict.hedspi.itss2.gyatto.moneysavior.common.enums.TransactionType;
import soict.hedspi.itss2.gyatto.moneysavior.dto.report.*;
import soict.hedspi.itss2.gyatto.moneysavior.mapper.TransactionMapper;
import soict.hedspi.itss2.gyatto.moneysavior.repository.TransactionRepository;
import soict.hedspi.itss2.gyatto.moneysavior.service.ReportService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public GetOverviewResponse getOverview(GetOverviewRequest request) {
        var result = transactionRepository.findOverviewByUserUuid(
                request.getUserUuid(),
                request.getStartDate(),
                request.getEndDate()
        );
        return GetOverviewResponse.builder()
                .balance(result.getTotalIncomes().subtract(result.getTotalExpenses()))
                .totalIncomes(result.getTotalIncomes())
                .totalExpenses(result.getTotalExpenses())
                .build();
    }

    @Override
    public GetCategorySummaryResponse getCategorySummary(GetCategorySummaryRequest request) {
        var data = transactionRepository.findCategorySummaryByUserUuid(
                request.getUserUuid(),
                request.getStartDate(),
                request.getEndDate()
        );
        return GetCategorySummaryResponse.builder()
                .data(data)
                .build();
    }

    @Override
    public GetTransactionSummaryByPeriodResponse getTransactionSummaryByPeriod(GetTransactionSummaryByPeriodRequest request) {
        if (TransactionType.UNDEFINED.equals(request.getTransactionType())) {
            throw new IllegalStateException("Unexpected value: " + request.getTransactionType());
        }
        switch (request.getSummaryType()) {
            case DAILY -> {
                int days = 14;
                List<Pair<LocalDate, LocalDate>> dateRanges = new ArrayList<>();
                var date = LocalDate.now().minusDays(days - 1);
                for (int i = 0; i < days; i++) {
                    dateRanges.add(Pair.of(date, date));
                    date = date.plusDays(1);
                }
                var formatter = DateTimeFormatter.ofPattern("dd/MM");
                return getTransactionSummaryByPeriod(
                        request,
                        dateRanges,
                        dateRange -> dateRange.getFirst().format(formatter)
                );
            }
            case WEEKLY -> {
                int weeks = 8;
                List<Pair<LocalDate, LocalDate>> dateRanges = new ArrayList<>();
                var date = LocalDate.now().minusWeeks(weeks - 1);
                for (int i = 0; i < weeks; i++) {
                    dateRanges.add(Pair.of(date, date.plusDays(6)));
                    date = date.plusWeeks(1);
                }
                var formatter = DateTimeFormatter.ofPattern("dd/MM");
                return getTransactionSummaryByPeriod(
                        request,
                        dateRanges,
                        dateRange -> dateRange.getFirst().format(formatter) + " - " + dateRange.getSecond().format(formatter)
                );
            }
            case MONTHLY -> {
                int months = 12;
                List<Pair<LocalDate, LocalDate>> dateRanges = new ArrayList<>();
                var date = LocalDate.now().withDayOfMonth(1).minusMonths(months - 1);
                for (int i = 0; i < months; i++) {
                    var first = date;
                    var last = date.withDayOfMonth(date.lengthOfMonth());
                    dateRanges.add(Pair.of(first, last));
                    date = date.plusMonths(1);
                }
                var formatter = DateTimeFormatter.ofPattern("MM/yyyy");
                return getTransactionSummaryByPeriod(
                        request,
                        dateRanges,
                        dateRange -> dateRange.getFirst().format(formatter)
                );
            }
            case YEARLY -> {
                int years = 10;
                List<Pair<LocalDate, LocalDate>> dateRanges = new ArrayList<>();
                var date = LocalDate.now().withDayOfYear(1).minusYears(years - 1);
                for (int i = 0; i < years; i++) {
                    var first = date;
                    var last = date.withDayOfYear(date.lengthOfYear());
                    dateRanges.add(Pair.of(first, last));
                    date = date.plusYears(1);
                }
                var formatter = DateTimeFormatter.ofPattern("yyyy");
                return getTransactionSummaryByPeriod(
                        request,
                        dateRanges,
                        dateRange -> dateRange.getFirst().format(formatter)
                );
            }
            default -> {
                return null;
            }
        }
    }

    private GetTransactionSummaryByPeriodResponse getTransactionSummaryByPeriod(
            GetTransactionSummaryByPeriodRequest request,
            List<Pair<LocalDate, LocalDate>> dateRanges,
            Function<Pair<LocalDate, LocalDate>, String> labelConverter
    ) {
        var data = dateRanges.stream()
                .map(dateRange -> {
                    var amount = transactionRepository.findTransactionAmountByPeriod(
                            request.getUserUuid(),
                            request.getTransactionType(),
                            dateRange.getFirst(),
                            dateRange.getSecond()
                    );
                    return TransactionSummaryByPeriodResult.builder()
                            .period(labelConverter.apply(dateRange))
                            .amount(amount)
                            .startDate(dateRange.getFirst())
                            .endDate(dateRange.getSecond())
                            .build();
                }).toList();

        return GetTransactionSummaryByPeriodResponse.builder()
                .data(data)
                .build();
    }
}
