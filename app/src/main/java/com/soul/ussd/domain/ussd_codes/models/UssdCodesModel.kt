package com.soul.ussd.domain.ussd_codes.models

data class UssdCodesModel(
    var id: Int,
    var title: String,
    var content: String,
    var lang: String,
    var operator: Int,
    var code: String,
    var type: Int,
    var full_content: String,
)