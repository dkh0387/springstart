package de.dkh.kafkawikimediaproducer

import com.launchdarkly.eventsource.EventHandler
import de.dkh.kafkawikimediaproducer.kafka.WikimediaChangesHandler
import de.dkh.kafkawikimediaproducer.kafka.WikimediaChangesProducer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaWikimediaProducerApplication(private val wikimediaChangesProducer: WikimediaChangesProducer) :
    CommandLineRunner {
    override fun run(vararg args: String?) {
        wikimediaChangesProducer.sendMessage()
        //this list contains all events from wikimedia collected during 10 minutes of request
        val eventData = wikimediaChangesProducer.wikimediaChangesHandler
    }
}

fun main(args: Array<String>) {
    runApplication<KafkaWikimediaProducerApplication>(*args)
}
