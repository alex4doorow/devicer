package com.afa.devicer.core.bl.repositories;

import com.afa.devicer.core.bl.entities.BaseEntity;
import com.afa.devicer.core.bl.entities.sys.SEConfig;
import com.afa.devicer.core.bl.entities.sys.SEUser;
import com.afa.devicer.core.bl.repositories.sys.ConfigRepository;
import com.afa.devicer.core.bl.repositories.sys.UserRepository;
import com.afa.devicer.core.errors.CoreException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Slf4j
public class ConfigRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConfigRepository configRepository;

    private void setUp() throws Exception {

    }

    @Test
    @CacheEvict(value = "seConfigCache", allEntries = true)
    public void testConfig() throws CoreException {
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

        SEConfig config = new SEConfig();
        config.setId(1L);
        config.setCode("code_j_name");
        config.setValue("Apple ltd");
        config.setAnnotation("company short name");
        config.setRecStatus(BaseEntity.ACTIVE);
        config.setUserAdded(user);
        config = configRepository.save(config);

        //List<SEConfig> configValues = configRepository.findByRecStatus(BaseEntity.ACTIVE);
        List<SEConfig> configValues = configRepository.findAll();
        log.info("configValues: {}", configValues);

    }

}
