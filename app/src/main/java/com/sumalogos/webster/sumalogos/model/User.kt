package com.sumalogos.webster.sumalogos.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by webster on 01/01/18.
 */
//
//@Entity(tableName = "user")
//data class User(
//        @PrimaryKey var id: Long,
//        var fullName: String,
//        var dob: Date?,
//        var email: String,
//        var sex: String,
//        var churchName: String,
//        var relationshipStatus: String,
//        var groupId: Int
//
//)
//
@Entity(tableName = "user")
data class User (
    @PrimaryKey
    @SerializedName("id")
    var id: Long = 0,

    @ColumnInfo(name = "full_name")
    @SerializedName("full_name")
    var fullName: String = "",

    @SerializedName("dob")
    var dob: Date? = null,

    @SerializedName("email")
    var email: String = "",

    @SerializedName("sex")
    var sex: String = "",

    @ColumnInfo(name = "church_name")
    @SerializedName("church_name")
    var churchName: String = "",

    @ColumnInfo(name = "relationship_status")
    @SerializedName("relationship_status")
    var relationshipStatus: String = "",

    @ColumnInfo(name = "group_id")
    @SerializedName("group_id")
    var groupId: Int = 0,

    @ColumnInfo(name = "photo_url")
    @SerializedName("photo_url")
    var photoUrl: String = ""

)
