package codeGen.JVM;

public class CGWrite extends CGInstruction {


    void codeGen() {
        target.code.add("getstatic java/lang / System / out Ljava / io / PrintStream;");
        target.code.add("invokevirtual java/io / PrintStream / println(I) V)")
    }
}
