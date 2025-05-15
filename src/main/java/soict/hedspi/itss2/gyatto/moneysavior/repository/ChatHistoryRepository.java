package soict.hedspi.itss2.gyatto.moneysavior.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import soict.hedspi.itss2.gyatto.moneysavior.entity.ChatHistory;
import soict.hedspi.itss2.gyatto.moneysavior.entity.Transaction;

import java.util.List;

@Repository
public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {
    List<ChatHistory> findAllByUserUuidOrderByCreatedAtAsc(String userUuid);

    @Modifying
    @Transactional
    void deleteAllByTransaction(Transaction transaction);
}
