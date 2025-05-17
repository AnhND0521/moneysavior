package soict.hedspi.itss2.gyatto.moneysavior.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import soict.hedspi.itss2.gyatto.moneysavior.entity.ExpenseCategory;
import soict.hedspi.itss2.gyatto.moneysavior.repository.ExpenseCategoryRepository;
import soict.hedspi.itss2.gyatto.moneysavior.service.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final ExpenseCategoryRepository expenseCategoryRepository;

    @Override
    public List<String> getAllCategories() {
        return expenseCategoryRepository.findAll()
                .stream()
                .map(ExpenseCategory::getName)
                .toList();
    }
}
