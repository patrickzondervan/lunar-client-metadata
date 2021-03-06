package io.github.devrawr.lunar

import io.github.devrawr.lunar.model.LunarClientMetadataContext
import kotlinx.serialization.decodeFromString

object LunarClientMetadataRetriever
{
    /**
     * Read the metadata from the [context]'s endpoint.
     *
     * This method simply reads from the endpoint and returns
     * it as a string.
     *
     * Will automatically retrieve the correct endpoint
     * according to the [LunarClientMetadataContext] provided.
     *
     * @param context the context used to identify the correct endpoint
     * @return the data read from the endpoint
     */
    @JvmStatic
    fun readMetadataAsText(
        context: LunarClientMetadataContext
    ): String
    {
        return context.endpoint.readText()
    }

    /**
     * Read the metadata from the [context]'s endpoint.
     *
     * This method will automatically try to parse the
     * retrieved data from the server using the provided
     * generic type of [T].
     *
     * @param T the generic type to serialize to
     * @param context the metadata context
     * @return the parsed data
     */
    inline fun <reified T> readMetadata(
        context: LunarClientMetadataContext
    ): T
    {
        return json.decodeFromString(
            readMetadataAsText(context)
        )
    }
}
