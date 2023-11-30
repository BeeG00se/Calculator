import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Test {
    I (1, "I"), II(2, "II"), III(3, "III"),
    IV(4, "IV"), V(5, "V"), VI(6, "VI"),
    VII(7, "VII"), VIII(8, "VIII"), IX(9, "IX"),
    X(10, "X"), XL(40, "XL"), L(50, "L"),
    XC(90, "XC"), C(100, "C");

    private int arabskayaNoch;
    private String divnijVostok;

    Test(int arabskayaNoch,  String divnijVostok) {
        this.arabskayaNoch = arabskayaNoch;
        this.divnijVostok = divnijVostok;
    }
    public int getArabskayaNoch() {
        return arabskayaNoch;
    }

    public String getDivnijVostok() {
        return divnijVostok;
    }
    public static List<Test> getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((Test e) -> e.arabskayaNoch).reversed())
                .collect(Collectors.toList());
    }

    public static Test fromString(String text) {
        for (Test b : Test.values()) {
            if (b.divnijVostok.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
