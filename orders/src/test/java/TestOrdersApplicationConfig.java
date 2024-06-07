import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"${app.beans-xml-path}"})
@EnableCaching
public class TestOrdersApplicationConfig {

}
