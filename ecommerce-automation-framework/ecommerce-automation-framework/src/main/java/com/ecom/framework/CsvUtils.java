package com.ecom.framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/** Minimal CSV reader for TestNG @DataProvider. First line is the header; '#' lines are ignored. */
public final class CsvUtils {
    private CsvUtils() {}

    public static Object[][] read(String classpathResource) {
        InputStream in = CsvUtils.class.getClassLoader().getResourceAsStream(classpathResource);
        if (in == null) throw new IllegalArgumentException("CSV not found: " + classpathResource);

        List<Object[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String line;
            boolean header = true;
            while ((line = br.readLine()) != null) {
                if (header) { header = false; continue; }
                if (line.isBlank() || line.startsWith("#")) continue;
                rows.add(line.split(",", -1));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed reading " + classpathResource, e);
        }
        return rows.toArray(new Object[0][]);
    }
}
