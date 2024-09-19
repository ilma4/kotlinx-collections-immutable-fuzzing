package fuzz

import com.code_intelligence.jazzer.api.FuzzedDataProvider
import com.code_intelligence.jazzer.junit.FuzzTest
import fuzz.utils.consumeMapOperation
import kotlinx.collections.immutable.toPersistentMap
import org.junit.jupiter.api.Assertions.assertEquals

class PersistentOrderedMapBuilderTests {
    @FuzzTest(maxDuration = "2h")
    fun randomOpsVsOrderedMap(data: FuzzedDataProvider) {
        val firstMap = data.consumeInts(1000)
            .asSequence().chunked(2).filter { it.size == 2 }
            .map { list -> list[0] to list[1] }
            .toMap()

        val builder = firstMap.toPersistentMap().builder()
        val hashMap = firstMap.toMutableMap()

        assertEquals(hashMap, builder)

        val opsNum = data.consumeInt(10, 1000)
        repeat(opsNum) {
            val op = data.consumeMapOperation(builder)
            op.apply(builder)
            op.apply(hashMap)
            assertEquals(hashMap, builder)
        }
    }
}