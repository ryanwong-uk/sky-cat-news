/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.weblink.ui.viewmodel

import uk.ryanwong.skycatnews.app.util.ErrorMessage

data class WebLinkUIState(
    val url: String? = null,
    val isLoading: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList(),
)
