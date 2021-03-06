package me.syari.ss.battle.equipment

import org.bukkit.Color

/**
 * 属性
 * (火・水・木・聖・闇, 無)
 */
enum class ElementType(
    val color: Color,
    val display: String
) {
    Fire(Color.RED, "&c火"),
    Water(Color.AQUA, "&b水"),
    Wood(Color.LIME, "&a木"),
    Holy(Color.YELLOW, "&e聖"),
    Dark(Color.PURPLE, "&d闇"),
    None(Color.GRAY, "&7無");

    companion object {
        private val elementEffect by lazy {
            mapOf(
                Fire to Water to 1.5F,
                Fire to Wood to 0.7F,
                Water to Wood to 1.5F,
                Water to Fire to 0.7F,
                Wood to Fire to 1.5F,
                Wood to Water to 0.7F,
                Dark to Fire to 0.8F,
                Dark to Water to 0.8F,
                Dark to Wood to 0.8F,
                Dark to Holy to 2.0F,
                Holy to Dark to 0.5F
            )
        }

        /**
         * 防御時の属性相性を取得します
         * @param attack 攻撃側の属性
         * @param defense 防御側の属性
         */
        fun getDefenseRate(
            attack: ElementType,
            defense: ElementType
        ): Float {
            return elementEffect.getOrDefault(attack to defense, 1.0F)
        }
    }
}