package soict.hedspi.itss2.gyatto.moneysavior.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soict.hedspi.itss2.gyatto.moneysavior.entity.ChatHistory;

import java.util.List;

@Repository
public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {
    List<ChatHistory> findAllByUserUuidOrderByCreatedAtAsc(String userUuid);
}
