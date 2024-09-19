package fuzz

import com.code_intelligence.jazzer.api.FuzzedDataProvider
import com.code_intelligence.jazzer.junit.FuzzTest
import fuzz.templates.persistentMapRandomOps
import fuzz.utils.validateBuilder
import fuzz.utils.validateInvariants
import fuzz.utils.validateReplay
import fuzz.utils.validateReverse
import kotlinx.collections.immutable.toPersistentHashMap

class PersistentHashMapTests {
    @FuzzTest(maxDuration = "2h")
    fun randomOpsValidateInvariants(data: FuzzedDataProvider) {
        validateInvariants = true
        persistentMapRandomOps(data) { toPersistentHashMap() }
    }

    @FuzzTest(maxDuration = "2h")
    fun randomOpsValidateReverse(data: FuzzedDataProvider) {
        validateReverse = true
        persistentMapRandomOps(data) { toPersistentHashMap() }
    }

    @FuzzTest(maxDuration = "2h")
    fun randomOpsValidateReplay(data: FuzzedDataProvider) {
        validateReplay = true
        persistentMapRandomOps(data) { toPersistentHashMap() }
    }

    @FuzzTest(maxDuration = "2h")
    fun randomOpsValidateBuilder(data: FuzzedDataProvider) {
        validateBuilder = true
        persistentMapRandomOps(data) { toPersistentHashMap() }
    }
}