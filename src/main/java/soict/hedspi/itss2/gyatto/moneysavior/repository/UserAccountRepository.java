package soict.hedspi.itss2.gyatto.moneysavior.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import soict.hedspi.itss2.gyatto.moneysavior.entity.UserAccount;

import java.util.List;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findFirstByEmail(String email);

    @Query("""
            SELECT u FROM UserAccount u
            WHERE EXISTS (
                SELECT 1 FROM Transaction t
                WHERE t.userUuid = u.uuid
            )
            """)
    List<UserAccount> findAllThatExistTransactions();
}
