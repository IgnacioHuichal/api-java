package cl.lupoconecta.apijava.common.infrastructure.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
/**
   * Spring usa este bean para obtener la base/URI correcta desde properties y gestionar la conexión a MongoDB
   */
@Configuration
public class CustomMongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongoUri);
    }
}
