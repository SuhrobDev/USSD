package com.soul.ussd.data.ussd_codes.remote.dto

data class UssdCodesResponse(
    var id: Int,
    var title: String,
    var content: String,
    var lang: String,
    var operator: Int,
    var code: String,
    var type: Int,
    var full_content: String,
)