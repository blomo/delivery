package com.blomo.integration.containers.postgres

enum class PostgresVersion(val tag: String, val type: TagType) {
    V_10_8("10.8", TagType.BULLSEYE),

    V_13_7("13.7", TagType.BULLSEYE),
    V_13_7_ALPINE("13.7", TagType.ALPINE),

    V_14_7("14.7", TagType.BULLSEYE),
    V_14_7_ALPINE("14.7", TagType.ALPINE);

    val tagName
        get() = when (type) {
            TagType.BULLSEYE -> tag
            TagType.ALPINE   -> "${tag}-alpine"
        }

}

enum class TagType { BULLSEYE, ALPINE }
