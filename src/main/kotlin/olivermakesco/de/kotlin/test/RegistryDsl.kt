package olivermakesco.de.kotlin.test

import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

/**
 * RegistryDsl class, used for inputting a mod id before registering
 * @param modid The mod id to register under
 * @param action The DSL action
 *
 * @author Oliver-makes-code (Emma)
 * */
class RegistryDsl(private val modId: String, action: RegistryDsl.() -> Unit) {
    init {
        apply(action)
    }

    /**
     * Used for registering an object, uses the mod id provided in the constructor
     * @param name The name to register under
     * @param t The object to register
     *
     * @author Oliver-makes-code (Emma)
     * */
    fun <T> Registry<T>.register(name: String, t: T) {
        Registry.register(this, Identifier(modId, name), t)
    }
}
/**
 * Base registry DSL, allows you to call the register functions one after another
 * @param action The DSL action
 *
 * @author Oliver-makes-code (Emma)
 * */
inline operator fun <reified T> Registry<T>.invoke(action: Registry<T>.() -> Unit) {
    apply(action)
}

/**
 * Used for registering an object, uses the mod id provided in the constructor
 * @param identifier The Identifier to register under
 * @param t The object to register
 *
 * @author Oliver-makes-code (Emma)
 * */
fun <T> Registry<T>.register(identifier: Identifier, t: T) {
    Registry.register(this, identifier, t)
}
