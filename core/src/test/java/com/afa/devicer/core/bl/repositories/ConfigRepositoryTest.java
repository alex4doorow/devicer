package com.afa.devicer.core.bl.repositories;

import com.afa.devicer.core.bl.entities.BaseEntity;
import com.afa.devicer.core.bl.entities.sys.SEConfig;
import com.afa.devicer.core.bl.entities.sys.SEUser;
import com.afa.devicer.core.bl.repositories.sys.ConfigRepository;
import com.afa.devicer.core.bl.repositories.sys.UserRepository;
import com.afa.devicer.core.errors.CoreException;
import com.afa.devicer.core.services.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
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
    @Autowired
    private ConfigService configService;

    private void setUp() throws Exception {

    }

    @Test
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

        SEConfig configOne = new SEConfig();
        configOne.setCode("code_j_name");
        configOne.setValue("Apple ltd");
        configOne.setAnnotation("company short name");
        configOne.setRecStatus(BaseEntity.ACTIVE);
        configOne.setUserAdded(user);
        configOne.setDateAdded(LocalDateTime.now());
        configOne = configRepository.saveAndFlush(configOne);

        SEConfig configTwo = new SEConfig();
        configTwo.setCode("code_j_inn");
        configTwo.setValue("1234567890");
        configTwo.setAnnotation("company inn");
        configTwo.setRecStatus(BaseEntity.ACTIVE);
        configTwo.setUserAdded(user);
        configTwo.setDateAdded(LocalDateTime.now());
        configTwo = configRepository.saveAndFlush(configTwo);

        List<SEConfig> configValues = configService.getAll();

        String code = "code_j_name";
        log.info("config value: {}, {}", code, configService.getValueByCode(code).getValue());
        log.info("config value: {}, {}", code, configService.getValueByCode(code).getValue());

        code = "code_j_inn";
        log.info("config value: {}, {}", code, configService.getValueByCode(code).getValue());
        log.info("config value: {}, {}", code, configService.getValueByCode(code).getValue());
    }
}
