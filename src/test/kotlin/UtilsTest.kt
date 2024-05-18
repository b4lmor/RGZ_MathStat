import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import ru.nsu.lisitsin.calculateInverseCumulativeProbabilityOfNormalDistribution
import ru.nsu.lisitsin.calculateInverseCumulativeProbabilityOfTDistribution

const val EPSILON = 0.0001

class UtilsTest {

    @Test
    fun calculateInverseCumulativeProbabilityOfNormalDistributionTest() {
        val eps = 0.05
        val tau1 = calculateInverseCumulativeProbabilityOfNormalDistribution(eps)
        val tau2 = calculateInverseCumulativeProbabilityOfNormalDistribution(1 - eps)
        assertTrue(tau1 - -tau2 < EPSILON)
    }

    @Test
    fun calculateInverseCumulativeProbabilityOfTDistributionTest() {
        val eps = 0.05
        val degreesOfFreedom = 49.0
        val tau1 = calculateInverseCumulativeProbabilityOfTDistribution(eps, degreesOfFreedom)
        val tau2 = calculateInverseCumulativeProbabilityOfTDistribution(1 - eps, degreesOfFreedom)
        assertTrue(tau1 - -tau2 < EPSILON)
    }

}