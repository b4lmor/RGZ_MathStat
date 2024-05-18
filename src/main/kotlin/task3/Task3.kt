package ru.nsu.lisitsin.task3

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ru.nsu.lisitsin.calculateAverage
import ru.nsu.lisitsin.calculateInverseCumulativeProbabilityOfFDistribution
import ru.nsu.lisitsin.calculateInverseCumulativeProbabilityOfTDistribution
import ru.nsu.lisitsin.calculateSampleDispersion
import kotlin.math.sqrt

val log: Logger = LoggerFactory.getLogger("Logger")

fun solveTask3A(sample1: Array<Double>, sample2: Array<Double>, epsilon: Double) {
    log.info("Calculating task3_a ...")

    val dis1 = calculateSampleDispersion(sample1)
    val dis2 = calculateSampleDispersion(sample2)
    val n = sample1.size.toDouble()
    val m = sample2.size.toDouble()
    val eta = (n * (m - 1) * dis1) / (m * (n - 1) * dis2)
    val q1 = calculateInverseCumulativeProbabilityOfFDistribution(epsilon / 2, n, m)
    val q2 = calculateInverseCumulativeProbabilityOfFDistribution(1 - epsilon / 2, n, m)

    log.info("[q1; q2] = [$q1; $q2], eta = $eta")
    log.info("Accept the criterion: {}", q1 < eta && eta < q2)

    log.info("Calculating task3_a ... Done!\n")
}

fun solveTask3B(sample1: Array<Double>, sample2: Array<Double>, epsilon: Double) {
    log.info("Calculating task3_b ...")

    val avg1 = calculateAverage(sample1)
    val avg2 = calculateAverage(sample2)
    val dis1 = calculateSampleDispersion(sample1)
    val dis2 = calculateSampleDispersion(sample2)
    val n = sample1.size.toDouble()
    val m = sample2.size.toDouble()

    val psi = (avg1 - avg2) / (sqrt(1 / n + 1 / m) * sqrt((n * dis1 + m * dis2) / (n + m - 2)))
    val q = calculateInverseCumulativeProbabilityOfTDistribution(1 - epsilon / 2, n + m - 2)

    log.info("[q1; q2] = [{}; {}], psi = {}", -q, q, psi)
    log.info("Accept the criterion: {}", -q < psi && psi < q)

    log.info("Calculating task3_b ... Done!\n")
}
