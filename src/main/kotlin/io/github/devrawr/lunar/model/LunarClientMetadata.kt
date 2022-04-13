package io.github.devrawr.lunar.model

import io.github.devrawr.lunar.LUNAR_METADATA_ENDPOINT
import io.github.devrawr.lunar.LunarMetadataRetriever
import io.github.devrawr.lunar.model.blog.LunarClientBlogPost
import io.github.devrawr.lunar.model.feature.LunarClientFeatureFlag
import io.github.devrawr.lunar.model.integration.LunarClientIntegratedServer
import io.github.devrawr.lunar.model.sentry.SentryFilteredException
import io.github.devrawr.lunar.model.server.LunarClientPinnedServer
import io.github.devrawr.lunar.model.server.LunarClientStarredServer
import io.github.devrawr.lunar.model.settings.LunarClientClientSettings
import io.github.devrawr.lunar.model.settings.LunarClientModSettings
import io.github.devrawr.lunar.model.store.LunarClientStoreState
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author GrowlyX
 * @since 4/12/2022
 */
@Serializable
data class LunarClientMetadata(
    val blogPosts: List<LunarClientBlogPost>,
    // settings
    val modSettings: Map<String, LunarClientModSettings>,
    val clientSettings: Map<String, LunarClientClientSettings>,
    // servers
    val pinnedServers: List<LunarClientPinnedServer>,
    val starServers: List<LunarClientStarredServer>,
    @SerialName("serverIntegration")
    val integratedServers: List<LunarClientIntegratedServer>,
    val knownServersHash: String,
    // features
    @SerialName("featureFlag")
    val featureFlags: List<LunarClientFeatureFlag>,
    // misc
    @SerialName("store")
    val storeState: LunarClientStoreState,
    val sentryFilteredExceptions: List<SentryFilteredException>
)
{
    companion object
    {
        /**
         * Parse the [LunarClientMetadata] class.
         *
         * This method calls the [LunarMetadataRetriever.readMetadata] method
         * with [LunarClientMetadata] as type parameter.
         *
         * This original method reads the metadata from the [LUNAR_METADATA_ENDPOINT]
         * endpoint URL, which contains all data required to fill this class.
         */
        @JvmStatic
        fun parseMetadata(): LunarClientMetadata
        {
            return LunarMetadataRetriever.readMetadata()
        }
    }
}