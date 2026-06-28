import os

def update_file(filepath):
    if not os.path.exists(filepath):
        print(f"File not found: {filepath}")
        return

    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()

    # Replaces in SetupPresupuestoDB.java and SetupDashboardDB.java
    # We target the IF IS NULL SET assignments specifically
    old_start = "@fechaInicio = '2026-06-01'"
    new_start = "@fechaInicio = CONVERT(VARCHAR(10), DATEFROMPARTS(YEAR(GETDATE()), MONTH(GETDATE()), 1), 120)"
    
    old_end = "@fechaFin = '2026-06-30'"
    new_end = "@fechaFin = CONVERT(VARCHAR(10), EOMONTH(GETDATE()), 120)"

    updated = content.replace(old_start, new_start).replace(old_end, new_end)

    if updated != content:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(updated)
        print(f"Successfully updated dates in {filepath}")
    else:
        print(f"No dates needed update in {filepath}")

# Update files
update_file("scratch/SetupPresupuestoDB.java")
update_file("scratch/SetupDashboardDB.java")
