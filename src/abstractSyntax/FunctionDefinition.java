/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;
import codeGen.JVM.*;

public class FunctionDefinition extends GrammarDef {
    public FunctionDefinition(TCparser tcp) {
        super(tcp);
    }

    @Override
    void parseDefinition() {
        if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            new FunctionHeader(parser);
        }
        else
        {
            logError("missing '('");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.LCURLY))
        {
            new FunctionBody(parser);
            //TODO: return to address in register 0
            return;
        }
        else
        {
            logError("missing '{'");
        }
    }

    @Override
    public String toString()
    {
        return "reducing FunctionDefinition";
    }
}
