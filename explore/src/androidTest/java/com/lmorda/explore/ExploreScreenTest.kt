package com.lmorda.explore

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.lmorda.domain.model.mockDomainData
import com.lmorda.explore.list.ExploreContract
import com.lmorda.explore.list.ExploreScreen
import org.junit.Rule
import org.junit.Test

class ExploreScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testExploreScreenList() {
        composeTestRule.setContent {
            ExploreScreen(
                state = ExploreContract.State(
                    githubRepos = mockDomainData,
                ),
                onNextPage = {},
                onRefresh = {},
                onNavigateToDetails = {},
            )
        }

        composeTestRule.onNodeWithText("my-application").assertIsDisplayed()
        composeTestRule.onNodeWithText("my-application-2").assertIsDisplayed()
    }
}

