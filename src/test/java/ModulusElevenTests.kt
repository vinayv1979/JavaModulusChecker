

import com.github.pauldambra.moduluschecker.ModulusAlgorithm
import com.github.pauldambra.moduluschecker.ModulusCheckParams
import com.github.pauldambra.moduluschecker.account.BankAccount
import com.github.pauldambra.moduluschecker.chain.checks.ModulusElevenCheck
import com.github.pauldambra.moduluschecker.valacdosFile.WeightRow
import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class ModulusElevenTests {
    @Test
    fun canRunModulusElevenCheck() {
        val sc = "107999"
        val an = "88837491"
        val row = WeightRow(
          ModulusAlgorithm.MOD11,
          listOf(0, 0, 0, 0, 0, 0, 8, 7, 6, 5, 4, 3, 2, 1),
          null
        )

        val params = ModulusCheckParams(BankAccount(sc, an), row)
        val checker = ModulusElevenCheck()

        val result = checker.check(params, ({ obj: ModulusCheckParams -> obj.firstWeightRow!! })(params))

        assert.that(result, equalTo(true))
    }
}
