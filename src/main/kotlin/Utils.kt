package ru.nsu.lisitsin

import org.apache.commons.math3.distribution.ChiSquaredDistribution
import org.apache.commons.math3.distribution.NormalDistribution
import org.apache.commons.math3.distribution.TDistribution
import kotlin.math.pow

data class Interval(val start: Double, val end: Double)

fun convertDataToArray(data: String): Array<Double> =
    data.split(" ").map { it.toDouble() }.toTypedArray()

fun calculateAverage(values: Array<Double>): Double =
    values.fold(0.0) { acc, x -> acc + x } / values.size

fun calculateSampleDispersion(values: Array<Double>): Double =
    values.fold(0.0) { acc, x -> acc + (x - calculateAverage(values)).pow(2) } / values.size

fun calculateUndisplacedSampleDispersion(values: Array<Double>): Double =
    values.fold(0.0) { acc, x -> acc + (x - calculateAverage(values)).pow(2) } / (values.size - 1)

fun calculateInverseCumulativeProbabilityOfNormalDistribution(probability: Double): Double =
    NormalDistribution().inverseCumulativeProbability(probability)

fun calculateInverseCumulativeProbabilityOfTDistribution(probability: Double, degreesOfFreedom: Double): Double =
    TDistribution(degreesOfFreedom).inverseCumulativeProbability(probability)

fun calculateInverseCumulativeProbabilityOfChiSquaredDistribution(probability: Double, degreesOfFreedom: Double): Double =
    ChiSquaredDistribution(degreesOfFreedom).inverseCumulativeProbability(probability)
