/* This file was generated by SableCC (http://www.sablecc.org/). */

package legacy.commons.calculator.parser;

import legacy.commons.calculator.analysis.AnalysisAdapter;
import legacy.commons.calculator.node.EOF;
import legacy.commons.calculator.node.TDot;
import legacy.commons.calculator.node.TMinus;
import legacy.commons.calculator.node.TNum;
import legacy.commons.calculator.node.TPlus;
import legacy.commons.calculator.node.TRcb;
import legacy.commons.calculator.node.TRob;
import legacy.commons.calculator.node.TSlash;
import legacy.commons.calculator.node.TStar;

class TokenIndex extends AnalysisAdapter
{
    int index;

    @Override
    public void caseTRob(@SuppressWarnings("unused") TRob node)
    {
        this.index = 0;
    }

    @Override
    public void caseTRcb(@SuppressWarnings("unused") TRcb node)
    {
        this.index = 1;
    }

    @Override
    public void caseTPlus(@SuppressWarnings("unused") TPlus node)
    {
        this.index = 2;
    }

    @Override
    public void caseTMinus(@SuppressWarnings("unused") TMinus node)
    {
        this.index = 3;
    }

    @Override
    public void caseTStar(@SuppressWarnings("unused") TStar node)
    {
        this.index = 4;
    }

    @Override
    public void caseTSlash(@SuppressWarnings("unused") TSlash node)
    {
        this.index = 5;
    }

    @Override
    public void caseTNum(@SuppressWarnings("unused") TNum node)
    {
        this.index = 6;
    }

    @Override
    public void caseTDot(@SuppressWarnings("unused") TDot node)
    {
        this.index = 7;
    }

    @Override
    public void caseEOF(@SuppressWarnings("unused") EOF node)
    {
        this.index = 8;
    }
}