package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    private static final String[] FORMATS = {
        "dd/MM/yyyy",
        "yyyy-MM-dd",
        "yyyy-MM-dd'T'HH:mm:ssXXX",
        "yyyy-MM-dd HH:mm:ss",
        "MMM dd, yyyy",
        "MMM d, yyyy",
        "dd-MM-yyyy"
    };

    private static final SimpleDateFormat TARGET_FORMAT = new SimpleDateFormat("dd 'de' MMM 'de' yyyy", new Locale("es", "ES"));

    public static String formatLongDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return "";
        }
        
        // Try parsing
        for (String format : FORMATS) {
            try {
                SimpleDateFormat sdf;
                if (format.contains("MMM")) {
                    sdf = new SimpleDateFormat(format, Locale.US);
                } else {
                    sdf = new SimpleDateFormat(format);
                }
                sdf.setLenient(false);
                Date date = sdf.parse(dateStr.trim());
                synchronized (TARGET_FORMAT) {
                    return TARGET_FORMAT.format(date).toLowerCase().replace(".", "");
                }
            } catch (ParseException e) {
                // Try next
            }
        }
        
        // If everything fails, return the original string
        return dateStr;
    }

    public static String formatLongDate(Date date) {
        if (date == null) {
            return "";
        }
        synchronized (TARGET_FORMAT) {
            return TARGET_FORMAT.format(date).toLowerCase().replace(".", "");
        }
    }

    public static final String[] MESES = {
        "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
        "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
    };

    public static String getCurrentPeriodLabel() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        return MESES[cal.get(java.util.Calendar.MONTH)] + " " + cal.get(java.util.Calendar.YEAR);
    }

    public static String getPreviousPeriodLabel() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.MONTH, -1);
        return MESES[cal.get(java.util.Calendar.MONTH)] + " " + cal.get(java.util.Calendar.YEAR);
    }

    public static String[] getPeriodDateRange(String selectionPeriod) {
        if (selectionPeriod == null || selectionPeriod.trim().isEmpty()) {
            java.util.Calendar cal = java.util.Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            cal.set(java.util.Calendar.DAY_OF_MONTH, 1);
            String fIni = sdf.format(cal.getTime());
            cal.set(java.util.Calendar.DAY_OF_MONTH, cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH));
            String fFin = sdf.format(cal.getTime());
            return new String[]{fIni, fFin};
        }

        selectionPeriod = selectionPeriod.trim();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if ("Todo el tiempo".equalsIgnoreCase(selectionPeriod)) {
            return new String[]{"2000-01-01", "2099-12-31"};
        }

        if ("Todo el año".equalsIgnoreCase(selectionPeriod)) {
            int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
            return new String[]{year + "-01-01", year + "-12-31"};
        }

        // Check for year values (e.g., "Año 2025" or "Todo el año 2026")
        if (selectionPeriod.toLowerCase().contains("año") || selectionPeriod.toLowerCase().contains("ano")) {
            java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\d{4}");
            java.util.regex.Matcher m = p.matcher(selectionPeriod);
            if (m.find()) {
                String year = m.group();
                return new String[]{year + "-01-01", year + "-12-31"};
            }
        }

        // Check for monthly labels (e.g. "Junio 2026")
        for (int i = 0; i < MESES.length; i++) {
            if (selectionPeriod.toLowerCase().startsWith(MESES[i].toLowerCase())) {
                java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\d{4}");
                java.util.regex.Matcher m = p.matcher(selectionPeriod);
                if (m.find()) {
                    int year = Integer.parseInt(m.group());
                    java.util.Calendar cal = java.util.Calendar.getInstance();
                    cal.set(java.util.Calendar.YEAR, year);
                    cal.set(java.util.Calendar.MONTH, i);
                    cal.set(java.util.Calendar.DAY_OF_MONTH, 1);
                    String fIni = sdf.format(cal.getTime());
                    cal.set(java.util.Calendar.DAY_OF_MONTH, cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH));
                    String fFin = sdf.format(cal.getTime());
                    return new String[]{fIni, fFin};
                }
            }
        }

        // Fallback
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(java.util.Calendar.DAY_OF_MONTH, 1);
        String fIni = sdf.format(cal.getTime());
        cal.set(java.util.Calendar.DAY_OF_MONTH, cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH));
        String fFin = sdf.format(cal.getTime());
        return new String[]{fIni, fFin};
    }
}

