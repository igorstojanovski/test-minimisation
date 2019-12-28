package co.igorski;

import co.igorski.model.TestEncoding;
import co.igorski.model.TestSuite;
import org.junit.jupiter.api.Test;

class KeyWordBasedMinimiserTest {

    @Test
    protected void shouldMinimiseSuite() {
        KeyWordBasedMinimiser keyWordBasedMinimiser = new KeyWordBasedMinimiser();
        TestSuite testSuite = new TestSuite();

        TestEncoding testEncoding = new TestEncoding();
        testEncoding.setTestId("TC_1");
        testEncoding.setEncoding("<<valid_user.email>>, <<valid_user.pwd>>, <<valid_user>>, access, app, button, field, home, in, is, login, mymovieapp, password, press, registered, screen, shown, the, type, user");
        testSuite.addTestEncoding(testEncoding);

        testEncoding = new TestEncoding();
        testEncoding.setTestId("TC_2");
        testEncoding.setEncoding("<<invalid_user.mail>>, <<invalid_user.pwd>>, <<invalid_user>>, access, button, field, in, is, login, message, mymovieapp, not, password, press, registered, registered”, shown, the, type, user, “user");
        testSuite.addTestEncoding(testEncoding);

        testEncoding = new TestEncoding();
        testEncoding.setTestId("TC_3");
        testEncoding.setEncoding("<<movie>>, <<user>>, <<user>>’s, add, added, app, button, choose, click, created, entry, feature, field, home, in, is, logged, main, manual, message, movie, movies, not, of, press, profile, screen, shown, successfully, table, the, title, type, updated, was, yet");
        testSuite.addTestEncoding(testEncoding);

        testEncoding = new TestEncoding();
        testEncoding.setTestId("TC_4");
        testEncoding.setEncoding("<<movie>>, <<movie_url>>, <<user>>, <<user>>’s, accessible, add, added, app, button, choose, click, created, feature, field, from, home, imdb, import, in, internet, is, logged, main, message, movie, movies, not, of, press, profile, screen, shown, successfully, table, the, title, type, updated, via, was, yet");
        testSuite.addTestEncoding(testEncoding);

        testEncoding = new TestEncoding();
        testEncoding.setTestId("TC_5");
        testEncoding.setEncoding("<<cast.movies>>, <<cast.name>>, <<cast>>, <<user>>, <<user>>’s, actor/actress, add, added, app, button, cast, choose, click, created, entry, feature, field, home, in, is, logged, main, manual, members, message, movie, name, not, of, press, profile, screen, shown, successfully, table, the, title, type, updated, was, yet");
        testSuite.addTestEncoding(testEncoding);

        testEncoding = new TestEncoding();
        testEncoding.setTestId("TC_6");
        testEncoding.setEncoding("<<cast.url>>, <<cast>>, <<user>>, <<user>>’s, actor/actress, add, added, app, button, cast, choose, click, created, feature, field, from, home, imdb, import, in, is, logged, main, members, message, movie, not, of, press, profile, screen, shown, successfully, table, the, type, updated, url, was, yet");
        testSuite.addTestEncoding(testEncoding);

        testEncoding = new TestEncoding();
        testEncoding.setTestId("TC_7");
        testEncoding.setEncoding("<<movie.title>>, <<movie>>, <<user>>, <<user>>’s, a, app, button, by, choose, click, created, delete, deleted, dialog, feature, field, home, in, is, logged, main, message, movie, movies, of, press, profile, screen, shown, shows, successfully, table, the, title, type, updated, was");
        testSuite.addTestEncoding(testEncoding);

        testEncoding = new TestEncoding();
        testEncoding.setTestId("TC_8");
        testEncoding.setEncoding("<<cast.movies>>, <<cast.name>>, <<cast>>, <<user>>, <<user>>’s, a, actor/actress, app, at, button, by, cast, choose, click, created, delete, deleted, dialog, feature, field, from, home, in, is, least, logged, main, member, message, movie, movies, of, one, press, profile, screen, shown, shows, successfully, table, the, title, type, updated, was");
        testSuite.addTestEncoding(testEncoding);

        testEncoding = new TestEncoding();
        testEncoding.setTestId("TC_9");
        testEncoding.setEncoding("<<movie.year>>, <<movies>>, <<user>>, <<user>>’s, a, app, button, click, created, deleted, dialog, field, home, in, is, logged, main, message, movie, movies, of, press, profile, screen, search, shown, shows, successfully, table, the, type, updated, was, year");
        testSuite.addTestEncoding(testEncoding);

        testEncoding = new TestEncoding();
        testEncoding.setTestId("TC_10");
        testEncoding.setEncoding("<<user>>, app, button, click, confirm, home, in, is, logged, login, logout, press, screen, shown, successfully, the");
        testSuite.addTestEncoding(testEncoding);

        keyWordBasedMinimiser.minimise(testSuite);
    }

}