package com.soul.ussd.data.services.remote.dto

data class ServiceResponse(
    var id: Int,
    var title: String,
    var content: String,
    var lang: String,
    var operator: Int,
    var code: String,
    var type: Int,
    var full_content: String,
)