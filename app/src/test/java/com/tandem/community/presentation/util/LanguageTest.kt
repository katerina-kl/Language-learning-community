package com.tandem.community.presentation.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LanguageTest {

    @Test
    fun `known code resolves to display name`() {
        assertThat(Language.displayName("en")).isEqualTo("English")
        assertThat(Language.displayName("DE")).isEqualTo("German")
    }

    @Test
    fun `unknown code falls back to upper-cased code`() {
        assertThat(Language.displayName("xx")).isEqualTo("XX")
    }

    @Test
    fun `label combines flag and name`() {
        assertThat(Language.label("en")).contains("English")
    }
}
