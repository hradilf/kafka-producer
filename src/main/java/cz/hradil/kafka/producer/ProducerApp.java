package cz.hradil.kafka.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

//import kafka.message.ExchangeMessage.Order;

public class ProducerApp {

    public static String KAFKA_BROKERS = "localhost:9092";

    public static Integer MESSAGE_COUNT=1000;

    public static String CLIENT_ID="client1";

    public static String TOPIC_NAME="fh-topic1";

    public static String GROUP_ID_CONFIG="consumerGroup1";

    public static Integer MAX_NO_MESSAGE_FOUND_COUNT=100;

    public static String OFFSET_RESET_LATEST="latest";

    public static String OFFSET_RESET_EARLIER="earliest";

    public static Integer MAX_POLL_RECORDS=1;


    private static Producer<Long, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer(props);

    }

    public static void main(String[] args) {
        Producer<Long, String> producer = createProducer();

//        ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(TOPIC_NAME, "value-" + Math.random());
        ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(TOPIC_NAME, "value-6");
        try {
            RecordMetadata metadata = producer.send(record).get();
            System.out.println("Record sent: topic=" + metadata.topic() + ", partition=" + metadata.partition() + ", offset " + metadata.offset());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
