/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.storydetail.data.remote

import uk.ryanwong.skycatnews.storydetail.data.remote.model.ContentDTO
import uk.ryanwong.skycatnews.storydetail.data.remote.model.HeroImageDTO
import uk.ryanwong.skycatnews.storydetail.data.remote.model.StoryDTO

class FakeStoryService() : StoryService {
    override suspend fun getStory(storyId: Int): Result<StoryDTO> {
        return Result.success(
            StoryDTO(
                contents = listOf(
                    ContentDTO(
                        accessibilityText = null,
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
                        type = "paragraph",
                        url = null
                    ),
                    ContentDTO(
                        accessibilityText = null,
                        text = "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                        type = "paragraph",
                        url = null
                    ),
                    ContentDTO(accessibilityText = "", text = null, type = "image", url = ""),
                    ContentDTO(
                        accessibilityText = null,
                        text = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.",
                        type = "paragraph",
                        url = null
                    ),
                    ContentDTO(accessibilityText = "", text = null, type = "image", url = ""),
                    ContentDTO(
                        accessibilityText = null,
                        text = "Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.",
                        type = "paragraph",
                        url = null
                    )
                ),
                creationDate = "2020-11-18T00:00:00Z",
                headline = "Cat story headline",
                heroImage = HeroImageDTO(
                    accessibilityText = "Image content description",
                    imageUrl = ""
                ),
                id = 1,
                modifiedDate = "2020-11-19T00:00:00Z"
            )
        )
    }
}
