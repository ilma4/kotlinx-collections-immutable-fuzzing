package fuzz

import com.code_intelligence.jazzer.api.FuzzedDataProvider
import com.code_intelligence.jazzer.junit.FuzzTest
import fuzz.templates.persistentListBubbleSort
import fuzz.utils.MAX_BUFFER_SIZE
import fuzz.utils.MemorisingList
import fuzz.utils.consumeListOperation
import fuzz.utils.forceConsumeInts
import kotlinx.collections.immutable.toPersistentList

class PersistentVectorTests {
    @FuzzTest(maxDuration = "2h")
    fun randomOps(data: FuzzedDataProvider) = with(data) {
        val first = consumeInts(1000).toList()
        val memorisingList = MemorisingList(mutableListOf(first.toPersistentList()))

        val opsNum = consumeInt(0, 1000)
        repeat(opsNum) {
            val op = consumeListOperation(memorisingList.last)
            memorisingList.applyOperation(op)
            if (memorisingList.last.size > MAX_BUFFER_SIZE) {
                memorisingList.history.removeLast()
                memorisingList.operations.removeLast()
            }
        }
        memorisingList.validate()
    }

    @FuzzTest(maxDuration = "2h")
    fun bubbleSort(data: FuzzedDataProvider) = with(data) {
        persistentListBubbleSort(forceConsumeInts(consumeInt(MAX_BUFFER_SIZE + 1, 1000)))
    }
}