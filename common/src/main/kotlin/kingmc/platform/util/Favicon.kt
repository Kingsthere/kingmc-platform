package kingmc.platform.util

import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import javax.imageio.ImageIO

/**
 * Represents a Minecraft server favicon. A Minecraft server favicon is a 64x64 image that can be
 * displayed to a remote client that sends a Server List Ping packet, and is automatically displayed
 * in the Minecraft client
 */
data class Favicon(
    /**
     * The Base64-encoded URI for this image.
     *
     * @return a URL representing this favicon
     */
    val base64Url: String
) {
    companion object {
        /**
         * Creates a new `Favicon` from the specified `image`.
         *
         * @param image the image to use for the favicon
         * @return the created [Favicon] instance
         */
        fun create(image: BufferedImage): Favicon {
            val width = image.width
            val height = image.height
            require(width == 64 && height == 64) { "Image is not 64x64 (found ${width}x${height})" }
            val os = ByteArrayOutputStream()
            try {
                ImageIO.write(image, "PNG", os)
            } catch (e: IOException) {
                throw AssertionError(e)
            }
            return Favicon(
                "data:image/png;base64," + Base64.getEncoder().encodeToString(os.toByteArray())
            )
        }

        /**
         * Creates a new `Favicon` by reading the image from the specified `path`.
         *
         * @param path the path to the image to create a favicon for
         * @return the created [Favicon] instance
         * @throws IOException if the file could not be read from the path
         */
        @Throws(IOException::class)
        fun create(path: Path): Favicon {
            Files.newInputStream(path).use { stream ->
                val image: BufferedImage = ImageIO.read(stream) ?: throw IOException("Unable to read the image.")
                return create(image)
            }
        }
    }
}