package com.blomo.api.adapters.kafka.basket.deserializer


import BasketConfirmed.BasketConfirmedEvent
import com.google.protobuf.Message
import org.apache.kafka.common.serialization.Deserializer

class ProtoMessageDeserializer : Deserializer<Message> {
    override fun deserialize(
        topic: String,
        data: ByteArray,
    ): Message = BasketConfirmedEvent.BasketConfirmedIntegrationEvent.parseFrom(data)
}
