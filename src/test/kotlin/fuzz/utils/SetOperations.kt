package fuzz.utils

import com.code_intelligence.jazzer.api.FuzzedDataProvider

private val setOperations = SetOperation::class.sealedSubclasses
private val emptySetOperations = setOperations.filter{ it is EmptyOperation}

fun FuzzedDataProvider.consumeSetOperation(set: kotlin.collections.Set<Int>): SetOperation{
    val op = if (set.isEmpty()) pickValue(emptySetOperations) else pickValue(setOperations)

    return when(op){
        Add::class -> Add(consumeInt())
        Remove::class -> Remove(consumeInt())
        else -> throw IllegalStateException("Unexpected operation: $op")
    }
}

