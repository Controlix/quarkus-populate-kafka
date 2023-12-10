package be.mbict.quarkus.kafka.producer

import io.smallrye.mutiny.Multi
import io.smallrye.reactive.messaging.kafka.Record
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.reactive.messaging.Outgoing
import kotlin.random.Random

@ApplicationScoped
class KafkaKlantenProducer {

    @Outgoing("klanten-out")
    fun generate() : Multi<Record<Int, Klant>> {
        return Multi.createFrom().range(1, 500)
            .map { Klant(id = Random.nextInt(20), naam = "Peeters - $it", voornaam = "Jan") }
            .map { Record.of(it.id, it) }
    }
}

data class Klant(
    val id: Int,
    val naam: String,
    val voornaam: String
)