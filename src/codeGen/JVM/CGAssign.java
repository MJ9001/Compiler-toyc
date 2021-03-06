package codeGen.JVM;

import compiler.TCGlobals;
import symTable.Symbol;
import symTable.SymbolAlreadyDeclared;

public class CGAssign extends CGInstruction {
    Symbol LAS;
    boolean gottenSym = false;
    boolean reload;
    public CGAssign(boolean reload)
    {
        this.reload = reload;
    }
    void codeGen()
    {
        if(!gottenSym) {
            LAS = target.symtable.getCurrentScope().getLastAccessedSym();
            gottenSym = true;
            target.code.remove(target.code.size()-1);
            //if(!TCGlobals.lazyCheck)
            //    target.code.add("pop");
        }
        else {
            try {
                if (LAS.getName().contains("GLOBAL")) {
                    target.code.add("putstatic " + TCGlobals.className + "/" + LAS.getName().replace("GLOBAL", "") + " I");
                } else {
                    target.code.add("istore " + LAS.getOffset());
                    if(reload)
                        target.code.add("iload " + LAS.getOffset());
                }
            }
            catch(NullPointerException e)
            {
                e.printStackTrace();
                //target.code.add("istore " + LAS.getOffset());
            }
        }
    }
}
