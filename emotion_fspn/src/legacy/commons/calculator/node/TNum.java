/* This file was generated by SableCC (http://www.sablecc.org/). */

package legacy.commons.calculator.node;

import legacy.commons.calculator.analysis.Analysis;

@SuppressWarnings("nls")
public final class TNum extends Token
{
    public TNum(String text)
    {
        setText(text);
    }

    public TNum(String text, int line, int pos)
    {
        setText(text);
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TNum(getText(), getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTNum(this);
    }
}