package com.example.realmflows.Entity

import io.realm.RealmObject
import io.realm.annotations.Required
import org.bson.types.ObjectId

open class RealmFeedBack (
    @Required
    var id : String=ObjectId().toHexString(),
    @Required
    var name:String="",
     var message:String?=null

): RealmObject()