package fuzz

import com.code_intelligence.jazzer.api.FuzzedDataProvider
import com.code_intelligence.jazzer.junit.FuzzTest
import fuzz.templates.persistentSetRandomOps
import fuzz.utils.validateBuilder
import fuzz.utils.validateInvariants
import fuzz.utils.validateReplay
import kotlinx.collections.immutable.toPersistentHashSet

class PersistentHashSetTests {
    @FuzzTest(maxDuration = "2h")
    fun randomOpsValidateInvariants(data: FuzzedDataProvider) {
        validateInvariants = true
        persistentSetRandomOps(data) { toPersistentHashSet() }
    }

    @FuzzTest(maxDuration = "2h")
    fun randomOpsValidateReplay(data: FuzzedDataProvider) {
        validateReplay = true
        persistentSetRandomOps(data) { toPersistentHashSet() }
    }

    @FuzzTest(maxDuration = "2h")
    fun randomOpsValidateBuilder(data: FuzzedDataProvider) {
        validateBuilder = true
        persistentSetRandomOps(data) { toPersistentHashSet() }
    }
}