package market.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:cloudinary.properties")
@ComponentScan(basePackages = "market.config")
public class CloudinaryConfig {

    @Value("${cloudinary.cloud_name}")
    private String cloudName;

    @Value("${cloudinary.api_key}")
    private String apiKey;

    @Value("${cloudinary.api_secret}")
    private String apiSecret;

    @Bean
    public Cloudinary getCloudinary() {
        return new Cloudinary(generateInitMap());
    }

    private Map<String, String> generateInitMap() {
        Map<String, String> initMap = new HashMap<>();
        initMap.put("cloud_name", this.cloudName);
        initMap.put("api_key", this.apiKey);
        initMap.put("api_secret", this.apiSecret);
        return initMap;
    }
}
