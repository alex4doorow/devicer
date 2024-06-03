package com.afa.devicer.core.bl.repositories;

import com.afa.devicer.core.bl.entities.SEOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<SEOrder, Long> {

    @Query("SELECT MAX(se.orderNum) FROM SEOrder se")
    Integer findMaxOrderNum();

    Optional<SEOrder> findByOrderNum(Long orderNum);
    //List<SEOrder> findAllByOrderNum(Long orderNum);

}
