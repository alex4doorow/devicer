package com.afa.devicer.core.bl;

import com.afa.devicer.core.bl.entities.BaseEntity;
import com.afa.devicer.core.bl.entities.SEOrder;
import com.afa.devicer.core.errors.CoreException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrdersBLImpl implements OrdersBL {

    @Autowired
    private EntityManager entityManager;


    @Override
    public Collection<SEOrder> findOrdersByParams(LocalDate createdFrom, LocalDate createdTo) throws CoreException {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SEOrder> query = cb.createQuery(SEOrder.class);
        Root<SEOrder> root = query.from(SEOrder.class);
//        Join<SEOrder, SECustomer> rootCustomerJoin = root.join("customer", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("recStatus"), BaseEntity.ACTIVE));
        predicates.add(cb.greaterThanOrEqualTo(root.get("orderDate"), createdFrom));
        predicates.add(cb.lessThanOrEqualTo(root.get("orderDate"), createdTo));

        query.select(root).where(cb.and(predicates.toArray(new Predicate[0])));
        query.orderBy(cb.desc(root.get("id")));
        return entityManager.createQuery(query).getResultList();
    }

}
