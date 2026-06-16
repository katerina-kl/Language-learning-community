package com.tandem.community.data.mapper

import com.google.common.truth.Truth.assertThat
import com.tandem.community.data.remote.dto.CommunityMemberDto
import org.junit.Test

class CommunityMapperTest {

    @Test
    fun `maps all fields correctly`() {
        val dto = CommunityMemberDto(
            id = 42,
            topic = "Topic",
            firstName = "Tobi",
            pictureUrl = "https://example.com/p.png",
            natives = listOf("de", "ja"),
            learns = listOf("en"),
            referenceCnt = 3,
        )

        val domain = dto.toDomain()

        assertThat(domain.id).isEqualTo(42)
        assertThat(domain.topic).isEqualTo("Topic")
        assertThat(domain.firstName).isEqualTo("Tobi")
        assertThat(domain.pictureUrl).isEqualTo("https://example.com/p.png")
        assertThat(domain.natives).containsExactly("de", "ja").inOrder()
        assertThat(domain.learns).containsExactly("en")
        assertThat(domain.referenceCount).isEqualTo(3)
        assertThat(domain.isLiked).isFalse()
    }

    @Test
    fun `null fields fall back to safe defaults`() {
        val dto = CommunityMemberDto(
            id = 1,
            topic = null,
            firstName = null,
            pictureUrl = null,
            natives = null,
            learns = null,
            referenceCnt = null,
        )

        val domain = dto.toDomain()

        assertThat(domain.topic).isEmpty()
        assertThat(domain.firstName).isEmpty()
        assertThat(domain.pictureUrl).isEmpty()
        assertThat(domain.natives).isEmpty()
        assertThat(domain.learns).isEmpty()
        assertThat(domain.referenceCount).isEqualTo(0)
        assertThat(domain.isNew).isTrue()
    }

    @Test
    fun `maps a list of dtos`() {
        val dtos = listOf(
            CommunityMemberDto(1, "t", "A", "u", listOf("de"), listOf("en"), 0),
            CommunityMemberDto(2, "t", "B", "u", listOf("es"), listOf("pt"), 5),
        )

        val domain = dtos.toDomain()

        assertThat(domain).hasSize(2)
        assertThat(domain[0].firstName).isEqualTo("A")
        assertThat(domain[1].firstName).isEqualTo("B")
    }
}
