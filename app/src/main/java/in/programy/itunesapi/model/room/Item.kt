package `in`.programy.itunesapi.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    val artistId: Int?,
    val artistName: String,
    val artistViewUrl: String?,
    val artworkUrl100: String,
    val artworkUrl30: String?,
    val artworkUrl60: String?,
    val artworkUrl600: String?,
    val collectionArtistId: Int?,
    val collectionArtistName: String?,
    val collectionArtistViewUrl: String?,
    val collectionCensoredName: String?,
    val collectionExplicitness: String?,
    val collectionHdPrice: Double?,
    val collectionId: Int?,
    val collectionName: String?,
    val collectionPrice: Double?,
    val collectionViewUrl: String?,
    val contentAdvisoryRating: String?,
    val copyright: String?,
    val country: String?,
    val currency: String?,
    val description: String?,
    val discCount: Int?,
    val discNumber: Int?,
    val feedUrl: String?,
    val genreIds: List<String>?,
    val genres: List<String>?,
    val isStreamable: Boolean?,
    val kind: String?,
    val longDescription: String?,
    val previewUrl: String?,
    val primaryGenreName: String?,
    val releaseDate: String?,
    val shortDescription: String?,
    val trackCensoredName: String?,
    val trackCount: Int?,
    val trackExplicitness: String?,
    val trackHdPrice: Double?,
    val trackHdRentalPrice: Double?,

    @PrimaryKey(autoGenerate = false)
    val trackId: Int?,
    val trackName: String?,
    val trackNumber: Int?,
    val trackPrice: Double?,
    val trackRentalPrice: Double?,
    val trackTimeMillis: Int?,
    val trackViewUrl: String?,
    val wrapperType: String?
)