package fuzz

import com.code_intelligence.jazzer.api.FuzzedDataProvider
import com.code_intelligence.jazzer.junit.FuzzTest
import fuzz.utils.consumeSetOperation
import kotlinx.collections.immutable.toPersistentSet
import org.junit.jupiter.api.Assertions.assertEquals

class PersistentOrderedSetBuilderTests {
    @FuzzTest(maxDuration = "2h")
    fun randomOpsVsOrderedMap(data: FuzzedDataProvider) {
        val firstSet = data.consumeInts(1000).toSet()

        val builder = firstSet.toPersistentSet().builder()
        val hashMap = firstSet.toMutableSet()

        assertEquals(hashMap, builder)

        val opsNum = data.consumeInt(10, 1000)
        repeat(opsNum) {
            val op = data.consumeSetOperation(builder)
            op.apply(builder)
            op.apply(hashMap)
            assertEquals(hashMap, builder)
        }
    }
}