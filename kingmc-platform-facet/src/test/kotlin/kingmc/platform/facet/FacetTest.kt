package kingmc.platform.facet

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ExampleFacetImplClass {
    val testFacet = Facet<String> { "test value" }

    fun facetFunction(): String = testFacet.invoke()

    @Test
    fun testFacet() {
        testFacet.after { value -> { value.uppercase() } }
        Assertions.assertTrue(facetFunction() == "TEST VALUE")
    }

}