package com.tandem.community.domain.model

import com.google.common.truth.Truth.assertThat
import com.tandem.community.util.member
import org.junit.Test

class CommunityMemberTest {

    @Test
    fun `member with referenceCount 0 is new`() {
        val result = member(id = 1, referenceCount = 0)
        assertThat(result.isNew).isTrue()
    }

    @Test
    fun `member with positive referenceCount is not new`() {
        val result = member(id = 1, referenceCount = 7)
        assertThat(result.isNew).isFalse()
    }
}
