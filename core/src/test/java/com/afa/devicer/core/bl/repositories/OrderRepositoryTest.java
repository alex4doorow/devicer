package com.afa.devicer.core.bl.repositories;

import com.afa.devicer.core.bl.entities.*;
import com.afa.devicer.core.bl.entities.dictionaries.SEAddressType;
import com.afa.devicer.core.bl.entities.dictionaries.SECustomerType;
import com.afa.devicer.core.bl.entities.dictionaries.SEOrderType;
import com.afa.devicer.core.bl.entities.sys.SEUser;
import com.afa.devicer.core.bl.repositories.dictionaries.AddressTypeRepository;
import com.afa.devicer.core.bl.repositories.dictionaries.CustomerTypeRepository;
import com.afa.devicer.core.bl.repositories.dictionaries.OrderTypeRepository;
import com.afa.devicer.core.bl.repositories.sys.UserRepository;
import com.afa.devicer.core.errors.CoreException;
import com.afa.devicer.core.model.types.ENAddressTypes;
import com.afa.devicer.core.model.types.ENCustomerTypes;
import com.afa.devicer.core.model.types.ENOrderTypes;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
            user.setId(1L);
            user.setUsername("test");
            user.setPassword("test");
            user.setEmail("test@test.com");
            user.setLastLogin(LocalDateTime.now());
            userRepository.save(user);
            user = userRepository.findById(1L)
                    .orElseThrow(() -> new CoreException("USER", "user not found", CoreException.THROWS));
        } else {
            user = userRepository
                    .findById(1L)
                    .orElseThrow(() -> new CoreException("USER", "user not found", CoreException.THROWS));
        }

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

        SEOrder order = new SEOrder();
        order.setOrderNum(777L);
        order.setType(orderType);
        order.setOrderDate(LocalDate.of(2024, 5, 15));
        order.setCustomer(customer);
        order.setDateAdded(LocalDateTime.now());
        order.setDateModified(LocalDateTime.now());
        order.setRecStatus(BaseEntity.ACTIVE);
        order.setUserAdded(user);
        orderRepository.save(order);

        Assertions.assertFalse(orderRepository.findByOrderNum(777L).isEmpty());
    }
}
