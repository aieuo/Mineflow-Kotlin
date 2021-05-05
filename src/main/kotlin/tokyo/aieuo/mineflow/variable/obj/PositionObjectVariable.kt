package tokyo.aieuo.mineflow.variable.obj

import tokyo.aieuo.mineflow.variable.DummyVariable
import tokyo.aieuo.mineflow.variable.Variable
import cn.nukkit.level.Position

open class PositionObjectVariable<T: Position>(value: T, showString: String? = null): Vector3ObjectVariable<T>(value, showString) {

    override fun getValueFromIndex(index: String): Variable<Any>? {
        super.getValueFromIndex(index).let { if (it !== null) return it }

        return when (index) {
            "position" -> PositionObjectVariable(value)
            "world" -> WorldObjectVariable(value.level, value.level.folderName)
            else -> null
        }
    }

    override fun toString(): String {
        return "${super.toString()},${value.level.folderName}"
    }

    companion object {
        fun getValuesDummy(): Map<String, DummyVariable<DummyVariable.Type>> {
            return Vector3ObjectVariable.getValuesDummy().plus(mapOf(
                "world" to DummyVariable(DummyVariable.Type.WORLD),
            ))
        }
    }
}