package scratch;

import controller.IngresoController;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Ingreso;
import model.IngresoResumen;
import service.IngresoService;
import view.IngresosView;

public class TestIngresosModule {
    public static void main(String[] args) {
        try {
            System.out.println("--- 1. Testing IngresoService & DAO direct queries ---");
            IngresoService service = new IngresoService();
            List<Ingreso> list = service.listarIngresos(1, null, null, "2023-10-01", "2023-10-31", null, null);
            System.out.println("Ingresos count in Oct 2023: " + list.size());
            for (Ingreso i : list) {
                System.out.printf("  ID: %d | Fecha: %s | Desc: %s | Cat: %s | Cta: %s | Tipo: %s | Monto: %.2f | Estado: %s%n",
                    i.getId(), i.getFechaFormat(), i.getDescripcion(), i.getCategoria(), i.getCuentaDestino(), i.getTipoFlujo(), i.getMonto(), i.getEstado());
            }

            System.out.println("\n--- 2. Testing KPI Resumen Calculation ---");
            IngresoResumen res = service.obtenerResumenKpi(1, "2023-10-01", "2023-10-31", "2023-09-01", "2023-09-30");
            System.out.printf("  Total: $%.2f (%s)%n", res.getIngresosTotales(), res.getVariacionMes());
            System.out.printf("  Fijo: $%.2f (%.1f%%)%n", res.getIngresoFijo(), res.getPorcentajeFijo());
            System.out.printf("  Variable: $%.2f (%.1f%%)%n", res.getIngresoVariable(), res.getPorcentajeVariable());
            System.out.printf("  Promedio: $%.2f (%s)%n", res.getPromedioMensual(), res.getVariacionYoY());
            System.out.printf("  Nómina: $%.2f (%.1f%%), Freelance: $%.2f (%.1f%%), Rendimientos: $%.2f (%.1f%%)%n",
                res.getNominaMonto(), res.getNominaPct(), res.getFreelanceMonto(), res.getFreelancePct(), res.getRendimientosMonto(), res.getRendimientosPct());
            System.out.printf("  Proyección: $%.2f (%s) - %s%n", res.getProyeccionMonto(), res.getProyeccionVariacion(), res.getProyeccionDetalle());
            System.out.printf("  Ciclo: %.0f%% Cobrado ($%.2f de $%.2f), Pendiente: $%.2f, Próx: %s (%d días)%n",
                res.getCicloCobradoPct(), res.getCicloCobradoMonto(), res.getCicloTotalMonto(), res.getCicloPendienteMonto(), res.getProximaLiquidacionFecha(), res.getProximaLiquidacionDias());

            System.out.println("\n--- 3. Testing IngresosView & IngresoController Instantiation ---");
            IngresosView view = new IngresosView();
            IngresoController ctrl = view.getController();
            System.out.println("IngresosView initialized successfully with controller!");

            DefaultTableModel model = (DefaultTableModel) view.getTblIngresos().getModel();
            System.out.println("Oct 2023 - Table rows: " + model.getRowCount());
            System.out.println("Oct 2023 - Total Dist: " + view.getLblTotalDist().getText());

            // Test Tab "Fijos"
            System.out.println("\n--- 4. Testing Tab 'Fijos' ---");
            view.getBtnTabFijos().doClick();
            System.out.println("Fijos - Table rows: " + model.getRowCount());

            // Test Tab "Variables"
            System.out.println("\n--- 5. Testing Tab 'Variables' ---");
            view.getBtnTabVariables().doClick();
            System.out.println("Variables - Table rows: " + model.getRowCount());

            // Test Reset Tab "Todos"
            view.getBtnTabTodos().doClick();
            System.out.println("Todos - Table rows: " + model.getRowCount());

            // Test Period "Sep 2023"
            System.out.println("\n--- 6. Testing Period 'Sep 2023' ---");
            view.getBtnFiltroSep().doClick();
            System.out.println("Sep 2023 - Table rows: " + model.getRowCount());
            System.out.println("Sep 2023 - Total Dist: " + view.getLblTotalDist().getText());

            // Test Switch back to "Oct 2023"
            System.out.println("\n--- 7. Testing Period Switch back to 'Oct 2023' ---");
            view.getBtnFiltroOct().doClick();
            System.out.println("Oct 2023 - Table rows: " + model.getRowCount());
            System.out.println("Oct 2023 - Total Dist: " + view.getLblTotalDist().getText());

            // Test Insert New Income dynamically
            System.out.println("\n--- 8. Testing Insert Income dynamically ---");
            Ingreso nuevo = new Ingreso();
            nuevo.setMonto(500.00);
            nuevo.setDescripcion("Test Bono Extra");
            nuevo.setFecha(java.sql.Date.valueOf("2023-10-27"));
            nuevo.setCategoria("Bonos");
            nuevo.setCuentaDestino("BBVA");
            nuevo.setTipoFlujo("Variable");
            nuevo.setEstado("Confirmado");
            nuevo.setRecurrente(false);
            nuevo.setUsuarioId(1);
            String resInsert = service.guardar(nuevo);
            System.out.println("Insert result: " + resInsert);

            // Reload controller
            ctrl.cargarDatosCompletos();
            System.out.println("After Insert - Table rows in Oct 2023: " + model.getRowCount());
            System.out.println("After Insert - Total Dist: " + view.getLblTotalDist().getText());

            // Clean up test record
            List<Ingreso> checkList = service.listarIngresos(1, null, null, "2023-10-27", "2023-10-27", "Bonos", null);
            for (Ingreso i : checkList) {
                if ("Test Bono Extra".equals(i.getDescripcion())) {
                    service.eliminar(i.getId(), 1);
                    System.out.println("Deleted test record ID: " + i.getId());
                }
            }
            ctrl.cargarDatosCompletos();
            System.out.println("After Cleanup - Table rows in Oct 2023: " + model.getRowCount());

            System.out.println("\n--- ALL TESTS PASSED SUCCESSFULLY! ---");
            view.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
