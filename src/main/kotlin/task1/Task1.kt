package ru.nsu.lisitsin.task1

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ru.nsu.lisitsin.*
import kotlin.math.pow
import kotlin.math.sqrt

val log: Logger = LoggerFactory.getLogger("Logger")

fun solveTask1A(sample: Array<Double>, sigma2: Double, epsilon: Double): Interval {
    log.info("Calculating task1_a ...")

    val average = calculateAverage(sample)
    val tau = calculateInverseCumulativeProbabilityOfNormalDistribution(1 - epsilon)

    log.info("average = $average")
    log.info("tau = $tau")

    val aStart = average - tau * sqrt(sigma2 / sample.size)
    val aEnd = average + tau * sqrt(sigma2 / sample.size)

    log.info("a- = $aStart")
    log.info("a+ = $aEnd")

    log.info("Calculating task1_a ... Done!")

    return Interval(aStart, aEnd)
}

fun solveTask1B(sample: Array<Double>, epsilon: Double): Interval {
    log.info("Calculating task1_b ...")

    val average = calculateAverage(sample)
    val q = calculateInverseCumulativeProbabilityOfTDistribution(
        1 - epsilon,
        (sample.size - 1).toDouble()
    )
    val undisplacedDispersion = calculateUndisplacedSampleDispersion(sample)

    log.info("average = $average")
    log.info("q = $q")
    log.info("S0 = $undisplacedDispersion")

    val aStart = average - q * sqrt(undisplacedDispersion / (sample.size - 1))
    val aEnd = average + q * sqrt(undisplacedDispersion / (sample.size - 1))

    log.info("a- = $aStart")
    log.info("a+ = $aEnd")

    log.info("Calculating task1_b ... Done!")

    return Interval(aStart, aEnd)
}

fun solveTask1C(sample: Array<Double>, alpha: Double, epsilon: Double): Interval {
    log.info("Calculating task1_c ...")

    val q1 = calculateInverseCumulativeProbabilityOfChiSquaredDistribution(
        epsilon / 2,
        sample.size.toDouble()
    )
    val q2 = calculateInverseCumulativeProbabilityOfChiSquaredDistribution(
        1 - epsilon / 2,
        sample.size.toDouble()
    )

    log.info("q1 = $q1")
    log.info("q2 = $q2")

    val sumOfDeltas = sample.fold(0.0) { acc, x -> acc + (x - alpha).pow(2) }

    val sigma2Start = sumOfDeltas / q2
    val sigma2End = sumOfDeltas / q1

    log.info("sigma2- = $sigma2Start")
    log.info("sigma2+ = $sigma2End")

    log.info("Calculating task1_c ... Done!")

    return Interval(sigma2Start, sigma2End)
}

fun solveTask1D(sample: Array<Double>, epsilon: Double): Interval {
    log.info("Calculating task1_d ...")

    val q1 = calculateInverseCumulativeProbabilityOfChiSquaredDistribution(
        epsilon / 2,
        sample.size.toDouble() - 1
    )
    val q2 = calculateInverseCumulativeProbabilityOfChiSquaredDistribution(
        1 - epsilon / 2,
        sample.size.toDouble() - 1
    )

    log.info("q1 = $q1")
    log.info("q2 = $q2")

    val undisplacedDispersion = calculateUndisplacedSampleDispersion(sample)
    log.info("S0 = $undisplacedDispersion")

    val sigma2Start = sample.size * undisplacedDispersion / q2
    val sigma2End = sample.size * undisplacedDispersion / q1

    log.info("sigma2- = $sigma2Start")
    log.info("sigma2+ = $sigma2End")

    log.info("Calculating task1_d ... Done!")

    return Interval(sigma2Start, sigma2End)
}
