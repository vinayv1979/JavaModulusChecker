package com.dambra.paul.moduluschecker;

import com.dambra.paul.moduluschecker.chain.*;
import com.dambra.paul.moduluschecker.valacdosFile.ModulusWeightRows;

final class ModulusCheckingChain {
    /**
     * AtLeastOneWeightRowGate
     *         |
     *         V
     * ExceptionSixGate
     *         |
     *         V
     * ExceptionSevenAccountTransformer
     *         |
     *         V
     * ExceptionEightAccountTransformer
     *         |
     *         V
     * ExceptionTenAccountTransformer
     *         |
     *         V
     * FirstModulusCheckRouter
     *         |
     *         V
     * SecondCheckRequiredGate
     *         |
     *         V
     * ExceptionTwoGate
     *         |
     *         V
     * ExceptionFourteenGate
     *         |
     *         V
     * ExceptionTwoAndNineGate
     *         |
     *         V
     * SecondModulusCheckRouter
     */
    static ModulusChainLink create(ModulusWeightRows weightRows, SortCodeSubstitution sortCodeSubstitution) {
        final SecondModulusCheckRouter secondModulusCheckRouter = new SecondModulusCheckRouter(sortCodeSubstitution);
        final ExceptionTwoAndNineGate exceptionTwoAndNineGate = new ExceptionTwoAndNineGate(secondModulusCheckRouter);
        final ExceptionFourteenGate exceptionFourteenGate = new ExceptionFourteenGate(exceptionTwoAndNineGate);
        final ExceptionTwoGate exceptionTwoGate = new ExceptionTwoGate(exceptionFourteenGate);
        final SecondCheckRequiredGate secondCheckRequiredGate = new SecondCheckRequiredGate(exceptionTwoGate);
        final FirstModulusCheckRouter firstModulusCheckRouter = new FirstModulusCheckRouter(
                sortCodeSubstitution,
                secondCheckRequiredGate
        );
        final ExceptionTenAccountTransformer exceptionTenAccountTransformer
                = new ExceptionTenAccountTransformer(firstModulusCheckRouter);
        final ExceptionEightAccountTransformer exceptionEightAccountTransformer
                = new ExceptionEightAccountTransformer(exceptionTenAccountTransformer);
        final ExceptionSevenAccountTransformer exceptionSevenAccountTransformer
                = new ExceptionSevenAccountTransformer(exceptionEightAccountTransformer);
        final ExceptionSixGate exceptionSixGate = new ExceptionSixGate(exceptionSevenAccountTransformer);
        return new AtLeastOneWeightRowGate(weightRows, exceptionSixGate);
    }
}
