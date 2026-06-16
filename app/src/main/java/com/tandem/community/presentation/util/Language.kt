package com.tandem.community.presentation.util

/**
 * Maps language codes to their display name and flag for the UI.
 * Unknown codes use a default globe icon.
 */
object Language {

    private data class Info(val displayName: String, val flag: String)

    private val codes = mapOf(
        "en" to Info("English", "\uD83C\uDDEC\uD83C\uDDE7"),
        "de" to Info("German", "\uD83C\uDDE9\uD83C\uDDEA"),
        "es" to Info("Spanish", "\uD83C\uDDEA\uD83C\uDDF8"),
        "it" to Info("Italian", "\uD83C\uDDEE\uD83C\uDDF9"),
        "ja" to Info("Japanese", "\uD83C\uDDEF\uD83C\uDDF5"),
        "ko" to Info("Korean", "\uD83C\uDDF0\uD83C\uDDF7"),
        "pt" to Info("Portuguese", "\uD83C\uDDF5\uD83C\uDDF9"),
        "fr" to Info("French", "\uD83C\uDDEB\uD83C\uDDF7"),
        "zh" to Info("Chinese", "\uD83C\uDDE8\uD83C\uDDF3"),
        "ru" to Info("Russian", "\uD83C\uDDF7\uD83C\uDDFA"),
        "nl" to Info("Dutch", "\uD83C\uDDF3\uD83C\uDDF1"),
        "ar" to Info("Arabic", "\uD83C\uDDF8\uD83C\uDDE6"),
    )

    private val fallbackFlag = "\uD83C\uDF10"

    fun displayName(code: String): String =
        codes[code.lowercase()]?.displayName ?: code.uppercase()

    fun flag(code: String): String =
        codes[code.lowercase()]?.flag ?: fallbackFlag

    fun label(code: String): String = "${flag(code)} ${displayName(code)}"
}
