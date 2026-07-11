//GameLogger Class:-

public class GameLogger {

    // Attributes:-
    private static StringBuilder buffer = new StringBuilder();
    private static Runnable onLog;

    // Methods:-
    // Log Method:-
    public static void log(String msg) {

        // Append the message to the buffer and trigger the onLog callback:
        buffer.append(msg).append("\n");
        if (onLog != null) onLog.run();
    }

    // Get and Clear Method:-
    public static String getAndClear() {

        // Get the current log and clear the buffer:
        String s = buffer.toString();
        buffer.setLength(0);
        return s;
    }

    // Set On Log Method:-
    public static void setOnLog(Runnable r) {
        onLog = r;
    }
}
//End of GameLogger Class.