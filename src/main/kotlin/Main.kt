package ru.nsu.lisitsin

import ru.nsu.lisitsin.task1.solveTask1A
import ru.nsu.lisitsin.task1.solveTask1B
import ru.nsu.lisitsin.task1.solveTask1C
import ru.nsu.lisitsin.task1.solveTask1D
import ru.nsu.lisitsin.task2.solveTask2A
import ru.nsu.lisitsin.task2.solveTask2B
import ru.nsu.lisitsin.task3.solveTask3A
import ru.nsu.lisitsin.task3.solveTask3B

private const val RAW_DATA_1 = "-0.496 0.534 -0.993 -0.159 -0.926 -0.824 -2.284 -1.614 -0.017 -1.762 -1.319 -0.199 0.087 -0.137 -2.077 -0.813 -0.308 -0.848 -1.190 -1.927"

private const val RAW_DATA_2 = "0.023 -2.670 -2.027 0.442 -1.676 -1.317 -0.607 0.168 -0.505 -1.075 0.008 -1.217 -0.445 -1.331 -0.794 -0.039 -1.803 -0.556 -0.564 -1.197 -1.822 -0.279 -2.057 -1.628 -1.728 -1.113 -0.988 -1.028 -1.520 -1.221"

private const val RAW_DATA_3 = "0.277 0.690 0.233 0.799 0.400 0.105 0.542 0.633 0.652 0.953 0.725 0.224 0.769 0.269 0.458 0.191 0.777 0.533 0.658 0.444 0.636 0.332 0.976 0.616 0.083 0.039 0.406 0.002 0.056 0.318"

private const val SIGMA2 = 0.5

private const val ALPHA = -1.0

private const val EPSILON = 0.05

fun main() {
    val valuesTask1 = convertDataToArray("$RAW_DATA_1 $RAW_DATA_2")

    val valuesTask2 = convertDataToArray(RAW_DATA_3)

    val valuesTask3_1 = convertDataToArray(RAW_DATA_1)
    val valuesTask3_2 = convertDataToArray(RAW_DATA_2)

    solveTask1A(valuesTask1, SIGMA2, EPSILON)
    solveTask1B(valuesTask1, EPSILON)
    solveTask1C(valuesTask1, ALPHA, EPSILON)
    solveTask1D(valuesTask1, EPSILON)

    solveTask2A(valuesTask2, EPSILON)
    solveTask2B(valuesTask2, EPSILON)

    solveTask3A(valuesTask3_1, valuesTask3_2, EPSILON)
    solveTask3B(valuesTask3_1, valuesTask3_2, EPSILON)
}
