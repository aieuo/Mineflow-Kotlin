package tokyo.aieuo.mineflow.trigger.event

import cn.nukkit.entity.Entity
import cn.nukkit.event.Event
import cn.nukkit.event.inventory.InventoryPickupItemEvent
import cn.nukkit.inventory.PlayerInventory
import tokyo.aieuo.mineflow.utils.DummyVariableMap
import tokyo.aieuo.mineflow.utils.VariableMap
import tokyo.aieuo.mineflow.variable.DefaultVariables
import tokyo.aieuo.mineflow.variable.DummyVariable
import tokyo.aieuo.mineflow.variable.Variable
import tokyo.aieuo.mineflow.variable.obj.ItemObjectVariable

class InventoryPickupItemEventTrigger(subKey: String = "") : EventTrigger(InventoryPickupItemEvent::class, subKey) {

    override fun getTargetEntity(event: Event): Entity? = getTargetEntity(event as InventoryPickupItemEvent)

    override fun getVariables(event: Event): VariableMap = getVariables(event as InventoryPickupItemEvent)

    fun getTargetEntity(event: InventoryPickupItemEvent): Entity? {
        return event.inventory.let { if (it is PlayerInventory) it.holder else null }
    }

    fun getVariables(event: InventoryPickupItemEvent): VariableMap {
        var variables = mapOf<String, Variable<Any>>()
        val inventory = event.inventory
        if (inventory is PlayerInventory) {
            variables = variables + DefaultVariables.getEntityVariables(inventory.holder)
        }
        return variables + mapOf(
            "item" to ItemObjectVariable(event.item.item),
        )
    }

    override fun getVariablesDummy(): DummyVariableMap {
        return mapOf(
            "target" to DummyVariable(DummyVariable.Type.PLAYER),
            "item" to DummyVariable(DummyVariable.Type.ITEM),
        )
    }
}