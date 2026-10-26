package scratch;

import view.PresupuestoView;

public class TestPresupuestoInit {
    public static void main(String[] args) {
        try {
            System.out.println("Testing PresupuestoView & PresupuestoController initialization...");
            PresupuestoView pv = new PresupuestoView();
            System.out.println("PresupuestoView initialized successfully!");
            System.out.println("cbPeriodo item count: " + pv.cbPeriodo.getItemCount());
            System.out.println("cbPeriodo selected item: " + pv.cbPeriodo.getSelectedItem());
            pv.dispose();
            System.out.println("TEST PASSED.");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
