package com.afa.devicer.core.bl.repositories;

import com.afa.devicer.core.bl.entities.*;
import com.afa.devicer.core.bl.entities.dictionaries.*;
import com.afa.devicer.core.bl.entities.sys.SEStore;
import com.afa.devicer.core.bl.entities.sys.SEUser;
import com.afa.devicer.core.bl.repositories.dictionaries.*;
import com.afa.devicer.core.bl.repositories.sys.StoreRepository;
import com.afa.devicer.core.bl.repositories.sys.UserRepository;
import com.afa.devicer.core.errors.CoreException;
import com.afa.devicer.core.model.types.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Slf4j
public class OrderRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private CategoryProductRepository categoryProductRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerTypeRepository customerTypeRepository;
    @Autowired
    private AddressTypeRepository addressTypeRepository;
    @Autowired
    private OrderTypeRepository orderTypeRepository;
    @Autowired
    private OrderSourceTypeRepository orderSourceTypeRepository;
    @Autowired
    private OrderAdvertTypeRepository orderAdvertTypeRepository;
    @Autowired
    private OrderEmailStatusTypeRepository orderEmailStatusTypeRepository;
    @Autowired
    private OrderStatusTypeRepository orderStatusTypeRepository;
    @Autowired
    private OrderPaymentTypeRepository orderPaymentTypeRepository;
    @Autowired
    private OrderPaymentDeliveryTypeRepository orderPaymentDeliveryTypeRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testOrder() throws CoreException {

        Optional<SEUser> optionalUser = userRepository.findById(1L);
        SEUser user;
        if (optionalUser.isEmpty()) {
            user = new SEUser();
            user.setUsername("test");
            user.setPassword("test");
            user.setEmail("test@test.com");
            user.setLastLogin(LocalDateTime.now());
            user = userRepository.saveAndFlush(user);
            user = userRepository.findByUsername("test")
                    .orElseThrow(() -> new CoreException("USER", "user not found", CoreException.THROWS));
        } else {
            user = userRepository
                    .findById(1L)
                    .orElseThrow(() -> new CoreException("USER", "user not found", CoreException.THROWS));
        }

        SEStore store = new SEStore();
        store.setId(ENStores.PM.getId());
        store.setCode(ENStores.PM.getPrefix());
        store.setAnnotation(ENStores.PM.getAnnotation());
        store.setUrl(ENStores.PM.getSite());
        store.setRecStatus(BaseEntity.ACTIVE);
        store.setUserAdded(user);
        store = storeRepository.save(store);

        SECategoryProduct categoryProduct = new SECategoryProduct();
        categoryProduct.setId(101L);
        categoryProduct.setGroup("отпугиватели");
        categoryProduct.setAnnotation("отпугиватели кротов");
        categoryProduct.setRecStatus(BaseEntity.ACTIVE);
        categoryProduct.setUserAdded(user);
        categoryProduct = categoryProductRepository.save(categoryProduct);

        SEPerson person = new SEPerson();
        person.setId(1L);
        person.setCountryIsoCode("UK");
        person.setFirstName("John");
        person.setLastName("Lennon");
        person.setCountryIsoCode("UK");
        person.setPhoneNumber("(000) 000-00-01");
        person.setEmail("lennon@beatles.com");
        person.setRecStatus(BaseEntity.ACTIVE);
        person.setUserAdded(user);
        person = personRepository.save(person);

        SECustomerType customerType = new SECustomerType();
        customerType.setId(ENCustomerTypes.CUSTOMER.getId());
        customerType.setAnnotation(ENCustomerTypes.CUSTOMER.getLongName());
        customerType.setRecStatus(BaseEntity.ACTIVE);
        customerType.setUserAdded(user);
        customerType = customerTypeRepository.save(customerType);

        SEAddressType addressType = new SEAddressType();
        addressType.setId(ENAddressTypes.MAIN.getId());
        addressType.setAnnotation(ENAddressTypes.MAIN.getAnnotation());
        addressType.setRecStatus(BaseEntity.ACTIVE);
        addressType.setUserAdded(user);
        addressType = addressTypeRepository.save(addressType);

        SEAddress seAddress = new SEAddress();
        seAddress.setType(addressType);
        seAddress.setCountryIsoCode("UK");
        seAddress.setStreet("London, Backer street");
        seAddress.setPostCode("123456");
        seAddress.setRecStatus(BaseEntity.ACTIVE);
        seAddress.setUserAdded(user);
        seAddress = addressRepository.save(seAddress);

        SECustomer customer = new SECustomer();
        customer.setId(1L);
        customer.setType(customerType);
        customer.setPerson(person);
        customer.setRecStatus(BaseEntity.ACTIVE);
        customer.setAddresses(Set.of(seAddress));
        customer.setUserAdded(user);
        customer = customerRepository.save(customer);

        SEOrderType orderType = new SEOrderType();
        orderType.setId(ENOrderTypes.BILL.getId());
        orderType.setAnnotation(ENOrderTypes.BILL.getAnnotation());
        orderType.setRecStatus(BaseEntity.ACTIVE);
        orderType.setUserAdded(user);
        orderType = orderTypeRepository.save(orderType);

        SEOrderAdvertType orderAdvertType = new SEOrderAdvertType();
        orderAdvertType.setId(ENOrderAdvertTypes.ADVERT.getId());
        orderAdvertType.setAnnotation(ENOrderAdvertTypes.ADVERT.getAnnotation());
        orderAdvertType.setRecStatus(BaseEntity.ACTIVE);
        orderAdvertType.setUserAdded(user);
        orderAdvertType = orderAdvertTypeRepository.save(orderAdvertType);

        SEOrderSourceType orderSourceType = new SEOrderSourceType();
        orderSourceType.setId(ENOrderSourceTypes.LID.getId());
        orderSourceType.setAnnotation(ENOrderSourceTypes.LID.getAnnotation());
        orderSourceType.setRecStatus(BaseEntity.ACTIVE);
        orderSourceType.setUserAdded(user);
        orderSourceType = orderSourceTypeRepository.save(orderSourceType);

        SEOrderEmailStatusType orderEmailStatusType = new SEOrderEmailStatusType();
        orderEmailStatusType.setId(ENOrderEmailStatuses.UNKNOWN.getId());
        orderEmailStatusType.setAnnotation(ENOrderEmailStatuses.UNKNOWN.getAnnotation());
        orderEmailStatusType.setRecStatus(BaseEntity.ACTIVE);
        orderEmailStatusType.setUserAdded(user);
        orderEmailStatusType = orderEmailStatusTypeRepository.save(orderEmailStatusType);

        SEOrderStatusType orderStatusType = new SEOrderStatusType();
        orderStatusType.setId(ENOrderStatuses.BID.getId());
        orderStatusType.setAnnotation(ENOrderStatuses.BID.getAnnotation());
        orderStatusType.setRecStatus(BaseEntity.ACTIVE);
        orderStatusType.setUserAdded(user);
        orderStatusType = orderStatusTypeRepository.save(orderStatusType);

        SEOrderPaymentDeliveryType orderPaymentDeliveryType = new SEOrderPaymentDeliveryType();
        orderPaymentDeliveryType.setId(ENPaymentDeliveryTypes.CUSTOMER.getId());
        orderPaymentDeliveryType.setAnnotation(ENPaymentDeliveryTypes.CUSTOMER.getAnnotation());
        orderPaymentDeliveryType.setRecStatus(BaseEntity.ACTIVE);
        orderPaymentDeliveryType.setUserAdded(user);
        orderPaymentDeliveryType = orderPaymentDeliveryTypeRepository.save(orderPaymentDeliveryType);

        SEOrderPaymentType orderPaymentType = new SEOrderPaymentType();
        orderPaymentType.setId(ENPaymentTypes.PREPAYMENT.getId());
        orderPaymentType.setAnnotation(ENPaymentTypes.PREPAYMENT.getAnnotation());
        orderPaymentType.setRecStatus(BaseEntity.ACTIVE);
        orderPaymentType.setUserAdded(user);
        orderPaymentType = orderPaymentTypeRepository.save(orderPaymentType);

        SEOrder order = new SEOrder();
        order.setOrderNum(777L);
        order.setOrderDate(LocalDate.of(2024, 5, 15));
        order.setStore(store);
        order.setCategoryProduct(categoryProduct);
        order.setType(orderType);
        order.setSourceType(orderSourceType);
        order.setAdvertType(orderAdvertType);
        order.setPaymentType(orderPaymentType);
        order.setStatus(orderStatusType);
        order.setEmailStatus(orderEmailStatusType);
        order.setCustomer(customer);
        order.setAmountTotal(BigDecimal.ZERO);
        order.setOfferCountDay(0L);
        order.setDateAdded(LocalDateTime.now());
        order.setDateModified(LocalDateTime.now());
        order.setRecStatus(BaseEntity.ACTIVE);
        order.setUserAdded(user);
        orderRepository.save(order);

        Assertions.assertFalse(orderRepository.findByOrderNum(777L).isEmpty());
    }
}
