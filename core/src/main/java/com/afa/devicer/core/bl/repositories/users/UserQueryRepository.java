package com.afa.devicer.core.bl.repositories.users;

import com.afa.devicer.core.bl.entities.users.SEUserQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserQueryRepository extends JpaRepository<SEUserQuery, Long> {

    @Query("SELECT MAX(se.value) FROM SEUserQuery se where se.user.id = :userId and se.shelf = :shelf and se.code = :code")
    String findValueByUserAndShelfAndCode(Long userId, String shelf, String code);

    Optional<SEUserQuery> findByUserIdAndShelfAndCode(Long userId, String shelf, String code);
}
