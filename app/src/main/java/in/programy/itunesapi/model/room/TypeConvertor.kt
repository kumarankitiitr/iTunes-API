package `in`.programy.itunesapi.model.room

import androidx.room.TypeConverter

class TypeConvertor {

    @TypeConverter
    fun fromList(list: List<String>?): String?{
        return list?.toString()
    }

    @TypeConverter
    fun toList(ele: String?): List<String>?{
        return null
    }
}