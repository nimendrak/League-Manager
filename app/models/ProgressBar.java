package models;

// reference to the ProgressBar
// https://masterex.github.io/archive/2011/10/23/java-cli-progress-bar.html

public class ProgressBar {
    private StringBuilder progress;

    public ProgressBar() {
        init();
    }

    public void update(int done, int total) {
        char[] workChars = {'|', '/', '-', '\\'};
        String format = "\r%3d%% %s %c";

        int percent = (++done * 100) / total;
        int extrachars = (percent / 2) - this.progress.length();

        while (extrachars-- > 0) {
            progress.append('#');
        }

        System.out.printf(format, percent, progress, workChars[done % workChars.length]);

        if (done == total) {
            System.out.flush();
            System.out.println();
            init();
        }
    }

    private void init() {
        this.progress = new StringBuilder(60);
    }
}
