package com.soul.ussd.domain.services.models

data class ServicesModel(
    var id: Int,
    var title: String,
    var content: String,
    var lang: String,
    var operator: Int,
    var code: String,
    var type: Int,
    var full_content: String,
)