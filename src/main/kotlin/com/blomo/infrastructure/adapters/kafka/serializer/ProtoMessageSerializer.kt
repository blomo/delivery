package com.blomo.infrastructure.adapters.kafka.serializer

import com.google.protobuf.Message
import org.apache.kafka.common.serialization.Serializer

class ProtoMessageSerializer : Serializer<Message> {
    override fun serialize(
        topic: String,
        data: Message,
    ): ByteArray = data.toByteArray()
}
