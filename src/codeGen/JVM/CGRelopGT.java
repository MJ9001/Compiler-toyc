package codeGen.JVM;

public class CGRelopGT extends CGInstruction
{
    void codeGen()
    {
        String temp1 = target.addLabel();
        String temp2 = target.addLabel();
        target.code.add("if_icmple " + temp1);
        target.code.add("sipush 1");
        target.code.add("goto " + temp2);
        target.code.add(temp1 + ":");
        target.code.add("sipush 0");
        target.code.add(temp2 + ":");
    }
}
