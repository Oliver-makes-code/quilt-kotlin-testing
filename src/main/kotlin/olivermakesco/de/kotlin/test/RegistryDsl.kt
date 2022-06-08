package olivermakesco.de.kotlin.test

import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings

class RegistryDsl(val modid: String, action: RegistryDsl.() -> Unit) {
    fun <T> Registry<T>.register(name: String, t: T) {
        Registry.register(this, Identifier(modid, name), t)
    }
    init {
        apply(action)
    }
}
inline operator fun <reified T> Registry<T>.invoke(action: Registry<T>.() -> Unit) {
    apply(action)
}
fun <T> Registry<T>.register(identifier: Identifier, t: T) {
    Registry.register(this, identifier, t)
}

fun test() {
    RegistryDsl("modid") {
        Registry.BLOCK {
            register("test_1", Block(QuiltBlockSettings.of(Material.STONE)))
        }
    }
    Registry.BLOCK {
        register(Identifier("modid","test_2"), Block(QuiltBlockSettings.of(Material.STONE)))
    }
    Registry.BLOCK.register(Identifier("modid","test_3"), Block(QuiltBlockSettings.of(Material.STONE)))
}
