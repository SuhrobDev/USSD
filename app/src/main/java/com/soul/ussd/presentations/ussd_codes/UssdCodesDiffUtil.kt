package com.soul.ussd.presentations.ussd_codes

import androidx.recyclerview.widget.DiffUtil
import com.soul.ussd.domain.ussd_codes.models.UssdCodesModel

class UssdCodesDiffUtil(var oldList: List<UssdCodesModel>, var newList: List<UssdCodesModel>) :
    DiffUtil.Callback() {
    override fun areItemsTheSame(p0: Int, p1: Int) = oldList[p0].id == newList[p1].id
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size
    override fun areContentsTheSame(p0: Int, p1: Int) =
        oldList[p0].code == newList[p1].code
                && oldList[p0].content == newList[p1].content
                && oldList[p0].lang == newList[p1].lang
                && oldList[p0].operator == newList[p1].operator
                && oldList[p0].title == newList[p1].title
                && oldList[p0].type == newList[p1].type
//                && oldList[p0].trainerCode == newList[p1].trainerCode
}