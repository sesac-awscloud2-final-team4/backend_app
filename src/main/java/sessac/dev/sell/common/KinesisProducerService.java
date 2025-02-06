package sessac.dev.sell.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;
import software.amazon.awssdk.services.kinesis.model.PutRecordRequest;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KinesisProducerService {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	private final ObjectFactory<HttpSession> httpSessionFactory;
	private final KinesisAsyncClient kinesisAsyncClient;
	@Value("${cloud.aws.kinesis.stream-name}")
	private String STREAM_NAME;
	@Value("${cloud.aws.kinesis.partition-key}")
	private String PARTITION_KEY;

	public void sendMessage(String message) {
		PutRecordRequest request = PutRecordRequest.builder()
				.streamName(STREAM_NAME)
				.partitionKey(PARTITION_KEY)
				.data(SdkBytes.fromString(message, StandardCharsets.UTF_8))
				.build();

		kinesisAsyncClient.putRecord(request)
				.thenAccept(response -> log.info("Message sent. SequenceNumber: {}, message : {}", response.sequenceNumber(), message))
				.exceptionally(e -> {
					log.error("Failed to send message: {}", e.getMessage());
					return null;
				});
	}

	public void sendEventMessage(EventType eventType, Long productId, String page, Map<String, Object> metadata) {
		EventMessageDto eventMessageDto = toEventMessageDto(eventType, productId, page);
		Map<String, Object> map = addMetadata(eventMessageDto, metadata);
		try {
			sendMessage(objectMapper.writeValueAsString(map));
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	private Map<String, Object> addMetadata(EventMessageDto eventMessageDto, Map<String, Object> metadata) {
		if(ObjectUtils.isEmpty(metadata)) {
			metadata = new HashMap<>();
		}

		Map<String, Object> map = objectMapper.convertValue(eventMessageDto, Map.class);
		map.putAll(metadata);
		return map;
	}

	private EventMessageDto toEventMessageDto(EventType eventType, Long productId, String page) {
		return EventMessageDto.builder()
				.timestamp(LocalDateTime.now())
				.memberId((Long)(httpSessionFactory.getObject().getAttribute("id")))
				.eventType(eventType)
				.productId(productId)
				.page(page)
				.build();
	}
}