import os
import re

views_dir = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view"

# Define the expected sidebar components in each view class
configs = {
    "DashboardView": {
        "active": "pnlDashboard",
        "menu": {
            "pnlGasto": "pnlGastoMouseClicked",
            "pnlPresupuesto": "pnlPresupuestoMouseClicked",
            "pnlAhorros": "pnlAhorrosMouseClicked",
            "pnlDeudas": "pnlDeudasMouseClicked",
            "pnlUsuarios": "pnlUsuariosMouseClicked"
        }
    },
    "SystemView": {
        "active": "pnlGasto",
        "menu": {
            "jPanelProducts": "jPanelProductsMouseClicked",
            "pnlPresupuesto": "pnlPresupuestoMouseClicked",
            "jPanelEmployes": "jPanelEmployesMouseClicked",
            "jPanelSupplimers": "jPanelSupplimersMouseClicked",
            "jPanelCategories": "jPanelCategoriesMouseClicked"
        }
    },
    "PresupuestoView": {
        "active": "jPanelCustomers",
        "menu": {
            "jPanelProducts": "jPanelProductsMouseClicked",
            "pnlGasto": "pnlGastoMouseClicked",
            "jPanelEmployes": "jPanelEmployesMouseClicked",
            "jPanelSupplimers": "jPanelSupplimersMouseClicked",
            "jPanelCategories": "jPanelCategoriesMouseClicked"
        }
    },
    "AhorrosView": {
        "active": "jPanelEmployes",
        "menu": {
            "jPanelProducts": "jPanelProductsMouseClicked",
            "pnlGasto": "pnlGastoMouseClicked",
            "jPanelCustomers": "jPanelCustomersMouseClicked",
            "jPanelSupplimers": "jPanelSupplimersMouseClicked",
            "jPanelCategories": "jPanelCategoriesMouseClicked"
        }
    },
    "DeudasView": {
        "active": "jPanelSupplimers",
        "menu": {
            "jPanelProducts": "jPanelProductsMouseClicked",
            "pnlGasto": "pnlGastoMouseClicked",
            "jPanelCustomers": "jPanelCustomersMouseClicked",
            "jPanelEmployes": "jPanelEmployesMouseClicked",
            "jPanelCategories": "jPanelCategoriesMouseClicked"
        }
    },
    "UsuariosView": {
        "active": "jPanelCategories",
        "menu": {
            "jPanelProducts": "jPanelProductsMouseClicked",
            "pnlGasto": "pnlGastoMouseClicked",
            "jPanelCustomers": "jPanelCustomersMouseClicked",
            "jPanelEmployes": "jPanelEmployesMouseClicked",
            "jPanelSupplimers": "jPanelSupplimersMouseClicked"
        }
    }
}

for view_name, config in configs.items():
    java_path = os.path.join(views_dir, view_name + ".java")
    if not os.path.exists(java_path):
        print(f"File not found: {java_path}")
        continue
        
    with open(java_path, "r", encoding="utf-8") as f:
        content = f.read()
        
    print(f"\n=== Checking {view_name} ===")
    for comp, handler in config["menu"].items():
        # Check if listener registration exists
        reg_exists = f"{comp}.addMouseListener" in content
        # Check if handler method exists
        handler_exists = f"void {handler}(" in content
        
        if not reg_exists:
            print(f"  [MISSING REGISTRATION] Component '{comp}' has no addMouseListener (should call '{handler}')")
        if not handler_exists:
            print(f"  [MISSING METHOD] Method '{handler}' is not defined in the class")
        if reg_exists and handler_exists:
            print(f"  [OK] '{comp}' -> '{handler}' is correctly wired.")
