package kingmc.platform.audience.sound

import kingmc.util.key.Key

data class SoundImpl(
    override val type: Key,
    override val source: Sound.Source,
    override val volume: Float,
    override val pitch: Float
) : Sound {
    override fun toBuilder(): Sound.Builder {
        return BuilderImpl(type, source, volume, pitch)
    }

    class BuilderImpl(
        override var type: Key = Key("item.armor.equip_diamond"),
        override var source: Sound.Source = Sound.Source.MASTER,
        override var volume: Float = 1f,
        override var pitch: Float = 0f
    ) : Sound.Builder {
        override fun type(type: Key): Sound.Builder {
            this.type = type
            return this
        }

        override fun source(source: Sound.Source): Sound.Builder {
            this.source = source
            return this
        }

        override fun volume(volume: Float): Sound.Builder {
            this.volume = volume
            return this
        }

        override fun pitch(pitch: Float): Sound.Builder {
            this.pitch = pitch
            return this
        }

        override fun build(): Sound {
            return SoundImpl(type, source, volume, pitch)
        }

    }
}
