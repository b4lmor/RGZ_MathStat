package ru.nsu.lisitsin.task2

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ru.nsu.lisitsin.*
import java.util.*
import java.util.stream.IntStream
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

val log: Logger = LoggerFactory.getLogger("Logger")

fun solveTask2A(sample: Array<Double>, epsilon: Double) {
    log.info("Calculating task2_a ...")

    val maxKolmogorovDistance = Arrays.stream(sample)
        .mapToDouble { abs(calculateEmpiricalCdf(sample, it) - calculateUniformDistribution(it)) }
        .max()
        .orElse(0.0) * sqrt(sample.size.toDouble())
    val q = calculateInverseCumulativeProbabilityOfKolmogorovDistribution(1 - epsilon)
    val pValue = 1 - calculateCumulativeProbabilityOfKolmogorovDistribution(maxKolmogorovDistance)

    log.info("sqrt(n) * Dn = {}, q = {}", maxKolmogorovDistance, q)
    log.info("Accept the criterion: {}", maxKolmogorovDistance < q)
    log.info("p-value: eps* = {}", pValue)

    log.info("Calculating task2_a ... Done!\n")
}

fun solveTask2B(sample: Array<Double>, epsilon: Double) {
    log.info("Calculating task2_b ...")

    val pieces = doubleArrayOf(0.2, 0.2, 0.1, 0.5)
    require(pieces.sum() == 1.0) { "sum of pieces must be equal to 1" }

    val chiSquared = IntStream.range(0, pieces.size).mapToDouble{
        (calculateV(sample, it, pieces) - sample.size * calculateIntervalProbability(sample, it, pieces)).pow(2) /
                (sample.size * calculateIntervalProbability(sample, it, pieces))
    }.sum()
    val c = calculateInverseCumulativeProbabilityOfChiSquaredDistribution(
        1 - epsilon,
        (pieces.size - 1).toDouble()
    )
    val pValue = 1 - calculateCumulativeProbabilityOfChiSquaredDistribution(chiSquared, (pieces.size - 1).toDouble())

    log.info("ChiSquared = {}, X(c) = 0.95 => c = {}", chiSquared, c)
    log.info("Accept the criterion: {}", chiSquared < c)
    log.info("p-value: eps* = {}", pValue)

    log.info("Calculating task2_b ... Done!\n")
}
