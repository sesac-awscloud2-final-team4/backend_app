package sessac.dev.sell.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;

@Configuration
public class KinesisProducerConfig {
	@Bean
	public KinesisAsyncClient kinesisAsyncClient() {
		return KinesisAsyncClient.builder()
				.region(Region.AP_NORTHEAST_2)
				.credentialsProvider(DefaultCredentialsProvider.create())
				.build();
	}
}
