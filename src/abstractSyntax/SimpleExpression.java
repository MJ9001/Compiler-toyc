/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;
import codeGen.JVM.*;

public class SimpleExpression extends GrammarDef
{
    public SimpleExpression(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition() {
        //TODO: PRINJTING
        if(parser.tokPrimaryCheck())
        {
            new Term(parser);
        }
        else
        {
            logError("");
        }

        while(parser.tok.getTok().equals(TCscanner.Tokens.ADDOP))
        {
            if(parser.tok.getTok().equals(TCscanner.Tokens.ADDOP))
            {
                //consume addop
                parser.printer.print(" " + parser.tok.getLex() + ", ");
                parser.getNextToken();
                new Term(parser);
                if(parser.tok.getLex() == "+")
                {
                    parser.codegenerator.insert(new CGAdd());
                }
                if(parser.tok.getLex() == "-")
                {
                    parser.codegenerator.insert(new CGSub());
                }
            }
            else
            {
                logError("addop expected");
            }
        }
        return;
    }

    @Override
    public String toString()
    {
        return "reducing SimpleExpression";
    }
}
