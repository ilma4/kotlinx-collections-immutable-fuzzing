package fuzz

import com.code_intelligence.jazzer.api.FuzzedDataProvider
import com.code_intelligence.jazzer.junit.FuzzTest
import fuzz.templates.persistentListBubbleSort
import fuzz.utils.MAX_BUFFER_SIZE
import fuzz.utils.MemorisingList
import fuzz.utils.consumeListOperation
import fuzz.utils.initSize
import kotlinx.collections.immutable.toPersistentList

class SmallPersistentVectorTests {
    @FuzzTest(maxDuration = "2h")
    fun randomOps(data: FuzzedDataProvider) {
        val first = data.consumeInts(initSize).toList()
        val memorisingList = MemorisingList(mutableListOf(first.toPersistentList()))

        memorisingList.last.iterator()

        val opsNum = data.consumeInt(10, 1000)
        repeat(opsNum) {
            val op = data.consumeListOperation(memorisingList.last)
            memorisingList.applyOperation(op)
            if (memorisingList.last.size <= MAX_BUFFER_SIZE) {
                memorisingList.history.removeLast()
                memorisingList.operations.removeLast()
            }
        }
        memorisingList.validate()
    }

    @FuzzTest(maxDuration = "2h")
    fun bubbleSort(data: FuzzedDataProvider) = with(data) {
        persistentListBubbleSort(consumeInts(MAX_BUFFER_SIZE))
    }
}