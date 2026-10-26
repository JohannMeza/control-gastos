package scratch;

import view.DashboardView;

public class TestDashboardInit {
    public static void main(String[] args) {
        try {
            System.out.println("Testing DashboardView & DashboardController initialization...");
            DashboardView dv = new DashboardView();
            System.out.println("DashboardView initialized successfully without any IllegalArgumentException!");
            dv.dispose();
            System.out.println("Test PASSED.");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
