/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;
import codeGen.JVM.*;

public class FunctionCall extends GrammarDef
{
    public FunctionCall(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        CGFunctionCall fc = new CGFunctionCall(parser.codegenerator.buffer);
        if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            //consume lparen
            parser.getNextToken();
        }
        else
        {
            logError("'(' expected");
        }

        if(parser.tokPrimaryCheck())
        {
            new ActualParameters(parser);
        }
        parser.codegenerator.insert(fc);
        if(parser.tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consume rparen
            parser.getNextToken();
        }
        else
        {
            logError(") expected");
        }
    }

    @Override
    public String toString()
    {
        return "reducing FunctionCall";
    }
}
