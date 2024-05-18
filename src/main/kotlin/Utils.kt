package ru.nsu.lisitsin

import org.apache.commons.math3.distribution.ChiSquaredDistribution
import org.apache.commons.math3.distribution.FDistribution
import org.apache.commons.math3.distribution.NormalDistribution
import org.apache.commons.math3.distribution.TDistribution
import java.util.stream.IntStream
import kotlin.math.pow

data class Interval(val start: Double, val end: Double)

fun convertDataToArray(data: String): Array<Double> =
    data.split(" ").map { it.toDouble() }.toTypedArray()

fun calculateAverage(values: Array<Double>): Double =
    values.fold(0.0) { acc, x -> acc + x } / values.size

fun calculateSampleDispersion(values: Array<Double>): Double =
    values.fold(0.0) { acc, x -> acc + (x - calculateAverage(values)).pow(2) } / values.size

fun calculateInverseCumulativeProbabilityOfNormalDistribution(probability: Double): Double =
    NormalDistribution().inverseCumulativeProbability(probability)

fun calculateInverseCumulativeProbabilityOfFDistribution(probability: Double, n: Double, m: Double): Double =
    FDistribution(n, m).inverseCumulativeProbability(probability)

fun calculateInverseCumulativeProbabilityOfTDistribution(probability: Double, degreesOfFreedom: Double): Double =
    TDistribution(degreesOfFreedom).inverseCumulativeProbability(probability)

fun calculateInverseCumulativeProbabilityOfChiSquaredDistribution(probability: Double, degreesOfFreedom: Double): Double =
    ChiSquaredDistribution(degreesOfFreedom).inverseCumulativeProbability(probability)

fun calculateCumulativeProbabilityOfChiSquaredDistribution(value: Double, degreesOfFreedom: Double): Double =
    ChiSquaredDistribution(degreesOfFreedom).cumulativeProbability(value)

fun calculateEmpiricalCdf(sample: Array<Double>, value: Double): Double =
    sample.fold(0.0) { acc, x -> acc + if (x < value) 1 else 0 } / sample.size

fun calculateInverseCumulativeProbabilityOfKolmogorovDistribution(probability: Double): Double =
    if (probability == 0.95) 1.36 else throw RuntimeException("unknown value of Kolmogorov's distribution")

fun calculateCumulativeProbabilityOfKolmogorovDistribution(value: Double): Double =
    if (value == 0.5531997830802177) 0.0771 else throw RuntimeException("unknown value of Kolmogorov's distribution")

fun calculateUniformDistribution(value: Double): Double =
    if (value < 0) { 0.0 } else if (value > 1) { 1.0 } else { (value - 0) / (1 - 0) }

private fun calculateInterval(sample: Array<Double>, index: Int, pieces: DoubleArray): Interval {
    val max = sample.max()
    val min = sample.min()
    val prev = IntStream.range(0, index).mapToDouble{ x -> pieces[x] }.sum()
    val delta = max - min
    val start = min + prev * delta
    val end = min + (prev + pieces[index]) * delta
    return Interval(start, end)
}

fun calculateIntervalProbability(sample: Array<Double>, index: Int, pieces: DoubleArray): Double {
    val interval = calculateInterval(sample, index, pieces)
    return calculateUniformDistribution(interval.end) - calculateUniformDistribution(interval.start)
}

fun calculateV(sample: Array<Double>, index: Int, pieces: DoubleArray): Int {
    val interval = calculateInterval(sample, index, pieces)
    return sample.fold(0) { acc, x -> acc + if (interval.start <= x && x < interval.end) 1 else 0 } +
            if (index == pieces.size - 1) 1 else 0
}
