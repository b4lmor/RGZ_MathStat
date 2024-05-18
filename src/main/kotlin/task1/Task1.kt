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
    val tau = calculateInverseCumulativeProbabilityOfNormalDistribution(1 - epsilon / 2)

//    log.info("average = $average")
//    log.info("tau = $tau")

    val aStart = average - tau * sqrt(sigma2 / sample.size)
    val aEnd = average + tau * sqrt(sigma2 / sample.size)

    log.info("a- = $aStart")
    log.info("a+ = $aEnd")

    log.info("Calculating task1_a ... Done!\n")

    return Interval(aStart, aEnd)
}

fun solveTask1B(sample: Array<Double>, epsilon: Double): Interval {
    log.info("Calculating task1_b ...")

    val average = calculateAverage(sample)
    val q = calculateInverseCumulativeProbabilityOfTDistribution(
        1 - epsilon / 2,
        (sample.size - 1).toDouble()
    )
    val sampleDispersion = calculateSampleDispersion(sample)

//    log.info("average = $average")
//    log.info("q = $q")
//    log.info("S0 = $sampleDispersion")

    val aStart = average - q * sqrt(sampleDispersion / (sample.size - 1))
    val aEnd = average + q * sqrt(sampleDispersion / (sample.size - 1))

    log.info("a- = $aStart")
    log.info("a+ = $aEnd")

    log.info("Calculating task1_b ... Done!\n")

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

//    log.info("q1 = $q1")
//    log.info("q2 = $q2")

    val sumOfDeltas = sample.fold(0.0) { acc, x -> acc + (x - alpha).pow(2) }

    val sigma2Start = sumOfDeltas / q2
    val sigma2End = sumOfDeltas / q1

    log.info("sigma2- = $sigma2Start")
    log.info("sigma2+ = $sigma2End")

    log.info("Calculating task1_c ... Done!\n")

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

    val sampleDispersion = calculateSampleDispersion(sample)

//    log.info("q1 = $q1")
//    log.info("q2 = $q2")
//    log.info("S0 = $sampleDispersion")

    val sigma2Start = sample.size * sampleDispersion / q2
    val sigma2End = sample.size * sampleDispersion / q1

    log.info("sigma2- = $sigma2Start")
    log.info("sigma2+ = $sigma2End")

    log.info("Calculating task1_d ... Done!\n")

    return Interval(sigma2Start, sigma2End)
}
