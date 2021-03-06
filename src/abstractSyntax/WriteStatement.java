/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import compiler.TCGlobals;
import parser.*;
import codeGen.JVM.*;

public class WriteStatement extends GrammarDef
{
    public WriteStatement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        parser.printer.print("writeState(");
        if(parser.tok.getTok().equals(TCscanner.Tokens.WRITE))
        {
            TCGlobals.isWrite = true;
            //consume write
            parser.codegenerator.insert(new CGWriteEnter());
            parser.codegenerator.insert(new CGWriteString());
            parser.getNextToken();
        }
        else
        {
            logError("write expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            //consume lparen
            parser.getNextToken();
            new ActualParameters(parser);
        }

        else
        {
            logError("( expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.RPAREN))
        {
            //consume rparen
            parser.getNextToken();
        }
        else
        {
            logError(") expected");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            //consume semicolon
            parser.codegenerator.insert(new CGWriteOut());
            parser.getNextToken();
            TCGlobals.isWrite = false;
        }
        else
        {
            logError("; expected");
        }
        parser.printer.print(")");
    }

    @Override
    public String toString()
    {
        return "reducing WriteStatement";
    }
}
